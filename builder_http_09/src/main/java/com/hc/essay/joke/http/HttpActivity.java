package com.hc.essay.joke.http;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hc.essay.joke.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpActivity extends AppCompatActivity implements View.OnClickListener {


    private String mUrl   = "https://api.github.com/users/octocat/repos";;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test_tv).setOnClickListener(this);
        findViewById(R.id.test_tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2();
            }


        });
    }
    private void test2() {
        try {
            int i = 0;
//            int b = 2 /0;
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            try {
                okHttpClient.newCall(new Request.Builder().url("https://www.baidu.com").build())
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e("TAG", "HttpActivity onFailure:");
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if(true)
                                    throw new RuntimeException("keepon");
                                Log.e("TAG", "HttpActivity onResponse:"+response);
                            }
                        });
            }catch (Exception e){
                Log.e("TAG", "HttpActivity test2  Exception2:");
            }

        }catch (Exception e){
            Log.e("TAG", "HttpActivity test2 Exception:"+e);
        }



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

