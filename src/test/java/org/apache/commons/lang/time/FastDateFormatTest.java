package org.apache.commons.lang.time;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.FastDateFormat;

public class FastDateFormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date();

		FastDateFormat dateInstance = FastDateFormat.getDateInstance(0);
		String dateInstanceTime = dateInstance.format(date);

		FastDateFormat dateInstance1 = FastDateFormat.getDateInstance(1);
		String dateInstanceTime1 = dateInstance1.format(date);

		FastDateFormat dateInstance2 = FastDateFormat.getDateInstance(2);
		String dateInstanceTime2 = dateInstance2.format(date);

		FastDateFormat dateInstance3 = FastDateFormat.getDateInstance(3);
		String dateInstanceTime3 = dateInstance3.format(date);

		// java.lang.IllegalArgumentException: Illegal date style 4
		// FastDateFormat dateInstance4 = FastDateFormat.getDateInstance(4);

		System.out.println("dateInstanceTime: " + dateInstanceTime);
		System.out.println("dateInstanceTime1: " + dateInstanceTime1);
		System.out.println("dateInstanceTime2: " + dateInstanceTime2);
		System.out.println("dateInstanceTime3: " + dateInstanceTime3);

		FastDateFormat instance = FastDateFormat.getInstance("yyyy-MM");
		String instanceTime = instance.format(date);
		System.out.println("instanceTime: " + instanceTime);

		String pattern = "yyyy-MM-dd HH:mm:ss";
		FastDateFormat format = FastDateFormat.getInstance(pattern, Locale.CHINA);
		String patternLocal = format.format(date);
		System.out.println("patternLocal: " + patternLocal);

		FastDateFormat format2 = FastDateFormat.getTimeInstance(1);
		String timeInstance = format2.format(date);
		System.out.println("timeInstance: " + timeInstance);

		FastDateFormat format3 = FastDateFormat.getTimeInstance(1, TimeZone.getDefault());
		String styleTimeZone = format3.format(date);
		System.out.println("styleTimeZone: " + styleTimeZone);

		/*
		 * Output: dateInstanceTime: 2012年8月14日 星期二 dateInstanceTime1:
		 * 2012年8月14日 dateInstanceTime2: 2012-8-14 dateInstanceTime3: 12-8-14
		 * instanceTime: 2012-08 patternLocal: 2012-08-14 13:05:57 timeInstance:
		 * 下午01时05分57秒 styleTimeZone: 下午01时05分57秒
		 */
	}

}
