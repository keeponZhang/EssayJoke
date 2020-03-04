package com.zhang.app.lifecycle;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.zhang.app.R;

/**
 * 1.需要让 Activity 实现 LifecycleOwner 接口。
 */
public class LiveActivity extends Activity implements LifecycleOwner {

    private Button mBtnBind;
    private Button mBtnUnBind;
    //2.创建 mLifecycleRegistry 对象，其构造函数为实现了 LifecycleOwner 的对象。
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    private LifecycleObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_appcompact);
        mBtnBind = findViewById(R.id.bt_bind);
        mBtnBind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mObserver == null) {
                    mObserver = new LiveObserver();
                    //4.1 注册的方法和之前相同。
                    getLifecycle().addObserver(mObserver);
                }
            }
        });
        mBtnUnBind = findViewById(R.id.bt_unbind);
        mBtnUnBind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mObserver != null) {
                    //4.2 反注册的方法和之前相同。
                    getLifecycle().removeObserver(mObserver);
                    mObserver = null;
                }
            }
        });
    }

    /**
     * 3.该函数返回 mLifecycleRegistry。
     *
     * @return 返回 mLifecycleRegistry。
     */
    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}