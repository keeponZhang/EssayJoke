package com.zhang.app.jump;


import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.hc.baselibrary.base.BaseJumpActivity;
import com.hc.baselibrary.util.IntentFlag;
import com.hc.baselibrary.util.Utils;
import com.zhang.app.aop.AopActivity;
import com.zhang.app.draw.CustomSpanActivity;
import com.zhang.app.draw.DrawActivity;
import com.zhang.app.transitiondemo.CTTargetSupportActivity;
import com.zhang.app.transitiondemo.CTTargetActivity;
import com.zhang.app.transitiondemo.FragmentCustomAnimationsActivity;
import com.zhang.app.R;
import com.zhang.app.transitiondemo.TransitionActivity;
import com.zhang.app.banner.BannerActivity;
import com.zhang.app.indicator.IndicatorActivity;
import com.zhang.app.indicator.ViewpagerActivity;
import com.zhang.app.loader.LoaderActivity;
import com.zhang.app.zhiwen.LoginActivity;
import com.zhang.recyclerview.itemdecoration.ItemDecorationActivity;
import com.zhang.webview.WebChromeClientActivity;
import com.zhang.webview.WebViewActivity;
import com.zhang.webview.WebviewClientActivity;
import com.zhang.webview.WebviewLoudongActivity;
import com.zhang.webview.WebviewLoudongFixActivity;

import java.util.ArrayList;
import java.util.List;

public class JumpActivity extends BaseJumpActivity {


    @Override
    protected void onRecyclerviewItemClick(View v, int position) {
        switch (position) {
            case 0:
                Utils.startActivity(v.getContext(), IndicatorActivity.class);
                break;
            case 1:
                Utils.startActivity(v.getContext(), BannerActivity.class);
                break;
            case 2:
                Utils.startActivity(v.getContext(), ItemDecorationActivity.class);
                break;
            case 3:
                Utils.startActivity(v.getContext(), WebViewActivity.class);
                break;
            case 4:
                Utils.startActivity(v.getContext(), WebviewLoudongActivity.class);
                break;
            case 5:
                Utils.startActivity(v.getContext(), WebviewLoudongFixActivity.class);
                break;
            case 6:
                Utils.startActivity(v.getContext(), WebviewClientActivity.class);
                break;
            case 7:
                Utils.startActivity(v.getContext(), WebChromeClientActivity.class);
                break;
            case 8:
                Utils.startActivity(v.getContext(), LoaderActivity.class);
                break;
            case 9:
                Utils.startActivity(v.getContext(), ViewpagerActivity.class);
                break;
            case 10:
                Utils.startActivity(this, TransitionActivity.class);
                //这个方法需要在startActivity()或者finish()方法之后立即被调用。
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case 11:
                Utils.startActivity(this, FragmentCustomAnimationsActivity.class);
                break;
            case 12:
                List<Pair> pairs = new ArrayList<>();
                //1.得到ActivityOptionsCompact对象
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(JumpActivity.this,
                                pairs.toArray(new android.support.v4.util.Pair[pairs.size()]));
                Intent intent = new Intent(JumpActivity.this, CTTargetSupportActivity.class);
                //2.调用第1步生成的ActivityOptionsCompact的toBundle方法
               startActivity(intent, compat.toBundle());
                break;
            case 13:
                List<Pair> pairs1 = new ArrayList<>();
                //1.得到ActivityOptionsCompact对象
                ActivityOptionsCompat compat2= ActivityOptionsCompat
                        .makeSceneTransitionAnimation(JumpActivity.this,
                                pairs1.toArray(new android.support.v4.util.Pair[pairs1.size()]));
                Intent intent2 = new Intent(JumpActivity.this, CTTargetSupportActivity.class);
                //2.调用第1步生成的ActivityOptionsCompact的toBundle方法
                intent2.putExtra(IntentFlag.INTENT_FLAG_CUSTOM, true);
                startActivity(intent2, compat2.toBundle());
                break;
            case 14:
                List<Pair> pairs3 = new ArrayList<>();
                //1.得到ActivityOptionsCompact对象
                ActivityOptionsCompat compat3= ActivityOptionsCompat
                        .makeSceneTransitionAnimation(JumpActivity.this,
                                pairs3.toArray(new android.support.v4.util.Pair[pairs3.size()]));
                Intent intent3 = new Intent(JumpActivity.this, CTTargetActivity.class);
                //2.调用第1步生成的ActivityOptionsCompact的toBundle方法
                intent3.putExtra(IntentFlag.INTENT_FLAG_CUSTOM, true);
                startActivity(intent3, compat3.toBundle());
                break;
            case 15:
                Utils.startActivity(this, LoginActivity.class);
                break;
            case 16:
                Utils.startActivity(this, DrawActivity.class);
                break;
            case 17:
                Utils.startActivity(this, CustomSpanActivity.class);
                break;
            case 18:
                Utils.startActivity(this, AopActivity.class);
                break;
            default:
                break;
        }

    }

    @Override
    protected void setData(List<String> titles) {
        titles.add("IndicatorActivity");
        titles.add("BannerActivity");
        titles.add("ItemDecorationActivity");
        titles.add("WebviewActivity");
        titles.add("LouDongWebviewActivity");
        titles.add("LouDongWebviewFixActivity");
        titles.add("WebviewClientActivity");
        titles.add("WebChromeClientActivity");
        titles.add("LoaderActivity");
        titles.add("ViewpagerActivity");
        titles.add("TransitionActivity");
        titles.add("FragmentCustomAnimationsActivity");
        titles.add("CTTargetSupportActivity");
        titles.add("CTTargetSupportActivityCustom");
        titles.add("CTTargetActivity");
        titles.add("LoginActivity");
        titles.add("DrawActivity");
        titles.add("CustomSpanActivity");
        titles.add("AopActivity");

    }
}
