package com.codefans.java.concurrent.officialapi.Future.example01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * @Author: Sean
 * @Time: 2015-06-23 17:11:04
 */
public class FutureMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FutureMain fm = new FutureMain();
		fm.test();
	}

	public void test() {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(corePoolSize * 10);
		final CountDownLatch latch = new CountDownLatch(2);

		int threadNum = 5;
		for (int i = 0; i < threadNum; i++) {
			Future f = executor.submit(new WorkThread(i));
		}

	}

	class WorkThread implements Runnable {

		private int threadIndex;

		public WorkThread(int threadIndex) {
			this.threadIndex = threadIndex;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {

		}

	}

}
