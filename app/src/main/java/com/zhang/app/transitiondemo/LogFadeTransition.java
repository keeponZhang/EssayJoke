package com.zhang.app.transitiondemo;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.transition.Fade;
import android.support.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * createBy keepon
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class LogFadeTransition extends Fade {
    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        Log.w("TAG", "LogFadeTransition captureEndValues:");
        super.captureEndValues(transitionValues);
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        Log.w("TAG", "LogFadeTransition captureStartValues:");
        super.captureStartValues(transitionValues);
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues,
                             TransitionValues endValues) {
        Log.e("TAG", "LogFadeTransition onAppear view:"+view);
        return super.onAppear(sceneRoot, view, startValues, endValues);
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues,
                                TransitionValues endValues) {
        Log.e("TAG", "LogFadeTransition onDisappear view:"+view);
        return super.onDisappear(sceneRoot, view, startValues, endValues);
    }
}
