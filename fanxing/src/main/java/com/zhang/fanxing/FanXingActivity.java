package com.zhang.fanxing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhang.fanxing.bean.CEO;
import com.zhang.fanxing.bean.Employee;
import com.zhang.fanxing.bean.Manager;

import java.util.ArrayList;
import java.util.List;

public class FanXingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1();
//		test2();
//		test3();


    }

    private void test2() {
        //extends可用于返回类型限定,super用于返回类型限定,只能转成Object，没有意义
        List<? extends Employee> employees = testExtend();
        Employee employee = employees.get(0);
        List<? super Manager> testExtends2 = testExtend2();
        Object object = testExtends2.get(0);
    }

    private void test1() {
        //通配符重点  父类引用指向子类对象（要理解<? super Manager>，? extends Employee这句话是重点）
        //2.1标注：指向赋值的话，包括边界，super的话，可以指向父类
        List<? super Manager> list;
        list = new ArrayList<>();
        list = new ArrayList<Manager>();
        list = new ArrayList<Employee>();
        // list = new ArrayList<CEO>();//编译错误,因为这里不满足父类引用指向子类对象

        ArrayList list3 = new ArrayList<Manager>();
        list3.add(new Employee());

        //super 可以存，存Manager或者Manager的子类对象,因为存Manager或者Manager的子类对象，肯定是满足父类引用指向子类对象的
//		list.add(new Employee());  //
        list.add(new Manager());
        list.add(new CEO());
        //不可以取,因为取出来的是object类型，没有意义
        Object object = list.get(0);
        Manager manager = (Manager) list.get(0);

//2.1标注：指向赋值的话，包括边界，extends的话，可以指向子类
        List<? extends Manager> list2;
        // list2 = new ArrayList<Employee>();
        list2 = new ArrayList<Manager>();
        list2 = new ArrayList<CEO>();
        //extends 不可以存，List<? extends Employee>是未知类型，可能是很低级的子类，所以不能往里面存
		// list2.add(new Employee());//编译错误
		// list2.add(new Manager());//编译错误
		// list2.add(new CEO());//编译错误
        //可以取，因为存的都是Employee的子类，用Employee来接，符合父类引用指向子类对象
        Employee employee = list2.get(0);
        List<? extends Employee> employees = testExtend();
        Employee employee1 = employees.get(0);
//		PECS原则
//		最后看一下什么是PECS（Producer Extends Consumer Super）原则，已经很好理解了：
//
//		频繁往外读取内容的，适合用上界Extends。
//		经常往里插入的，适合用下界Super。
//		总结
//		extends 可用于返回类型限定，不能用于参数类型限定（换句话说：? extends xxx 只能用于方法返回类型限定，jdk能够确定此类的最小继承边界为xxx，只要是这个类的父类都能接收，但是传入参数无法确定具体类型，只能接受null的传入）。
//		super 可用于参数类型限定，不能用于返回类型限定（换句话说：? supper xxx 只能用于方法传参，因为jdk能够确定传入为xxx的子类，返回只能用Object类接收）。
//		? 既不能用于方法参数传入，也不能用于方法返回。

        //如果你既想存，又想取，那就别用通配符
    }

    private List<? extends Employee> testExtend() {
        List<CEO> list2 = new ArrayList<>();
        list2.add(new CEO());
        return list2;
    }

    private List<? super Manager> testExtend2() {
        List<Manager> list2 = new ArrayList<>();
        list2.add(new Manager());
        return list2;
    }

    private void test3() {
        List<? super Manager> list2 = new ArrayList<>();
//		list2.add(new Employee());
        list2.add(new CEO());
        testSuper(list2);
//		testSuper2(list2);
    }

    private void testSuper(List<? super Manager> list) {
		list.add(new CEO());
        Log.e("TAG", "testSuper: ");
    }

    private void testSuper2(List<? extends Manager> list) {
    	//super是不能往里面加的
		// list.add(new Manager());
    }
}
