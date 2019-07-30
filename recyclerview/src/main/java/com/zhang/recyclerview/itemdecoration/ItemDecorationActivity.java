package com.zhang.recyclerview.itemdecoration;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hc.essay.joke.http.HttpCallBack;
import com.hc.essay.joke.http.HttpUtils;
import com.android.recyclerview.R;
import com.android.recyclerview.itemdecoration.bean.FollowerBean;
import com.android.recyclerview.itemdecoration.bean.FollowerInfos;
import com.android.recyclerview.itemhelper.DragCallBack;

import java.lang.reflect.Type;
import java.util.List;

public class ItemDecorationActivity extends AppCompatActivity {
	private RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_decoration);
		mRecyclerView = findViewById(R.id.recyclerview);


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
						updateData(followerBeans);
					}
				});
			}
		});
	}

	private void updateData(List<FollowerBean> followerBeans ) {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.addItemDecoration(new CategoryItemDecoration(getResources().getColor(R.color.blue)));
		CategoryRecyclerviewAdapter categoryListAdapter = new CategoryRecyclerviewAdapter(this, followerBeans);
		mRecyclerView.setAdapter(categoryListAdapter);
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new DragCallBack(mRecyclerView));
		itemTouchHelper.attachToRecyclerView(mRecyclerView);
	}
}
