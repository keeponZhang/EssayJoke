package com.hc.essay.joke.navigationbar;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/2/26.
 * Version 1.0
 * Description: 导航条的规范
 */
public interface INavigationBar {

    // 头部的规范,inflate到一个parent中
    public int bindLayoutId();


    // 绑定头部的参数，标题点击事件啥的
    public void applyView();
}
