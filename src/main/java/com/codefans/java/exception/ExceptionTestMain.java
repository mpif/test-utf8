package com.codefans.java.exception;

public class ExceptionTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExceptionThrow thr = new ExceptionThrow();
		int n = 1;
		for (int i = 0; i < n; i++) {
			try {
				thr.test();
				// throw new RuntimeException("run time exception !");
			} catch (Exception e) {
				System.out.println("====index: " + i + ", msg: " + e.getMessage());
				// e.printStackTrace();
			}
		}

	}

}
