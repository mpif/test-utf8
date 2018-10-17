package com.test;

public class ArrayTest {

	String name = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayTest[] test = new ArrayTest[2];
		ArrayTest t = new ArrayTest();
		t.setName("111");
		test[0] = t;
		t = new ArrayTest();
		t.setName("222");
		test[1] = t;

		if (test != null) {
			if (test.length > 0) {
				ArrayTest at = test[0];
				at.setName("aaa");
				at = test[1];
				at.setName("bbb");
			}
		}

		for (int i = 0; i < test.length; i++) {
			ArrayTest at = test[i];
			System.out.println(at.getName());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
