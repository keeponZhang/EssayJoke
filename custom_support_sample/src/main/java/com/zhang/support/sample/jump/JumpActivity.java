package com.zhang.support.sample.jump;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhang.support.sample.R;

import java.util.ArrayList;
import java.util.List;

public class JumpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        initData();
    }
    private void initData() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        List<String> titles = new ArrayList<>();
        titles.add("SwipeRefreshActivity");
        titles.add("AppBarLayoutExituntilCollapsedRecyclerviewActivity");
        titles.add("CoverFolowActivity");
        titles.add("CoordinatorLayoutActivity");
        titles.add("CoordinatorLayoutToolBarActivity");
        titles.add(" CoordinatorLayoutAppBarLayoutNoBehaviorActivity  scroll");
        titles.add(" CoordinatorLayoutAppBarLayoutExituntilCollapsedActivity   scroll|enterAlways|exitUntilCollapsed");
        titles.add(" CoordinatorLayoutAppBarLayoutEnterAlwaysCollapsedActivity");
        titles.add("CoordinatorLayoutCollapsingToolbarLayoutActivity");
        titles.add("DarrenBehaviorActivity");
        titles.add("DetailActivity");
        titles.add("TestFragmentActivity");
        titles.add("FragmentPagerAdapterBugActivity");

        for (int i = 0; i < 50; i++) {
            titles.add("test=" + i);
        }
        JumpAdapter mainAdapter = new JumpAdapter(this,titles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new CustomLayoutManager());
//           recyclerView.setLayoutManager(new CustomLayoutManagerRecyclered());
//        recyclerView.setLayoutManager(new CustomLayoutManagerRecyclered1());
//        recyclerView.setLayoutManager(new CustomLayoutManagerRecyclered2());
//        recyclerView.addItemDecoration(new LinearItemDecoration2());
//        recyclerView.addItemDecoration(new LinearItemDecoration());
        recyclerView.setAdapter(mainAdapter);
    }
}
