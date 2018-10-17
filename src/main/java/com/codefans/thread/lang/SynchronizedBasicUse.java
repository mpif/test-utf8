package com.codefans.thread.lang;

import static java.lang.Thread.sleep;

/**
 * Created by caishengzhi on 2017/3/6 11:56.
 * 交替执行
 * 需要解决两个问题:
 * (1)控制一个线程只打印奇数，一个线程只打印偶数
 * (2)控制两个线程交替执行
 */
public class SynchronizedBasicUse implements Runnable {

    private int num;
    private static Object lock = new Object();

    public SynchronizedBasicUse(int num) {
        this.num = num;
    }

    @Override
    public void run() {


        for(int i = num; i <= 10; i = i + 2) {

            synchronized (lock) {
                if (num == 0) {
                    System.out.println("偶数:" + i);

                } else {
                    System.out.println("奇数:" + i);
                }

                lock.notify();
                try {

                    lock.wait();
                } catch (InterruptedException e) {
                }

            }


        }


    }

    public static void main(String[] args){
        int evenNum = 0; //偶数
        int oddNum = 1;  //奇数
        Thread t01 = new Thread(new SynchronizedBasicUse(evenNum));
        t01.start();

        try {

            sleep(100L);

        } catch (InterruptedException e) {
        }

        Thread t02 = new Thread(new SynchronizedBasicUse(oddNum));
        t02.start();

    }



}
