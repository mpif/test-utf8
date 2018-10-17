package com.codefans.java._static;

public class TestStatic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// StaticInit.print();

		new Status01();
		try {
			Thread.currentThread().sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Status02();

	}

}
