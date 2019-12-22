package com.zhang.app.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.os.CancellationSignal;
import android.support.v4.os.OperationCanceledException;

public abstract class BaseDataLoader<Result> extends AsyncTaskLoader<Result> {

    Result mResult;
    Bundle mBundles;
    CancellationSignal mCancellationSignal;

    //所有的子类最多只需要实现下面的三个方法，而不要回收资源的只用实现loadData就可以了。
    protected abstract Result loadData(Bundle bundle);
    protected void releaseResult(Result result) {}
    protected boolean isResultReleased(Result result) { return true; }

    public BaseDataLoader(Context context, Bundle bundle) {
        super(context);
        mBundles = bundle;
    }

    @Override
    public Result loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }
            mCancellationSignal = new CancellationSignal();
        }
        try {
            return loadData(mBundles);
        } finally {
            synchronized (this) {
                mCancellationSignal = null;
            }
        }
    }

    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            if (mCancellationSignal != null) {
                mCancellationSignal.cancel();
            }
        }
    }

    @Override
    public void deliverResult(Result result) {
        if (isReset()) {
            if (result != null && !isResultReleased(result)) {
                releaseResult(result);
            }
            return;
        }
        Result oldResult = mResult;
        mResult = result;
        if (isStarted()) {
            super.deliverResult(result);
        }
        if (oldResult != null && oldResult != result && !isResultReleased(oldResult)) {
            releaseResult(oldResult);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mResult != null) {
            deliverResult(mResult);
        }
        if (takeContentChanged() || mResult == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(Result result) {
        if (result != null && !isResultReleased(result)) {
            releaseResult(result);
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mResult != null && !isResultReleased(mResult)) {
            releaseResult(mResult);
        }
        mResult = null;
    }

}