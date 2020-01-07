package com.zhang.app.transitiondemo;

import android.os.Build;
import android.os.Bundle;
import android.support.transition.Slide;
import android.support.transition.TransitionInflater;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc.baselibrary.util.IntentFlag;
import com.zhang.app.R;

public class CTTargetSupportActivity extends AppCompatActivity {

    private ViewGroup mRootView;
    private ImageView mIvBg;
    private ImageView mIvHeader;
    private TextView mTvHeader;
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean custom = getIntent().getBooleanExtra(IntentFlag.INTENT_FLAG_CUSTOM, false);
        if(custom){
            setContentView(R.layout.activity_ct_target_custom);
            initView();
            setUpCustomTransition();
        }else{
            setContentView(R.layout.activity_ct_target);
            initView();
            // setUpNormalTransition();
            setUpTargetTransition();
        }


    }

    private void initView() {
        mRootView = findViewById(R.id.ll_root);
        mIvBg = findViewById(R.id.iv_bg);
        mTvHeader = findViewById(R.id.tv_header);
        mIvHeader = findViewById(R.id.iv_header);
        mTvContent = findViewById(R.id.tv_content);
    }

    private void setUpNormalTransition() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.e("TAG", "CTTargetSupportActivity setUpNormalTransition:");
            LogFadeTransition transition = new LogFadeTransition();
            TransitionManager.beginDelayedTransition(mRootView, transition);
            // window.setEnterTransition(transition);
            // window.setReturnTransition(transition);
        }
    }
    private void setUpTargetTransition() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.e("TAG", "CTTargetSupportActivity setUpNormalTransition:");
            LogFadeTransition transition = new LogFadeTransition();
            transition.addTarget(R.id.iv_bg);
            TransitionManager.beginDelayedTransition(mRootView, transition);
            // window.setEnterTransition(transition);
            // window.setReturnTransition(transition);
        }

    }
    private void setUpNormalTransitionSet() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LogFadeTransition transition = new LogFadeTransition();
            Slide slide = new Slide();
            TransitionSet set = new TransitionSet();
            set.addTransition(slide);
            set.addTransition(transition);
            TransitionManager.beginDelayedTransition(mRootView, set);

            // window.setEnterTransition(set);
            // window.setReturnTransition(set);
        }

    }
    private void setUpCustomTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.e("TAG", "CTTargetSupportActivity setUpCustomTransition:");
            Window window = getWindow();
            CustomSupportContentTransition contentTransition = new CustomSupportContentTransition();
            contentTransition.addTarget(R.id.iv_bg);
            contentTransition.addTarget(R.id.tv_header);
            contentTransition.addTarget(R.id.iv_header);
            contentTransition.addTarget(R.id.tv_content);
            contentTransition.setDuration(500);
            TransitionManager.beginDelayedTransition(mRootView, contentTransition);
            // window.setEnterTransition(contentTransition);
            // window.setReturnTransition(contentTransition);
        }
    }

}