package com.codefans.java.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/3/22.
 */
public class NotifyAndNotifyAllDiff {

    public static ReentrantLock lock = new ReentrantLock();
    public static ReentrantLock lock02 = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public static Condition condition02 = lock02.newCondition();

    public static void main(String[] args) {
        new Thread(new Thread02()).start();
        new Thread(new Thread01("thread01_1")).start();
        new Thread(new Thread01("thread01_2")).start();
        new Thread(new Thread01("thread01_3")).start();
    }


}
class Thread01 implements Runnable {

    private String name;
    public Thread01(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        for(int i = 0; i < 3; i ++) {
            NotifyAndNotifyAllDiff.lock.lock();
            try {

                System.out.println(name + ":i=" + i);
                NotifyAndNotifyAllDiff.condition.signalAll();
                try {
                    NotifyAndNotifyAllDiff.condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                NotifyAndNotifyAllDiff.lock.unlock();
            }
        }
    }

}

class Thread02 implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 3; i ++) {
            try {
                NotifyAndNotifyAllDiff.lock02.lock();
                System.out.println("Thread02:j=" + i);
                NotifyAndNotifyAllDiff.condition02.signalAll();
                try {
                    NotifyAndNotifyAllDiff.condition02.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                NotifyAndNotifyAllDiff.lock02.unlock();
            }
        }
    }

}

