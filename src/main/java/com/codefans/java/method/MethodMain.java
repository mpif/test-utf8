package com.codefans.java.method;

public class MethodMain {

	public static void main(String[] args) {
		MethodMain methodMain = new MethodMain();
		methodMain.test();
	}

	public void test() {
		// String[] arr1 = new String[]{"11"};
		String[] arr1 = null;
		String[] arr2 = new String[] { "22", "222" };
		String[] arr3 = new String[] { "33", "333", "3333" };
		method(arr1, arr2, arr3);
	}

	public void method(String[]... args) {
		for (int i = 0; i < args.length; i++) {
			String[] arr = args[i];
			System.out.println(arr.length);
		}
	}

}
