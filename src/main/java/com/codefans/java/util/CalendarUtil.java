package com.codefans.java.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.XMLGregorianCalendar;

public class CalendarUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalendarUtil util = new CalendarUtil();
		// util.getDefault();
		// util.getThisMonth();

		util.timeZone();
		util.listTimeZone();
	}

	private void listTimeZone() {
		StringBuilder sb = new StringBuilder();
		for (String id : java.util.TimeZone.getAvailableIDs()) {
			sb.append(java.util.TimeZone.getTimeZone(id).getDisplayName() + "\r");
		}

		// p("ids: " + sb.toString());
		TimeZone zone = TimeZone.getDefault();
		String def = zone.getID();
		String displayName = zone.getDisplayName();
		String displayNameLocal = zone.getDisplayName(Locale.ENGLISH);
		int size = zone.getAvailableIDs().length;
		p("default id: " + def);
		p("default displayName: " + displayName);
		p("default displayName local: " + displayNameLocal);
		p("size: " + size);

		p("date.toLocalString(): " + new Date().toLocaleString());
		p("date.toGMTString(): " + new Date().toGMTString());
		p("date.toString(): " + new Date().toString());
	}

	private void timeZone() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String snow0 = sdf.format(now);

		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String snow = sdf.format(now); // snow = 2011-12-04 21:22:24
		sdf.setTimeZone(TimeZone.getTimeZone("UTC+8"));
		String snow2 = sdf.format(now);

		sdf.setTimeZone(TimeZone.getTimeZone("+0800"));
		String snow3 = sdf.format(now);

		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		String snow4 = sdf.format(now);

		p("GMT: " + snow0);
		p("GMT+8: " + snow);
		p("UTC+8: " + snow2);
		p("+0800: " + snow3);
		p("Asia/Shanghai: " + snow4);

		// XMLGregorianCalendar xml = new XMLGregorianCalendar();
		String str = "";
		String pattern = "";

		str = "Wed Jul 18 10:25:07 CST 2012";
		pattern = "EEE MMM dd HH:mm:ss 'CST' yyyy";

		DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
		Date date = null;
		boolean flag = true;
		try {
			date = (Date) df.parse(str);// parse函数进行转换
		} catch (ParseException e) {
			flag = false;
			// e.printStackTrace();
		}

		p(flag);
		p(date.toString());

		// getDate(str, pattern);
		//
		str = "Mon Jul 16 10:34:51 CST 2012";
		pattern = "EEE MMM dd HH:mm:ss 'CST' yyyy";
		getDate(str, pattern);
		//
		// str = "Mon, 16 Jul 2012 10:34:50 +0800";
		// pattern = "EEE, dd MMM yyyy HH:mm:ss '+0800'";
		// getDate(str, pattern);

		// str = "Wed, 18 Jul 2012 02:11:20 -0700";
		// str = "Wed, 18 Jul 2012 09:15:53 -0700";
		// pattern = "EEE, dd MMM yyyy HH:mm:ss '-0700'";
		// getDate(str, pattern);
		//
		// str = "Wed, 18 Jul 2012 09:15:53 GMT";
		// pattern = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";
		// getDate(str, pattern);
		//
		// str = "Thu, 13 Mar 2007 10:07:46 +0800";
		// pattern = "EEE, dd MMM yyyy HH:mm:ss '+0800'";
		// getDate(str, pattern);

		// System.out.println(new Date());
		// p(Calendar.getInstance().getTime());
		// itemType.getDateTimeReceived().toGregorianCalendar().getTime();

		// String[] source = new String[]{"Mon Jul 16 10:34:51 CST 2012"};
		// str = "Mon Jul 16 10:34:51 CST 2012";
		// p(this.convert(str));
		// str = "Wed, 18 Jul 2012 02:11:20 -0700";
		// p(this.convert(str));
		// str = "Wed, 18 Jul 2012 09:15:53 GMT";
		// p(this.convert(str));
	}

	private Date getDate(String str, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
		Date date = null;
		try {
			date = (Date) df.parse(str);// parse函数进行转换
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// System.out.println(date);//打印CST日期格式
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));// 打印常用日期格式
		System.out.println(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss '+0800'", Locale.ENGLISH).format(date));// 打印常用日期格式

		return date;

	}

	private String convert(String str) {
		String result = "";
		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
		Date date = null;
		boolean flag = true;
		try {
			date = (Date) df.parse(str);// parse函数进行转换
		} catch (ParseException e) {
			flag = false;
		}
		if (flag) {
			result = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss '+0800'", Locale.ENGLISH).format(date);
		} else {
			result = date.toLocaleString();
		}
		return result;
	}

	private String convertPatterns(String str) {
		String result = "";
		String[] patterns = new String[] { "EEE MMM dd HH:mm:ss 'CST' yyyy", "EEE, dd MMM yyyy HH:mm:ss 'GMT'",
				"EEE, dd MMM yyyy HH:mm:ss '-0700'" };
		for (String pattern : patterns) {
			DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
			Date date = null;
			boolean flag = true;
			try {
				date = (Date) df.parse(str);// parse函数进行转换
			} catch (ParseException e) {
				flag = false;
			}
			if (flag) {
				result = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss '+0800'", Locale.ENGLISH).format(date);
				break;
			}
		}
		return result;
	}

	private String findPattern(String str) {
		String result = "";
		String[] patterns = new String[] { "EEE MMM dd HH:mm:ss 'CST' yyyy", "EEE, dd MMM yyyy HH:mm:ss 'GMT'",
				"EEE, dd MMM yyyy HH:mm:ss '-0700'" };
		for (String pattern : patterns) {
			DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
			Date date = null;
			boolean flag = true;
			try {
				date = (Date) df.parse(str);// parse函数进行转换
			} catch (ParseException e) {
				flag = false;
			}
			if (flag) {
				result = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss '+0800'", Locale.ENGLISH).format(date);
				break;
			}
		}
		return result;
	}

	private void getThisMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int month = cal.get(Calendar.MONTH) + 1;
		p("month: " + month);

	}

	private void getDefault() {

		Calendar cal = Calendar.getInstance();
		// we need last month data
		// int month = cal.get(Calendar.MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		String m = String.valueOf(month + 1);
		if (m.length() == 1) {
			m = "0" + m;
		}
		int day = cal.get(Calendar.DATE);

		p("year: " + year + ", month: " + m + ", day: " + day);
	}

	public void p(Object o) {
		System.out.println(o);
	}

}
