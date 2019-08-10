package com.android.coordinatorLayout.recyclerview.manager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * createBy	 keepon
 */
public class CustomLayoutManager extends RecyclerView.LayoutManager {
    public CustomLayoutManager() {
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

//    所有Item的布局都是在onLayoutChildren()函数中处理的，所以我们在CustomLayoutItem中添加onLayoutChildren()函数
    private int mTotalHeight = 0;
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //定义竖直方向的偏移量
        int offsetY = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);
//            getDecoratedMeasuredWidth(view)得到的是item+decoration的总宽度
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
//            layoutDecorated();函数将每个item摆放在对应的位置，每个Item的左右位置都是相同的，从左侧x=0开始摆放，只是y的点需要计算
            layoutDecorated(view, 0, offsetY, width, offsetY + height);
            offsetY += height;

        }
//        这里只所以取最offsetY和getVerticalSpace()的最大值是因为，offsetY是所有item的总高度，
//        而当item填不满RecyclerView时，offsetY应该是比RecyclerView的真正高度小的，而此时的真正的高度应该是RecyclerView本身所设置的高度。
        mTotalHeight = Math.max(offsetY, getVerticalSpace());
        Log.e("TAG", "CustomLayoutManager onLayoutChildren mTotalHeight:"+mTotalHeight);
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }

//    首先，我们通过在canScrollVertically()中return true；使LayoutManager具有垂直滚动的功能。
    @Override
    public boolean canScrollVertically() {
        return true;
    }

//    然后在scrollVerticallyBy中接收每次滚动的距离dy
    private int mSumDy = 0;
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int travel = dy;
        //如果滑动到最顶部
        if (mSumDy + dy < 0) {
            travel = -mSumDy;
        }else if (mSumDy + dy > mTotalHeight - getVerticalSpace()) {
            travel = mTotalHeight - getVerticalSpace() - mSumDy;
        }

        mSumDy += travel;
        // 平移容器内的item
        offsetChildrenVertical(-travel);
        return dy;
    }


}
