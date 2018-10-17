package com.codefans.java.classes.test01;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SystemInfo info = new SystemInfo(111);
		info.setEmailAddress("bbb");

		System.out.println(info.getEmailAddress());
	}

}
