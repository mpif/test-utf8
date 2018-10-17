package com.codefans.java.exception;

public class ReturnInFinally {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReturnInFinally rif = new ReturnInFinally();
		rif.test();
	}

	public void test() {
		int index = 0;
		try {
			System.out.println("program is running here." + index++);
			throw new RuntimeException("IOException was throw");

		} finally {
			System.out.println("program is running in finally block." + index++);

			// finally 语句块中如果使用了throw和return，上面的RuntimeException就不会抛出。
			throw new NullPointerException("aaaaaaa");
			// return;
		}
	}

}
