package com.zhang.app.zhixingshunxu;

class Grantfather {
    //静态成员变量g_staticMember
    static G_StaticMember g_staticMember = new G_StaticMember();
    //实例成员变量g_member
    G_Member g_member = new G_Member();
    //静态成员变量name
    public static String name = "grantfather";
    //实例成员变量age
    public int age = 18;
 
    static {
        System.out.println("Grantfather类，static静态代码块");
    }
 
    {
        System.out.println("Grantfather类，构造代码块");
    }
    Grantfather() {
 
        System.out.println("Grantfather类，无参构造方法,name = "+ name);
    }
 
    Grantfather(String name){
        this.name = name; //如果没有将传入的参数赋值给本类的name，则默认输出是传入的参数值，而非本类静态name值
        System.out.println("Grantfather类，有参构造方法,name = "+name);
    }
 
    Grantfather(int age){
        System.out.println("Grantfather类，有参构造方法,age = "+age);//默认输出是传入的参数值，而非本类age
    }
}
