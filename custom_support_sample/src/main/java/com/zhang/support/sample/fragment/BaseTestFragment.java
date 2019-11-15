package com.zhang.support.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhang.support.sample.R;

/**
 * createBy	 keepon
 */
public class BaseTestFragment extends Fragment {
    public   String TAG = "BaseTestFragment";
    private String mTitle;
    public BaseTestFragment(){
        super();
    }



    public static BaseTestFragment newInstance(String title){
        BaseTestFragment baseTestFragment = new BaseTestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        baseTestFragment.setArguments(bundle);
        return baseTestFragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onAttach:");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mTitle = getArguments().getString("title");
        super.onCreate(savedInstanceState);
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onCreate:");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onCreateView:");
        return getContentView(inflater, container, savedInstanceState);

    }

    protected View getContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_base,null);
        TextView tv = view.findViewById(R.id.tv_title);
        tv.setText(mTitle);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onActivityCreated:");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onStart  ----- ");
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onPause:");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onStop:");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onResume:");
    }

    @Override
    public void onDestroyView() {
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onDestroyView:");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onDetach:");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onDestroy:");
        super.onDestroy();
    }
}

