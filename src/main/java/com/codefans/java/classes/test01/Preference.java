package com.codefans.java.classes.test01;

import java.io.*;
import java.util.*;

public class Preference implements Serializable {
	private static final long serialVersionUID = 2832746759512286392L;

	public static Preference toObject(byte[] bytes) {
		Preference pref = new Preference();
		if (bytes == null || bytes.length == 0) {
			return pref;
		} else {
			try {
				ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
				if (in.readInt() == versionSerial) {
					pref.ht = (Hashtable) in.readObject();
				}
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return pref;
		}
	}

	public Preference() {
		ht = new Hashtable();
	}

	public byte[] getBytes() {
		byte[] data = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeInt(versionSerial);
			// write hashtable
			oos.writeObject(ht);
			oos.flush();
			oos.close();
			data = baos.toByteArray();
		} catch (Throwable th) {
			th.printStackTrace();
			return data;
		}
		return data;
	}

	public void put(Object key, Object value) {
		ht.put(key, value);
	}

	public void putAll(Map map) {
		ht.putAll(map);
	}

	public Object get(Object key) {
		return ht.get(key);
	}

	public Object remove(Object key) {
		return ht.remove(key);
	}

	private Hashtable ht;

	private static final int versionSerial = 300;

}
