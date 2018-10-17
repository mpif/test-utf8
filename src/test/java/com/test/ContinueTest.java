package com.test;

public class ContinueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 10, i = 0;
		for (i = 0; i < n; i++) {
			if (i == 5) {
				continue;
			}
			System.out.println(i);
		}
		System.out.println("N: " + i);
	}

}
