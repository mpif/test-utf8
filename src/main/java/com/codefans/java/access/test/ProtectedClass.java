package com.codefans.java.access.test;

/**
 * Created by Administrator on 2017/2/25.
 */
public class ProtectedClass {

    public static void main(String[] args) {

        PrivateClass pc = new PrivateClass();
//        pc.privateMethod(); //private不能被同一个包下的类访问

        DefaultMethod dm = new DefaultMethod();
        dm.print(); //默认访问权限，可以被同个包下面的类访问

        PublicClass publicClass = new PublicClass();
        publicClass.print(); //public可以在同一个包下的类中访问


    }

    protected void protectedMethod() {
        System.out.println("protected method access.");
    }
}
