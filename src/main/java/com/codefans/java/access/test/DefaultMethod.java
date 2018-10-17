package com.codefans.java.access.test;

/**
 * Created by Administrator on 2017/2/25.
 */
public class DefaultMethod {

    public static void main(String[] args) {
        DefaultMethod dm = new DefaultMethod();
        dm.print(); //默认访问权限, 可以在本类中访问

        ProtectedClass pc = new ProtectedClass();
        pc.protectedMethod(); //protected可以被同一个包下的类访问

    }

    /**
     * 默认的访问权限，只能在本类或者同一个包下的类访问
     */
    void print() {
        System.out.println("default access");
    }
}
