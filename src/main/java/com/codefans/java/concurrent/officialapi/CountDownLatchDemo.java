package com.codefans.java.concurrent.officialapi;

import java.util.concurrent.CountDownLatch;

/*
 * User: Sean
 * Date: 2015-4-1
 * Time: 下午2:14:02
 * 
 * CountDownLatch类是一个同步计数器，构造时传入int参数，该参数就是计数器的初始值，每调用一次countDown()方法，计数器减1，
 * 计数器大于0时，await()方法会阻塞程序继续执行；
 * 【适用场景：如：开5个线程去下载，当5个线程都执行完了才算下载成功!】
 * 
 * 
 */

public class CountDownLatchDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2); // two concurrent thread.
														// (两个工人的协作)
		Worker worker1 = new Worker("zhangsan", latch);
		Worker worker2 = new Worker("lisi", latch);
		worker1.start();
		worker2.start();
		latch.await(); // wait for all thread done. (等待所有工人完成工作)
		System.out.println("all work done.");
	}

	static class Worker extends Thread {
		String workerName;
		CountDownLatch latch;

		public Worker(String workerName, CountDownLatch latch) {
			this.workerName = workerName;
			this.latch = latch;
		}

		public void run() {
			System.out.println("Worker " + workerName + " do work begin");
			try {
				Thread.sleep(5000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Worker " + workerName + " do work complete");
			latch.countDown(); // finish one , the count down one.(
								// 工人完成工作，计数器减一)
		}
	}
}
