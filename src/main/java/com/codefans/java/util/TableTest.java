package com.codefans.java.util;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TableTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TableTest test = new TableTest();
		// test.test01();
		test.test02();

	}

	public void test02() {
		Hashtable table = new Hashtable();
		Map<String, String> map = new HashMap<String, String>();
		map.put("AAA", "DDD");

		table.put(String.valueOf(1111), map);

		Set set = table.keySet();
		Iterator iter = set.iterator();
		Map<String, String> temp = null;
		while (iter.hasNext()) {
			temp = (Map<String, String>) table.get(iter.next()); // （A）
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("AAA", "BBB");
			table.put(String.valueOf(2222), map2); // 此时正在遍历table，这时往该table中加入元素，（A）句会报错.java.util.ConcurrentModificationException
			System.out.println(temp.get("AAA"));
		}
	}

	public void test01() {
		Hashtable table = new Hashtable();
		Map<String, String> map = new HashMap<String, String>();
		map.put("AAA", "AAA");
		map.put("AAA", "AAA");
		map.put("AAA", "AAA");

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("BBB", "BBB");
		map2.put("BBB", "BBB");
		map2.put("BBB", "BBB");

		table.put(String.valueOf(1111), map);
		table.put(String.valueOf(1111), map2);

		Map<String, String> result = (Map<String, String>) table.get("1111");
		Set set = result.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			System.out.println(result.get(iter.next())); // BBB
		}
	}

}
