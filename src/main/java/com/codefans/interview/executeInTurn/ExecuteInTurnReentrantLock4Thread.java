package com.codefans.interview.executeInTurn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/3/22.
 */
public class ExecuteInTurnReentrantLock4Thread {

    public static int j = 0;
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new AddThread("AddThread01", 4)).start();
        Thread.sleep(1000);
        new Thread(new AddThread("AddThread02", 3)).start();
        Thread.sleep(1000);
        new Thread(new SubThread("SubThread01", 2)).start();
        Thread.sleep(1000);
        new Thread(new SubThread("SubThread02", 1)).start();
    }

}

class AddThread implements Runnable {

    private String name;
    private int times;
    public AddThread(String name, int times) {
        this.name = name;
        this.times = times;
    }

    @Override
    public void run() {


        for(int i = 0; i < times; i ++) {

            try {
                ExecuteInTurnReentrantLock4Thread.lock.lock();

                ExecuteInTurnReentrantLock4Thread.condition.signalAll();


                System.out.println("thread:[" + name + "],j=" + (++ExecuteInTurnReentrantLock4Thread.j));


                try {
                    ExecuteInTurnReentrantLock4Thread.condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                ExecuteInTurnReentrantLock4Thread.lock.unlock();
            }

        }

    }
}

class SubThread implements Runnable {

    private String name;
    private int times;
    public SubThread(String name, int times) {
        this.name = name;
        this.times = times;
    }

    @Override
    public void run() {

        for(int i = 0; i < times; i ++) {

            try {
                ExecuteInTurnReentrantLock4Thread.lock.lock();

                ExecuteInTurnReentrantLock4Thread.condition.signalAll();

                System.out.println("thread:[" + name + "],j=" + (--ExecuteInTurnReentrantLock4Thread.j));


                try {
                    ExecuteInTurnReentrantLock4Thread.condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                ExecuteInTurnReentrantLock4Thread.lock.unlock();
            }

        }

    }
}

