package com.zhang.support.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zhang.support.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * createBy	 keepon
 */
public class Fragment2 extends BaseTestFragment {
    @BindView(R.id.backFragment1)
    Button mBackFragment1;
    @BindView(R.id.jumpFragment3)
    Button mJumpFragment3;
    Unbinder unbinder;
    @Override
    public void onAttach(Context context) {
        TAG = "Fragment2";
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_fragment2,null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.backFragment1, R.id.jumpFragment3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backFragment1:
                //回退到Fragment1
                FragmentManager fm = getFragmentManager();
            //将当前的事务退出回退栈
                fm.popBackStack();
                break;
            case R.id.jumpFragment3:
                //跳转到Fragment3
                Fragment3 f3 = new Fragment3();
                FragmentManager fm2 = getFragmentManager();
                FragmentTransaction tx = fm2.beginTransaction();
                tx.replace(R.id.container, f3);
                tx.addToBackStack("fragment2");
                tx.commit();
                break;
        }
    }
}
