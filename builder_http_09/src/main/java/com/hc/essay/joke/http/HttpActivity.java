package com.hc.essay.joke.http;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hc.essay.joke.R;


public class HttpActivity extends AppCompatActivity implements View.OnClickListener {


    private String mUrl   = "https://api.github.com/users/octocat/repos";;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test_tv).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
//        HttpUtils.with(this).url("").addParam("","").execute(new EngineCallBack() {
//            @Override
//            public void onPreExecute(Context context, Map<String, Object> params) {
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//
//            @Override
//            public void onSuccess(String result) {
//
//            }
//        });


        HttpUtils.with(this).url(mUrl).execute(new HttpCallBack<ApiBean>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onSuccess(ApiBean result) {
                Log.e(TAG, "onSuccess: "+result );
            }
        });
    }

    private static final String TAG = "HttpActivity";
}

