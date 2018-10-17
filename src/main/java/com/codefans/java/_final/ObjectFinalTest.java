package com.codefans.java._final;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-08-14 22:44
 */

public class ObjectFinalTest {

    private static final ObjectFinalTest oft = new ObjectFinalTest();

//    private ObjectFinalTest(ObjectFinalTest objectFinalTest) {
//        ObjectFinalTest.oft = objectFinalTest;
//    }

    public static void main(String[] args) {
        ObjectFinalTest oft = new ObjectFinalTest();
        oft.getInstance();
    }

    public static ObjectFinalTest getInstance() {

        //报错
        //Cannot assign a value to final variable 'oft'
        //oft = new ObjectFinalTest();

        return oft;
    }



}
