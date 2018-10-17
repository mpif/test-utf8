package com.codefans.interview;

/**
 * Created by Administrator on 2017/2/28.
 */
public class IntegerTest {

    public static void main(String[] args) {
        IntegerTest it = new IntegerTest();
        it.test();
    }

    public void test() {
        Integer int01 = 1;
        Integer int02 = 1;
        Integer int03 = 200;
        Integer int04 = 200;

        System.out.println(int01 == int02);
        System.out.println(int03 == int04);

    }
}
