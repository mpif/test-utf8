package com.codefans.algorithm;

/*
 * @Author: Sean
 * @Time: 2015-07-07 17:39:24
 */
public class GeLiGaoLiGongShi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeLiGaoLiGongShi glglgs = new GeLiGaoLiGongShi();
		// glglgs.test();
		glglgs.test2();
	}

	public void test() {
		int flag, t;
		double item, pi;

		flag = 1;
		t = 1;

		item = 1.0;
		pi = 0;

		while (Math.abs(item) >= 0.00001) {
			item = flag * 1.0 / t;
			pi = pi + item;
			flag = -flag;
			t = t + 2;
		}

		pi = pi * 4;

		System.out.println("pi=" + pi);

	}

	public void test2() {
		int s, n;
		double t, pi;

		s = 1;
		n = 1;

		t = 1.0;
		pi = 0;

		while (Math.abs(t) >= 0.00001) {
			t = s * 1.0 / n;
			pi = pi + t;
			s = -s;
			n = n + 2;
		}

		pi = pi * 4;

		System.out.println("pi=" + pi);

	}

}
