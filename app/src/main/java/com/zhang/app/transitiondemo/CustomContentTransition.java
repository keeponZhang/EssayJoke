package com.zhang.app.transitiondemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class CustomContentTransition extends Visibility {

    public static final String TAG = "CustomContentTransition";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        Log.e("TAG", "CustomContentTransition captureStartValues:");
        super.captureStartValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        Log.e("TAG", "CustomContentTransition captureEndValues:");
        super.captureEndValues(transitionValues);
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, final View view, TransitionValues startValues, TransitionValues endValues) {
        Animator animator = null;
        String viewTag = (String) view.getTag();
        Log.e("TAG", "CustomContentTransition onAppear viewTag:"+viewTag);
        if ("transition_reveal".equals(viewTag)) {
            animator = createRevealAnimator(view, false);
        } else if ("transition_translationY".equals(viewTag)) {
            animator = createTranslateYAnimator(view, 200, 0, false);
        } else if ("transition_scale".equals(viewTag)) {
            animator = createScaleAnimator(view, .8f, 1f, false);
        }
        return animator;
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        Animator animator = null;
        String viewTag = (String) view.getTag();
        Log.e("TAG", "CustomContentTransition onDisappear viewTag:"+viewTag);
        if ("transition_reveal".equals(viewTag)) {
            animator = createRevealAnimator(view, true);
        } else if ("transition_translationY".equals(viewTag)) {
            animator = createTranslateYAnimator(view, 0, 200, true);
        } else if ("transition_scale".equals(viewTag)) {
            animator = createScaleAnimator(view, 1f, .8f, true);
        }
        return animator;
    }

    private Animator createScaleAnimator(final View view, float startValue, final float endValues, final boolean dismiss) {
        ValueAnimator animator = ValueAnimator.ofFloat(startValue, endValues);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (Float) animation.getAnimatedValue();
                float faction = animation.getAnimatedFraction();
                view.setScaleX(scale);
                view.setScaleY(scale);
                view.setAlpha(dismiss ? (1 - faction) : faction);
            }
        });
        return animator;
    }

    private Animator createTranslateYAnimator(final View view, final int startValue, int endValue, final boolean dismiss) {
        ValueAnimator animator = ValueAnimator.ofInt(startValue, endValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int translationY = (Integer) animation.getAnimatedValue();
                float faction = animation.getAnimatedFraction();
                view.setTranslationY(translationY);
                view.setAlpha(dismiss ? (1 - faction) : faction);
            }
        });
        return animator;
    }

    private Animator createRevealAnimator(final View view, boolean dismiss) {
        int cx = (view.getLeft() + view.getRight()) / 2 - 270;
        int cy = (view.getTop() + view.getBottom()) / 2 - 120;
        float maxRadius = (float) Math.hypot(view.getWidth(), view.getHeight());
        float startRadius = dismiss ? maxRadius : 0;
        float finalRadius = dismiss ? 0 : maxRadius;
        return ViewAnimationUtils.createCircularReveal(view, cx, cy, startRadius, finalRadius);
    }

}