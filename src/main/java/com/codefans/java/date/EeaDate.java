package com.codefans.java.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/*
 * @Author: Sean
 * @Time: 2015-06-25 16:05:28
 */
public class EeaDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EeaDate eeaDate = new EeaDate();
		eeaDate.test();
	}

	public void test() {
		// testXmlTimeFormat();
		testMimeTimeFormat();
	}

	public void testXmlTimeFormat() {
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date t = new Date();
		System.out.println(df1.format(t));
		try {
			System.out.println(df1.format(df1.parse("2014-08-27T18:02:59.676Z")) + "***********");
			df1.setTimeZone(TimeZone.getTimeZone("UTC"));
			System.out.println(df1.format(t));// 2015-06-25T08:06:53.237Z
			System.out.println("-----------");
			System.out.println(df1.format(df1.parse("2014-08-27T18:02:59.676Z")) + "***********");
			System.out.println("2014-08-27T18:02:59.676Z");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void testMimeTimeFormat() {

		// String strDateTime = "星期四, 25 六月 2015 11:10:10 +0800";
		// SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy
		// hh:mm:ss Z");

		String strDateTime = "Thu, 25 Jun 2015 11:10:10 +0800";
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z", Locale.ENGLISH);

		try {

			// Date date = formatter.parse(strDateTime);
			// System.out.println(date.toString());

			String dateStr = formatter.format(new Date());
			System.out.println(dateStr);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
