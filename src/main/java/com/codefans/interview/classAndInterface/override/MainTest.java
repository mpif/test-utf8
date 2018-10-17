package com.codefans.interview.classAndInterface.override;

/**
 * Created by Administrator on 2017/3/22.
 */
public class MainTest extends MyClass implements MyInterface {

    public static void main(String[] args) {
        MainTest mcls = new MainTest();
        mcls.print();

    }

    public void print() {
        //System.out.println(x); 报错, 对x的引用不明确
    }


}
