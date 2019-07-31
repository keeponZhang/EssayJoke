package com.zhang.recyclerview.itemdecoration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.android.recyclerview.itemdecoration.bean.FollowerBean;
import com.bumptech.glide.Glide;
import com.zhang.recyclerview.R;

import java.util.List;

/**
 * createBy	 keepon
 */
public class CategoryRecyclerviewAdapter extends BaseRecyclerViewAdapter<FollowerBean,CategoryRecyclerviewAdapter.ViewHolder> {

    public CategoryRecyclerviewAdapter(Context context) {
        super(context);
    }

    public CategoryRecyclerviewAdapter(Context context, List<FollowerBean> datas) {
        super(context, datas);
    }

    @Override
    public ViewHolder onCreateMyViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 创建条目
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, parent, false);
        // 创建ViewHolder
        CategoryRecyclerviewAdapter.ViewHolder viewHolder = new CategoryRecyclerviewAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindMyViewHolder(@NonNull ViewHolder holder, int position) {
        // 设置绑定数据
        FollowerBean item = mDatas.get(position);
        holder.mTvName.setText(item.getLogin());
        holder.mTvUrl.setText(item.getUrl());
        // 加载图片
        Glide.with(mContext).load(item.getAvatar_url()).centerCrop().into(holder.mIv);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvName;
        public TextView  mTvUrl;

        public ImageView mIv;

        public ViewHolder(View itemView) {
            super(itemView);
            // 在创建的时候利用传递过来的View去findViewById
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvUrl= (TextView) itemView.findViewById(R.id.tv_url);
            mIv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
