package com.codefans.algorithm.test001;

/*
 * @Author: Sean
 * @Time: 2015-05-11 14:26:49
 */
public class Second {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Second second = new Second();
		second.addTwoArr();
	}

	public void addTwoArr() {
		// String[] arrOne = new String[]{"a", "B", "C"};
		// String[] arrTwo = new String[]{"1", "2", "3"};
		// String[] arrOne = new String[]{"a", "B", "C", "D", "E", "F"};
		// String[] arrTwo = new String[]{"1", "2", "3"};
		String[] arrOne = new String[] { "a", "B", "C" };
		String[] arrTwo = new String[] { "1", "2", "3", "4", "5", "6" };
		// String[] result = addTwoArr(arrOne, arrTwo);
		String[] result = addTwoArr2(arrOne, arrTwo);
		print(result);
	}

	/**
	 * Begin with the first element of shorter
	 * 
	 * @param arrOne
	 * @param arrTwo
	 * @return
	 */
	public String[] addTwoArr(String[] arrOne, String[] arrTwo) {
		String[] longer = null;
		String[] shorter = null;
		String[] result = new String[arrOne.length + arrTwo.length];
		if (arrOne.length >= arrTwo.length) {
			longer = arrOne;
			shorter = arrTwo;
		} else if (arrOne.length < arrTwo.length) {
			longer = arrTwo;
			shorter = arrOne;
		}

		int index = 0;
		for (int i = 0, j = 0; i < longer.length && j < shorter.length; i++, j++) {
			result[index] = shorter[i];
			index++;
			result[index] = longer[j];
			index++;
		}

		int len = longer.length - shorter.length;
		index = shorter.length * 2;
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				result[index] = longer[shorter.length + i];
				index++;
			}
		}

		return result;
	}

	/**
	 * begin with the first element of first arr
	 * 
	 * 
	 * @param arrOne
	 * @param arrTwo
	 * @return
	 */
	public String[] addTwoArr2(String[] arrOne, String[] arrTwo) {
		int arrOneLen = arrOne.length;
		int arrTwoLen = arrTwo.length;
		String[] result = new String[arrOneLen + arrTwoLen];

		int index = 0;
		for (int i = 0, j = 0; i < arrOneLen && j < arrTwoLen; i++, j++) {
			result[index] = arrOne[i];
			index++;
			result[index] = arrTwo[j];
			index++;
		}

		int len = 0;
		int shorterLen = 0;
		String[] longer = null;
		if (arrOneLen >= arrTwoLen) {
			len = arrOneLen - arrTwoLen;
			shorterLen = arrTwoLen;
			index = arrTwoLen * 2;
			longer = arrOne;
		} else {
			len = arrTwoLen - arrOneLen;
			shorterLen = arrOneLen;
			index = arrOneLen * 2;
			longer = arrTwo;
		}

		if (len > 0) {
			for (int i = 0; i < len; i++) {
				result[index] = longer[shorterLen + i];
				index++;
			}
		}

		return result;
	}

	public void print(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				System.out.print(", " + arr[i]);
			} else {
				System.out.print(arr[i]);
			}
		}
		System.out.println();
	}
}
