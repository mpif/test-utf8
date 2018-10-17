package com.codefans.system;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class MainTest {

	/**
	 * @author caisz
	 * @param args
	 */
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		Iterator iter = properties.entrySet().iterator();
		Map.Entry map = null;

		long start = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		while (iter.hasNext()) {
			map = (Map.Entry) iter.next();
			// System.out.println("key: " + map.getKey() + ", value: " +
			// map.getValue());
			System.out.println(map.getKey() + ": " + map.getValue());
		}

		long end = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		System.out.println("used: " + (end - start) / 1000 + "kb");
		// System.out.println("totalMemory: " +
		// Runtime.getRuntime().totalMemory()); //b
		// System.out.println("maxMemory: " + Runtime.getRuntime().maxMemory());
		System.out.println("freeMemory: " + Runtime.getRuntime().freeMemory() / (1000 * 1024));

	}

}
