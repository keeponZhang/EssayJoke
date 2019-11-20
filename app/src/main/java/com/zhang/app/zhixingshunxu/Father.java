package com.zhang.app.zhixingshunxu;

class Father extends Grantfather{
    public String name = "father";
    //静态成员变量 f_staticMember
    static F_StaticMember f_staticMember = new F_StaticMember();
    //实例成员变量f_member
    F_Member f_member = new F_Member();
 
    static {
        System.out.println("Father类，static静态代码块");
    }
 
    {
        System.out.println("Father类，构造代码块");
    }
    Father(){
//        super();
//        super.name = "txx";
        super("txx");
        //super(20);
        System.out.println("Father类，无参构造方法,name = "+name);
    }
    Father(int age){
        super();
        super.age = age;//此处将修改Grantfather类的age，则Father类的age也改变
        System.out.println("Father类，有参构造方法,name = "+name);
        System.out.println("Father类，有参构造方法,age = "+age);
    }
}
