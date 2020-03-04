package com.zhang.app.lifecycle;

import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhang.app.R;

public class LiveAppCompactActivity extends AppCompatActivity {

    private Button mBtnBind;
    private Button mBtnUnBind;
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
                    getLifecycle().addObserver(mObserver);
                }
            }
        });
        mBtnUnBind = findViewById(R.id.bt_unbind);
        mBtnUnBind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mObserver != null) {
                    getLifecycle().removeObserver(mObserver);
                    mObserver = null;
                }
            }
        });

    }
}