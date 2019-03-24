package com.hc.essay.joke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ioc.CheckNet;
import ioc.OnClick;
import ioc.ViewById;
import ioc.ViewUtils;


public class MyIOCActivity extends AppCompatActivity {

	@ViewById(R.id.test_tv)
	private TextView mTestTv;

	private int mPage = 0;
	@ViewById(R.id.test_iv)
	private ImageView mTestIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		mTestTv.setText("IOCTV");
	}

	@OnClick(R.id.test_tv)
	@CheckNet               // 没网就不执行该方法   而是直接打印没网的Toast
	private void login(TextView testTv) {
		int i = 2 / 1;// 老板  用户手上不会闪退   有利于这个体验  调试测试比较麻烦，需要去看打印w  xUtils
		Toast.makeText(this, "dayin:" + i, Toast.LENGTH_LONG).show();
	}

	@OnClick(R.id.test_iv)
	private void testIvClick(ImageView testIv) {
		int i = 2 / 2;// 老板  用户手上不会闪退   有利于这个体验  调试测试比较麻烦，需要去看打印w  xUtils
		Toast.makeText(this, "dayin:" + i, Toast.LENGTH_LONG).show();
	}
}

