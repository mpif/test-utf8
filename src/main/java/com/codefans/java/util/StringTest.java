package com.codefans.java.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringTest {

	@Before
	public void before() {

	}

	@Test
	public void test() {
		// test0();
		// test1();
		testIntern();
	}

	public void testIntern() {

		int n = 1000000;
		String s = "";
		String ss = "转发到附近的房价的开发将打开房间爱对方就打开附件地方见到了房间都是风景都上了飞机的看法基督教";
		for (int i = 0; i < n; i++) {
			// s = ss.intern();
			s = ss;
			// s = "转发到附近的房价的开发将打开房间爱对方就打开附件地方见到了房间都是风景都上了飞机的看法基督教";
		}
		long total = Runtime.getRuntime().totalMemory();
		long free = Runtime.getRuntime().freeMemory();
		System.out.println("The busy memory is: " + (total - free));

		// String s1=new String("kvill");
		// String s2=s1.intern();
		// System.out.println( s1==s1.intern() );
		// System.out.println( s1+" "+s2 );
		// System.out.println( s2==s1.intern() );

	}

	public void test0() {
		String str = "AAMkAGJjM2FhYzJlLTYxYzYtNDkwNC04YmFlLWQyYWFhNDNlMWYzYwAuAAAAAADlPxttskfuSahYvDPAKvh0AQCn1kz51vOmToCVMZhd02/YAAAwrrrZAAA=";
		String str2 = "AAMkAGJjM2FhYzJlLTYxYzYtNDkwNC04YmFlLWQyYWFhNDNlMWYzYwAuAAAAAADlPxttskfuSahYvDPAKvh0AQCn1kz51vOmToCVMZhd02/YAAAwrrrZAAA=";
		p(equals(str, str2));

		p("\u6536\u4ef6\u7bb1");
	}

	public void test1() {
		String str = "org.springframework.beans.factory.BeanCreationException, "
				+ "Caused by: java.lang.IllegalStateException: No thread-bound request found: "
				+ "Are you referring to request attributes outside of an actual web request? "
				+ "If you are actually operating within a web request and still receive this message,y"
				+ "our code is probably running outside of DispatcherServlet/DispatcherPortlet: In this "
				+ "case, use RequestContextListener or RequestContextFilter to expose the current request.";
		p(str.length());
	}

	public boolean equals(String str, String str2) {
		return str.equals(str2);
	}

	@After
	public void after() {

	}

	public void p(Object o) {
		System.out.println(o);
	}

}
