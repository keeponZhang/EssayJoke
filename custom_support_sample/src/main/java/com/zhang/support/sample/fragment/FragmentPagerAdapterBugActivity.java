package com.zhang.support.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zhang.support.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * createBy	 keepon
 */
public class FragmentPagerAdapterBugActivity extends AppCompatActivity {
    private static final int INCREASE = 4;
    private FPAdapter mFPAdapter;
    private List<String> mTitles;
    private int mGroup = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_update);
        TextView updateTv = (TextView) findViewById(R.id.tv_update);
        updateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFragments();
            }
        });
        initFPAFragments();
    }

    private void initFPAFragments() {
        mTitles = new ArrayList<>();
        for (int i = 0; i < INCREASE; i++) {
            mTitles.add("index=" + i + ",group=0");
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_content);
        mFPAdapter = new FPAdapter(getSupportFragmentManager(), mTitles);
        viewPager.setAdapter(mFPAdapter);
    }

    private void updateFragments() {
        mTitles.clear();
        mGroup++;
        for (int i = 0; i < INCREASE; i++) {
            mTitles.add("index=" + i + ",group=" + mGroup);
        }
        mFPAdapter.notifyDataSetChanged();
    }

    private class FPAdapter extends FragmentPagerAdapter {

        private List<String> mTitles;

        public FPAdapter(FragmentManager fm, List<String> titles) {
            super(fm);
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("LogcatFragment", "get Item from FPAdapter, position=" + position);
            return BaseTestFragment.newInstance(mTitles.get(position));
        }

        @Override
        public int getCount() {
            Log.e("TAG", "FPAdapter getCount  mTitles.size():"+ mTitles.size());
            return mTitles.size();
        }

    }
}
