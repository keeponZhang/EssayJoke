package com.zhang.app.aop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhang.app.R;

/**
 * createBy keepon
 */
public class AopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop);
        findViewById(R.id.testAop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testaop();
            }
        });
    }

    public void testaop(){
        Log.e("TAG", "MainActivity testAop:");
        Toast.makeText(this,"MainActivity testAop",Toast.LENGTH_LONG).show();
        testaop1();
    }

    public void testaop1() {
        System.out.println("MainActivity testAop1 ");
    }
}
