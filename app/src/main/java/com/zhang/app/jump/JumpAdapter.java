package com.android.app.jump;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.R;
import com.android.app.banner.BannerActivity;
import com.android.app.indicator.IndicatorActivity;
import com.android.coordinatorLayout.CoordinatorLayoutActivity;
import com.android.coordinatorLayout.CoordinatorLayoutAppBarLayoutEnterAlwaysCollapsedActivity;
import com.android.coordinatorLayout.CoordinatorLayoutAppBarLayoutExituntilCollapsedActivity;
import com.android.coordinatorLayout.CoordinatorLayoutAppBarLayoutNoBehaviorActivity;
import com.android.coordinatorLayout.CoordinatorLayoutCollapsingToolbarLayoutActivity;
import com.android.coordinatorLayout.CoordinatorLayoutToolBarActivity;
import com.android.coordinatorLayout.darren.DarrenBehaviorActivity;
import com.android.recyclerview.itemdecoration.ItemDecorationActivity;

import java.util.ArrayList;
import java.util.List;

public class JumpAdapter extends RecyclerView.Adapter<JumpAdapter.MainViewHolder> {

    private List<String> mTitles = new ArrayList<>();


    public JumpAdapter(List<String> titles) {
        mTitles = titles;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, parent, false);
        return new MainViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        final String title = mTitles.get(position);
        TextView titleView = holder.mTvTitle;
        titleView.setText(title);
        titleView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                switch (position) {
                    case 0:
                        Utils.startActivity(v.getContext(), IndicatorActivity.class);
                        break;
                    case 1:
                        Utils.startActivity(v.getContext(), BannerActivity.class);
                        break;
                    case 2:
                        Utils.startActivity(v.getContext(), ItemDecorationActivity.class);
                        break;
                    case 3:
                        Utils.startActivity(v.getContext(), CoordinatorLayoutActivity.class);
                        break;
                    case 4:
                        Utils.startActivity(v.getContext(), CoordinatorLayoutToolBarActivity.class);
                        break;
                    case 5:
                        Utils.startActivity(v.getContext(), CoordinatorLayoutAppBarLayoutNoBehaviorActivity.class);
                        break;
                    case 6:
                        Utils.startActivity(v.getContext(), CoordinatorLayoutAppBarLayoutExituntilCollapsedActivity.class);
                        break;
                    case 7:
                        Utils.startActivity(v.getContext(), CoordinatorLayoutAppBarLayoutEnterAlwaysCollapsedActivity.class);

                        break;
                    case 8:
                        Utils.startActivity(v.getContext(), CoordinatorLayoutCollapsingToolbarLayoutActivity.class);
                        break;
                    case 9:
                        Utils.startActivity(v.getContext(), DarrenBehaviorActivity.class);
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
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

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        public TextView mTvTitle;

        public MainViewHolder(View rootView) {
            super(rootView);
            mTvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        }
    }
}