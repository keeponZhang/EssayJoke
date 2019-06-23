package com.android.resource_assetmanager_2_01;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hc.baselibrary.base.BaseActivity;
import com.hc.baselibrary.ioc.OnClick;
import com.hc.baselibrary.ioc.ViewById;

import java.io.File;
import java.lang.reflect.Method;

//AssetManager.cpp
public class MainActivity extends BaseActivity {
	@ViewById(R.id.image_iv)
	private ImageView mImageIv;

	@Override
	protected void initData() {
		// resources.arsc
	}


	@Override
	protected void initView() {
		// 初始化 View
		// viewById(R.id.test_tv).setOnClickListener(this);
	}

	@Override
	protected void initTitle() {

	}

	private static final String TAG = "MainActivity";
	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_main);
	}

	@OnClick({R.id.test_tv,R.id.jump})
	public void onClick(View v) {
		int id = v.getId();
		if(id ==R.id.jump){
			startActivity(SecondActivity.class);
		} else{
			Toast.makeText(this,"点击",Toast.LENGTH_LONG).show();
			try {
				String s = Environment.getExternalStorageDirectory().getAbsolutePath() +
						File.separator + "red.skin";
				File file = new File(s);
				if(!file.exists()){
					return;
				}
				// 读取本地的一个 .skin里面的资源
				Resources superRes = getResources();
				// 创建AssetManager
				AssetManager asset = AssetManager.class.newInstance();

				// 添加本地下载好的资源皮肤   Native层c和c++怎么搞的
				Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
				 method.setAccessible(true); //如果是私有的
				// 反射执行方法
				method.invoke(asset, Environment.getExternalStorageDirectory().getAbsolutePath()+
						File.separator + "red.skin");
				Log.e(TAG, "onClick: "+Environment.getExternalStorageDirectory().getAbsolutePath());

				Resources resource = new Resources(asset,Resources.getSystem().getDisplayMetrics(),
						superRes.getConfiguration());

				// 获取资源 id
				int drawableId = resource.getIdentifier("main_bg2","drawable","com.hc.essay.joke");

				Drawable drawable = resource.getDrawable(drawableId);

				mImageIv.setImageDrawable(drawable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

