package com.codefans.java.thread.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadConcurrent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MultiThreadConcurrent multiThreadConcurrent = new MultiThreadConcurrent();
		multiThreadConcurrent.testMultiThread();
	}

	public void testMultiThread() {

		int threadPoolNum = 2;
		Thread thread = null;
		for (int i = 0; i < threadPoolNum; i++) {
			// thread = new Thread(new Runnable() {
			// @Override
			// public void run() {
			int availableProcessors = Runtime.getRuntime().availableProcessors();
			System.out.println("availableProcessors:" + availableProcessors);
			final ExecutorService searchExecutor = Executors.newFixedThreadPool(availableProcessors,
					new NamedThreadFactory("SingleThreadPool"));

			int threadNum = 5;
			Thread t = null;

			for (int j = 0; j < threadNum; j++) {
				final int threadIndex = j;
				t = new Thread(new Runnable() {
					@Override
					public void run() {
						Future<ResultSet> result = searchExecutor.submit(new TaskThread(threadIndex));
						try {
							ResultSet rs = result.get();
							System.out.println("result name:" + rs.getName());
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
						// System.out.println("thread num " + (j + 1) + "
						// submit.");
					}
				});
				t.start();
			}
			// }
			// });
			// thread.start();

		}

	}
}
