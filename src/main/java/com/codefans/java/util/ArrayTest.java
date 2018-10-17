package com.codefans.java.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayTest {

	@Before
	public void before() {

	}

	@Test
	public void test() {
		test2();
		// test1();
		// test0();
	}

	public void test2() {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// Integer[] array = (Integer[])arr; //Cannot cast from int[] to
		// Integer[]

		Integer[] arrs = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		List<Integer[]> list = new ArrayList<Integer[]>();
		list.add(arrs);
		list.add(arrs);
		int index = 0;
		int temp = 0;
		Integer[] ara = null;
		for (int i = 0; i < list.size(); i++) {
			ara = list.get(i);
			index = 0;
			temp = ara[index++];
			temp = ara[index++];
			temp = ara[index++];
			temp = ara[index++];
			temp = ara[index++];
		}
	}

	public void test1() {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		// Integer[] array = (Integer[])arr;
	}

	public void test0() {
		int[][] arr = new int[3][2];
		int n = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = n++;
			}
		}

		print(arr);
	}

	public void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + ", ");
			}
			System.out.println();
		}
	}

	@After
	public void after() {

	}

	public void p(Object o) {
		System.out.println(o);
	}

}
