package com.codefans.java.util;

public class TimeUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("==========");
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("cost: " + (end - start) / 1000);
	}

}
