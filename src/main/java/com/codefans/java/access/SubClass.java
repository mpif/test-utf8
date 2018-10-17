package com.codefans.java.access;

import com.codefans.java.access.test.DefaultMethod;
import com.codefans.java.access.test.ProtectedClass;
import com.codefans.java.access.test.PublicClass;

/**
 * Created by Administrator on 2017/2/25.
 */
public class SubClass extends ProtectedClass {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.protectedMethod(); //protected可以被子类访问

        ProtectedClass protectedClass = new ProtectedClass();
//        protectedClass.protectedMethod(); //报错:has protected access in ...

        DefaultMethod dm = new DefaultMethod();
//        默认访问权限,不能在子类中被访问
//        dm.print(); //报错:Cannot be accessed from outside package


        PublicClass publicClass = new PublicClass();
        publicClass.print(); //public可以在子类中被访问

    }
}
