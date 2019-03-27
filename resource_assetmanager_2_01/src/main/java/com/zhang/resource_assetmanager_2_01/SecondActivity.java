package com.zhang.resource_assetmanager_2_01;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;

/**
 * @创建者 keepon
 * @创建时间 2019/1/3 0003 下午 8:12
 * @描述 ${TODO}
 * @版本 $$Rev$$
 * @更新者 $$Author$$
 * @更新时间 $$Date$$
 */
public class SecondActivity extends Activity {

	private ViewStub mViewById;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			 setContentView(R.layout.activity_second);
		mViewById = (ViewStub) findViewById(R.id.view_stub);
	}
	public void more(View view){
		mViewById.inflate();
	}
}
