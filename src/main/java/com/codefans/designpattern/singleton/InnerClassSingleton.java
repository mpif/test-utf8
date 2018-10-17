package com.codefans.designpattern.singleton;

/**
 * Created by Administrator on 2017/2/25.
 * 利用了classloader的机制来保证初始化instance时只有一个线程，所以也是线程安全的，同时没有性能损耗
 * 既实现了线程安全，又避免了同步带来的性能影响
 */
public class InnerClassSingleton {

    private InnerClassSingleton() {

    }

    private static class LazyHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    public static final InnerClassSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private String name = new String("InnerClassSingleton");
    public String getName() {
        return name;
    }


}
