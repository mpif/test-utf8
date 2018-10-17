package com.codefans.java.concurrent.deadlock.ex02;

public class DeadLock {
	public static void main(String[] args) {
		Object o1 = new Object(); // 资源1
		Object o2 = new Object(); // 资 源2
		Runner r1 = new Runner(1, o1, o2, 5000); // 线程1
		Runner r2 = new Runner(2, o2, o1, 500); // 线程2
		new Thread(r1).start();
		new Thread(r2).start();
	}
}

class Runner implements Runnable {
	private int id;
	private Object o1; // 所需的资源1
	private Object o2; // 所需的资源2
	private int sleep;

	public Runner(int id, Object o1, Object o2, int sleep) {
		this.id = id;
		this.o1 = o1;
		this.o2 = o2;
		this.sleep = sleep;
	}

	public void run() {
		System.out.println(id);
		// 锁定资源1
		synchronized (o1) {
			try {
				Thread.sleep(sleep); // 这里主要是放大效果，让其他线程获取到时间片
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 锁定资源2
			synchronized (o2) {
				System.out.println("Runner " + id);
			}
		}
	}

}
