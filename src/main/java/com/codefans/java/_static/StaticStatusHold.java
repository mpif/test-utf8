package com.codefans.java._static;

import java.util.HashMap;
import java.util.Map;

public class StaticStatusHold {

	public static Map<String, Map<String, Integer>> holder = new HashMap<String, Map<String, Integer>>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Status01();
		new Status02();
	}

}

class Status01 {

	public Status01() {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("key01", 11);
		map.put("key02", 22);
		StaticStatusHold.holder.put("Status01", map);
		map = null;

	}
}

class Status02 {

	public Status02() {
		Map<String, Integer> map = StaticStatusHold.holder.get("Status01");
		System.out.println("key01: " + map.get("key01"));
		System.out.println("key02: " + map.get("key02"));
	}
}
