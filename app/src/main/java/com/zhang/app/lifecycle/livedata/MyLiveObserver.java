package com.zhang.app.lifecycle.livedata;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.zhang.app.lifecycle.LiveObserver;

/**
 * createBy keepon
 */
public abstract class MyLiveObserver extends LiveObserver implements Observer<Boolean> {
    @Override
    public abstract void onChanged(@Nullable Boolean aBoolean);
}
