package com.codefans.algorithm.combination;

public class Combination {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 0;
		for (int i = 122345; i <= 543221; i++) {
			String str = i + "";
			if (str.indexOf("35") == -1 && str.indexOf("53") == -1) {
				if (str.indexOf("4") != 2) {
					if (isTemple(str)) {
						System.out.println(str);
						count++;
					}
				}
			}
		}
		p("count: " + count);

		// p(isBetweenOneAndFive("102122"));
		// p(isBetweenOneAndFive("213453"));
		// p('1' < 2);
		// p('1' < '2');

		p(isTemple("123453"));
		p(isTemple("123452"));
		p(isTemple("235123"));
	}

	public static void p(Object o) {
		System.out.println(o);
	}

	public static boolean isBetweenOneAndFive(String str) {
		boolean flag = true;
		char[] chars = str.toCharArray();
		for (char c : chars) {
			if (c < '1' || c > '5') {
				flag = false;
			}
		}
		return flag;
	}

	// temple: 1 2 2 3 4 5
	public static boolean isTemple(String str) {
		boolean flag = true;
		char[] chars = str.toCharArray();
		int twoCount = 0;
		boolean one = false;
		boolean two = false;
		boolean two2 = false;
		boolean three = false;
		boolean four = false;
		boolean five = false;

		for (char c : chars) {
			if (c == '1') {
				one = true;
			}
			if (c == '2') {
				if (twoCount == 0) {
					two = true;
					twoCount++;
				} else {
					two2 = true;
				}
			}
			if (c == '3') {
				three = true;
			}
			if (c == '4') {
				four = true;
			}
			if (c == '5') {
				five = true;
			}
		}

		flag = one && two && two2 && three && four && five;
		return flag;
	}
}
