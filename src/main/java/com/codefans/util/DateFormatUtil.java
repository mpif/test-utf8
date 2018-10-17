package com.codefans.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatUtil {

	String DEFAULT_TIMEZONE = "GMT+8";
	String DEFAULT_FORMAT = "d-MMM-yyyy HH:mm (z)";

	public static void main(String[] args) {

		DateFormatUtil dfu = new DateFormatUtil();
		// String dateStr = "30 Jul 2013 22:46:25 -0400";
		String dateStr = "Tue Oct 12 00:00:00 UTC 0800 2010";
		// String dateStr = "Wed Jul 31 10:46:25 CST 2013";
		// Date date = dfu.parseDate(dateStr);
		// System.out.println(date);

		// System.out.println(new Date());
		// System.out.println(Calendar.getInstance().getTime());

		// dfu.testCalendar();

		// System.out.printf("Hello, %s. Next year, you'll be %d", "c", 22);

		// dfu.testTimeZone();

		try {

			// System.out.println(TimeZone.getDefault());
			// System.out.println(Calendar.getInstance().getTimeZone().getDisplayName());
			// System.out.println(Calendar.getInstance().getTimeZone().getID());

			String convertString = "Wed Jul 31 10:46:25 UTC 2013";
			// String convertString = new Date().toString();
			System.out.println("sourceString: " + convertString);
			String format = "EEE MMM dd HH:mm:ss ZZZ yyyy";

			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
			System.out.println("targetString: " + sdf.parse(convertString));

			// String sourceTimeZone = "GMT+8";
			// String targetTimeZone = "GMT-6";
			String sourceTimeZone = "UTC-6";
			// String targetTimeZone = "CST6CDT";
			// String targetTimeZone = "US/Central";
			String targetTimeZone = "UTC+8";
			// TimeZone.setDefault(TimeZone.getTimeZone("CST6CDT"));
			// Date d = dfu.ConverDateGMT(convertString, format, sourceTimeZone,
			// targetTimeZone);
			// System.out.println("targetString: " + d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 转换时间时区
	 * 
	 * @param convertString
	 *            需要转的时间字符串
	 * @param format
	 *            格式话字符串 例如d-MMM-yyyy HH:mm (z)
	 * @param sourceTimeZone
	 *            源时间时区
	 * @param targetTimeZone
	 *            目标时间时区
	 * @return
	 * @throws ParseException
	 */
	public Date ConverDateGMT(String convertString, String format, String sourceTimeZone, String targetTimeZone)
			throws ParseException {

		Date date = null;

		if (isEmpty(sourceTimeZone)) {
			sourceTimeZone = DEFAULT_TIMEZONE;
		}

		if (isEmpty(targetTimeZone)) {
			targetTimeZone = DEFAULT_TIMEZONE;
		}

		if (isEmpty(format)) {
			format = DEFAULT_FORMAT;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);

		// 获取传入的时间值
		Long time = new Date(sdf.parse(convertString).getTime()).getTime();

		// 获取源时区时间相对的GMT时间
		Long sourceRelativelyGMT = time - TimeZone.getTimeZone(sourceTimeZone).getRawOffset();

		// GMT时间+目标时间时区的偏移量获取目标时间
		Long targetTime = sourceRelativelyGMT + TimeZone.getTimeZone(targetTimeZone).getRawOffset();

		date = new Date(targetTime);

		return date;

	}

	/**
	 * Check empty string
	 * 
	 * <pre>
	 * null: true "": true " ":true </>
	 * 
	 * @param value
	 * @return
	 */
	public boolean isEmpty(String value) {
		boolean emptyFlg = false;
		if (null == value || value.trim().length() <= 0) {
			emptyFlg = true;
		}
		return emptyFlg;
	}

	public void testTimeZone() {
		// UTC-12:00 国际日期变更线西, 时间格式：Mon Aug 26 14:42:02 GMT-12:00 2013

		String[] availableIds = TimeZone.getAvailableIDs();
		// Date now = new Date();
		long millis = System.currentTimeMillis();
		TimeZone timeZone = null;
		Calendar cal = null;
		Map<String, String> times = new HashMap<String, String>();
		String time = "";
		for (int i = 0; i < availableIds.length; i++) {
			System.out.println(availableIds[i]);
			timeZone = TimeZone.getTimeZone(availableIds[i]);
			cal = Calendar.getInstance();
			cal.setTimeZone(timeZone);
			cal.setTimeInMillis(millis);
			// cal.setTime(now);
			// System.out.println(cal.getTime());
			time = cal.getTime().toString();
			times.put(time, time);
			// System.out.println(cal.getTime());
		}

		System.out.println("ids.length:[" + availableIds.length + "]");
		System.out.println("times.length:[" + times.size() + "]");

		System.out.println(times);
	}

	public void testCalendar() {
		System.out.println(Calendar.JANUARY);// 0
		System.out.println(Calendar.JUNE);// 5
		System.out.println("===================================");
		System.out.println(Calendar.MONDAY);// 2
		System.out.println(Calendar.SUNDAY);// 1
		System.out.println(Calendar.SATURDAY);// 7
		System.out.println("===================================");

		System.out.println(Calendar.DAY_OF_MONTH);

		System.out.println("===================================");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, 7); // 8月
		cal.set(Calendar.DAY_OF_MONTH, 3);

		System.out.println(cal.getTime());
	}

	public Date parseDate(String dateStr) {
		Calendar cal = Calendar.getInstance();
		String[] dateArr = dateStr.split(" ");
		String tmp = "";
		for (int i = 0; i < dateArr.length; i++) {
			tmp = dateArr[i];
			if (tmp != null && tmp.trim().length() > 0) {
				if (tmp.length() == 2) {
					cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tmp));
				} else if (tmp.length() == 3) {
					int mon = getMonth(tmp);
					if (mon != -1) {
						cal.set(Calendar.MONTH, mon);
					}
				} else if (tmp.length() == 4) {
					if (!tmp.startsWith("0")) {
						cal.set(Calendar.YEAR, Integer.parseInt(tmp));
					}
				} else if (tmp.length() == 8) {
					String[] times = tmp.split(":");
					cal.set(Calendar.HOUR, Integer.parseInt(times[0]));
					cal.set(Calendar.MINUTE, Integer.parseInt(times[1]));
					cal.set(Calendar.SECOND, Integer.parseInt(times[2]));
				}
			}
		}
		return cal.getTime();
	}

	public int getMonth(String mon) {
		int month = -1;
		if (mon != null) {
			if (mon.equals("Jan")) {
				month = 0;
			}
			if (mon.equals("Feb")) {
				month = 1;
			}
			if (mon.equals("Mar")) {
				month = 2;
			}
			if (mon.equals("Apr")) {
				month = 3;
			}
			if (mon.equals("May")) {
				month = 4;
			}
			if (mon.equals("Jun")) {
				month = 5;
			}
			if (mon.equals("Jul")) {
				month = 6;
			}
			if (mon.equals("Aug")) {
				month = 7;
			}
			if (mon.equals("Sep")) {
				month = 8;
			}
			if (mon.equals("Oct")) {
				month = 9;
			}
			if (mon.equals("Nov")) {
				month = 10;
			}
			if (mon.equals("Dec")) {
				month = 11;
			}
		}
		return month;
	}

	public boolean isCSTFormat(String date) {
		boolean flag = false;
		try {
			String regex = "[A-Z]{1}[a-z]{2} [A-Z]{1}[a-z]{2} [0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2} CST [0-9]{4}";
			Pattern pattern = Pattern.compile(regex);
			// String date = "Wed Jul 31 15:57:11 CST 2013";
			Matcher matcher = pattern.matcher(date);
			while (matcher.find()) {
				flag = true;
				// System.out.println(matcher.group());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}