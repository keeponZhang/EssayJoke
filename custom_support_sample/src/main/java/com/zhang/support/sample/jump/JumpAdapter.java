package com.zhang.support.sample.jump;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.coordinatorLayout.AppBarLayoutEnterAlwaysCollapsedActivity;
import com.android.coordinatorLayout.AppBarLayoutExituntilCollapsedActivity;
import com.android.coordinatorLayout.AppBarLayoutExituntilCollapsedRecyclerviewActivity;
import com.android.coordinatorLayout.CoordinatorLayoutDepencyActivity;
import com.android.coordinatorLayout.AppBarLayoutNoBehaviorActivity;
import com.android.coordinatorLayout.CollapsingToolbarLayoutActivity;
import com.android.coordinatorLayout.CoordinatorLayoutToolBarActivity;
import com.android.coordinatorLayout.DetailActivity;
import com.android.coordinatorLayout.adapter.CoverFolowActivity;
import com.android.coordinatorLayout.darren.DarrenBehaviorActivity;
import com.hc.baselibrary.base.BaseRecyclerViewAdapter;
import com.zhang.support.sample.R;
import com.zhang.support.sample.SwipeRefreshActivity;
import com.zhang.support.sample.fragment.FixPagerAdapterBugActivity;
import com.zhang.support.sample.fragment.FragmentPagerAdapterBugActivity;
import com.zhang.support.sample.fragment.TestFragmentActivity;

import java.util.List;

public class JumpAdapter extends BaseRecyclerViewAdapter<String,JumpAdapter.MainViewHolder> {


    public JumpAdapter(Context context) {
        super(context);
    }

    public JumpAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    private static final String TAG = "JumpAdapter";
    private int mCreatedHolder=0;
    @Override
    public MainViewHolder onCreateMyViewHolder(@NonNull ViewGroup parent, int viewType) {
        mCreatedHolder++;
        Log.d(TAG, "onCreateViewHolder  num:"+mCreatedHolder);
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_layout_news_item, parent, false);
        return new MainViewHolder(rootView);
    }

    @Override
    public void onBindMyViewHolder(@NonNull final MainViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position ="+position);
        final String title = mDatas.get(position);
        TextView titleView = holder.mTvTitle;
        titleView.setText(title);
        titleView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                switch (position) {
                    case 0:
                        Utils.startActivity(v.getContext(), SwipeRefreshActivity.class);
                        break;
                    case 1:
//                        Utils.startActivity(v.getContext(), "AppBarLayoutExituntilCollapsedRecyclerviewActivity");
                        Utils.startActivity(v.getContext(), AppBarLayoutExituntilCollapsedRecyclerviewActivity.class);
                        break;
                    case 2:
//                        Utils.startActivity(v.getContext(), "CoverFolowActivity");
                        Utils.startActivity(v.getContext(), CoverFolowActivity.class );
                        break;
                    case 3:
//                        Utils.startActivity(v.getContext(), "CoordinatorLayoutDepencyActivity");
                        Utils.startActivity(v.getContext(), CoordinatorLayoutDepencyActivity.class);
                        break;
                    case 4:
//                        Utils.startActivity(v.getContext(),"CoordinatorLayoutToolBarActivity" );
                        Utils.startActivity(v.getContext(), CoordinatorLayoutToolBarActivity.class );
                        break;
                    case 5:
//                        Utils.startActivity(v.getContext(),"AppBarLayoutNoBehaviorActivity" );
                        Utils.startActivity(v.getContext(), AppBarLayoutNoBehaviorActivity.class );
                        break;
                    case 6:
//                        Utils.startActivity(v.getContext(), "AppBarLayoutExituntilCollapsedActivity");
                        Utils.startActivity(v.getContext(), AppBarLayoutExituntilCollapsedActivity.class);
                        break;
                    case 7:
//                        Utils.startActivity(v.getContext(),"AppBarLayoutEnterAlwaysCollapsedActivity" );
                        Utils.startActivity(v.getContext(), AppBarLayoutEnterAlwaysCollapsedActivity.class );

                        break;
                    case 8:
//                        Utils.startActivity(v.getContext(), "CollapsingToolbarLayoutActivity");
                        Utils.startActivity(v.getContext(), CollapsingToolbarLayoutActivity.class);
                        break;
                    case 9:
//                        Utils.startActivity(v.getContext(), "DarrenBehaviorActivity");
                        Utils.startActivity(v.getContext(), DarrenBehaviorActivity.class);
                        break;
                    case 10:
//                        Utils.startActivity(v.getContext(),"DetailActivity");
                        Utils.startActivity(v.getContext(), DetailActivity.class);
                        break;
                    case 11:
                        Utils.startActivity(v.getContext(), TestFragmentActivity.class);
                        break;
                    case 12:
                        Utils.startActivity(v.getContext(), FragmentPagerAdapterBugActivity.class);
                        break;
                    case 13:
                        Utils.startActivity(v.getContext(), FixPagerAdapterBugActivity.class);
                        break;
                    case 14:
                        break;
                    case 15:
                        break;
                    case 16:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        public TextView mTvTitle;

        public MainViewHolder(View rootView) {
            super(rootView);
            mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        }
    }
}