package com.codefans.interview.executeInTurn;

import static java.lang.Thread.sleep;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.codefans.thread.concurrent.ReentrantLockBasicUse;

/**
 * Created by caishengzhi on 2017/3/8 14:15.
 */
public class ExecuteInTurnReentrantLock implements Runnable {

    private int num;
    private static ReentrantLock lock = new ReentrantLock();
    final static Condition condition  = lock.newCondition();

    public ExecuteInTurnReentrantLock(int num) {
        this.num = num;
    }

    @Override
    public void run() {

        for(int i = num; i <= 10; i = i + 2) {

            lock.lock();
            try {
                if (num == 0) {
                    System.out.println("偶数:" + i);

                } else {
                    System.out.println("奇数:" + i);
                }

                condition.signalAll();
                try {

                    condition.await();
                } catch (InterruptedException e) {
                }

            } finally {
                lock.unlock();
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
