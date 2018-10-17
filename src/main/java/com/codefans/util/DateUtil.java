package com.codefans.util;

import org.apache.commons.lang.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

	public static Date parse(String source, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public void p(Object o) {
		System.out.println(o);
	}

	private int dayCount(String dateStartStr, String dateEndStr) {
		int totalDays = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateStart = sdf.parse(dateStartStr);
			Date dateEnd = sdf.parse(dateEndStr);

			// Calendar calendarStart = Calendar.getInstance();
			// calendarStart.setTime(dateStart);
			// Calendar calendarEnd = Calendar.getInstance();
			// calendarEnd.setTime(dateEnd);
			//
			// totalDays = getDaysBetween(calendarStart, calendarEnd);

			totalDays = getIntervalDays(dateStart, dateEnd);

		} catch (ParseException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}

		return totalDays;
	}

	// the same as the getDaysBetween
	public int getIntervalDays(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	// the same as the getIntervalDays
	public int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	public static String getDateStr(long timeMillis) {

		String result = "";

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeMillis);
		Date date = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = sdf.format(date);

		return result;

	}

	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	private static final String YYYY_MM_DD = "yyyy-MM-dd";
	private static final String YYYYMM = "yyyyMM";

	public static final FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");


	/**
	 * 将时间格式化为:yyyy-MM-dd HH:mm:ss这种格式
	 * @param date
	 * @return
	 */
	public static String formatYYYYMMDDHHMMSS(Date date) {
		return fastDateFormat.format(date);
	}

	public static String formatYYYYMM(Date date) {
		FastDateFormat fdf = FastDateFormat.getInstance(YYYYMM);
		return fdf.format(date);
	}

	/**
	 * 计算指定日期过month个月之后的时间
	 * 例如：2017-03-15 18:34:25,设置3个月之后的时间为:2017-06-14 23:59:59或者2017-06-15 00:00:00
	 * @param date
	 * @param month
	 * @param dateFormat
	 * @return
	 */
	public static String getMonthLater(Date date, int month, int dateFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		if(dateFormat == 1) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		} else {
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}
		return formatYYYYMMDDHHMMSS(calendar.getTime());
	}

	public static String getMonthLater(Date date, int month) {
		return getMonthLater(date, month, 0);
	}

	/**
	 * 获取当前月份,格式为201705
	 * @return
	 */
	public static String getCurrentMonth() {
		return formatYYYYMM(new Date());
	}


	/**
	 * 将201705这种格式的日期转换为Date对象
	 * @param dateStr
	 * @return
	 */
	public static Date parse(String dateStr) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(YYYYMM);
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * dateStr格式为201705
	 * @param dateStr
	 * @return
	 */
	public static String getBeginDateOfMonth(String dateStr) {
		Date date = parse(dateStr);
		return getBeginDateOfMonth(date);
	}

	/**
	 * dateStr格式为201705
	 * @param dateStr
	 * @return
	 */
	public static String getEndDateOfMonth(String dateStr) {
		Date date = parse(dateStr);
		return getEndDateOfMonth(date);
	}

	/**
	 * 获取给定时间所在月份的最开始时间
	 * 如date为：2017-05-04 13:34:23，那么该方法将返回: 2017-05-01 00:00:00
	 * @param date
	 * @return
	 */
	public static String getBeginDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date firstDayOfMonth = calendar.getTime();
		return formatYYYYMMDDHHMMSS(firstDayOfMonth);
	}

	/**
	 * 获取给定时间所在月份的最后时间
	 * 如date为：2017-05-04 13:34:23，那么该方法将返回: 2017-05-31 23:59:59
	 * @param date
	 * @return
	 */
	public static String getEndDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date lastDayOfMonth = calendar.getTime();
		return formatYYYYMMDDHHMMSS(lastDayOfMonth);
	}

	public static void main(String[] args) {
		DateUtil util = new DateUtil();
		int totalDays = util.dayCount("2014-04-23", "2015-01-10");
		System.out.println("totalDays:[" + totalDays + "]");
	}



}
