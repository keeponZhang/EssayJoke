package com.zhang.app.pingmu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hc.baselibrary.base.BaseActivity;
import com.zhang.app.R;

/**
 * createBy	 keepon
 */
public class DeviceActivity extends BaseActivity {

    private EditText mEditText;
    private TestDialog mTestDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//         requestWindowFeature(Window.FEATURE_NO_TITLE);
// // 隐藏顶部的状态栏
//         getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option | decorView.getSystemUiVisibility());
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
     setContentView(R.layout.activity_device);
        mEditText = findViewById(R.id.et);
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                Log.w("TAG",
                        "DeviceActivity postDelayed rect.top:"+rect.top+"  rect.bottom="+rect.bottom);
                //不设置全屏主题：top:89  rect.bottom=2009
                //设置全屏主题：top:0  rect.bottom=2009
                // 全面屏没全面主题rect.top:89  rect.bottom=2158
                // 全面屏设置全面主题top:0  rect.bottom=2158
            }
        }, 2000);

        findViewById(android.R.id.content).getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                        Log.w("TAG",
                                "DeviceActivity ViewTreeObserver rect.top:"+rect.top+"  rect.bottom="+rect.bottom);
                        //不设置全屏主题：top:89  rect.bottom=2009
                        //设置全屏主题：top:0  rect.bottom=2009,没移除getViewTreeObserver的话rect.top:0  rect.bottom=2009
                        //全面屏没全面主题rect.top:89  rect.bottom=2158
                        //全面屏设置全面主题rect.top:89  rect.bottom=2158，,没移除getViewTreeObserver的话rect.top:0  rect.bottom=2158
                        // findViewById(android.R.id.content).getViewTreeObserver()
                        //         .removeGlobalOnLayoutListener(this);

                    }
                });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            Log.e("TAG", "DeviceActivity onWindowFocusChanged rect.top:"+rect.top+"  rect.bottom="+rect.bottom);
            //不设置全屏主题：top:89  rect.bottom=2009
            //设置全屏主题：top:89  rect.bottom=2009
            // 全面屏没全面主题rect.top:89  rect.bottom=2158
            // 全面屏设置全面主题top:89  rect.bottom=2158
        }
    }
    private InputMethodManager mInputManager;

    public void showsoftInput(View view) {
        mInputManager =    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputManager.showSoftInput(mEditText, 0);
        //全面屏设置全面主题 弹出键盘
        // rect.top:0  rect.bottom=1257
    }
    public void hidesoftInput(View view) {
        //全面屏设置全面主题 隐藏键盘
        // rect.top:0  rect.bottom=2158
        mInputManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }


    public void showTestDialog(View view) {
        if(mTestDialog == null){
            mTestDialog = new TestDialog(this);
        }
        mTestDialog.show();

    }
}
