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
public class Fragment1 extends BaseTestFragment {

    @BindView(R.id.jumpFragment2)
    Button mJumpFragment2;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        TAG = "Fragment1";
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_fragment1,null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.jumpFragment2)
    public void onClick() {
        Fragment2 f2 = new Fragment2();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.container, f2);
      //将当前的事务添加到了回退栈
//        tx.addToBackStack("fragment1");
        tx.commit();
    }
}
