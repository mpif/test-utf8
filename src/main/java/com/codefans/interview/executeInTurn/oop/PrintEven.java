package com.codefans.interview.executeInTurn.oop;

/**
 * Created by caishengzhi on 2017/3/8 14:40.
 */
public class PrintEven implements Runnable {

    private Num num;
    public PrintEven(Num num) {
        this.num = num;
    }

    @Override
    public void run() {

        while(num.i<=100) {
            synchronized (num) {
                if (!num.flag) {
                    try {
                        num.flag = true;
                        num.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    num.flag = false;
                    System.out.println("偶数:" + num.i);
                    num.i++;
                    num.notify();
                }
            }
        }
    }
}
