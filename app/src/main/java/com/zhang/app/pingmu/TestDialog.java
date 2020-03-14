package com.zhang.app.pingmu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.zhang.app.R;

/**
 * createBy keepon
 */
public class TestDialog extends Dialog {

    // https://blog.csdn.net/ccpat/article/details/55224475
    public TestDialog(@NonNull Context context) {
        super(context, R.style.common_dialog);
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option | decorView.getSystemUiVisibility());
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.dialog_test);


        //全面屏，全面主题MATCH_PARENT MATCH_PARENT rect.top:89  rect.bottom=2158
        // getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
        //         ViewGroup.LayoutParams.MATCH_PARENT);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //         WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //全面屏 全面主题 rect.top:0  rect.bottom=2158
        //全面屏 不全面主题 rect.top:79  rect.bottom=2158(因为与系统状态栏覆盖为79)
        final WindowManager.LayoutParams params =getWindow().getAttributes();
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        params.width = 800;
        params.height = 2138;
        params.gravity = Gravity.CENTER;
       getWindow().setAttributes(params);

        setCancelable(true);
        setCanceledOnTouchOutside(true);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                Log.w("TAG",
                        "TestDialog  rect.top:"+rect.top+"  rect.bottom="+rect.bottom);
            }
        }, 1000);

    }
}
