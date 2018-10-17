package com.codefans.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollectionUtil {

	public static void printMap(Map map) {
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	public static void printMapList(List<Map> list) {
		Map map = null;
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
		}
	}
}
