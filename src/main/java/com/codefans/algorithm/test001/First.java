package com.codefans.algorithm.test001;

public class First {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		First first = new First();
		first.add();
	}

	public void add() {
		int[] arr = new int[] { 1, 3, 7, 8, 2, 9, 4, 6, 5 };

		System.out.println(this.addFor(arr));
		System.out.println(this.addWhile(arr));
		System.out.println(this.addDiGui(arr));

	}

	public int addFor(int[] arr) {
		int result = 0;
		for (int i = 0; i < arr.length; i++) {
			result += arr[i];
		}
		return result;
	}

	public int addWhile(int[] arr) {
		int result = 0;
		int len = arr.length;
		int index = 0;
		while (len > 0) {
			result += arr[index];
			index++;
			len--;
		}
		return result;
	}

	public int addDiGui(int[] arr) {
		int result = 0;
		result = addDiGui(arr, arr.length);
		return result;
	}

	public int addDiGui(int[] arr, int len) {
		int result = 0;
		int index = len - 1;
		if (len != 0) {
			result = arr[index] + addDiGui(arr, index);
		}
		return result;
	}

}
