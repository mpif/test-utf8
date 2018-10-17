package com.codefans.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-5-16 Time: 下午3:16 To
 * change this template use File | Settings | File Templates.
 */
public class HandleExceptionInThread extends Thread {

	public static void main(String[] args) {
		HandleExceptionInThread heit = new HandleExceptionInThread();
		heit.test();
	}

	public void test() {
		// testRuntimeException();
		testExceptionInThreadPool();
	}

	public void testRuntimeException() {
		try {
			RuntimeExceptionInThread reit = new RuntimeExceptionInThread();
			new Thread(reit).start();
		} catch (Exception e) {
			System.out.println("try to catch RuntimeException");
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}

		System.out.println("hello world.");
	}

	public void testExceptionInThreadPool() {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(corePoolSize);

		int taskPerLoop = 10;
		for (int i = 0; i < taskPerLoop; i++) {
			if (i == 5) {
				System.out.println("i == 5, execute RuntimeExceptionInThread");
				executor.submit(new RuntimeExceptionInThread()); // exception in
																	// run() of
																	// this
																	// thread
																	// will be
																	// missing，
																	// it is
																	// better to
																	// catch
																	// exception
																	// in method
																	// of run()
			}
			executor.submit(new ExceptionInThread()); // exception in run() of
														// this thread will be
														// missing， it is better
														// to catch exception in
														// method of run()
		}
		executor.shutdown();
	}

	class RuntimeExceptionInThread implements Runnable {
		public void run() {
			// throw new RuntimeException("RuntimeException in Thread.run()
			// !!!");
			String str = null;
			System.out.println("running in RuntimeException.run()");
			System.out.println(str.length());
		}
	}

	class ExceptionInThread implements Runnable {
		public void run() {
			System.out.println("currentThread:" + Thread.currentThread().getName());
		}
	}
}
