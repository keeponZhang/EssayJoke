package com.zhang.webview;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebviewClientActivity extends AppCompatActivity {
    private WebView mWebView;
    private Button mBtn;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_setting);
        mWebView = (WebView) findViewById(R.id.webview);
        mBtn = (Button) findViewById(R.id.btn);
        mProgressDialog = new ProgressDialog(this);

        mWebView.getSettings().setJavaScriptEnabled(true);
        test1();
//        test2();

    }

    private void test2() {
//        当出现SSL错误时，WebView默认是取消加载当前页面，只有去掉onReceivedSslError的默认操作，然后添加SslErrorHandler.proceed()才能继续加载出错页面
//        当HTTPS传输出现SSL错误时，错误会只通过onReceivedSslError回调传过来
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                Log.e("TAG", "WebviewClientActivity onReceivedSslError:" + error.toString());
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Log.e("TAG","onReceivedError:"+errorCode+"  "+description);
            }

//            在每一次请求资源时，都会通过这个函数来回调，比如超链接、JS文件、CSS文件、图片等，也就是说浏览器中每一次请求资源时，都会回调回来，无论任何资源！但是必须注意的是shouldInterceptRequest函数是在非UI线程中执行的，在其中不能直接做UI操作，如果需要做UI操作，则需要利用Handler来实现
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.e("TAG", "WebviewClientActivity shouldInterceptRequest:");
                return super.shouldInterceptRequest(view, request);
            }
        });
        mWebView.loadUrl("https://www.12306.cn/");
    }

    private void test1() {
        mWebView.loadUrl("http://blog.csdn.net/harvic880925");
        mWebView.setWebViewClient(new WebViewClient() {
            //            在利用shouldOverrideUrlLoading来拦截URL时，如果return true，则会屏蔽系统默认的显示URL结果的行为，
//           不需要处理的URL也需要调用loadUrl()来加载进WebVIew，不然就会出现白屏；如果return false，
//          则系统默认的加载URL行为是不会被屏蔽的，所以一般建议大家return false，我们只关心我们关心的拦截内容，对于不拦截的内容，让系统自己来处理即可。
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                Log.e("TAG", "WebviewClientActivity shouldOverrideUrlLoading url:" + url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("TAG", "WebviewClientActivity onPageStarted:");
                if(!mProgressDialog.isShowing()){
                    mProgressDialog.show();
                }

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("TAG", "WebviewClientActivity onPageFinished:");
                if(mProgressDialog.isShowing()){
                    mProgressDialog.hide();
                }

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                mWebView.loadUrl("file:///android_asset/error.html");
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //改写物理返回键的逻辑
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if(mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
