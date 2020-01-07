package com.zhang.app.transitiondemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.zhang.app.R;

public class TransitionActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewGroup mRootView;
    private View mRedBox, mGreenBox, mBlueBox, mBlackBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        mRootView = (ViewGroup) findViewById(R.id.layout_root_view);
        mRootView.setOnClickListener(this);

        mRedBox = findViewById(R.id.red_box);
        mGreenBox = findViewById(R.id.green_box);
        mBlueBox = findViewById(R.id.blue_box);
        mBlackBox = findViewById(R.id.black_box);
    }

    @Override
    public void onClick(View v) {
        if(Build.VERSION.SDK_INT >19){
            // 第一步：通过beginDelayedTranstion传入场景对应布局的根节点（mRootView）以及场景变换的规则（Fade），此时系统理解调用Transition的captureStartValues方法，来确定场景当中所有子View的visibility。
            //TransitionManager.sceneChangeSetup->Transition.captureValues(sceneRoot, true);
            TransitionManager.beginDelayedTransition(mRootView, new Fade());
            // 第二步：当beginDeleyedTransition返回后，我们将子View设置为不可见。
            toggleVisibility(mRedBox, mGreenBox, mBlueBox, mBlackBox);
            // 第三步：在下一帧，系统调用Transtion的captureEndValues()方法获取场景当中所有子View的可见性。
            // 第四步：这时候系统发现在起始场景中View是VISIBLE的，而在终点场景中它变为了INVISIBLE，那么Fade Transition就会根据这些信息创建并返回AnimatorSet，用它来将那些发生变化的View的alpha值渐变为0，而不是直接设为不可见。
            // 第五步：系统启动这个Animator，使得这些View慢慢隐藏。

        }

    }



    private static void toggleVisibility(View... views) {
        for (View view : views) {
            boolean isVisible = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
        }
    }

    public void onClickFinish(View view) {
        finish();
        overridePendingTransition(R.anim.down_bottom_in, R.anim.down_bottom_out);
    }

    // 3.2.3 小结
    // 无论是AActivity启动BActivity，还是BActivity返回到AActivity，当View的可见性不断切换的时候，
    // 系统能保证根据状态信息来创建所需的动画。很显然，所有的Content transition对象都需要能够捕捉并记录View的起始状态和终点状态，
    // 幸运的是，抽象类Visiblity已经帮我们做了，我们只需要实现onAppear和onDisappear方法，在里面创建一个Animator来定义进入和退出场景的View的动画，
    // 系统默认提供了三种Transition - Fade、Slide、Explode，下面我们在分析Fade源码的时候，会详细解释这一过程。

    // 3.3 Content Transition和Shared Element Transition
    // 在上面的讨论当中，我们是从切换的角度来考虑的，而如果我们从Transition的角度来看，那么每个Transition又可以细分为两类：
    //
    // content transitions：定义了Activity非共享View进入和退出场景的方式。
    // shared element transitions：定义了Acitivity共享View进入和退出场景的方法。


    // 作者：泽毛
    // 链接：https://www.jianshu.com/p/510e415c56f2
    // 来源：简书
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}