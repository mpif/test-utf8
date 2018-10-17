package com.codefans.util;

import java.util.UUID;

public class UniqueStringGenerator {
	private static final int MAX_GENERATE_COUNT = Integer.MAX_VALUE;
	private static int generateCount = 0;

	private UniqueStringGenerator() {
	}

	public static synchronized String getUniqueString() {
		if (generateCount > MAX_GENERATE_COUNT) {
			generateCount = 0;
		}
		String uniqueNumber = Long.toString(System.currentTimeMillis()) + System.getProperty("java.runtime.name")
				+ Integer.toString(generateCount);
		uniqueNumber = MD5Util.getHashCodeStr(uniqueNumber);
		generateCount++;
		return uniqueNumber;
	}

	public static synchronized String getUniqueUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static void main(String[] args) {
		// Properties prop = System.getProperties();
		// Set<Map.Entry<Object, Object>> entry = prop.entrySet();
		// Iterator<Map.Entry<Object, Object>> iter = entry.iterator();
		// Map.Entry<Object, Object> en = null;
		// while(iter.hasNext()) {
		// en = iter.next();
		// System.out.println(en.getKey() + ":" + en.getValue());
		// }

		System.out.println(getUniqueString());
		System.out.println(getUniqueString());

	}

}
