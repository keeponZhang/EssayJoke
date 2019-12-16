package com.zhang.app.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;

public class MyLoader extends Loader<String> {

    public static final String QUERY =  "query" ;
    private Bundle mBundle;

    public MyLoader(Context context, Bundle bundle) {
        super(context);
        mBundle = bundle;
    }
    protected String loadData() {
        String result = mBundle != null ? mBundle.getString(QUERY) : "empty";
        Log.e("TAG", "MyLoader loadData result "+result);
        deliverResult(result);
        return result;
    }

}