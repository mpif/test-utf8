package com.codefans.java.regex;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class JUnitTest {

	@Test
	public void test() {

		String str = " hello   world  com ";
		p(str.trim().replaceAll("\\s{1,}", "."));
		// p(str.replaceAll(" {1,}", "."));

	}

	public void test4() {
		String str = null;
		Pattern p = Pattern.compile("(?<=[<>])([^<>].*?)(?=[<>])");
		// Pattern p = Pattern.compile("(?<=<)(.*?)(?=>)");
		String address = "user1<user1@new2010.local>";
		Matcher matcher = p.matcher(address);
		if (matcher.find()) {
			str = matcher.group(0);
		}
		System.out.println(str); // 输出user1@new2010.local
	}

	public void test3() {
		String regex = "\\=\\?(.*?)\\?\\=";
		String source = "=?UTF-8?B?a1a1a1a1a1a1a1?= =?UTF-8?B?b2b2b2b2b2b2b2b2?= =?UTF-8?c3c3c3c3c3c3c3c3?=";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		// boolean flag = matcher.find();
		// p("flag: " + flag);

		while (matcher.find()) {
			p(matcher.group(0));
			p(matcher.group(1));
			// p(matcher.group(2));
		}

	}

	public void test2() {
		isFind("\"(.*?)\"(.*?)", "\"=?UTF-8?B?LuaZruWIqeeJuSDmsaQu5pmu5Yip54m5?=\"<tem.plate@new2010.local>",
				"isMatch: ");

		Pattern pattern = Pattern.compile("\"(.*?)\"(.*?)");
		Matcher matcher = pattern.matcher("\"=?UTF-8?B?LuaZruWIqeeJuSDmsaQu5pmu5Yip54m5?=\"<tem.plate@new2010.local>");
		while (matcher.find()) {
			System.out.println(matcher.group(0) + ": " + matcher.group(1) + ": " + matcher.group(2));
		}

		String str = "\"=?UTF-8?B?LuaZruWIqeeJuSDmsaQu5pmu5Yip54m5?=\"<tem.plate@new2010.local>";
		int index = str.indexOf("?=\"");
		System.out.println(str.substring(index + 3));

		String value = "=?UTF-8?B?LuaZruWIqeeJuSDmsaQu5pmu5Yip54m5?=";
		try {
			System.out.println("   value: " + value);
			String newvalue = javax.mail.internet.MimeUtility.decodeText(value);
			System.out.println("newvalue: " + newvalue);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void test01() {
		// String addressvalue2 = "\"Hello, Boy Girl\" <user1@new2010.local>";
		// Pattern p2 = Pattern.compile("\"(.*?),(.*?)\"<(.*?)>");
		// Matcher m2 = p2.matcher(addressvalue2);
		// System.out.println("m2.find(): " + m2.find());
		isFind("\"(.*?),(.*?)\"<(.*?)>", "\"Hello, Boy Girl\" <user1@new2010.local>", "isMatch1: "); //
		isFind("\"(.*?),(.*?)\"(.*?)<(.*?)>", "\"Hello, Boy Girl\"<user1@new2010.local>", "isMatch2: ");
		// String addressvalue3 = "\"Hello, Boy Girl\"<user1@new2010.local>";
		// Pattern p3 = Pattern.compile("\"(.*?),(.*?)\"(.*?)<(.*?)>");
		// Matcher m3 = p3.matcher(addressvalue3);
		// System.out.println("m3.find(): " + m3.find());
	}

	public boolean isFind(String regex, String source, String msg) {
		boolean flag = false;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		System.out.println(msg + matcher.matches());
		return flag;
	}

	public void p(Object o) {
		System.out.println(o);
	}

}
