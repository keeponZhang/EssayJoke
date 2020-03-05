package com.hc.baselibrary.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hc.baselibrary.R;

import java.util.ArrayList;
import java.util.List;

abstract public class BaseJumpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_jump);
        initData();
    }

    protected void initData() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        List<String> titles = new ArrayList<>();
        setData(titles);
        JumpAdapter mainAdapter = new JumpAdapter(titles);
        mainAdapter.setOnItemClickListener(new JumpAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                onRecyclerviewItemClick(view,position);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);
    }

    abstract protected void onRecyclerviewItemClick(View view,int position);

    abstract protected void setData(List<String> titles);
}