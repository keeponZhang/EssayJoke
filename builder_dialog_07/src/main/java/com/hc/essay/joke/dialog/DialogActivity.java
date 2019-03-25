package com.hc.essay.joke.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hc.essay.joke.R;


public class DialogActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test_tv).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        // 前提你自己一定能够写出这些效果

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setContentView(R.layout.detail_comment_dialog)
                .formBottom(true).fullWidth().show();


        // dialog去操作点击事件
        final EditText commentEt = dialog.getView(R.id.comment_editor);

        // 特有操作对象  getView方法   ListView  RecyclerView  CheckBox

        dialog.setOnclickListener(R.id.submit_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogActivity.this,
                        commentEt.getText().toString().trim(), Toast.LENGTH_LONG).show();
            }
        });

        // 弹出软键盘，网上找找


        /*BaseDialog dialog = new BaseDialog.Builder(this)
                .setContentView(R.layout.detail_comment_dialog)
                .fullWith().fromBottom(true)
                .show();*/
        // Okhttp  + RXjava + retrofit  方式叫做链式调用，并非都是Builder设计模式
    }
}

