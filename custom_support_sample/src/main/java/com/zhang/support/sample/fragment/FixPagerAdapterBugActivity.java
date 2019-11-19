package com.zhang.support.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zhang.support.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * createBy	 keepon
 */
public class FixPagerAdapterBugActivity extends AppCompatActivity {
    private static final int INCREASE = 4;
    private FixedPagerAdapter mFixedPagerAdapter;
    private List<String> mTitles;

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
        initFragments();
    }

    private void initFragments() {
        mTitles = new ArrayList<>();
        for (int i = 0; i < INCREASE; i++) {
            mTitles.add(String.valueOf(i));
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_content);
        mFixedPagerAdapter = new MyFixedPagerAdapter(getSupportFragmentManager(), mTitles);
        viewPager.setAdapter(mFixedPagerAdapter);
    }

    private void updateFragments() {
        mTitles.clear();
        mTitles.add("3");
        mTitles.add("2");
        mFixedPagerAdapter.notifyDataSetChanged();
    }

    private class MyFixedPagerAdapter extends FixedPagerAdapter<String> {

        private List<String> mTitles;

        public MyFixedPagerAdapter(FragmentManager fragmentManager, List<String> titles) {
            super(fragmentManager);
            mTitles = titles;
        }

        @Override
        public String getItemData(int position) {
            return mTitles.size() > position ? mTitles.get(position) : null;
        }

        @Override
        public int getDataPosition(String s) {
            return mTitles.indexOf(s);
        }

        @Override
        public boolean equals(String oldD, String newD) {
            return TextUtils.equals(oldD, newD);
        }

        @Override
        public Fragment getItem(int position) {
            return BaseTestFragment.newInstance(mTitles.get(position));
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }
    }

}
