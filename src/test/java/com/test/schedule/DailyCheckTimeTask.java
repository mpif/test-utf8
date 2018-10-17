package com.test.schedule;

import java.util.Date;
import java.util.TimerTask;

import com.codefans.util.DateUtil;

public class DailyCheckTimeTask extends TimerTask {

	private String name;

	public DailyCheckTimeTask(String name) {
		this.name = name;
	}

	public void p(Object o) {
		System.out.println(o);
	}

	@Override
	public void run() {
		p("time is: " + DateUtil.format(new Date()) + ", task: " + name);
	}

}
