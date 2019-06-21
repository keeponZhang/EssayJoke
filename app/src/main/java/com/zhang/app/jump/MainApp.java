package com.zhang.app.jump;

import android.app.Application;

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.setAppContext(this);
    }
}