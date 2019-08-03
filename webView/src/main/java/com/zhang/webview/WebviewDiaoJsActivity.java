package com.zhang.webview;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class WebviewDiaoJsActivity extends AppCompatActivity {
    private WebView mWebView;
    private Button mBtn;
    private WebSettings mWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_diao_js);
        mWebView = (WebView) findViewById(R.id.webview);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setText("java调用js");

        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(this, "android");
        mWebView.loadUrl("file:///android_asset/webjs.html");

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:sum(3,8)");
            }
        });


    }

    public void getReturnFromJsBefore19(View view) {
        //在这里调用不行，要在加载html的时候调用
//        mWebSettings.setJavaScriptEnabled(true);
//        mWebView.addJavascriptInterface(this, "android");
        mWebView.loadUrl("javascript:sum(100,150)");
    }

    @JavascriptInterface
    public void onSumResult(int result) {
        Log.e("TAG", "WebviewDiaoJsActivity onSumResult:");
        Toast.makeText(this,"onSumResult before 4.4 result=" + result,Toast.LENGTH_LONG).show();
    }
    public void getReturnFromJsAfter19(View view) {
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT){
            mWebView.evaluateJavascript("getGreetings()", new ValueCallback() {
                @Override
                public void onReceiveValue(Object value) {
                    Log.e("TAG", "WebviewDiaoJsActivity getReturnFromJsAfter19:"+value);
                    Toast.makeText(WebviewDiaoJsActivity.this,"getReturnFromJsAfter19=" + value,Toast.LENGTH_LONG).show();

                }
            });
        }



    }
}
