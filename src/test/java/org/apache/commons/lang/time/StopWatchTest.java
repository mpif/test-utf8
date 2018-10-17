package org.apache.commons.lang.time;

import org.apache.commons.lang3.time.StopWatch;

public class StopWatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StopWatch watch = new StopWatch();
		watch.start();

		try {
			for (int i = 0; i < 10000; i++) {
				String str = "" + i + "";
			}
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		watch.stop();

		System.out.println(watch.getTime());
	}

}
