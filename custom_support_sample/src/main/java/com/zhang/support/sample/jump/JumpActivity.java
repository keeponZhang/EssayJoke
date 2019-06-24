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
        titles.add("");
        titles.add("");
        titles.add("CoordinatorLayoutActivity");
        titles.add("CoordinatorLayoutToolBarActivity");
        titles.add(" CoordinatorLayoutAppBarLayoutNoBehaviorActivity  scroll");
        titles.add(" CoordinatorLayoutAppBarLayoutExituntilCollapsedActivity   scroll|enterAlways|exitUntilCollapsed");
        titles.add(" CoordinatorLayoutAppBarLayoutEnterAlwaysCollapsedActivity");
        titles.add("CoordinatorLayoutCollapsingToolbarLayoutActivity");
        titles.add("DarrenBehaviorActivity");

        JumpAdapter mainAdapter = new JumpAdapter(titles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);
    }
}
