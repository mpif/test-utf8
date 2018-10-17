package com.codefans.java.exception;

public class ExceptionThrow {

	private static int n = 0;

	public void test() throws Exception {
		try {
			// throw new Exception("hhhhhhhhhhhh");
			ExceptionThrow et = null;
			et.test();
		} catch (Exception e) {
			System.out.println("index: " + (n++) + ", msg: " + e.getMessage());
			e.printStackTrace();
			// throw e;
			throw new NullPointerException("aaaaaaa");
		}
	}
}
