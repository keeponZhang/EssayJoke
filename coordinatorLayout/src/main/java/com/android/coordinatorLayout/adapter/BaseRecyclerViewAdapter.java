package com.android.coordinatorLayout.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keepon on
 */
public abstract class BaseRecyclerViewAdapter<T,VH extends RecyclerView.ViewHolder>  extends RecyclerView.Adapter<VH>{
	protected Context mContext;

	public List<T> getmDatas() {
		return mDatas;
	}

	protected List<T> mDatas = new ArrayList<>();
	public BaseRecyclerViewAdapter(Context context) {
		mContext = context;
	}
	public BaseRecyclerViewAdapter(Context context, List<T> datas) {
		mContext = context;
		mDatas = datas;
	}

	@NonNull
	@Override
	public final VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		return onCreateMyViewHolder(parent,viewType);
	}

	@Override
	public  final void onBindViewHolder(@NonNull VH holder, int position) {
		onBindMyViewHolder(holder, position);
	}

	@Override
	public  int getItemCount() {
		return mDatas!=null?mDatas.size():0;
	}

	public void updateDatas(List<T> datas) {
		if(mDatas==null){
			mDatas = new ArrayList<>();
		}else{
			mDatas.clear();
		}
		mDatas.addAll(datas);
		notifyDataSetChanged();
	}
	public void addDatas(List<T> datas) {
		mDatas.addAll(datas);
		notifyDataSetChanged();
	}

	public abstract VH onCreateMyViewHolder(@NonNull ViewGroup parent, int viewType) ;
	public abstract void onBindMyViewHolder(@NonNull VH holder, int position);

	public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
		mOnItemClickListener = onItemClickListener;
	}

	public OnItemClickListener<T> mOnItemClickListener;
	public    interface OnItemClickListener<T>{
		void  onItemClick(int positon, T t);
	}

}
