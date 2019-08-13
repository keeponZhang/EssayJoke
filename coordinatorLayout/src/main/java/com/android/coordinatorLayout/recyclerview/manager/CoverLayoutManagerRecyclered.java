package com.android.coordinatorLayout.recyclerview.manager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

//https://blog.csdn.net/harvic880925/article/details/84866486
public class CoverLayoutManagerRecyclered extends LayoutManager {
    private int mSumDx = 0;
    private int mTotalWidth = 0;
    private int mStartX;

    @Override
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }

    private int mItemWidth, mItemHeight;
    private SparseArray<Rect> mItemRects = new SparseArray<>();
//    在RecyclerView中，总共有四个池子：mAttachedScrap、mCachedViews、mViewCacheExtension、mRecyclerPool；
//    其中：
//    mAttachedScrap不参与回收复用，只保存从在重新布局时，从RecyclerView中剥离的当前在显示的HolderView列表。
//    所以，mCachedViews、mViewCacheExtension、mRecyclerPool组成了回收复用的三级缓存，当RecyclerView要拿一个复用的HolderView时，获取优先级是mCachedViews > mViewCacheExtension > mRecyclerPool。由于一般而言我们是不会自定义mViewCacheExtension的。
//    所以获取顺序其实就是mCachedViews > mRecyclerPool，在下面的讲述中，我也将不再牵涉mViewCacheExtension，大家这里知道即可。
//    其实，mCachedViews是不参与回收复用的，它的作用就是保存最新被移除的HolderView（通过removeAndRecycleView(view, recycler)方法），它的作用是在需要新的HolderView时，精确匹配是不是刚移除的那个，如果是，就直接返回给RecyclerView展示，如果不是它，那么即使这里有HolderView实例，也不会返回给RecyclerView，而是到mRecyclerPool中去找一个HolderView实例，返回给RecyclerView，让它重新绑定数据使用。
//    所以，在mAttachedScrap、mCachedViews中的holderView都是精确匹配的，真正被标识为废弃的是存放在mRecyclerPool中的holderView，当我们向RecyclerView申请一个HolderView来使用的时，如果在mAttachedScrap、mCachedViews精确匹配不到，即使他们中有HolderView也不会返回给我们使用，而是会到mRecyclerPool中去拿一个废弃的HolderView返回给我们。

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {//没有Item，界面空着吧
            detachAndScrapAttachedViews(recycler);
            return;
        }
        mHasAttachedItems.clear();
        mItemRects.clear();

        detachAndScrapAttachedViews(recycler);

        //将item的位置存储起来
        View childView = recycler.getViewForPosition(0);
        measureChildWithMargins(childView, 0, 0);
        mItemWidth = getDecoratedMeasuredWidth(childView);
        mItemHeight = getDecoratedMeasuredHeight(childView);

        //定义水平方向的偏移量
        mIntervalWidth = getIntervalWidth();
//        int visibleCount = getHorizontalSpace() / mItemWidth + 1;
        int visibleCount = getHorizontalSpace() / mIntervalWidth;


        mStartX = getWidth()/2 - mIntervalWidth;
        //定义水平方向的偏移量
        int offsetX = 0;

        for (int i = 0; i < getItemCount(); i++) {
            Rect rect = new Rect(mStartX + offsetX, 0, mStartX + offsetX + mItemWidth, mItemHeight);
            mItemRects.put(i, rect);
            mHasAttachedItems.put(i, false);
            offsetX += mIntervalWidth;
        }


        Rect visibleRect = getVisibleArea();
        for (int i = 0; i < visibleCount; i++) {
            insertView(i, visibleRect, recycler, false);
        }

        //如果所有子View的宽度和没有填满RecyclerView的宽度，
        // 则将宽度设置为RecyclerView的宽度
        mTotalWidth = Math.max(offsetX, getHorizontalSpace());
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }


    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() <= 0) {
            return dx;
        }

        int travel = dx;
        //如果滑动到最顶部
        if (mSumDx + dx < 0) {
            travel = -mSumDx;
        } else if (mSumDx + dx > mTotalWidth - getHorizontalSpace()) {
            //如果滑动到最底部
            travel = mTotalWidth - getHorizontalSpace() - mSumDx;
        }

        mSumDx += travel;

        Rect visibleRect = getVisibleArea();

        //回收越界子View
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            int position = getPosition(child);
            Rect rect = mItemRects.get(position);

            if (!Rect.intersects(rect, visibleRect)) {
                removeAndRecycleView(child, recycler);
                mHasAttachedItems.put(position, false);
            } else {
                layoutDecoratedWithMargins(child, rect.left - mSumDx, rect.top, rect.right - mSumDx, rect.bottom);
                mHasAttachedItems.put(position, true);
            }
        }

        //填充空白区域
        View lastView = getChildAt(getChildCount() - 1);
        View firstView = getChildAt(0);
        if (travel >= 0) {
            int minPos = getPosition(firstView);
            for (int i = minPos; i < getItemCount(); i++) {
                insertView(i, visibleRect, recycler, false);
            }
        } else {
            int maxPos = getPosition(lastView);
            for (int i = maxPos; i >= 0; i--) {
                insertView(i, visibleRect, recycler, true);
            }
        }
        return travel;

    }

    //    我们需要一个变量来保存在这里哪些item已经布局好了
    private SparseBooleanArray mHasAttachedItems = new SparseBooleanArray();

    private void insertView(int pos, Rect visibleRect, RecyclerView.Recycler recycler, boolean firstPos) {
        Rect rect = mItemRects.get(pos);
        if (Rect.intersects(visibleRect, rect) && !mHasAttachedItems.get(pos)) {
            View child = recycler.getViewForPosition(pos);
            if (firstPos) {
                addView(child, 0);
            } else {
                addView(child);
            }
            measureChildWithMargins(child, 0, 0);
            layoutDecoratedWithMargins(child, rect.left - mSumDx, rect.top, rect.right - mSumDx, rect.bottom);

            mHasAttachedItems.put(pos, true);
        }
    }

    /**
     * 获取可见的区域Rect
     *
     * @return
     */
    private Rect getVisibleArea() {
        Rect result = new Rect(getPaddingLeft() + mSumDx, getPaddingTop(), getWidth() - getPaddingRight() + mSumDx, getHeight() - getPaddingBottom());
        return result;
    }


    private int mIntervalWidth;

    private int getIntervalWidth() {
        return mItemWidth / 2;
    }


}
