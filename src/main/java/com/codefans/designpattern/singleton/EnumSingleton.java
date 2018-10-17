package com.codefans.designpattern.singleton;

/**
 * Created by Administrator on 2017/2/25.
 * 这个优秀的思想直接源于Joshua Bloch的《Effective Java》（《Java高效编程指南》）。
 * 这里有几个原因关于为什么在Java中宁愿使用一个枚举量来实现单例模式：
 1、 自由序列化；
 2、 保证只有一个实例（即使使用反射机制也无法多次实例化一个枚举量）；
 3、 线程安全；
 */
public enum EnumSingleton {

    instance;

    private EnumSingleton() {

    }

    private String name = new String("hello world");
    public String getName() {
        return name;
    }

}
