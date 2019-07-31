package com.zhang.app.banner;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.recyclerview.itemdecoration.bean.FollowerBean;
import com.android.recyclerview.itemdecoration.bean.FollowerInfos;
import com.android.recyclerview.view.WrapRecyclerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hc.baselibrary.ioc.ViewById;
import com.hc.baselibrary.ioc.ViewUtils;
import com.hc.essay.joke.http.HttpCallBack;
import com.hc.essay.joke.http.HttpUtils;
import com.zhang.app.R;
import com.zhang.banner.BannerAdapter;
import com.zhang.banner.BannerView;
import com.zhang.banner.BannerViewPager;
import com.zhang.recyclerview.itemdecoration.CategoryItemDecoration;
import com.zhang.recyclerview.itemdecoration.CategoryListAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity implements BannerViewPager.BannerItemClickListener {
	@ViewById(R.id.recycler_view)
	private WrapRecyclerView mRecyclerView;
	private List<FollowerBean> mList = new ArrayList<>();
	private CategoryListAdapter mCategoryListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banner);
		ViewUtils.inject(this);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.addItemDecoration(new CategoryItemDecoration(getResources().getColor(R.color.blue)));
		mCategoryListAdapter = new CategoryListAdapter(this,mList);
		mRecyclerView.setAdapter(mCategoryListAdapter);
		setBanner();
	}

	String url = "https://api.github.com/users/momodiy/followers";
	public void getData(View view){
		HttpUtils.with(this).url(url).execute(new HttpCallBack<FollowerInfos>() {
			@Override
			public void onError(Exception e) {
				Log.e("TAG", "ItemDecorationActivity onError:" );
			}

			@Override
			public void onSuccess(FollowerInfos result) {
				Log.e("TAG", "ItemDecorationActivity onSuccess:" );
			}

			@Override
			public boolean isOriginalString() {
				return true;
			}

			@Override
			public void getOriginalString(String result) {
				Type listType = new TypeToken<List<FollowerBean>>() {}.getType();
				final List<FollowerBean> followerBeans = new Gson().fromJson(result, listType);
				Log.e("TAG", "ItemDecorationActivity getOriginalString:"+followerBeans );
				new Handler(Looper.getMainLooper()).post(new Runnable() {
					@Override
					public void run() {
						updateData2(followerBeans);
					}
				});
			}
		});
	}

	private void updateData2(List<FollowerBean> followerBeans ) {
		mList.addAll(followerBeans);
		mCategoryListAdapter.notifyDataSetChanged();

	}
	private void updateData(List<FollowerBean> followerBeans ) {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.addItemDecoration(new CategoryItemDecoration(getResources().getColor(R.color.blue)));
		CategoryListAdapter categoryListAdapter = new CategoryListAdapter(this, followerBeans);
		mRecyclerView.setAdapter(categoryListAdapter);
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







	@Override
	public void click(int position) {
		// 轮播点击
		Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
	}


}
