package com.zhang.banner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class WebViewActivity extends AppCompatActivity {
    private WebView mWebView;
    private Button mBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = (WebView)findViewById(R.id.webview);
        mBtn = (Button)findViewById(R.id.btn);
//        test1();
//        test2();
        test3();
    }

    private void test3() {


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("file:///android_asset/localweb.html");
            }
        });

    }

    private void test2() {
//        mWebView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                Log.e("TAG", "CSProMaterialStudyActivity onPageStarted:");
//                super.onPageStarted(view, url, favicon);
//            }
//
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//            }
//
//            @TargetApi(Build.VERSION_CODES.M)
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                //有重定向的话，下面这个不能注释
//               view.loadUrl(url);
//                return true;
//            }
//        });
//        在线网址中，如果要使用webview打开，记得设置WebViewClient
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("http://www.baidu.com");
            }
        });
    }

    private void test1() {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("https://blog.csdn.net/harvic880925/article/details/51464687");
            }
        });
    }

//1、如果是在线网址记得添加网络访问权限
//2、在线网址中，如果要使用webview打开，记得设置WebViewClient
//3、打开本地html文件时，是不需要设置WebViewClient，对应的asstes目录的url为：file:///android_asset/xxxxx
}
