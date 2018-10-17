package com.codefans.java.concurrent.officialapi.Semaphore.example01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
 * User: Sean
 * Date: 2015-4-1
 * Time: 下午3:48:07
 * 
 * 【简单介绍】：
	Semaphore类是一个计数信号量，从概念上讲，信号量维护了一个许可集合。如有必要，在许可可用前会阻塞每一个acquire(),然后再获取该许可。
每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。
【适用场景】：
	就像一个排队进入上海博物馆一样，放几个人等一下，有几个人走了然后再放几个人进入(比如走了3个就可以有3个进入)，就像是一种排队机制。
	
 */

public class HelloSemaphore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(); // 线程池
		final Semaphore semp = new Semaphore(5); // 只能5个线程同时访问
		for (int index = 0; index < 50; index++) { // 模拟50个客户端访问
			final int NO = index;
			Runnable run = new Runnable() {
				@Override
				public void run() {
					try {
						semp.acquire(); // 获取许可
						System.out.println("Accessing: " + NO);
						Thread.sleep((long) (Math.random() * 10000));
						semp.release(); // 访问完后，释放
						// availablePermits()指的是当前信号灯库中有多少个可以被使用
						System.out.println("-------------" + semp.availablePermits());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			};
			exec.execute(run);
		}
		exec.shutdown(); // 退出线程池
	}

}
