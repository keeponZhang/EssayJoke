package com.hc.essay.joke.db;

import android.view.View;
import android.widget.Toast;

import com.hc.baselibrary.base.BaseActivity;
import com.hc.essay.joke.R;

import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void initData() {

        // 1. 为什么用Factory  目前的数据是在  内存卡中  有时候我们需要放到 data/data/xxx/database
        // 获取的Factory不一样那么写入的位置   是可以不一样的

        // 2. 面向接口编程，获取IDaoSoupport 那么不需要关心实现 ，目前的实现 是我们自己写的，方便以后使用第三方的

        // 3. 就是为了高扩展

        // 强调一下  科学教学 ， 反射  泛型  设计模式  单个单个讲   后面不断结合（设计模式 + 泛型 + 反射）
        // 特别注意一定要敲

        IDaoSoupport<Person> daoSoupport = DaoSupportFactory.getFactory().getDao(Person.class);

        // 最少的知识原则 插入是   对象
        daoSoupport.insert(new Person("darren",23));

        /*List<Person> persons = new ArrayList<>();


        for (int i=0;i<5000;i++){
            persons.add(new Person("darren",22+i));
        }

        // 功能都能实现 主要还是在优化
        long startTime = System.currentTimeMillis();
        // daoSoupport.insert(persons);
        DataSupport.saveAll(persons);
        long endTime = System.currentTimeMillis();

        Log.e("TAG","time -> "+(endTime - startTime));

        Toast.makeText(this,"time -> "+(endTime - startTime),Toast.LENGTH_LONG).show();*/

        // 第三方的 Litpal  比较方便的
        // Darren: 8328ms  833ms  最后优化  729ms  636ms  671ms
        // litepal: 471ms  1343ms 最后优化  1010ms 1505ms 1463ms


        List<Person> persons = daoSoupport.query();
        // 28010
        Toast.makeText(this, "count -> " + persons.size(), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void initView() {
        // 初始化 View
        viewById(R.id.test_tv).setOnClickListener(this);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {

    }
}

