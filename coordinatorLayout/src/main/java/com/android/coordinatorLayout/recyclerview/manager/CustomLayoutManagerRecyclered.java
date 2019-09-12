package com.android.coordinatorLayout.recyclerview.manager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
//https://blog.csdn.net/harvic880925/article/details/84866486
public class CustomLayoutManagerRecyclered extends LayoutManager {
    private int mSumDy = 0;
    private int mTotalHeight = 0;


//    int getItemCount()
//    得到的是Adapter中总共有多少数据要显示，也就是总共有多少个item
//
//    int getChildCount()
//    得到的是当前RecyclerView在显示的item的个数，所以这就是getChildCount()与 getItemCount()的区别
//
//    View getChildAt(int position)
//    获取某个可见位置的View，需要非常注意的是，它的位置索引并不是Adapter中的位置索引，而是当前在屏幕上的位置的索引。也就是说，要获取当前屏幕上在显示的第一个item的View,应该用getChidAt(0)，同样，如果要得到当前屏幕上在显示的最后一个item的View，应该用getChildAt(getChildCount()-1)
//
//    int getPosition(View view)
//    这个函数用于得到某个View在Adapter中的索引位置，我们经常将它与getChildAt(int position)联合使用，得到某个当前屏幕上在显示的View在Adapter中的位置，比如我们要拿到屏幕上在显示的最后一个View在Adapter中的索引：

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
        Log.e("TAG", "CustomLayoutManagerRecyclered onLayoutChildren:");
        if (getItemCount() == 0) {//没有Item，界面空着吧
            //detachAndScrapAttachedViews(recycler);的作用就是把当前屏幕上所有的HolderView与屏幕分离，将它们从RecyclerView的布局中拿下来，
            // 然后存放在一个列表中，在重新布局时，像搭积木一样，把这些HolderView重新一个个放在新位置上去。
            // 将屏幕上的HolderView从RecyclerView的布局中拿下来后，存放的列表叫mAttachedScrap，它依然是一个List，
            // 就是用来保存从RecyclerView的布局中拿下来的HolderView列表
            //detachAndScrapAttachedViews(recycler);只会被用在onLayoutChildren函数中。
            // 就是因为onLayoutChildren函数是用来布局新的Item的，只有在布局时，才会先把HolderView detach掉然后再add进来重新布局
            //但大家需要注意的是mAttachedScrap中存储的就是新布局前从RecyclerView中剥离下来的当前在显示的Item的holderView。
            // 这些holderView并不参与回收复用。单纯只是为了先从RecyclerView中拿下来，再重新布局上去。对于新布局中没有用到的HolderView，会从mAttachedScrap移到mCachedViews中，让它参与复用。
            detachAndScrapAttachedViews(recycler);
            return;
        }
        detachAndScrapAttachedViews(recycler);

        //将item的位置存储起来
        View childView = recycler.getViewForPosition(0);
        measureChildWithMargins(childView, 0, 0);
        mItemWidth = getDecoratedMeasuredWidth(childView);
        mItemHeight = getDecoratedMeasuredHeight(childView);

        int visibleCount = getVerticalSpace() / mItemHeight;
        Log.e("TAG", "CustomLayoutManagerRecyclered onLayoutChildren:");

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
        Log.w("TAG", "CustomLayoutManagerRecyclered scrollVerticallyBy ------------:");
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

        //回收越界子View
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            //向上滑（手指向上滑动就是向上滑）
            if (travel > 0) {//需要回收当前屏幕，上越界的View
                if (getDecoratedBottom(child) - travel < 0) {
                    // 只需要告诉RecyclerView哪些HolderView已经不用了即可（使用removeAndRecycleView(view, recycler)函数）。然后RecyclerView中用两级缓存（mCachedViews和mRecyclerPool）
                    // 来保存这些已经被废弃(Removed)的HolderView。这两个缓存的区别是：mCachedViews是第一级缓存，它的size为2，只能保存两个HolderView。这里保存的始终是最新鲜被移除的HolderView，
                    // 当mCachedViews满了以后，会利用先进先出原则，把老的HolderView存放在mRecyclerPool中。在mRecyclerPool中，它的默认size是5。这就是RecyclerView的回收原则。
                    Log.e("TAG", "CustomLayoutManagerRecyclered scrollVerticallyBy 向上滑动 回收上越界:");
                    removeAndRecycleView(child, recycler);
                    continue;
                }
            } else if (travel < 0) {//回收当前屏幕，下越界的View
                if (getDecoratedTop(child) - travel > getHeight() - getPaddingBottom()) {
                    Log.w("TAG", "CustomLayoutManagerRecyclered scrollVerticallyBy 向下滑动 回收下越界:");
                    //在滚动时，所有移除的View都是使用removeAndRecycleView(child, recycler),千万不要将它与detachAndScrapAttachedViews(recycler)搞混了。
                    // 在滚动时，已经超出边界的HolderView是需要被回收的，而不是被detach。detach的意思是暂时存放，立马使用。
                    // 很显然，我们这里在越界之后，立马使用的可能性不大，所以必须回收。如果立马使用，它会从mCachedViews中去取。
                    // 大家也可以简单的记忆，在onLayoutChildren函数中（布局时），就使用detachAndScrapAttachedViews(recycler)，
                    // 在scrollVerticallyBy函数中（滚动时），就使用removeAndRecycleView(child, recycler)，当然能理解就更好啦
                    removeAndRecycleView(child, recycler);
                    continue;
                }
            }
        }

        Rect visibleRect = getVisibleArea(travel);
    //布局子View阶段
        if (travel >= 0) {
            //获取某个可见位置的View，需要非常注意的是，它的位置索引并不是Adapter中的位置索引，而是当前在屏幕上的位置的索引。也就是说，要获取当前屏幕上在显示的第一个item的View,应该用getChidAt(0)，
            //同样，如果要得到当前屏幕上在显示的最后一个item的View，应该用getChildAt(getChildCount()-1)
            //首先找到当前屏幕上在显示的最后一个item的View
            View lastView = getChildAt(getChildCount() - 1);

            // 这个函数用于得到某个View在Adapter中的索引位置，我们经常将它与getChildAt(int position)联合使用，得到某个当前屏幕上在显示的View在Adapter中的位置，
            //比如我们要拿到屏幕上在显示的最后一个View在Adapter中的索引：View lastView = getChildAt(getChildCount() - 1);int pos = getPosition(lastView);
            int minPos = getPosition(lastView) + 1;//从最后一个View+1开始吧

            //顺序addChildView
            for (int i = minPos; i <= getItemCount() - 1; i++) {
                Rect rect = mItemRects.get(i);
                if (Rect.intersects(visibleRect, rect)) {
                    Log.e("TAG", "CustomLayoutManagerRecyclered scrollVerticallyBy 向上滑动 添加view:");
                    //我们一般是使用recycler.getViewForPosition(int position),它的意思就是给指定位置获取一个HolderView实例。
                    // recycler.getViewForPosition(int position)获取过程就比较有意思，它会先在mAttachedScrap中找，看要的View是不是刚刚剥离的，如果是就直接返回使用，
                    // 如果不是，先在mCachedViews中查找，因为在mCachedViews中精确匹配，如果匹配到，就说明这个HolderView是刚刚被移除的，也直接返回，
                    // 如果匹配不到就会最终到mRecyclerPool找，如果mRecyclerPool有现成的holderView实例，这时候就不再是精确匹配了，只要有现成的holderView实例就返回给我们使用，只有在mRecyclerPool为空时，才会调用onCreateViewHolder新建
                    View child = recycler.getViewForPosition(i);
                    addView(child);
                    measureChildWithMargins(child, 0, 0);
                    layoutDecorated(child, rect.left, rect.top - mSumDy, rect.right, rect.bottom - mSumDy);
                } else {
                    break;
                }
            }
        } else {
            View firstView = getChildAt(0);
            int maxPos = getPosition(firstView) - 1;

            for (int i = maxPos; i >= 0; i--) {
                Rect rect = mItemRects.get(i);
                if (Rect.intersects(visibleRect, rect)) {
                    Log.w("TAG", "CustomLayoutManagerRecyclered scrollVerticallyBy 向下滑动 添加view:");
                    View child = recycler.getViewForPosition(i);
                    addView(child, 0);//将View添加至RecyclerView中，childIndex为1，但是View的位置还是由layout的位置决定
                    //measureCh需要注意的是，我们的item的位置rect是包含有滚动距离的，而在layout到屏幕上时，屏幕坐标是从(0,0)开始的，
                    // 所以我们需要把高度减去移动距离。需要注意的是，这个移动距离是不包含最新的移动距离travel的，
                    // 虽然我们在判断哪些item是新增的显示的，是假设已经移动了travel，但这只是识别哪些item将要显示出来的策略，
                    // 到目前为止，所有的item并未真正的移动，(或者说屏幕上的item是整体移动的，即调用offsetChildrenVertical(-travel);)所以我们在布局时，仍然需要按上次的移动距离来进行布局，
                    // 所以这里在布局时使用是layoutDecorated(child, rect.left, rect.top - mSumDy, rect.right, rect.bottom - mSumDy),
                    // 单纯只是减去了mSumDy,并没有同时减去mSumDy和travel，最后才调用offsetChildrenVertical(-travel)来整体移动布局好的item。这时才会把我们刚才新增布局上的item显示出来
                    layoutDecoratedWithMargins(child, rect.left, rect.top - mSumDy, rect.right, rect.bottom - mSumDy);
                } else {
                    break;
                }
            }
        }

        mSumDy += travel;
        Log.d("TAG", "CustomLayoutManagerRecyclered scrollVerticallyBy mSumDy:"+mSumDy);
        // 平移容器内的item
        offsetChildrenVertical(-travel);
        return travel;
    }


    /**
     * 获取可见的区域Rect
     *
     * @return
     */
    private Rect getVisibleArea(int dy) {
        Rect result = new Rect(getPaddingLeft(), getPaddingTop() + mSumDy + dy, getWidth() + getPaddingRight(), getVerticalSpace() + mSumDy + dy);
        return result;
    }
}
