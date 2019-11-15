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


    @Override
    protected View getContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment3,null);
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
//                popBackStack(String tag,int flags)：
//                tag可以为null或者相对应的tag，flags只有0和1(POP_BACK_STACK_INCLUSIVE)两种情况
//                如果tag为null，flags为0时，弹出回退栈中最上层的那个fragment。
//                如果tag为null ，flags为1时，弹出回退栈中所有fragment。
//                如果tag不为null，那就会找到这个tag所对应的fragment，flags为0时，弹出该
//                fragment以上的Fragment，如果是1，弹出该fragment（包括该fragment）以
//                上的fragment。
//                原文链接：https://blog.csdn.net/qq_20280683/article/details/79641182
//                fm.popBackStack("fragment1", POP_BACK_STACK_INCLUSIVE);
                //有待验证
//                fm.popBackStack(null, 0);
//                fm.popBackStack(null, 1);
                break;
            case R.id.backFragment2:
                //回退到Fragment2
                FragmentManager fm2 = getFragmentManager();
                fm2.popBackStack("fragment2", POP_BACK_STACK_INCLUSIVE);
                break;
        }
    }
}
