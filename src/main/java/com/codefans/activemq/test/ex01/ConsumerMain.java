package com.codefans.activemq.test.ex01;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * @Author: Sean
 * @Time: 2015-05-14 10:41:03
 */
public class ConsumerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsumerMain cm = new ConsumerMain();
		cm.start();
	}

	public void start() {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		System.out.println("corePoolSize:[" + corePoolSize + "]");

		ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(corePoolSize);
		ConsumerRunnable command = new ConsumerRunnable();
		long initialDelay = 1000;
		long period = 100;
		stpe.scheduleAtFixedRate(command, initialDelay, period, TimeUnit.MILLISECONDS);
	}

}
