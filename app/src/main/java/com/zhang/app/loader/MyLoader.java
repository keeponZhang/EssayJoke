package com.zhang.app.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;

public class MyLoader extends Loader<String> {

    public static final String QUERY =  "query" ;

    public MyLoader(Context context) {
        super(context);
    }
    protected String loadData(String bundle) {
        deliverResult(bundle);
        return bundle;
    }

}