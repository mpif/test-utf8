package com.codefans.algorithm;

public class HanoiTower {

	static int count;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n;
		n = Integer.parseInt("5");
		hanoi(n, 'A', 'B', 'C');
		System.out.println("move count:[" + count + "]");
	}

	static void hanoi(int n, char a, char b, char c) {
		if (n == 1) {
			moves(a, c);
		} else {
			hanoi(n - 1, a, c, b);
			moves(a, c);
			hanoi(n - 1, b, a, c);
		}
	}

	static void moves(char a, char c) {
		count++;
		System.out.println("From [" + a + "]-->[" + c + "]");
	}

}
