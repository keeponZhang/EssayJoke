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

/**
 * createBy	 keepon
 */
public class BaseTestFragment extends Fragment {
    public   String TAG = "BaseTestFragment";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onAttach:");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onCreate:");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("BaseTestFragment", "--------"+TAG+"----------"+" onCreateView:");
        return super.onCreateView(inflater, container, savedInstanceState);

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

