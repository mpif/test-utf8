package com.codefans.java.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.ParseException;

public class TimerTest {

	private Timer timer = new Timer();

	// 启动计时器
	public void lanuchTimer() {
		try {
			timer.schedule(new MyTimerTask(), 1000 * 3, 2 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().equals("Timer already cancelled.")) {
				timer = new Timer();
				lanuchTimer();
			}
		}
	}

	// 向计时器添加一个任务
	public void addOneTask() {
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("hello world");
			}
		}, 1000 * 1, 1000 * 5);
	}

	public void cancle() {
		timer.cancel();
	}

	public Timer getTimer() {
		timer = new Timer();
		return timer;
	}

	public static void main(String[] args) throws Exception {
		TimerTest test = new TimerTest();
		test.lanuchTimer();
		Thread.sleep(1000 * 5);// 5秒钟之后添加一个新任务
		test.addOneTask();

		// Timer timer = new Timer();
		// timer.cancel();
		// try {
		// timer.schedule(new TimerTask() {
		// @Override
		// public void run() {
		//
		// }
		// }, new Date(), 3000);
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }
	}

	class MyTimerTask extends TimerTask {

		public void run() {
			try {
				// throw new MessagingException();
				throw new OutOfMemoryError();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			// throw new RuntimeException();
			// throw new NullPointerException();
		}

	}
}
