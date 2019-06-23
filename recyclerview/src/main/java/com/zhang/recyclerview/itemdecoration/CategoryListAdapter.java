package com.android.recyclerview.itemdecoration;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.android.recyclerview.R;
import com.android.recyclerview.itemdecoration.bean.FollowerBean;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private List<FollowerBean> mList = new ArrayList<>();
    private Context            mContext;
    private LayoutInflater     mInflater;

    public CategoryListAdapter(Context context) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public CategoryListAdapter(Context context, List<FollowerBean> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 创建条目ViewHolder
     *
     * @param parent   RecyclerView
     * @param viewType view的类型可以用来显示多列表布局等等
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建条目
        View itemView = mInflater.inflate(R.layout.item_recyclerview, parent, false);
        // 创建ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    /**
     * 绑定ViewHolder设置数据
     *
     * @param holder
     * @param position 当前位置
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 设置绑定数据
        FollowerBean item = mList.get(position);
        holder.mTvName.setText(item.getLogin());
        holder.mTvUrl.setText(item.getUrl());
        // 加载图片
        Glide.with(mContext).load(item.getAvatar_url()).centerCrop().into(holder.mIv);
    }

    /**
     * 总共有多少条数据
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * RecyclerView的Adapter需要一个ViewHolder必须要extends RecyclerView.ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  mTvName;
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
