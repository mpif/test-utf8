package com.codefans.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class For {

	@Before
	public void before() {

	}

	@Test
	public void test() {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");

		for (String str : list) {
			p(str);
		}
	}

	@After
	public void after() {

	}

	public void p(Object o) {
		System.out.println(o);
	}
}
