package com.android.coordinatorLayout.adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.android.coordinatorLayout.R;
import com.android.coordinatorLayout.recyclerview.manager.CoverFlowLayoutManager;

import java.util.ArrayList;

public class CoverFolowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_folow);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            datas.add("第" + i + "个item");
        }
        CoverFlowAdapter coverFlowAdapter = new CoverFlowAdapter(this, datas);
        recyclerView.setLayoutManager(new CoverFlowLayoutManager());
        recyclerView.setAdapter(coverFlowAdapter);

    }
}
