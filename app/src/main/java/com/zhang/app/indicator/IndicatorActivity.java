package com.android.app.indicator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.administrator.framelibrary.ItemFragment;
import com.hc.baselibrary.ioc.ViewById;
import com.hc.baselibrary.ioc.ViewUtils;
import com.android.app.R;
import com.android.indicator.ColorTrackTextView;
import com.android.indicator.IndicatorAdapter;
import com.android.indicator.TrackIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class IndicatorActivity extends AppCompatActivity {
	private String[]                 items = {"直播", "推荐", "视频", "图片", "段子", "精华","同城","游戏"};
	@ViewById(R.id.indicator_view)
	private TrackIndicatorView       mIndicatorContainer;
	private List<ColorTrackTextView> mIndicators;
	@ViewById(R.id.view_pager)
	private ViewPager                mViewPager;
	private String                   TAG = "ViewPagerActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indicator);
		// 加入注解
		ViewUtils.inject(this);
		initData();
		initViewPager();
	}
	protected void initData() {
		mIndicators = new ArrayList<>();
		initIndicator();
		initViewPager();
	}
	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public Fragment getItem(int position) {
				return ItemFragment.newInstance(items[position]);
			}

			@Override
			public int getCount() {
				return items.length;
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {

			}
		});

		/**
		 * 添加一个切换的监听那个setOnPageChangeListener过时了
		 * 这个看源码去吧
		 */
		mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				Log.e(TAG, "position --> " + position + " positionOffset --> " + positionOffset);
				if (positionOffset > 0) {
					// 获取左边
					ColorTrackTextView left = mIndicators.get(position);
					// 设置朝向
					left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
					// 设置进度  positionOffset 是从 0 一直变化到 1 不信可以看打印
					left.setCurrentProgress(1 - positionOffset);

					// 获取右边
					ColorTrackTextView right = mIndicators.get(position + 1);
					right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
					right.setCurrentProgress(positionOffset);
				}
			}
		});
	}

	/**
	 * 初始化可变色的指示器
	 */
	private void initIndicator() {

		mIndicatorContainer.setAdapter(new IndicatorAdapter<ColorTrackTextView>() {
			@Override
			public int getCount() {
				return items.length;
			}

			@Override
			public ColorTrackTextView getView(int position, ViewGroup parent) {
				ColorTrackTextView colorTrackTextView = new ColorTrackTextView(IndicatorActivity.this);
				// 设置颜色
				colorTrackTextView.setTextSize(20);
				colorTrackTextView.setChangeColor(Color.RED);
				colorTrackTextView.setText(items[position]);
				// 加入集合
				mIndicators.add(colorTrackTextView);
				return colorTrackTextView;
			}

			@Override
			public void highLightIndicator(ColorTrackTextView view) {
				// 当前选中的View
				view.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
				view.setCurrentProgress(1);
			}

			@Override
			public void restoreIndicator(ColorTrackTextView view) {
				// 上一个View
				view.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
				view.setCurrentProgress(0);
			}

			@Override
			public View getBottomTrackView() {
				View view = new View(IndicatorActivity.this);
				view.setBackgroundColor(Color.parseColor("#ff0000"));
				ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(80,3);
				view.setLayoutParams(layoutParams);
				return view;
			}
		},mViewPager,false);
	}
}
