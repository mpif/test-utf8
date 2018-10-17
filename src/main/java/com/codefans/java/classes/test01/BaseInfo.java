package com.codefans.java.classes.test01;

import java.util.Map;

public abstract class BaseInfo {

	Preference preference = new Preference();

	public BaseInfo() {
		System.out.println("BaseInfo");
	}

	public void putPrefAttribute(Object key, Object value) {
		synchronized (preference) {
			preference.put(key, value);
		}
	}

	public void putAllPrefAttributes(Map map) {
		synchronized (preference) {
			preference.putAll(map);
		}
	}

	public Object getPrefAttribute(Object key) {
		synchronized (preference) {
			return preference.get(key);
		}
	}

	public void removePrefAttribute(Object key) {
		preference.remove(key);
	}

}
