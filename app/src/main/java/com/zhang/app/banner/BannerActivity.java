package com.zhang.app.banner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hc.baselibrary.ioc.ViewById;
import com.hc.baselibrary.ioc.ViewUtils;
import com.hc.essay.joke.http.HttpCallBack;
import com.hc.essay.joke.http.HttpUtils;
import com.zhang.app.R;
import com.zhang.banner.BannerAdapter;
import com.zhang.banner.BannerView;
import com.zhang.banner.BannerViewPager;
import com.zhang.recyclerview.view.WrapRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity implements BannerViewPager.BannerItemClickListener {
	@ViewById(R.id.recycler_view)
	private WrapRecyclerView mRecyclerView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banner);
		ViewUtils.inject(this);
//		initData();
		showListData(null);
		setBanner();
	}

	private void setBanner() {
		bannerList.clear();
		for (String banner : banners) {
			bannerList.add(banner);

		}
		BannerView bannerView = (BannerView) LayoutInflater.from(this)
				.inflate(R.layout.layout_banner_view, mRecyclerView, false);

		// 自己把万能的无限轮播看一下
		bannerView.setAdapter(new BannerAdapter() {
			@Override
			public View getView(int position, View convertView) {
				if (convertView == null) {
					convertView = new ImageView(BannerActivity.this);
				}
				((ImageView) convertView).setScaleType(ImageView.ScaleType.CENTER_CROP);

				Glide.with(BannerActivity.this).load(bannerList.get(position)).into((ImageView) convertView);
				return convertView;
			}

			@Override
			public int getCount() {
				return bannerList.size();
			}

			@Override
			public String getBannerDesc(int position) {
				return "desc"+position;
			}
		});

		bannerView.setOnBannerItemClickListener(BannerActivity.this);
		// 开启滚动
		bannerView.startRoll();

		mRecyclerView.addHeaderView(bannerView);
	}

	public ArrayList<String> bannerList = new ArrayList<>();
	public String[]  banners = new String[] {
			"http://ep.dzb.ciwong.com/rep/3159ba11c79d016ff8b9ea82a84ed962.jpg",
			"http://ep.dzb.ciwong.com/rep/new/4055.jpg",
			"http://ep.dzb.ciwong.com/rep/image/3173.jpg",
			"http://ep.dzb.ciwong.com/rep/image/3170.jpg"
	};

	protected void initData() {
		HttpUtils.with(this).url("http://is.snssdk.com/2/essay/discovery/v3/?")
				.addParam("iid", 6152551759L)
				.addParam("aid", 7)
				.execute(new HttpCallBack<DiscoverListResult>() {
					@Override
					public void onError(Exception e) {

					}

					@Override
					public void onSuccess(DiscoverListResult result) {

						// 先显示列表
						showListData(result.getData().getCategories().getCategory_list());
						addBannerView(result.getData().getRotate_banner().getBanners());
					}
				});
	}

	/**
	 * 初始化Banner
	 * @param banners
	 */
	private void addBannerView(final List<DiscoverListResult.DataBean.RotateBannerBean.BannersBean> banners) {
		Log.e("TAG", "banners --> " + banners.size());

		// 后台没有轮播那就不添加
		if(banners.size()<=0){
			return;
		}

		BannerView bannerView = (BannerView) LayoutInflater.from(this)
				.inflate(R.layout.layout_banner_view, mRecyclerView, false);

		// 自己把万能的无限轮播看一下
		bannerView.setAdapter(new BannerAdapter() {
			@Override
			public View getView(int position, View convertView) {
				if (convertView == null) {
					convertView = new ImageView(BannerActivity.this);
				}
				((ImageView) convertView).setScaleType(ImageView.ScaleType.CENTER_CROP);

				Glide.with(BannerActivity.this).load(banners.get(position).getBanner_url().getUrl_list()
						.get(0).getUrl()).into((ImageView) convertView);
				return convertView;
			}

			@Override
			public int getCount() {
				return banners.size();
			}

			@Override
			public String getBannerDesc(int position) {
				return banners.get(position).getBanner_url().getTitle();
			}
		});

		bannerView.setOnBannerItemClickListener(BannerActivity.this);
		// 开启滚动
		bannerView.startRoll();

		mRecyclerView.addHeaderView(bannerView);
	}

	/**
	 * 显示列表
	 *
	 * @param list
	 */
	private void showListData(List<DiscoverListResult.DataBean.CategoriesBean.CategoryListBean> list) {
		final DiscoverListAdapter listAdapter = new DiscoverListAdapter(this, list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(listAdapter);
	}




	@Override
	public void click(int position) {
		// 轮播点击
		Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
	}
}
