package com.codefans.characterencoding;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class JUnitTest {

	@Test
	public void test() {

		// test03();
		// test02();
		test01();

	}

	public void test03() {
		String a = new String(".普利特 汤.普利特");
		try {
			// ISO
			String b = new String(a.getBytes(), "8859_1");
			System.out.println("b=" + b);
			String bb = new String(b.getBytes("8859_1"), "UTF-8");
			System.out.println("bb=" + bb);

			// GBK
			String c = new String(a.getBytes(), "gb2312");
			System.out.println("c=" + c);
			String cc = new String(a.getBytes("gb2312"), "GBK");
			System.out.println("cc=" + cc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public void test02() {
		String a = new String("中华人民共和国");
		try {
			// ISO
			String b = new String(a.getBytes(), "8859_1");
			System.out.println("b=" + b);
			String bb = new String(b.getBytes("8859_1"), "GBK");
			System.out.println("bb=" + bb);

			// GBK
			String c = new String(a.getBytes(), "gb2312");
			System.out.println("c=" + c);
			String cc = new String(a.getBytes("gb2312"), "GBK");
			System.out.println("cc=" + cc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public void test01() {
		// String b = new
		// String("=?UTF-8?B?LuaZruWIqeeJuSDmsaQu5pmu5Yip54m5?=");
		String b = new String("\"=?UTF-8?B?LuaZruWIqeeJuSDmsaQu5pmu5Yip54m5?=\"<tem.plate@new2010.local>");

		try {
			System.out.println(
					"=?UTF-8?B?LuaZruWIqeeJuSDmsaQu5pmu5Yip54m5?=: " + javax.mail.internet.MimeUtility.decodeText(b));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String c = null;
		try {
			c = new String(b.getBytes("UTF-8"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println("b=" + b);
		System.out.println("c=" + c);
	}

}
