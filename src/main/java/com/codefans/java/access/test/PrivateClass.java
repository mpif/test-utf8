package com.codefans.java.access.test;

/**
 * Created by Administrator on 2017/2/25.
 */
public class PrivateClass {

    public static void main(String[] args) {
        PrivateClass pc = new PrivateClass();
        pc.privateMethod();


    }

    /**
     * 私有访问权限, 只能在所在类被访问
     */
    private void privateMethod() {
        System.out.println("private method access.");
    }

}
