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
        titles.add("CoordinatorLayoutDepencyActivity");
        titles.add("CoordinatorLayoutToolBarActivity");
        titles.add(" AppBarLayoutNoBehaviorActivity  scroll|enterAlways");
        titles.add(" AppBarLayoutExituntilCollapsedActivity   scroll|enterAlways|exitUntilCollapsed 值设为exitUntilCollapsed的View，当这个View要往上逐渐“消逝”时，会一直往上滑动，直到剩下的的高度达到它的最小高度后，再响应ScrollView的内部滑动事件");
        titles.add(" AppBarLayoutEnterAlwaysCollapsedActivity  scroll|enterAlways|enterAlwaysCollapsed 这里涉及到Child View的高度和最小高度，向下滚动时，Child View先向下滚动最小高度值，然后Scrolling View开始滚动，到达边界时，Child View再向下滚动，直至显示完全");
        titles.add("CollapsingToolbarLayoutActivity");
        titles.add("DarrenBehaviorActivity");
        titles.add("DetailActivity");
        titles.add("TestFragmentActivity");
        titles.add("FragmentPagerAdapterBugActivity");
        titles.add("FixPagerAdapterBugActivity");

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
