package com.zhang.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class WebChromeClientActivity extends AppCompatActivity {
    //    WebViewClient:在影响View的事件到来时，会通过WebViewClient中的方法回调通知用户
//    WebChromeClient：当影响浏览器的事件到来时，就会通过WebChromeClient中的方法回调通知用法。
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_setting);

        mWebView = (WebView) findViewById(R.id.webview);
//        chromeClient();
//        loadData();
        loadDataWithBaseURL();
    }

    //    在代码中有三种地址：第一个是绝对的http地址，第二个是通过file指定的本地地址，对于这两类的绝对地址，baseUrl是不起作用的，而对于第三个相对地址，是会启用baseUrl的来拼接完整地址的。
    private void loadDataWithBaseURL() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        String baseURL = "http://img6.ph.126.net";
//        String data = "漂亮MM <img src='hBiG96B8egigBULxUWcOpA==/109212290980771276.jpg'>";
        String data = "绝对网页地址:" +
                "<img src='http://ep.dzb.ciwong.com/rep/image/3173.jpg'>" +
                "本地地址:" +
                "<img src='file:///android_asset/s07.jpg'>" +
                "相对地址:" +
                "<img src='hBiG96B8egigBULxUWcOpA==/109212290980771276.jpg'>";
        mWebView.loadDataWithBaseURL(baseURL, data, "text/html", "utf-8", null);
    }

    private void loadData() {
//        在使用loadData时，在数据里面不能出现英文字符：’#’, ‘%’, ‘\’ , ‘?’ 这四个字符，如果有的话可以用 %23, %25, %27, %3f，这些字符来替换，在平时测试时，你的数据时，你的数据里含有这些字符，但不会出问题，当出问题时，你可以替换下。
//        直接使用这四个字符会造成的问题如下：
//       %：会报找不到页面错误，页面全是乱码。
//         #，会让你的goBack失效，但canGoBAck是可以使用的。于是就会产生返回按钮生效，但不能返回的情况。
//        \ 和? 我在转换时，会报错，因为它会把\当作转义符来使用，如果用两级转义，也不生效，我是对它无语了

        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        String summary = "<html><body>You 张三 scored <b>192</b> points.</body></html>";
        mWebView.loadData(summary, "text/html", "utf-8");

    }

    private void chromeClient() {
        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(WebChromeClientActivity.this, "xxx", Toast.LENGTH_SHORT).show();

//                这句话非常重要，它表示向WebView通知操作结果，JsResult有两个函数：JsResult.confirm()和JsResult.cancel()，JsResult.confirm()表示点击了弹出框的确定按钮，JsResult.cancel()则表示点击了弹出框的取消按钮。
//                如果没有使用JsResult来告诉WebView处理结果，则WebView就会认为这个弹出框还一直弹在那里，你再点击alert按钮，将会无效；
                result.confirm();

//                表示告诉WebView我们已经拦截了alert()函数，不需要再弹出网页中的alert弹出框了，如果我们return false,那么WebView就会认为我们没有拦截alert()函数，会继续弹出alert对话框。
                return false;
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Toast.makeText(WebChromeClientActivity.this, consoleMessage.message(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.e("TAG", "WebChromeClientActivity onProgressChanged:" + newProgress);
                super.onProgressChanged(view, newProgress);
            }


        });

        mWebView.loadUrl("file:///android_asset/webchrome.html");
    }
}
