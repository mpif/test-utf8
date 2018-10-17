package com.codefans.java.socket.ex01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @Author: Sean
 * @Time: 2015-05-18 17:53:45
 */
public class ServerTaskThreadPool implements Runnable {

	private int corePoolSize;

	public ServerTaskThreadPool(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ExecutorService executor = Executors.newFixedThreadPool(corePoolSize);

		while (true) {

			int taskCount = ConcurrentSocketServer.queue.size();
			// int taskCount = corePoolSize * 100;
			int taskPerSubmit = corePoolSize * 100;
			while (taskCount > 0) {
				for (int i = 0; i < taskPerSubmit; i++) {
					executor.submit(new ServerTaskRunnable());
				}
				taskCount = taskCount - taskPerSubmit;
			}
			// executor.shutdown();
		}

	}

}
