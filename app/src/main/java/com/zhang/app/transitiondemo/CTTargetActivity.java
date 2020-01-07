package com.zhang.app.transitiondemo;

import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;

import com.hc.baselibrary.util.IntentFlag;
import com.zhang.app.R;

public class CTTargetActivity extends AppCompatActivity {

    private ViewGroup mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct_target_custom);
        mRootView = findViewById(R.id.ll_root);
        setUpCustomTransition();
    }




    private void setUpCustomTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            CustomContentTransition contentTransition = new CustomContentTransition();
            contentTransition.addTarget(R.id.iv_bg);
            contentTransition.addTarget(R.id.tv_header);
            contentTransition.addTarget(R.id.iv_header);
            contentTransition.addTarget(R.id.tv_content);
            contentTransition.setDuration(500);
            window.setEnterTransition(contentTransition);
            window.setReturnTransition(contentTransition);
        }
    }

}