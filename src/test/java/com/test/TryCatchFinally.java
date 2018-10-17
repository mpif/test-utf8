package com.test;

import org.junit.Test;

public class TryCatchFinally {

	@Test
	public void test() {
		// p("test: " + t());

		String eas_root = System.getProperty("eas.conf", "/opt/msol");
		p(eas_root);
	}

	public boolean t() {
		boolean flag = false;
		try {
			p("try block");
			// System.exit(0);
			throw new Exception("");
		} catch (Exception e) {
			p("catch block");
			return true;
		} finally {
			p("finally block");
		}
		// return flag;
	}

	public void p(Object o) {
		System.out.println(o);
	}
}
