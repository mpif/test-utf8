package com.codefans.algorithm.test;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-5-2 Time: 下午1:04 To
 * change this template use File | Settings | File Templates.
 */
public class Digui {

	public static void main(String[] args) {
		Digui gigui = new Digui();
		gigui.method(321);
	}

	public void method(int n) {
		if (n > 10) {
			System.out.println(n % 10);
			method(n / 10);
		} else {
			System.out.println(n);
		}
	}
}
