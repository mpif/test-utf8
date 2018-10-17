package com.codefans.java.access.test;

/**
 * Created by Administrator on 2017/2/25.
 */
public class PublicClass {

    public static void main(String[] args) {
        PublicClass pc = new PublicClass();
        pc.print(); //public可以在同一个类中访问



    }

    /**
     * 可以在本类，同一个包的类，子类，所有地方都可以访问
     */
    public void print() {
        System.out.println("public method access");
    }
}
