package android.support.design.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by hcDarren on 2017/7/10.
 */

public class TanslationBehavior extends FloatingActionButton.Behavior{
    public TanslationBehavior(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    // 关注垂直滚动 ，而且向上的时候是出来，向下是隐藏
    /**
     *  当coordinatorLayout 的子View试图开始嵌套滑动的时候被调用。当返回值为true的时候表明
     *  coordinatorLayout 充当nested scroll parent 处理这次滑动，需要注意的是只有当返回值为true
     *  的时候，Behavior 才能收到后面的一些nested scroll 事件回调（如：onNestedPreScroll、onNestedScroll等）
     *  这个方法有个重要的参数nestedScrollAxes，表明处理的滑动的方向。
     *
     * @param coordinatorLayout 和Behavior 绑定的View的父CoordinatorLayout
     * @param child  和Behavior 绑定的View
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes 嵌套滑动 应用的滑动方向，看 {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
     *                         {@link ViewCompat#SCROLL_AXIS_VERTICAL}
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        boolean b = nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
        Log.e("TAG", "TanslationBehavior onStartNestedScroll return:"+ b);
        return  b;
    }
    private boolean isOut = false;

    /**
     * 嵌套滚动发生之前被调用
     * 在nested scroll child 消费掉自己的滚动距离之前，嵌套滚动每次被nested scroll child
     * 更新都会调用onNestedPreScroll。注意有个重要的参数consumed，可以修改这个数组表示你消费
     * 了多少距离。假设用户滑动了100px,child 做了90px 的位移，你需要把 consumed［1］的值改成90，
     * 这样coordinatorLayout就能知道只处理剩下的10px的滚动。
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx  用户水平方向的滚动距离
     * @param dy  用户竖直方向的滚动距离
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        Log.d("TAG", "TanslationBehavior onNestedPreScroll dy:" +dy+"   consumed[1]="+ consumed[1]);
//        consumed[1] =dy;
        //表示已经处理了多少（这里如果不注释的话，recyclerview手指move的时候不会动，up的时候才会动（轻放的时候不会动，带点fling的时候会动））
        consumed[1] =dy ;
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    /**
     * 进行嵌套滚动时被调用
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed target 已经消费的x方向的距离
     * @param dyConsumed target 已经消费的y方向的距离
     * @param dxUnconsumed x 方向剩下的滚动距离
     * @param dyUnconsumed y 方向剩下的滚动距离
     */

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.w("TAG","TanslationBehavior onNestedScroll dyConsumed -> "+dyConsumed+" dyUnconsumed -> "+dyUnconsumed);
        // 而且向上的时候是出来，向下是隐藏
        if(dyConsumed > 0){
            if(!isOut) {
                // 往上滑动，是隐藏 , 加一个标志位 已经往下走了
                int translationY = ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).bottomMargin + child.getMeasuredHeight();
                child.animate().translationY(translationY).setDuration(500).start();
                isOut = true;
            }
        }else{
            if(dyConsumed<0){
                if(isOut) {
                    // 往下滑动
                    child.animate().translationY(0).setDuration(500).start();
                    isOut = false;
                }
            }

        }
    }
    /**
     *  嵌套滚动结束时被调用，这是一个清除滚动状态等的好时机。
     * @param coordinatorLayout
     * @param child
     * @param target
     */

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        Log.e("TAG", "TanslationBehavior onStopNestedScroll:" );
    }

    /**
     * onStartNestedScroll返回true才会触发这个方法，接受滚动处理后回调，可以在这个
     * 方法里做一些准备工作，如一些状态的重置等。
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param axes
     */
    @Override
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes) {
        Log.d("TAG", "TanslationBehavior onNestedScrollAccepted:" );
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes);
    }

    /**
     * 用户松开手指并且会发生惯性动作之前调用，参数提供了速度信息，可以根据这些速度信息
     * 决定最终状态，比如滚动Header，是让Header处于展开状态还是折叠状态。返回true 表
     * 示消费了fling.
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX x 方向的速度
     * @param velocityY y 方向的速度
     * @return
     */
    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, float velocityX, float velocityY) {
        Log.e("TAG", "TanslationBehavior onNestedPreFling:" );
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    /**
     * 摆放子 View 的时候调用，可以重写这个方法对子View 进行重新布局
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, FloatingActionButton child, int layoutDirection) {
//        Log.w("TAG", "TanslationBehavior onLayoutChild:" );
            return super.onLayoutChild(parent, child, layoutDirection);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        boolean b = super.layoutDependsOn(parent, child, dependency);
//        Log.w("TAG", "TanslationBehavior layoutDependsOn return:"+b );
        return b;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
//        Log.d("TAG", "TanslationBehavior onDependentViewChanged:" );
        return super.onDependentViewChanged(parent, child, dependency);

    }
}
