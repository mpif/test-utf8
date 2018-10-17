package com.codefans.java.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class SetTest {

	@Test
	public void test() {
		Set set = new HashSet();
		set.add("111");
		set.add("222");
		set.add("333");

		Iterator iter = set.iterator();
		String str = "";
		for (int i = 0; iter.hasNext(); i++) {
			if (i != set.size() - 1) {
				str += iter.next() + ";";
			} else {
				str += iter.next();
			}
		}

		System.out.println("str: " + str);

	}
}
