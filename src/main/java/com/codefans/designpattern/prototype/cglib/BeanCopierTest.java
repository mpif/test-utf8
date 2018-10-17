package com.codefans.designpattern.prototype.cglib;

/**
 * Created by Administrator on 2017/3/11.
 */
public class BeanCopierTest {

    public static void main(String[] args) {
        BeanCopierTest bct = new BeanCopierTest();
        bct.test();
    }

    public void test() {
        BeanCopierTestBean bctb = new BeanCopierTestBean();
        bctb.setName("helloName");
        bctb.setPassword("helloPassword");

        System.out.println(bctb);

        BeanCopierTestBean bctb2 = (BeanCopierTestBean) bctb.clone();
        System.out.println(bctb2);





    }

}
