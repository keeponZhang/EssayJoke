package com.hc.baselibrary.util;

import android.app.Activity;
import android.content.Context;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Toast util class.
 */
public class ToastUtil {

    private static final String TAG = "ToastUtil";

    public static void showMessage(Context act, String msg) {
        showMessage(act, msg, 1500);
    }

    public static void showMessage(Context act, int res) {
        showMessage(act, act.getString(res), 1500);
    }

    public static void showMessage(Context act, String msg, int len) {
        try {
            if (act instanceof Activity && ((Activity) act).isFinishing()) {
                return;
            }
            Toast.makeText(act, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "showMessage: ", e);
        }
    }

    public static void showHtmlMessage(Context act, Spanned spanned) {
        try {
            if (act instanceof Activity && ((Activity) act).isFinishing()) {
                return;
            }
            Toast.makeText(act, spanned, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "showHtmlMessage: ", e);
        }
    }

    /**
     * 自定义toast view
     *
     * @param context
     * @param layoutId view的布局id
     * @param gravity  方位 {@link android.view.Gravity}
     */
    public static void showToastWithView(Context context, int layoutId, int gravity) {
        try {
            if (context instanceof Activity && ((Activity) context).isFinishing()) {
                return;
            }
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(layoutId, null);
            toast.setView(view);
            toast.setGravity(gravity, 0, 0);
            toast.show();
        } catch (Exception e) {
            Log.e(TAG, "showToastWithView: ", e);
        }

    }
}
