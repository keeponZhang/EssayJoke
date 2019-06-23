package com.android.recyclerview.itemhelper;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.android.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.android.recyclerview.itemdecoration.bean.FollowerBean;

import java.util.List;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_DRAG;
import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_IDLE;

public  class DragCallBack extends ItemTouchHelper.Callback{
       RecyclerView rv;
       RecyclerView.ViewHolder lastDragViewHolder;
       public DragCallBack(RecyclerView rv){
           this.rv = rv;
       }

       /**
        * 当用户长按后，会触发拖拽的选中效果，viewHolder就是当前的选中
        * @param viewHolder
        * @param actionState 取下面中的值
        *                    {@link ItemTouchHelper#ACTION_STATE_IDLE},
        *                    {@link ItemTouchHelper#ACTION_STATE_SWIPE},
        *                    {@link ItemTouchHelper#ACTION_STATE_DRAG}.
        */
       @Override
       public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
           super.onSelectedChanged(viewHolder, actionState);
           //如果状态为拖拽，说明选中了
           //我在xml里面写的scale都为0.8 我们需要把当前的视图放大一下，所以设置为1就可以了
           if (viewHolder != null && actionState ==  ACTION_STATE_DRAG){
               lastDragViewHolder = viewHolder;
               viewHolder.itemView.setScaleX(1.1f);
               viewHolder.itemView.setScaleY(1.1f);
           }

           //ACTION_STATE_IDLE就是松开了，把大小改为原状
           if (lastDragViewHolder != null && actionState == ACTION_STATE_IDLE){
               lastDragViewHolder.itemView.setScaleX(1.0F);
               lastDragViewHolder.itemView.setScaleY(1.0F);
               lastDragViewHolder = null;
               //是为了实现RecyclerView自动回位的，实现在后面给出
               ensurePositionV1(rv);
           }
       }

       /**
        * 我们不需要滑动删除，所以返回false
        * @return
        */
       @Override
       public boolean isItemViewSwipeEnabled() {
           return false;
       }

       /**
        * 这个是用来设置用户可以对 viewHolder进行什么操作，推荐用makeMovementFlags(int dragFlags, int swipeFlags)来处理
        * 例如 makeMovementFlags(UP | DOWN, LEFT);就是可以上下拖拽，向左滑动
        * @param recyclerView
        * @param viewHolder
        * @return
        */
       @Override
       public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
           int position = viewHolder.getAdapterPosition();
           RecyclerView.Adapter adapter = (RecyclerView.Adapter) recyclerView.getAdapter();
           if (position == adapter.getItemCount() - 1){
               return 0;
           }
           return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,-1);
       }

       /**
        * 这个是拖拽的回调
        * @param recyclerView
        * @param viewHolder  这个是我们拖拽中的ViewHolder
        * @param target      这个是离我们拖拽ViewHolder最近的ViewHolder，也就是我们松手后需要替换的ViewHolder
        * @return  返回true的话 我们已经做好相关操作了，false就我们没做啥操作
        */
       @Override
       public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
           //把当前的和目标viewHoder提取出来换位置
           RecyclerView.Adapter adapter = (RecyclerView.Adapter) recyclerView.getAdapter();
           if(adapter instanceof BaseRecyclerViewAdapter){
               BaseRecyclerViewAdapter baseRecyclerViewAdapter = (BaseRecyclerViewAdapter) adapter;
               List list = baseRecyclerViewAdapter.getmDatas();
               int currentPos = viewHolder.getAdapterPosition();
               int targetPos  = target.getAdapterPosition();
               FollowerBean currentBean = (FollowerBean) list.get(currentPos);
               FollowerBean targetBean= (FollowerBean) list.get(targetPos);
               list.set(currentPos,targetBean);
               list.set(targetPos,currentBean);
               //最后还要通知下adapter
               adapter.notifyItemMoved(currentPos,targetPos);
           }

           return true;
       }

       @Override
       public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
           //这个是滑动的回调，这次关注拖拽
       }

       /**
        * 用来判断 target是否可以被替换
        * @param recyclerView
        * @param current
        * @param target
        * @return  true :target可以被current替换
        *          false：不可以
        */
       @Override
       public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
           //汽车之家最后一个为加号，所以不支持拖拽
           if (target.getAdapterPosition() == recyclerView.getAdapter().getItemCount() -1){
               return false;
           }
           return super.canDropOver(recyclerView, current, target);
       }

    /**
     * 确保第一个item自动回位，第一版
     * @param recyclerView
     */
    public static void ensurePositionV1(RecyclerView recyclerView){
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisible = layoutManager.findFirstVisibleItemPosition();
        View firstVisibleView = layoutManager.findViewByPosition(firstVisible);
        int left  = firstVisibleView.getLeft();
        int width = firstVisibleView.getWidth();
        //left就是这个item被RecyclerView裁剪掉了多少
        //如果第一个可视item的左边界在RecyclerView内左边线的右边则 left是正值，
        //如果在在RecyclerView内左边线的左边则为负值
        //如果滑动超过一定距离，就滚动到下个item去，我这里取一半多一点点，
        if (Math.abs(left) > width * 0.6){
            recyclerView.getScrollY();
            layoutManager.scrollToPositionWithOffset(firstVisible+1,0);
        }else {
            layoutManager.scrollToPositionWithOffset(firstVisible,0);
        }
    }
   }