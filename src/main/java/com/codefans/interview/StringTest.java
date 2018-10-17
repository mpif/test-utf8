package com.codefans.interview;

/**
 * Created by Administrator on 2017/2/28.
 */
public class StringTest {
    public static void main(String[] args) {
        StringTest st = new StringTest();
        st.test();
    }

    public void test() {
        String str01 = "helloworld";
        String str02 = new String("helloworld");
        System.out.println(str01 == str02);

        final String str03 = "hello";
        String str04 = "world";
        String str05 = str03 + "world";
        String str06 = new String("hello") + str04;
        String str07 = "hello" + "world";
        System.out.println(str01 == str05);
        System.out.println(str01 == str06);
        System.out.println(str01 == str07);

    }
}
