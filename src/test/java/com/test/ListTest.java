package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

	private static List<String> list = new ArrayList<String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("aaa");

		// List<String> ll = new ArrayList<String>();
		// List<String> ll = null;
		// list.addAll(ll); // it will throw NullPointerException if ll is null

		// System.out.println(list.size());

		// List<Integer> list01 = new ArrayList<Integer>();
		// List<Integer> list02 = new ArrayList<Integer>();
		//
		// list01.add(2);
		// list01.add(3);
		// list01.add(5);
		// list01.add(7);
		// list01.add(9);
		//
		// list02.add(2);
		// list02.add(3);
		// list02.add(5);
		// list02.add(7);
		// list02.add(19);

		// List<Integer> list03 = shouldExcludeFolderId(list02, list01);
		// System.out.println(list03);

		// String testStr = "100,300,400,545,666";
		// String[] array = testStr.split(",");
		// System.out.println(array.length);
		// for(int i = 0; i < array.length; i ++) {
		// System.out.println(array[i]);
		// }
		// List<String> test = Arrays.asList(array);
		// test.remove("100");

//		testMaxListSize();
//
//		List<String> results = null;
//		for (int i = 0; i < 3; i++) {
//			results = getList();
//			System.out.println("results.size(): [" + results.size() + "]");
//			results.clear();
//		}

		testListCompare();
		
	}
	
	public static void testListCompare() {
		
		List<String> list01 = new ArrayList<String>();
		list01.add("aaa");
		list01.add("bbb");
		list01.add("ddd");
		
		List<String> list02 = new ArrayList<String>();
		list02.add("aaa");
		list02.add("bbb");
		list02.add("eee");
		
		List<String> result = new ArrayList<String>();
		result.addAll(list01);
		result.retainAll(list02);
//		result.removeAll(list02);
		print(result);
		
		
		
	}

	public static void print(List<String> list) {
		for(String str : list) {
			System.out.println(str);
		}
	}
	
	public static List<String> getList() {
		list.add("111");
		list.add("222");
		return list;
	}

	public static void testMaxListSize() {
		List<String> strs = new ArrayList<String>();
		int strCount = Integer.MAX_VALUE;
		for (int i = 0; i < strCount; i++) {
			strs.add("string no." + i);
		}
		System.out.println(strs.size());
	}

	private static List<Integer> shouldExcludeFolderId(List<Integer> retrieveFolderIds,
			List<Integer> includedFolderIds) {
		List<Integer> folderIds = new ArrayList<Integer>();
		int includedFolderId = 0;
		for (int i = 0; i < includedFolderIds.size(); i++) {
			includedFolderId = includedFolderIds.get(i);
			if (!retrieveFolderIds.contains(includedFolderId)) {
				folderIds.add(includedFolderId);
			}
		}
		return folderIds;
	}

}
