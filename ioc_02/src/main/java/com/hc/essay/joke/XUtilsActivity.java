package com.hc.essay.joke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_xutils)
public class XUtilsActivity extends AppCompatActivity {
	@ViewInject(R.id.tv1)
	private TextView mTv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_xutils);
		x.view().inject(this);

		View viewById = findViewById(R.id.tv2);
		Log.e("TAG", "onCreate: "+viewById );
		mTv1.setText("hi");
	}

}
