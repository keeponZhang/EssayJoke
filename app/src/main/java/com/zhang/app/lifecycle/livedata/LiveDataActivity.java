package com.zhang.app.lifecycle.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhang.app.R;

import java.util.List;

/**
 * LiveData 学习 Demo。
 */
public class LiveDataActivity extends AppCompatActivity {
    // 注意:在界面inactive的状态下发生了数据的改变，不会立即通知观察者，而是要等到界面重新active之后，才会调用observer的onChanged()方法
    private Button mBtnRefresh;
    private TextView mTvResult;
    private DataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        mTvResult = findViewById(R.id.tv_result);
        //1.创建 ViewModel。
        mViewModel = ViewModelProviders.of(LiveDataActivity.this).get(DataViewModel.class);
        Log.w("DataViewModel", "createViewModel, model_address=" + mViewModel + " " +
                "实例LiveDataActivity.this="+LiveDataActivity.this);
        //2.添加观察者。
        mViewModel.getWatcher().observe(this, new Observer<List<String>>() {

            @Override
            public void onChanged(@Nullable List<String> strings) {
                Log.w("DataViewModel", "onChanged observe");
                String tvDisplay = "";
                for (String result : strings) {
                    tvDisplay += (result + "\n");
                }
                //4.数据发生了改变后会回调到这里。
                mTvResult.setText(tvDisplay);
            }
        });
        // observeForever，它只接受一个观察者参数，也就是说，它并不关注当前界面是否active，都会回调数据。
        mViewModel.getWatcher().observeForever( new Observer<List<String>>() {

            @Override
            public void onChanged(@Nullable List<String> strings) {
                Log.d("DataViewModel", "onChanged observeForever --------"+strings);
            }
        });
        mBtnRefresh = findViewById(R.id.btn_refresh);
        mBtnRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //3.触发加载。
                Log.d("DataViewModel", "mViewModel.load()");
                mViewModel.load();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DataViewModel", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DataViewModel", "onPause()");
    }
}