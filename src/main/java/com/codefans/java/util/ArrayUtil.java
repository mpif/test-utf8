package com.codefans.java.util;

public class ArrayUtil {

	public static int[] getArray(int start, int end) {
		int len = end - start + 1;
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = start++;
		}
		return arr;
	}

	public static int[] getArray(int len) {
		return getArray(0, len);
	}

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
			if ((i + 1) % 9 == 0) {
				System.out.println();
			}
		}
	}
}
