package com.codefans.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// test0();
		// test();
		// System.out.println(String.valueOf(0));
		// System.out.println(Boolean.parseBoolean("0"));

		test1();
		// test2();
		// map2list();

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("1", null);

		// if(map.get("1") == 1);

		System.out.println("" == null ? 0 : 1);
	}

	public static void map2list() {
		Map map = new HashMap();
		map.put("1", "111");
		map.put("2", "222");
		map.put("3", "333");
		map.put("3", "666");

		List list = new ArrayList(map.values());
		print(list);
	}

	public static void test0() {
		Map map = new HashMap();
		map.put("1", "111");
		map.put("2", "222");
		map.put("3", "333");

		p("map.size(): " + map.size());

		Map m = new HashMap();
		m.putAll(map);
		printMap(m);
	}

	public static void test1() {
		Map m = new HashMap();
		Map m2 = new HashMap();
		m2.put("111", "111");
		m2.put("222", "222");
		m2.put("333", "333");
		m.put("obj", m2);

		Map m3 = new HashMap();
		m3.put("444", "444");
		m3.put("555", "555");
		m.put("obj", m3);

		Map mm = (Map) m.get("obj");
		printMap(mm);
	}

	public static void test2() {
		Map m = new HashMap();
		Map m2 = new HashMap();
		m2.put("111", "111");
		m2.put("222", "222");
		m2.put("333", "333");
		m.put("obj", m2);

		Map m3 = (Map) m.get("obj");
		m3.put("222", "666");
		m.put("obj", m3);

		Map mm = (Map) m.get("obj");
		printMap(mm);
	}

	public static void test() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map m = new HashMap();
		m.put("1", "111");
		m.put("2", "222");
		m.put("3", "333");

		Map m2 = new HashMap();
		m2.put("4", "444");
		m2.put("5", "555");
		m2.put("6", "666");

		List list = new ArrayList();
		list.add("777");
		list.add("888");
		list.add("999");

		map.put("one", m);
		map.put("two", list);

		Collection<Object> objs = map.values();
		Iterator iter = objs.iterator();
		while (iter.hasNext()) {
			Map mp = (Map) iter.next();
			printMap(mp);
		}

	}

	public static void p(Object o) {
		System.out.println(o);
	}

	public static void printMap(Map map) {
		Set set = map.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			System.out.println(key + ": " + map.get(key));
		}
	}

	public static void print(List list) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}
}
