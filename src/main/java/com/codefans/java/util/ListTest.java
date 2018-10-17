package com.codefans.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// addAll();
		// toArray();
		// toArray2();
		// testList();

		testListRemove();
	}

	public static void testListRemove() {
		List<String> list = new ArrayList<String>();
		list.add("/0000/");
		list.add("/1111/");
		list.add("/2222/");
		list.add("/3333/");
		list.add("/4444/");

		List<String> list2 = new ArrayList<String>();
		list2.add("/2222/");

		String tmp = "";
		for (int i = 0; i < list.size(); i++) {
			tmp = list.get(i);
			if (!list2.equals(tmp)) {
				list.remove(i);
			}
		}

		if (list != null) {
			String arr = null;
			for (int i = 0; i < list.size(); i++) {
				arr = list.get(i);
				System.out.println(arr);
			}
		}

		printListString(list);

	}

	public static void testList() {

		// List list = new ArrayList();
		// String[] arr = new String[1];
		// String[] values = new String[]{"0000", "1111"};
		// for(int i = 0; i < values.length; i ++) {
		// arr[0] = values[i];
		// list.add(arr);
		// }
		// printList(list);

		List list = new ArrayList();
		String[] arr = new String[1];
		arr[0] = "aaaa";
		list.add(arr);
		arr[0] = "bbbb";
		list.add(arr);
		printListArray(list);

	}

	// test List addAll method
	public static void addAll() {
		List list = new ArrayList();
		list.add("222");
		list.add("111");
		list.add("333");

		List list2 = new ArrayList();
		list2.addAll(list);
		print(list2);
	}

	// list to array
	public static void toArray() {
		String[] arr = new String[3];
		arr[0] = "111";
		arr[1] = "222";
		arr[2] = "333";

		List list = new ArrayList();
		list.toArray(arr);
		print(list);
	}

	// list to array
	public static void toArray2() {

		List list = new ArrayList();
		list.add("111");
		list.add("222");
		list.add("333");

		Object[] arr = (Object[]) list.toArray();
		print(arr);
	}

	public static void print(List list) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}

	public static void printListArray(List<String[]> list) {
		if (list != null) {
			String[] arr = null;
			for (int i = 0; i < list.size(); i++) {
				arr = list.get(i);
				for (int j = 0; j < arr.length; j++) {
					System.out.println(arr[j]);
				}
			}
		}
	}

	public static void printListString(List<String> list) {
		if (list != null) {
			String arr = null;
			for (int i = 0; i < list.size(); i++) {
				arr = list.get(i);
				System.out.println(arr);
			}
		}
	}

	public static void print(String[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]);
			}
		}
	}

	public static void print(Object[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]);
			}
		}
	}

}
