package com.zhang.webview;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.File;

public class WebviewLoudongActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_loudong);
        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JSInterface(), "jsInterface");

        mWebView.loadUrl("file:///android_asset/loudongweb.html");
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Log.e("TAG", "WebviewLoudongActivity onCreate:" + externalStorageDirectory.getAbsolutePath());
    }
    public void testLoulong(){
        Toast.makeText(getApplicationContext(), "有漏洞啦------------ " , Toast.LENGTH_LONG).show();

    }



    class  JSInterface{
        //这个注解对4.2一下的没作用的，有跟没有是一样的
        // @JavascriptInterface
       public String   onButtonClick(String s){
           Toast.makeText(getApplicationContext(), "加的android " +s, Toast.LENGTH_LONG).show();
           return "加的android " +s;
       }
        // @JavascriptInterface
       public void onImageClick(String url,int  width, int height){
           Toast.makeText(getApplicationContext(), "图片url 地址 " +url, Toast.LENGTH_LONG).show();
       }
    }
}
