package com.codefans.java.timer.test01;

import java.util.Date;
import java.util.Timer;

public class TimerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Timer timer = new Timer();
			ScheduleTimerTask task = null;
			try {
				task = new ScheduleTimerTask();
			} catch (Exception e) {
				System.err.println("1: " + e);

			}
			// task.cancel();
			// timer.cancel();
			timer.schedule(task, new Date(), 2 * 1000);
			Thread.sleep(5 * 1000);
		} catch (Exception e) {
			System.err.println("2: " + e);
		}
	}

}
