package com.zhang.support.sample;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhang.support.sample.adapter.SwipeRecyclerviewAdapter;

import java.util.ArrayList;
import java.util.Random;

public class SwipeRefreshActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRecyclerviewAdapter swipeRecyclerviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refreshlayout);
        swipeRecyclerviewAdapter = new SwipeRecyclerviewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(swipeRecyclerviewAdapter);
        loadData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }
    private void loadData() {
        swipeRefreshLayout.setRefreshing(true);
        final ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int i1 = new Random().nextInt(100);
            datas.add("this is test: " + i1);
        }
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRecyclerviewAdapter.updateDatas(datas);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }
}
