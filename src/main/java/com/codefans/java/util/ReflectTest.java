package com.codefans.java.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReflectTest {

	@Before
	public void before() {

	}

	@Test
	public void test() {
		Class cls = ReflectTest.class;
		String name = cls.getName();
		cls = cls.getDeclaringClass();
		p("cls: " + cls);
		// List<?> list = new ArrayList<?>();
		p(this.toGetMethodName("username", "get"));
	}

	public String toGetMethodName(String field, String type) {
		StringBuilder sb = new StringBuilder();
		String temp = field.substring(1);
		String c = String.valueOf(field.charAt(0));
		c = c.toUpperCase();
		sb.append(type);
		sb.append(c);
		sb.append(temp);
		return sb.toString();
	}

	@After
	public void after() {

	}

	public void p(Object o) {
		System.out.println(o);
	}

}
