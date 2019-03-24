package com.hc.essay.joke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ButterknifeActivity extends AppCompatActivity {


	@Bind(R.id.bt_tv1)
	TextView mBtTv1;
	@Bind(R.id.bt_tv2)
	TextView mBtTv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_butterknife);
		ButterKnife.bind(this);
		mBtTv1.setText("keepon");
	}
}
