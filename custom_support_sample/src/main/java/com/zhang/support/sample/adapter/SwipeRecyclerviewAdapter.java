package com.zhang.support.sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhang.support.sample.R;

import java.util.List;

/**
 * createBy	 keepon
 */
public class SwipeRecyclerviewAdapter extends BaseRecyclerViewAdapter<String,SwipeRecyclerviewAdapter.ViewHolder> {

    public SwipeRecyclerviewAdapter(Context context) {
        super(context);
    }



    public SwipeRecyclerviewAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    public ViewHolder onCreateMyViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindMyViewHolder(@NonNull ViewHolder holder, int position) {
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
