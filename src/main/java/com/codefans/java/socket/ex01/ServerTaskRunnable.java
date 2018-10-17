package com.codefans.java.socket.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;

/*
 * @Author: Sean
 * @Time: 2015-05-15 16:37:59
 */
public class ServerTaskRunnable implements Runnable {

	private TransferObject to;
	private Socket socket;

	private static int index = 0;

	private int taskCountPreThread = 10;

	public static Object lock = new Object();
	// public static LinkedList<TransferObject> queue = new
	// LinkedList<TransferObject>();

	public ServerTaskRunnable(TransferObject to) {
		this.to = to;
		synchronized (lock) {
			index++;
		}
	}

	public ServerTaskRunnable(Socket socket) {
		this.socket = socket;
	}

	public ServerTaskRunnable() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// synchronized(lock) {
		// try {
		// Thread.sleep(3 * 1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		TransferObject to = null;
		for (int i = 0; i < taskCountPreThread; i++) {
			synchronized (ConcurrentSocketServer.lock) {
				to = ConcurrentSocketServer.queue.poll();
			}
			if (to != null) {
				System.out.println(Thread.currentThread().getName() + ", username:" + to.getUsername() + ", password:"
						+ to.getPassword());
				// System.out.println(Thread.currentThread().getName() + "_task
				// index:" + index + ", username:" + to.getUsername() + ",
				// password:" + to.getPassword());
				// System.out.println("username:[" + to.getUsername() + "],
				// password:[" + to.getPassword() + "]");
			}
		}

		// }

		// try {
		//
		// try {
		//
		// long sleepTime = 10 * 1000;
		//// System.out.println("sleep time: [" + sleepTime + "]");
		//// System.out.println("sleep begin:[" + System.currentTimeMillis() +
		// "]");
		// Thread.sleep(sleepTime);
		//// System.out.println("sleep end:[" + System.currentTimeMillis() +
		// "]");
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// InputStream is = null;
		// ObjectInputStream ois = null;
		// TransferObject to = null;
		// is = socket.getInputStream();
		// ois = new ObjectInputStream(is);
		// to = (TransferObject) ois.readObject();
		// System.out.println(Thread.currentThread().getName() + "_task index:"
		// + index + ", username:" + to.getUsername() + ", password:" +
		// to.getPassword());
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
