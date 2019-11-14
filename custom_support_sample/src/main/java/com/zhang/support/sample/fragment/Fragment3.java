package com.zhang.support.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhang.support.sample.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

/**
 * createBy	 keepon
 */
public class Fragment3 extends BaseTestFragment {
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        TAG = "Fragment3";
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_fragment3, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.backFragment1, R.id.backFragment2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backFragment1:
                //回退到Fragment2
                FragmentManager fm = getFragmentManager();
                fm.popBackStack("fragment1", POP_BACK_STACK_INCLUSIVE);
                break;
            case R.id.backFragment2:
                //回退到Fragment2
                FragmentManager fm2 = getFragmentManager();
                fm2.popBackStack("fragment2", POP_BACK_STACK_INCLUSIVE);
                break;
        }
    }
}
