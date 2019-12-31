package com.zhang.app.indicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.administrator.framelibrary.ItemFragment;
import com.hc.baselibrary.ioc.ViewById;
import com.hc.baselibrary.ioc.ViewUtils;
import com.zhang.app.R;
import com.zhang.indicator.ColorTrackTextView;
import com.zhang.indicator.IndicatorAdapter;
import com.zhang.indicator.TrackIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerActivity extends AppCompatActivity {
	@ViewById(R.id.view_pager)
	private ViewPager                mViewPager;
	private String                   TAG = "ViewPagerActivity";
	private ArrayList<View> viewList;
	private View view1;
	private View view2;
	private View view3;
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		// 加入注解
		ViewUtils.inject(this);
		initData();
		initViewPager();
	}
	protected void initData() {
		initViewPager();
	}
	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		LayoutInflater inflater=getLayoutInflater();
		view1 = inflater.inflate(R.layout.layout1, null);
		view2 = inflater.inflate(R.layout.layout2,null);
		view3 = inflater.inflate(R.layout.layout3, null);

		viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);


		mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return viewList.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
									Object object) {
				container.removeView(viewList.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(viewList.get(position));
				return viewList.get(position);
			}
		};




	}

	public void setData(View view) {
		mViewPager.setAdapter(mPagerAdapter);
	}
}
