package com.zhang.support.sample.jump;

import android.content.Context;
import android.content.Intent;

import com.hc.baselibrary.util.ToastUtil;

public class Utils {

    private static Context sAppContext;

    public static void startActivity(Context context, Class targetName) {
        Intent intent = new Intent(context, targetName);
        context.startActivity(intent);
    }
    public static void startActivity(Context context, String targetName) {

        try {
            Class<?> aClass = null;
            aClass = Class.forName(targetName);
            Intent intent = new Intent(context, aClass);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            ToastUtil.showMessage(context,"没有找到"+targetName);
        }


    }

    public static Context getAppContext() {
        return sAppContext;
    }

    public static void setAppContext(Context appContext) {
        sAppContext = appContext;
    }
}
