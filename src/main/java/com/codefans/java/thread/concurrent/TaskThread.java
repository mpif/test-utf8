package com.codefans.java.thread.concurrent;

import java.util.concurrent.Callable;

public class TaskThread implements Callable<ResultSet> {

	private int index = 0;

	public TaskThread() {
		index++;
	}

	public TaskThread(int index) {
		this.index = index;
	}

	public ResultSet call() {

		ResultSet rs = new ResultSet();
		String name = Thread.currentThread().getName();
		rs.setName(name);
		// while(true) {
		// System.out.println(name + ": " + new Date());

		int slp = 0;
		if (index == 0) {
			slp = 4000;
			System.out.println("index is 0, sleep time:" + slp);
		} else if (index == 1) {
			slp = 2000;
			System.out.println("index is 1, sleep time:" + slp);
		} else if (index == 2) {
			slp = 3000;
			System.out.println("index is 2, sleep time:" + slp);
		} else if (index == 3) {
			slp = 2000;
			System.out.println("index is 3, sleep time:" + slp);
		} else if (index == 4) {
			slp = 1000;
			System.out.println("index is 4, sleep time:" + slp);
		} else {
			System.out.println("index is " + index + ", sleep time: 5000");
		}
		try {
			Thread.sleep(slp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// }

		return rs;

	}

}
