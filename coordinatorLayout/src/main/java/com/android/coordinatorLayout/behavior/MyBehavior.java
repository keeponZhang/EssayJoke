package com.android.coordinatorLayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.coordinatorLayout.CustomView;
import com.android.coordinatorLayout.TempView;

/**
 * Package com.hc.studycoordinatelayout
 * Created by HuaChao on 2016/6/1.
 */
//泛型为child,behavior也作用于child上,其实Child是指要执行动作的CoordinatorLayout的子View,在这个例子，Dependency是TempView，child随着Dependency的动而动
public class MyBehavior extends CoordinatorLayout.Behavior<Button> {
    private int width;

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
    }

    //这个例子中，dependency既可能是CustomView ,也可能是TempView
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        Log.e("TAG", "MyBehavior layoutDependsOn dependency:" +dependency);
        //如果dependency是TempView的实例，说明它就是我们所需要的Dependency
        return dependency instanceof TempView ||dependency instanceof CustomView;
    }
    int count=0;
    //每次dependency位置发生变化，都会执行onDependentViewChanged方法
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button btn, View dependency) {
        if(count<=0){
            count++;
            return true;
        }
        //根据dependency的位置，设置Button的位置

        int top = dependency.getTop();
        int left = dependency.getLeft();

        int x = width - left - btn.getWidth();
        int y = top;
        Log.d("TAG", "MyBehavior onDependentViewChanged top:"+top+" left="+left );
        setPosition(btn, x, y);
        return true;
    }

    private void setPosition(View v, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        v.setLayoutParams(layoutParams);
    }


}
