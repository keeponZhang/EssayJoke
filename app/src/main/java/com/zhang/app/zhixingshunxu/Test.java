package com.zhang.app.zhixingshunxu;

public class Test{
    public static void main(String[] args){
        System.out.println("=======================第 1 次开始执行==================================");
//        Grantfather grantfather = new Grantfather();
 
        System.out.println("=======================第 2 次开始执行==================================");
//        Grantfather grantfather2 = new Grantfather();
 
        System.out.println("=======================第 3 次开始执行==================================");
//        Grantfather grantfather3 = new Grantfather("carol");
 
        System.out.println("=======================第 4 次开始执行==================================");
//        Grantfather grantfather4 = new Grantfather(20);
//        System.out.println("Grantfather类，age = "+grantfather4.age);
//        System.out.println("Grantfather类，name = "+grantfather4.name);
 
        System.out.println("=======================第 5 次开始执行==================================");
        Father father = new Father();
 
//        System.out.println("=======================第 6 次开始执行==================================");
//        Father father2 = new Father(99);
//        System.out.println("Father类，age = "+father2.age);
//
//        System.out.println("=======================第 7 次开始执行==================================");
//        Son son = new Son();
//
//        System.out.println("========================第 8 次开始执行=================================");
//        Grantfather grantfather5 = new Grantfather();
//        System.out.println("Grantfather类，name = "+grantfather5.name);
    }
}

//        =======================第 1 次开始执行==================================
//        [初始化G_StaticMember静态成员变量]
//        Grantfather类，static静态代码块
//        [初始化G_Member实例成员变量]
//        Grantfather类，构造代码块
//        Grantfather类，无参构造方法,name = grantfather

//说明一个类中，先静态成员，在静态代码块，然后成员变量，再构造代码块，然后构造方法。
//        =======================第 2 次开始执行==================================
//        [初始化G_Member实例成员变量]
//        Grantfather类，构造代码块
//        Grantfather类，无参构造方法,name = grantfather

//说明静态的只执行一次
//        =======================第 3 次开始执行==================================
//        [初始化G_Member实例成员变量]
//        Grantfather类，构造代码块
//        Grantfather类，有参构造方法,name = carol
//同第二次执行，构造代码块在构造方法前，每次创建新对象都会调用
//        =======================第 4 次开始执行==================================
//        [初始化G_Member实例成员变量]
//        Grantfather类，构造代码块
//        Grantfather类，有参构造方法,age = 20
//        Grantfather类，age = 18
//        Grantfather类，name = carol
//        =======================单独第 5 次开始执行==================================
//        [初始化G_StaticMember静态成员变量]
//        Grantfather类，static静态代码块
//        [初始化F_StaticMember静态成员变量]
//        Father类，static静态代码块
//        [初始化G_Member实例成员变量]
//        Grantfather类，构造代码块
//        Grantfather类，有参构造方法,name = txx
//        [初始化F_Member实例成员变量]
//        Father类，构造代码块
//        Father类，无参构造方法,name = father

//说明如果子父类，先执行父类静态成员变量，再执行父类静态代码块，再执行子类静态成员变量，子类静态代码块，
//然后父类成员变量，父类构造代码块，父类构造函数，然后子类成员变量，子类构造代码块，子类构造函数
//        =======================第 5 次开始执行==================================
//        [初始化F_StaticMember静态成员变量]
//        Father类，static静态代码块
//        [初始化G_Member实例成员变量]
//        Grantfather类，构造代码块
//        Grantfather类，有参构造方法,name = txx
//        [初始化F_Member实例成员变量]
//        Father类，构造代码块
//        Father类，无参构造方法,name = father
//同单独第5次对比，静态只会执行一次（静态成员变量和静态代码块）
//        =======================第 6 次开始执行==================================
//        [初始化G_Member实例成员变量]
//        Grantfather类，构造代码块
//        Grantfather类，无参构造方法,name = txx
//        [初始化F_Member实例成员变量]
//        Father类，构造代码块
//        Father类，有参构造方法,name = father
//        Father类，有参构造方法,age = 99
//        Father类，age = 99
//        =======================第 7 次开始执行==================================
//        [初始化G_Member实例成员变量]
//        Grantfather类，构造代码块
//        Grantfather类，有参构造方法,name = txx
//        [初始化F_Member实例成员变量]
//        Father类，构造代码块
//        Father类，无参构造方法,name = father
//        Son类，无参构造方法
//        Son类，name = father
//        Son类，age = 18
//        Son类，name = father
//        Son类，age = 18
//        ========================第 8 次开始执行=================================
//        [初始化G_Member实例成员变量]
//        Grantfather类，构造代码块
//        Grantfather类，无参构造方法,name = txx
//        Grantfather类，name = txx
