package com.codefans.interview.classload;

public class SubClassMain extends ClassLoadTest {

    private int i = 2;

    public SubClassMain() {
        i = 222;
    }

    @Override
    public void displayResult() {
        System.out.println("sub.class:" + i);
    }

    public static void main(String[] args) {
        new SubClassMain();
//        new ClassLoadTest();
//        new SubClassMain().displayResult();
    }

}
