package com.zhang.app.jump;

import android.app.Application;

import com.hc.baselibrary.util.Utils;

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.setAppContext(this);
    }
}