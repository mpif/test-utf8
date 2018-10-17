package com.codefans.designpattern.singleton;

/**
 * Created by Administrator on 2017/2/25.
 */
public class HungrySingleton {

    private HungrySingleton() {

    }

    private static final HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }

    private String name = new String("HungrySingleton");
    public String getName() {
        return name;
    }

}
