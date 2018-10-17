package com.codefans.designpattern.singleton;

/**
 * Created by Administrator on 2017/2/25.
 */
public class LazySingleton {

    private LazySingleton() {

    }

    private static LazySingleton instance;

    public static LazySingleton getInstance() {
        if(instance == null) {
            synchronized(LazySingleton.class) {
                if(instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    private String name = new String("LazySingleton");
    public String getName() {
        return name;
    }


}
