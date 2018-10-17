package com.codefans.number;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest {
	public static void main(String[] args) {
		MainTest test = new MainTest();
		String str = "03434";
		boolean flag = test.isNumeric(str);
		System.out.println(flag);

		int first = Integer.parseInt(String.valueOf(str.charAt(0)));// 字符串转为int型
		System.out.println("first == 0: " + (first == 0));
	}

	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
