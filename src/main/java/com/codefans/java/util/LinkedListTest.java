package com.codefans.java.util;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.push("1111");
		list.push("2222");
		list.push("3333");
		// list.add("1111");
		// list.add("2222");
		// list.add("3333");

		list.pollFirst();
		System.out.println();

		list.removeFirst();

		System.out.println(list.size());
		print(list);
	}

	public static void print(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
