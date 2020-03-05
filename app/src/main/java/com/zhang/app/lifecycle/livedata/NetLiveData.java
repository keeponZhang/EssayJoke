package com.zhang.app.lifecycle.livedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

public class NetLiveData extends LiveData<Boolean> {

    private BroadcastReceiver mBroadcastReceiver;
    private static Context sAppContext;
    private static volatile NetLiveData sInstance;
    private AtomicBoolean mNotice = new AtomicBoolean(false);

    public static NetLiveData getInstance(Context context) {
        if (sInstance == null) {
            synchronized (NetLiveData.class) {
                if (sInstance == null) {
                    sInstance = new NetLiveData(context);
                }
            }
        }
        return sInstance;
    }

    private NetLiveData(Context context) {
        sAppContext = context.getApplicationContext();
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<Boolean> observer) {
        super.observe(owner, new Observer<Boolean>() {

            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (mNotice.compareAndSet(true, false)) {
                    observer.onChanged(aBoolean);
                }
            }
        });

    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.e("TAG", "NetLiveData onActive:");
        registerBroadcast(sAppContext);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.e("TAG", "NetLiveData onInactive:");
        unRegisterReceiver(sAppContext);
    }

    @Override
    protected void setValue(Boolean value) {
        super.setValue(value);
        mNotice.set(true);
    }

    /**
     * 注册网络连接监听。
     */
    private void registerBroadcast(Context context) {
        if (mBroadcastReceiver == null) {
            mBroadcastReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {
                    setValue(isNetworkAvailable(context));
                }

            };
            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            context.registerReceiver(mBroadcastReceiver, filter);
        }
    }

    /**
     * 取消网络连接监听。
     */
    private void unRegisterReceiver(Context context) {
        if (mBroadcastReceiver != null) {
            context.unregisterReceiver(mBroadcastReceiver);
            mBroadcastReceiver = null;
        }
    }

    /**
     * 获取当前网络是否连接，连接返回 true，未连接返回 false。
     *
     * @param context 上下文。
     * @return 网络连接连接返回 true，否则返回 false。
     */
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
        if (networkInfo != null && networkInfo.length > 0) {
            for (int i = 0; i < networkInfo.length; i++) {
                if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}