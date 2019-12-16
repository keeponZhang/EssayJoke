package com.zhang.app.loader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.zhang.app.R;

import static android.provider.MediaStore.MediaColumns.MIME_TYPE;

public class LoaderActivity extends AppCompatActivity {

    private static final String LOADER_TAG = "loader_tag";
    private static final String QUERY = "query";

    private MyLoaderCallback mMyLoaderCallback;
    private TextView mResultView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        init();
    }

    private void init() {
        mEditText = (EditText) findViewById(R.id.loader_input);
        mResultView = (TextView) findViewById(R.id.loader_result);
        mEditText.addTextChangedListener(new MyEditTextWatcher());
        mMyLoaderCallback = new MyLoaderCallback();
        // startQuery("keepon");
    }

    private void startQuery(String query) {
        if (query != null) {
            Bundle bundle = new Bundle();
            bundle.putString(QUERY, query);
            Log.e("TAG", "LoaderActivity startQuery:");
            // Loader<String> stringLoader = getSupportLoaderManager().initLoader(0, bundle, mMyLoaderCallback);
            Loader<String> stringLoader = getSupportLoaderManager().restartLoader(0, bundle,
                    mMyLoaderCallback);
            MyLoader myLoader = (MyLoader) stringLoader;
            myLoader.loadData(query);
        }
    }

    private void showResult(String result) {
        if (mResultView != null) {
            Log.w("TAG", "LoaderActivity showResult:"+result);
            mResultView.setText(result);
        }
    }

    private class MyLoaderCallback implements LoaderManager.LoaderCallbacks<String> {

        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            Log.d("TAG", "onCreateLoader args " + args);
            return new MyLoader(getApplicationContext());
        }

        @Override
        public void onLoadFinished(Loader loader, String data) {
            Log.d("TAG", "onLoadFinished " + data);
            showResult((String) data);
        }

        @Override
        public void onLoaderReset(Loader loader) {
            Log.d("TAG", "onLoaderReset");
            showResult("");
        }
    }

    private class MyEditTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d("TAG", "onTextChanged=" + s);
            startQuery(s != null ? s.toString() : "");
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }


}
