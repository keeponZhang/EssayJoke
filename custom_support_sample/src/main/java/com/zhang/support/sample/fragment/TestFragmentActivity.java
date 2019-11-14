package com.zhang.support.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.zhang.support.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * createBy	 keepon
 */
public class TestFragmentActivity extends AppCompatActivity {
    @BindView(R.id.addFragment)
    Button mAddFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
        ButterKnife.bind(this);
        addFragment1();
    }

    @OnClick(R.id.addFragment)
    public void onClick() {
        addFragment1();
    }

    private void addFragment1() {
        Fragment1 f1 = new Fragment1();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fl为占位布局
        fragmentTransaction.add(R.id.container, f1);
        fragmentTransaction.commit();
    }
}
