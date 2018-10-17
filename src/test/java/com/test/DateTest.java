package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// test01();
		// test02();
		// test03();
		test04();
	}

	public static void test04() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.add(Calendar.MINUTE, 60);

		System.out.println(cal.getTime());
	}

	public static void test01() {
		// String strDateTime = "2 Mar 2013 15:12:10 +0800";
		String strDateTime = "Thu, 25 Jun 2015 11:10:10 +0800";
		SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss ZZZ");
		try {
			Date date = formatter.parse(strDateTime);
			System.out.println(date.toString());

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void test02() {

		XMLGregorianCalendar xmldate = convertToXMLGregorianCalendar(new Date());
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date());
		// XMLGregorianCalendar xmldate = convert(cal);
		System.out.println(xmldate.toString());

		System.out.println("2012-01-01 11:11:11".length());
		System.out.println("2012-01-01 11:11:11".substring(0, 11));
	}

	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		// cal.setTime(date);
		cal.setTimeInMillis(date.getTime());
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return gc;
	}

	// Convert Date To XMLGregorianCalendar
	private static XMLGregorianCalendar convert(Calendar calendar) {
		XMLGregorianCalendar cal = new com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl();
		cal.setYear(calendar.get(Calendar.YEAR));
		cal.setMonth(calendar.get(Calendar.MONTH) + 1);
		cal.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		cal.setHour(calendar.get(Calendar.HOUR_OF_DAY));
		cal.setMinute(calendar.get(Calendar.MINUTE));
		cal.setSecond(calendar.get(Calendar.SECOND));
		cal.setMillisecond(calendar.get(Calendar.MILLISECOND));
		cal.setTimezone(calendar.get(Calendar.ZONE_OFFSET) / 60000);
		return cal;
	}

	// Convert XMLGregorianCalendar To Date
	private static Date convert(XMLGregorianCalendar xmlg) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, xmlg.getYear());
		cal.set(Calendar.MONTH, xmlg.getMinute());
		cal.set(Calendar.DAY_OF_MONTH, xmlg.getDay());
		cal.set(Calendar.HOUR_OF_DAY, xmlg.getHour());
		cal.set(Calendar.MINUTE, xmlg.getMinute());
		cal.set(Calendar.SECOND, xmlg.getSecond());
		cal.set(Calendar.MILLISECOND, xmlg.getMillisecond());
		return cal.getTime();
	}

	public static void test03() {
		String pattern = "EEE MMM dd HH:mm:ss 'CST' yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern); //
		String t = "Wed Nov 28 17:08:29 CST 2012";

		Date d = null;
		try {
			d = sdf.parse(t);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		p(d);
	}

	public static void p(Object o) {
		System.out.println(o);
	}

}
