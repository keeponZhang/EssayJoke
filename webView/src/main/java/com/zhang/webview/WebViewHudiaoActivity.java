package com.zhang.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class WebViewHudiaoActivity extends AppCompatActivity {
    private WebView mWebView;
    private Button mBtn;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_setting);
        mWebView = (WebView)findViewById(R.id.webview);
        mBtn = (Button) findViewById(R.id.btn);


        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        mWebView.addJavascriptInterface(this, "android");
        mWebView.addJavascriptInterface(new JSBridge(), "android");

        mWebView.loadUrl("file:///android_asset/hudiaoweb.html");


    }
//    为了解决addJavascriptInterface()函数的安全问题，
// 在android:targetSdkVersion数值为17（Android4.2）及以上的APP中，JS只能访问带有 @JavascriptInterface注解的Java函数，所以如果你的android:targetSdkVersion是17+，与JS交互的Native函数中，必须添加JavascriptInterface注解，不然无效，比如
    @JavascriptInterface
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), "通过Natvie传递的 <<@JavascriptInterface>> Toast:"+message, Toast.LENGTH_LONG).show();
    }
    public void toastMessage2(String message) {
        Toast.makeText(getApplicationContext(), "通过Natvie传递的Toast:"+message, Toast.LENGTH_LONG).show();
    }


    public class JSBridge {
        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(), "JSBridge 通过Natvie传递的 <<@JavascriptInterface>> Toast:" + message, Toast.LENGTH_LONG).show();
        }
        public void toastMessage2(String message) {
            Toast.makeText(getApplicationContext(), "JSBridge 通过Natvie传递的Toast:"+message, Toast.LENGTH_LONG).show();
        }
    }

}
