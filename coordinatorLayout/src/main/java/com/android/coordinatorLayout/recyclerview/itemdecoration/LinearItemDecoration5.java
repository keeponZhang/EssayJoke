package com.android.coordinatorLayout.recyclerview.itemdecoration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LinearItemDecoration5 extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private Bitmap mMedalBmp;

    public LinearItemDecoration5(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        BitmapFactory.Options options = new BitmapFactory.Options();
//        mMedalBmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.xunzhang);
    }



//    onDraw和onDrawOver所整个ItemDecoration只执行一次的
//    ItemDecoration与Item的绘制顺序为：decoration 的 onDraw->item的 onDraw->decoration 的 onDrawOver，这三者是依次发生的。
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //画勋章
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);
            int left = manager.getLeftDecorationWidth(child);
            if (index % 5 == 0) {
                c.drawBitmap(mMedalBmp, left - mMedalBmp.getWidth() / 2, child.getTop(), mPaint);
            }
        }


        View temp = parent.getChildAt(0);
        LinearGradient gradient = new LinearGradient(parent.getWidth() / 2, 0, parent.getWidth() / 2, temp.getHeight() * 3,
                0xff0000ff, 0x000000ff, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
        c.drawRect(0, 0, parent.getWidth(), temp.getHeight() * 3, mPaint);
    }

//    getItemOffsets的主要作用就是给item的四周加上边距，实现的效果类似于margin，将item的四周撑开一些距离，在撑开这些距离后，我们就可以利用上面的onDraw函数，在这个距离上进行绘图了
//    这个是最难理解的部分，outRect就是表示在item的上下左右所撑开的距离，后面详细讲解。
//    getItemOffsets是针对每个Item都会走一次
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=200;
        outRect.bottom=1;
    }
}
