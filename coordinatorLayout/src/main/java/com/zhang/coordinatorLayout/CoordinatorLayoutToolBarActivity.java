package com.zhang.coordinatorLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class CoordinatorLayoutToolBarActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coordinator_layout_toolbar);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("这里是Title");
		toolbar.setSubtitle("这里是子标题");
		toolbar.setLogo(R.drawable.icon);
//		setSupportActionBar(toolbar);
	}
}
