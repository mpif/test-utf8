package com.test.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TimerTest test = new TimerTest();
		test.test3();
		// test.test();
		// test.test2();

		// StaticAccess.print();
	}

	public void test3() {
		Timer timer = new Timer();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.SECOND, 3);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		int taskNum = 5;
		try {
			// date = sdf.parse("2012-09-12 09:30:00");
			date = cal.getTime();
			for (int i = 0; i < taskNum; i++) {
				timer.schedule(new PrintTask("" + (i + 1) + ""), date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void test() {
		Timer timer = new Timer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1;
		Date date2;
		try {
			date1 = sdf.parse("2012-09-11 08:56:00");
			date2 = sdf.parse("2012-09-11 08:56:00");
			timer.schedule(new PrintTask("task 001"), date1);
			timer.schedule(new PrintTask("task 002"), date2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void test2() {
		Timer timer = new Timer();
		int taskNum = 5;
		Calendar cal = Calendar.getInstance();
		try {
			for (int i = 0; i < taskNum; i++) {
				cal.setTime(new Date());
				cal.add(Calendar.SECOND, 3);
				timer.schedule(new PrintTask("task " + i, cal.getTime()), cal.getTime());
				Thread.sleep(3 * 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class PrintTask extends TimerTask {

	private String name;
	private Date date = new Date();
	private static Object obj = new Object();

	public PrintTask(String name, Date date) {
		this.name = name;
		this.date = date;
	}

	public PrintTask(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		synchronized (obj) {
			System.out.println("task: " + name + " start");
		}
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println("time: " + sdf.format(date));
	}

	@Override
	public boolean cancel() {
		return super.cancel();
	}

}

class StaticAccess {

	public StaticAccess() {
		System.out.println("construct method called");
	}

	public static void print() {
		System.out.println("print method called");
	}

	static {
		System.out.println("static block called");
	}

	public static StaticAccess access = new StaticAccess();

	// public static void main(String[] args) {
	// print();
	// }

}