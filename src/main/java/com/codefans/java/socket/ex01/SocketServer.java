package com.codefans.java.socket.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.codefans.java.thread.concurrent.NamedThreadFactory;

/*
 * @Author: Sean
 * @Time: 2015-05-15 10:30:41
 */
public class SocketServer {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SocketServer ss = new SocketServer();
		ss.start();
	}

	public void start() throws Exception {
		// startServerConsumer();
		startSocketServer();
	}

	public void startSocketServer() throws Exception {

		ServerSocket ss = new ServerSocket(6668);

		// long keepAliveTime = 3 * 1000;
		// BlockingQueue<Runnable> workQueue = new
		// LinkedBlockingQueue<Runnable>(maximumPoolSize);
		// BlockingQueue<Runnable> workQueue = new
		// ArrayBlockingQueue<Runnable>(maximumPoolSize);

		// ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
		// maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new
		// ArrayBlockingQueue<Runnable>(maximumPoolSize));

		while (true) {
			Socket socket = ss.accept();
			// InputStream is = null;
			// ObjectInputStream ois = null;
			// TransferObject to = null;
			// is = socket.getInputStream();
			// ois = new ObjectInputStream(is);
			// to = (TransferObject) ois.readObject();
			//
			// synchronized(ServerTaskRunnable.lock) {
			// ServerTaskRunnable.queue.add(to);
			// }

			// ServerTaskRunnable str = new ServerTaskRunnable(to);
			ServerTaskRunnable str = new ServerTaskRunnable(socket);
			thread(str, false);
			// executor.execute(str);

			// socket.close();
			// socket = null;

			// System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
			// executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());

		}
	}

	public void startServerConsumer() {
		int corePoolSize = Runtime.getRuntime().availableProcessors();
		System.out.println("corePoolSize:[" + corePoolSize + "]");
		int maximumPoolSize = corePoolSize * 10;
		System.out.println("maximumPoolSize:[" + maximumPoolSize + "]");

		long initialDelay = 100; // 1s
		long period = 100; // 3s

		// ExecutorService executor =
		// Executors.newFixedThreadPool(maximumPoolSize);
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(corePoolSize,
				new NamedThreadFactory("SocketServerConsumerThread"));
		for (int i = 0; i < corePoolSize; i++) {
			executor.scheduleAtFixedRate(new ServerTaskRunnable(), initialDelay, period, TimeUnit.MILLISECONDS);
		}
		// System.out.println(executor.);
		// executor.shutdown();

	}

	public void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

}
