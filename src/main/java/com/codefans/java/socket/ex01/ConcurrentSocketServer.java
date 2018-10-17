package com.codefans.java.socket.ex01;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.codefans.java.thread.concurrent.NamedThreadFactory;

/*
 * @Author: Sean
 * @Time: 2015-05-18 16:32:30
 */
public class ConcurrentSocketServer {

	public static Object lock = new Object();
	public static LinkedBlockingQueue<TransferObject> queue = new LinkedBlockingQueue<TransferObject>();

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ConcurrentSocketServer css = new ConcurrentSocketServer();
		css.start();
	}

	public void start() throws Exception {
		startServerConsumer();
		startSocketServer();
	}

	public void startSocketServer() throws Exception {

		ServerSocket ss = new ServerSocket(6668);

		while (true) {
			Socket socket = ss.accept();
			InputStream is = null;
			ObjectInputStream ois = null;
			TransferObject to = null;
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			to = (TransferObject) ois.readObject();

			synchronized (ConcurrentSocketServer.lock) {
				ConcurrentSocketServer.queue.put(to);
			}

			// System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
			// executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());

		}
	}

	public void startServerConsumer() {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		System.out.println("corePoolSize:[" + corePoolSize + "]");
		int maximumPoolSize = corePoolSize * 10;
		System.out.println("maximumPoolSize:[" + maximumPoolSize + "]");

		long initialDelay = 1000; // 1s
		long period = 1000; // 3s

		// ExecutorService executor = Executors.newFixedThreadPool(1);
		// ScheduledExecutorService executor =
		// Executors.newScheduledThreadPool(1, new
		// NamedThreadFactory("SocketServerConsumerThread"));

		// for(int i = 0; i < corePoolSize; i ++) {
		// executor.submit(new ServerTaskRunnable());
		// executor.submit(new ServerTaskThreadPool(corePoolSize));
		thread(new ServerTaskThreadPool(corePoolSize), false);
		// ScheduledFuture future =
		// executor.scheduleAtFixedRate(new ServerTaskThreadPool(corePoolSize),
		// initialDelay, period, TimeUnit.MILLISECONDS);

		// }

		// executor.shutdown();

	}

	public void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

}
