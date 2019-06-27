package com.android.coordinatorLayout.recyclerview.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

//    getItemOffsets的主要作用就是给item的四周加上边距，实现的效果类似于margin，将item的四周撑开一些距离，在撑开这些距离后，我们就可以利用上面的onDraw函数，在这个距离上进行绘图了
//    这个是最难理解的部分，outRect就是表示在item的上下左右所撑开的距离，后面详细讲解。
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=50;
        outRect.right=100;
        outRect.bottom=1;
    }
}
