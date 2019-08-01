package com.zhang.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebviewSettingActivity extends AppCompatActivity {
    private WebView mWebView;
    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_setting);
        mWebView = (WebView) findViewById(R.id.webview);
        mBtn = (Button) findViewById(R.id.btn);
        mWebView.setWebViewClient(new WebViewClient());
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("https://www.w3school.com.cn/");
            }
        });
        WebSettings webSettings = mWebView.getSettings();
//        在WebView中启用JavaScript：
        webSettings.setJavaScriptEnabled(true);
//        优先使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        不使用缓存：
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

//      设置此属性，可任意比例缩放
        webSettings.setUseWideViewPort(true);//
//        打开页面时， 自适应屏幕：
        webSettings.setLoadWithOverviewMode(true);

        //开启javascript支持
        webSettings.setJavaScriptEnabled(true);
// 设置可以支持缩放
        webSettings.setSupportZoom(true);
// 设置出现缩放工具
        webSettings.setBuiltInZoomControls(true);

    }
}
