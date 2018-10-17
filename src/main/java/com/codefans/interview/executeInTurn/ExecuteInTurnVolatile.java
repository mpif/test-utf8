package com.codefans.interview.executeInTurn;

import com.codefans.thread.concurrent.ReentrantLockBasicUse;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Created by caishengzhi on 2017/3/8 14:15.
 */
public class ExecuteInTurnVolatile implements Runnable {

    private volatile int num;

    public ExecuteInTurnVolatile(int num) {
        this.num = num;
    }

    @Override
    public void run() {

        for(int i = num; i <= 10; i = i + 2) {

            if (num == 0) {
                System.out.println("偶数:" + i);

            } else {
                System.out.println("奇数:" + i);
            }



        }



    }

    public static void main(String[] args){
        int evenNum = 0; //偶数
        int oddNum = 1;  //奇数
        Thread t01 = new Thread(new ReentrantLockBasicUse(evenNum));
        t01.start();

        try {
            sleep(100L);
        } catch (InterruptedException e) {
        }

        Thread t02 = new Thread(new ReentrantLockBasicUse(oddNum));
        t02.start();

    }

}
