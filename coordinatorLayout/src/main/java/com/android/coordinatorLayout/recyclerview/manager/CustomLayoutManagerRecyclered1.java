package com.android.coordinatorLayout.recyclerview.manager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.util.SparseArray;
import android.view.View;

//https://blog.csdn.net/harvic880925/article/details/84866486
public class CustomLayoutManagerRecyclered1 extends LayoutManager {
    private int mSumDy = 0;
    private int mTotalHeight = 0;

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
    //onLayoutChildren 在滑动的时候不会调用
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {//没有Item，界面空着吧
            detachAndScrapAttachedViews(recycler);
            return;
        }
        mItemRects.clear();

        detachAndScrapAttachedViews(recycler);

        //将item的位置存储起来
        View childView = recycler.getViewForPosition(0);
        measureChildWithMargins(childView, 0, 0);
        mItemWidth = getDecoratedMeasuredWidth(childView);
        mItemHeight = getDecoratedMeasuredHeight(childView);

        int visibleCount = getVerticalSpace() / mItemHeight;

        //定义竖直方向的偏移量
        int offsetY = 0;

        for (int i = 0; i < getItemCount(); i++) {
            Rect rect = new Rect(0, offsetY, mItemWidth, offsetY + mItemHeight);
            mItemRects.put(i, rect);
            offsetY += mItemHeight;
        }

        for (int i = 0; i < visibleCount; i++) {
            Rect rect = mItemRects.get(i);
            View view = recycler.getViewForPosition(i);
            addView(view);
            //addView后一定要measure，先measure再layout
            measureChildWithMargins(view, 0, 0);
            layoutDecorated(view, rect.left, rect.top, rect.right, rect.bottom);
        }

        //如果所有子View的高度和没有填满RecyclerView的高度，
        // 则将高度设置为RecyclerView的高度
        mTotalHeight = Math.max(offsetY, getVerticalSpace());
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() <= 0) {
            return dy;
        }

        int travel = dy;
        //如果滑动到最顶部
        if (mSumDy + dy < 0) {
            travel = -mSumDy;
        } else if (mSumDy + dy > mTotalHeight - getVerticalSpace()) {
            //如果滑动到最底部
            travel = mTotalHeight - getVerticalSpace() - mSumDy;
        }



        //回收越界子View(原来的到顶、到底判断和回收越界item的代码都不变)
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (travel > 0) {//需要回收当前屏幕，上越界的View
                if (getDecoratedBottom(child) - travel < 0) {
                    removeAndRecycleView(child, recycler);
                    continue;
                }
            } else if (travel < 0) {//回收当前屏幕，下越界的View
                if (getDecoratedTop(child) - travel > getHeight() - getPaddingBottom()) {
                    removeAndRecycleView(child, recycler);
                    continue;
                }
            }

        }
        //在回收越界的holderView之后，我们需要在使用detachAndScrapAttachedViews(recycler);将现在显示的所有item离屏缓存之前，
        // 先得到当前在显示的第一个item和最后一个item的索引，因为如果在将所有item从屏幕上离屏缓存以后，
        // 利用getChildAt(int position)是拿不到任何值的，会返回null，因为现在屏幕上已经没有View存在了。
        View lastView = getChildAt(getChildCount() - 1);
        View firstView = getChildAt(0);
        detachAndScrapAttachedViews(recycler);
        //CustomLayoutManagerRecyclered是到了最后才叠加
        mSumDy += travel;
        Rect visibleRect = getVisibleArea();

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
    private void insertView(int pos, Rect visibleRect, RecyclerView.Recycler recycler, boolean firstPos) {
        Rect rect = mItemRects.get(pos);
        if (Rect.intersects(visibleRect, rect)) {
            View child = recycler.getViewForPosition(pos);
            if (firstPos) {
                addView(child, 0);
            }else {
                addView(child);
            }
            measureChildWithMargins(child, 0, 0);
            layoutDecoratedWithMargins(child, rect.left, rect.top - mSumDy, rect.right, rect.bottom - mSumDy);

            //在布局item后，修改每个item的旋转度数
//            child.setRotationY(child.getRotationY()+1);
        }
    }

    /**
     * 获取可见的区域Rect
     *
     * @return
     */
    private Rect getVisibleArea() {
        Rect result = new Rect(getPaddingLeft(), getPaddingTop() + mSumDy, getWidth() + getPaddingRight(), getVerticalSpace() + mSumDy);
        return result;
    }

}
