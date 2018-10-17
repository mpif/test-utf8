package com.codefans.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "dacb";
		if (str.indexOf("+") >= 0) {
			System.out.println(str.replaceAll("\\+", "%20"));
		}
	}

	public boolean isCSTDateFormat(String dateStr) {
		boolean finded = false;
		String regex = "[A-Z]{1}[a-z]{2} [A-Z]{1}[a-z]{2} [0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} CST [0-9]{4}";
		Pattern pattern = Pattern.compile(regex);
		// String dateStr = "Wed Jul 31 15:57:11 CST 2013";
		Matcher matcher = pattern.matcher(dateStr);
		while (matcher.find()) {
			finded = true;
			System.out.println(matcher.group());
		}
		return finded;
	}

}
