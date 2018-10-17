package com.codefans.algorithm.test001;

/*
 * @Author: Sean
 * @Time: 2015-05-11 14:52:07
 */
public class Third {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Third third = new Third();
		third.test();
		// System.out.println(Integer.MIN_VALUE);
		// System.out.println(Integer.MAX_VALUE);
	}

	public void test() {
		// int firstBefore = 0;
		// int secondBefore = 1;
		// int third = 0;
		// for(int i = 0; i < 98; i ++) {
		// third = firstBefore + secondBefore;
		// System.out.println(third);
		// firstBefore = secondBefore;
		// secondBefore = third;
		// }

		int count = 100;
		listBefore(count);

	}

	public void listBefore(int count) {

		// int[] arr = strToIntArr("2147483647");
		// print(arr);

		// String result = addBigInteger("2147483647", "2147483647");
		// //result:4294967294
		// String result = addBigInteger("5", "8"); //result:13
		// String result = addBigInteger("95", "149"); //result:244
		// result = convert(result);
		// System.out.println(result);

		String firstBefore = "0";
		String secondBefore = "1";
		System.out.println(firstBefore);
		System.out.println(secondBefore);
		String third = "0";
		for (int i = 0; i < count - 2; i++) {
			third = addBigInteger(firstBefore, secondBefore);
			third = convert(third);
			System.out.println(third);
			firstBefore = secondBefore;
			secondBefore = third;
		}

	}

	public String addBigInteger(String bigOne, String bigTwo) {
		StringBuffer result = new StringBuffer();
		int[] arrOne = strToIntArr(bigOne);
		int[] arrTwo = strToIntArr(bigTwo);

		int arrOneLen = arrOne.length;
		int arrTwoLen = arrTwo.length;

		int over = 0;
		int added = 0;
		int current = 0;
		for (int i = arrOne.length - 1, j = arrTwo.length - 1; i >= 0 && j >= 0; i--, j--) {
			added = arrOne[i] + arrTwo[j] + over;
			if (added >= 10) {
				over = added / 10;
				current = added % 10;
			} else {
				over = 0;
				current = added;
			}
			result.append(current);
		}

		int len = 0;
		int shorterLen = 0;
		int[] longer = null;
		if (arrOneLen >= arrTwoLen) {
			len = arrOneLen - arrTwoLen;
			shorterLen = arrTwoLen;
			longer = arrOne;
		} else {
			len = arrTwoLen - arrOneLen;
			shorterLen = arrOneLen;
			longer = arrTwo;
		}

		if (len > 0) {
			int curInt = 0;
			for (int i = 0; i < len; i++) {
				curInt = longer[len - 1 + i] + over;
				if (curInt >= 10) {
					over = curInt / 10;
					current = curInt % 10;
				} else {
					over = 0;
					current = curInt;
				}
				result.append(current);
			}
		}

		if (over > 0) {
			result.append(over);
		}

		return result.toString();
	}

	public int[] strToIntArr(String digit) {
		int[] arr = new int[digit.length()];
		for (int i = 0; i < digit.length(); i++) {
			arr[i] = Integer.parseInt(String.valueOf(digit.charAt(i)));
		}
		return arr;
	}

	public String convert(String str) {
		StringBuffer result = new StringBuffer();
		for (int i = str.length() - 1; i >= 0; i--) {
			result.append(str.charAt(i));
		}
		return result.toString();
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

	public void print(int[] arr) {
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
