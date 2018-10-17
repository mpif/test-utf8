package com.test.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codefans.util.DateUtil;

public class ScheduleTest {

	public static void main(String[] args) {

		ScheduleTest test = new ScheduleTest();
		// test.maintest();
		test.maintest02();

	}

	public void maintest02() {
		Timer timer = new Timer();
		Date date = DateUtil.parse("2012-10-27 01:57:00", "yyyy-MM-dd HH:mm:ss");
		long period = 60 * 1000;
		timer.schedule(new DailyCheckTimeTask("task 1"), date, period);
	}

	public void maintest() {
		Calendar cc = Calendar.getInstance();
		cc.setTime(DateUtil.parse("2012-12-13 11:59:59", "yyyy-MM-dd HH:mm:ss"));
		long t = cc.getTimeInMillis();

		Calendar c = Calendar.getInstance();
		c.setTime(DateUtil.parse("2012-12-13 12:00:01", "yyyy-MM-dd HH:mm:ss"));
		long ct = c.getTimeInMillis();

		pp("ct-t: " + (ct - t));
		pp("c.after(cc): " + c.after(cc));
		pp("cc.after(c): " + cc.before(c));

		pp("113322".substring(0, 2));
		pp("113322".substring(2, 4));

		Calendar sch = Calendar.getInstance();
		sch.set(Calendar.HOUR_OF_DAY, 15);
		sch.set(Calendar.MINUTE, 59);
		sch.set(Calendar.SECOND, 59);

		int sch_hours = sch.get(Calendar.HOUR_OF_DAY);

		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		int now_hours = now.get(Calendar.HOUR_OF_DAY);

		long delay = 0;
		long period = 0;

		if (now_hours >= sch_hours) {
			delay = (24 - now_hours) + sch_hours;
		} else {
			delay = (sch_hours - now_hours);
		}

		pp("delay: " + delay);

		delay = delay * 60 * 60 * 1000;
		period = 24 * 60 * 60 * 1000;

		pp("delay: " + delay);

		Timer timer = new Timer();
		// long delay = 60 * 1000; //one minitue
		// long period = 60 * 60 * 1000; //one hour
		timer.schedule(new DailyCheckTimeTask("aaa"), delay, period);
	}

	@Before
	public void before() {

	}

	@Test
	public void test() {
		// test01();
		test02();
	}

	public void test01() {
		Timer timer = new Timer();
		long delay = 0;
		long period = 60 * 1000;
		// delay = 1 * 1000;
		// period = 24 * 60 * 60 * 1000;
		timer.schedule(new DailyCheckTimeTask("task 1"), delay, period);

		Timer timer2 = new Timer();
		delay = 0;
		period = 60 * 1000;
		timer2.schedule(new DailyCheckTimeTask("task 2"), delay, period);
	}

	public void test02() {
		Timer timer = new Timer();
		Date date = DateUtil.parse("2012-10-27 00:15:00", "yyyy-MM-dd HH:mm:ss");
		long period = 60 * 1000;
		timer.schedule(new DailyCheckTimeTask("task 1"), date, period);
	}

	@After
	public void after() {

	}

	public void p(Object o) {
		System.out.println(o);
	}

	public static void pp(Object o) {
		System.out.println(o);
	}

}
