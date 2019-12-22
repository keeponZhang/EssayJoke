package com.android.coordinatorLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CoordinatorLayoutDepencyActivity extends AppCompatActivity {
//	CoordinatorLayou中最经典的设计就是Behavior，在前面我们提到了app：
//	layout_behavior="@string/appbar_scrolling_view_behavior"，其实@string/appbar_scrolling_view_behavior对应
//	着的是AppBarLayout.ScrollingViewBehavior。我们也可以自定义Behavior来实现自己的组件和滑动交互。自
//	定义Behavior可以分为两种方法：第一种是定义的View监听CoordinatorLayout里的滑动状态；第二种是定义
//	的View监听另一个View的状态变化，例如View的大小、位置和显示状态等。对于第一种方法，我们需要注
//	意onStartNestedScroll（）和onNestedPreScroll方法；而对于第二种方法，则需要注意layoutDependsOn（）和
//	onDependentViewChanged（）方法。
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coordinator_layout);
	}
}
