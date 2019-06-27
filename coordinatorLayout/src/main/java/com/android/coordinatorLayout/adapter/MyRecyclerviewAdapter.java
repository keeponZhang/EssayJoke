package com.android.coordinatorLayout.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.coordinatorLayout.R;

import java.util.List;

/**
 * createBy	 keepon
 */
public class MyRecyclerviewAdapter extends BaseRecyclerViewAdapter<String,MyRecyclerviewAdapter.ViewHolder> {

    public MyRecyclerviewAdapter(Context context) {
        super(context);
    }

    private static final String TAG = "MyRecyclerviewAdapter";

    public MyRecyclerviewAdapter(Context context, List datas) {
        super(context, datas);
    }
    private int mCreatedHolder=0;
    @Override
    public ViewHolder onCreateMyViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, parent, false);
        mCreatedHolder++;
        Log.d(TAG, "onCreateViewHolder  num:"+mCreatedHolder);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindMyViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final String title = mDatas.get(position);
        TextView titleView = holder.mTvTitle;
        titleView.setText(title);
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTvTitle;

        public ViewHolder(View rootView) {
            super(rootView);
            mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        }
    }
}
