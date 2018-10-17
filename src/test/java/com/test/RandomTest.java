package com.test;

import java.util.Random;

import org.junit.Test;

import com.codefans.java.util.ArrayUtil;
import com.codefans.java.util.RandomUtil;

public class RandomTest {

	public static void main(String[] args) {
		RandomUtil util = new RandomUtil();

		int[] arr = ArrayUtil.getArray(1, 9);
		ArrayUtil.print(arr);

		// int n = util.nextInt(1, 9);
		for (int i = 0; i < 9; i++) {
			// System.out.println(util.nextInt(1, 9));
			// System.out.println(util.nextInt(9));
			System.out.println((int) (Math.random() * 9));
		}

		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		int loc = 0;
		for (int i = 0; i < 7; i++) {
			loc = (int) (Math.random() * 19);
			int temp = a[i];
			a[i] = a[loc];
			a[loc] = temp;
		}
		for (int i = 0; i < 7; i++) {
			System.out.print(a[i] + "  ");
		}
	}

	@Test
	public void test() {
		test01();
	}

	public void test01() {
		Random random = new Random();
		int max = 200;
		int total = 10;
		for (int i = 0; i < total; i++) {
			int id = Math.abs(random.nextInt() % max) + 1;
			System.out.println("id: " + id);
		}

		System.out.println("200 % 200: " + (200 % 200));
		System.out.println("200 / 200: " + (200 / 200));
	}
}
