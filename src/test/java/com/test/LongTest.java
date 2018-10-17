package com.test;

public class LongTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LongTest lt = new LongTest();
		lt.longEquals();
	}

	public void longEquals() {
		Long l = new Long("20");
		System.out.println(l == 20);
	}

}
