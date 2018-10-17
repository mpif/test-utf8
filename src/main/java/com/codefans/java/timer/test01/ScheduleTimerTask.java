package com.codefans.java.timer.test01;

import java.util.TimerTask;

public class ScheduleTimerTask extends TimerTask {

	@Override
	public void run() {
		throw new IllegalStateException("IllegalStateException...");
		// try {
		// throw new IOException("RuntimfdeException...");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// System.out.println("task execute!");
	}

}
