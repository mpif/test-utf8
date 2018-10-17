package com.test;

import org.junit.Test;

public class ForTest {

	@Test
	public void test() {
		String[] str = new String[] { null };
		testFor(str);

		int[] ints = new int[] { 1, 2, 3 };
		testFor(ints);
	}

	public void testFor(String... strs) {
		for (String str : strs) {
			System.out.println("========[" + str + "]");
		}
	}

	public void testFor(int... strs) {
		for (int str : strs) {
			System.out.println("ints: " + str);
		}
	}
}
