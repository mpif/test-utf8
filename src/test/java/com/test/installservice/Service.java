package com.test.installservice;

/**
 * Created By MyEclipse 8.6 M1 User: caisz Date: 2012-8-23 Time: 下午04:05:32 To
 * change this template use Window | Preferences | Java | Code Style | Code
 * Templates
 */
public class Service implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println("running.....");
			try {
				Thread.sleep(10 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
