package com.codefans.java.finallyreturn;

public class FinallyReturn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FinallyReturn fr = new FinallyReturn();
		int n = fr.test();
		// int n = fr.test2();
		// int n = fr.test22();
		// int n = fr.test3();
		System.out.println(n);
	}

	public int test() {
		int count = 1;
		try {
			count++;
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return ++count;
			// ++count;
		}
	}

	public int test2() {
		int count = 1;
		try {
			throw new RuntimeException("异常1");
		} finally {
			return count++;
		}
		// 执行结果：返回值是1，同时不会抛出任何异常。
	}

	public int test22() {
		int count = 1;
		try {
			return count++;
		} finally {
			throw new RuntimeException("异常1");
		}
		// 执行结果：Exception in thread "main" java.lang.RuntimeException: 异常1
	}

	public int test3() {
		int count = 1;
		try {
			throw new RuntimeException("异常1");
		} finally {
			throw new RuntimeException("异常2");
		}
		// 执行结果：Exception in thread "main" java.lang.RuntimeException: 异常2
	}
}
