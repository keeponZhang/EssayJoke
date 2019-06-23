package com.android.coordinatorLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CoordinatorLayoutAppBarLayoutExituntilCollapsedActivity extends AppCompatActivity {
//	AppBarLayout继承自LinearLayout，布局方向为垂直方向。所以你可以把它当成垂直布局的LinearLayout来使用。AppBarLayout是在LinearLayou上加了一些材料设计的概念，它可以让你定制当某个可滚动View的滚动手势发生变化时，其内部的子View实现何种动作
//	作者：huachao1001
//	链接：https://www.jianshu.com/p/d159f0176576
//	请注意：上面提到的某个可滚动View，可以理解为某个ScrollView。怎么理解上面的话呢？就是说，当某个ScrollView发生滚动时，你可以定制你的“顶部栏”应该执行哪些动作（如跟着一起滚动、保持不动等等）。
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coordinator_layout_appbarlayout_exituntil_collapsed);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("这里是Title");
		toolbar.setSubtitle("这里是子标题");
		toolbar.setLogo(R.drawable.icon);
		setSupportActionBar(toolbar);
	}
}
