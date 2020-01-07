package com.zhang.app.jump;


import android.view.View;

import com.hc.baselibrary.base.BaseJumpActivity;
import com.zhang.app.transitiondemo.FragmentCustomAnimationsActivity;
import com.zhang.app.R;
import com.zhang.app.transitiondemo.TransitionActivity;
import com.zhang.app.banner.BannerActivity;
import com.zhang.app.indicator.IndicatorActivity;
import com.zhang.app.indicator.ViewpagerActivity;
import com.zhang.app.loader.LoaderActivity;
import com.zhang.recyclerview.itemdecoration.ItemDecorationActivity;
import com.zhang.webview.WebChromeClientActivity;
import com.zhang.webview.WebViewActivity;
import com.zhang.webview.WebviewClientActivity;
import com.zhang.webview.WebviewLoudongActivity;
import com.zhang.webview.WebviewLoudongFixActivity;

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
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
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

    }
}
