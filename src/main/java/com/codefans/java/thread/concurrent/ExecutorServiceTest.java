package com.codefans.java.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @Author: Sean
 * @Time: 2015-05-19 09:33:13
 */
public class ExecutorServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorServiceTest est = new ExecutorServiceTest();
		est.test();
	}

	public void test() {
		testNewFixedThreadPool();

	}

	public void testNewFixedThreadPool() {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println("availableProcessors:" + availableProcessors);
		final ExecutorService executor = Executors.newFixedThreadPool(availableProcessors,
				new NamedThreadFactory("SingleThreadPool"));

		int taskCount = 4;
		for (int i = 0; i < taskCount; i++) {
			executor.execute(new TaskThread());
		}

	}

	class TaskThread implements Runnable {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// try {
			// Thread.sleep(3 * 1000);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			System.out.println("ThreadName:" + Thread.currentThread().getName() + ", is running...");
		}

	}

}
