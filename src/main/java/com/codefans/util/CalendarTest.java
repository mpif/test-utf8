package com.codefans.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalendarTest test = new CalendarTest();
		String date = "2012-05";
		p(test.getLastDayOfMonth(date));
	}

	private int getLastDayOfMonth(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int day;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cal.add(Calendar.MONTH, 1);// 月增加1天
		cal.add(Calendar.DAY_OF_MONTH, -1);// 日期倒数一日,既得到本月最后一天
		day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static void p(Object o) {
		System.out.println(o);
	}

}
