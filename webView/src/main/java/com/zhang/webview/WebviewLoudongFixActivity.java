package com.zhang.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class WebviewLoudongFixActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_loudong);
        mWebView = findViewById(R.id.web_view);

        mWebView.loadUrl("file:///android_asset/loudongwebfix.html");
        mWebView.loadUrl("javascript:"+"file:///android_asset/loudongwebfix.js");
        mWebView.getSettings().setJavaScriptEnabled(true);
        //与WebviewLoudongActivity相比，不会有漏洞注入
//        mWebView.addJavascriptInterface(new JSInterface(), "jsInterface");
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Log.e("TAG", "WebviewLoudongFixActivity onJsPrompt:");
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.e("TAG", "Web   viewLoudongFixActivity onConsoleMessage:"+consoleMessage.message());
                return true;
            }

            @Override
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.e("TAG", "WebviewLoudongFixActivity onConsoleMessage:" + message);
                super.onConsoleMessage(message, lineNumber, sourceID);
            }
        });

    }

    class  JSInterface{
       public String   onButtonClick(String s){
           Toast.makeText(getApplicationContext(), "加的android " +s, Toast.LENGTH_LONG).show();
           return "加的android " +s;
       }
       public void onImageClick(String url,int  width, int height){
           Toast.makeText(getApplicationContext(), "图片url 地址 " +url, Toast.LENGTH_LONG).show();
       }
    }
}
