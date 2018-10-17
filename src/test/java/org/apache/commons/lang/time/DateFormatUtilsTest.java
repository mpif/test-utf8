package org.apache.commons.lang.time;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;

public class DateFormatUtilsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date();

		String isoDate = DateFormatUtils.ISO_DATE_FORMAT.format(date);
		String isoDateTimeZone = DateFormatUtils.ISO_DATE_TIME_ZONE_FORMAT.format(date);
		String isoDateTime = DateFormatUtils.ISO_DATETIME_FORMAT.format(date);
		String isoDateTimeTimeZone = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(date);
		String isoTime = DateFormatUtils.ISO_TIME_FORMAT.format(date);
		String isoTimeNoT = DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(date);

		String isoTimeNoTtimeZone = DateFormatUtils.ISO_TIME_NO_T_TIME_ZONE_FORMAT.format(date);
		String isoTimeTimeZone = DateFormatUtils.ISO_TIME_TIME_ZONE_FORMAT.format(date);

		System.out.println("isoDate: " + isoDate);
		System.out.println("isoDateTimeZone: " + isoDateTimeZone);
		System.out.println("isoDateTime: " + isoDateTime);
		System.out.println("isoDateTimeTimeZone: " + isoDateTimeTimeZone);
		System.out.println("isoTime: " + isoTime);
		System.out.println("isoTimeNoT: " + isoTimeNoT);
		System.out.println("isoTimeNoTtimeZone: " + isoTimeNoTtimeZone);
		System.out.println("isoTimeTimeZone: " + isoTimeTimeZone);

		/*
		 * Output: isoDate: 2012-08-14 isoDateTimeZone: 2012-08-14+08:00
		 * isoDateTime: 2012-08-14T11:26:41 isoDateTimeTimeZone:
		 * 2012-08-14T11:26:41+08:00 isoTime: T11:26:41 isoTimeNoT: 11:26:41
		 * isoTimeNoTtimeZone: 11:26:41+08:00 isoTimeTimeZone: T11:26:41+08:00
		 */
		String pattern = "yyyy-MM-dd HH:mm:ss";
		String datePattern = DateFormatUtils.format(date, pattern);
		String longPattern = DateFormatUtils.format(System.currentTimeMillis(), pattern);
		System.out.println("datePattern: " + datePattern);
		System.out.println("longPattern: " + longPattern);
		/*
		 * Output: datePattern: 2012-08-14 11:26:41 longPattern: 2012-08-14
		 * 11:26:41
		 */
		String datePatternLocal = DateFormatUtils.format(date, pattern, Locale.CHINA);
		String datePatternTimeZone = DateFormatUtils.format(date, pattern, TimeZone.getDefault());
		System.out.println("datePatternLocal: " + datePatternLocal);
		System.out.println("datePatternTimeZone: " + datePatternTimeZone);
		/*
		 * Output: datePatternLocal: 2012-08-14 11:26:41 datePatternTimeZone:
		 * 2012-08-14 11:26:41
		 */
	}

}
