package com.codefans.activemq.test.ex01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/*
 * @Author: Sean
 * @Time: 2015-05-14 10:40:46
 */
public class ProducerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProducerMain pm = new ProducerMain();
		pm.start();
	}

	public void start() {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		System.out.println("corePoolSize:[" + corePoolSize + "]");

		// ScheduledThreadPoolExecutor stpe = new
		// ScheduledThreadPoolExecutor(corePoolSize);

		// ExecutorService executor =
		// Executors.newFixedThreadPool(corePoolSize);

		// PreauditEmailMessage pemsg = new PreauditEmailMessage();
		// ProducerRunnable task = new ProducerRunnable(pemsg);

		int taskNum = 10;
		String subject = "";
		String emlFilePath = "";
		PreauditEmailMessage pemsg = null;
		ProducerRunnable task = null;

		long start = System.currentTimeMillis();

		for (int i = 0; i < taskNum; i++) {
			pemsg = new PreauditEmailMessage();
			subject = "test subject, index:[" + i + "]";
			emlFilePath = "test eml file path, index:[" + i + "]";
			pemsg.setSubject(subject);
			pemsg.setEmlFilePath(emlFilePath);
			task = new ProducerRunnable(pemsg);
			// stpe.submit(task);
			// executor.execute(task);
			thread(task, false);
		}

		// stpe.shutdown();
		// if(!executor.isShutdown()) {
		// executor.shutdown();
		// }

		long end = System.currentTimeMillis();

		System.out.println("total time cost:[" + (end - start) / 1000 + "s]");

	}

	public static void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

}
