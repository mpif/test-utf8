package com.codefans.thread.concurrent;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//import com.messagesolution.search.core.shop.ThreadShop.NamedThreadFactory;

public class ScheduledThreadPoolExecutorTest {

	private long SCANNER_DELAY = 1;
	private long SCANNER_PERIOD = 2;

	public static void main(String[] args) {
		ScheduledThreadPoolExecutorTest stpeTest = new ScheduledThreadPoolExecutorTest();
		stpeTest.test();
	}

	public void test() {

		String name = "Scanner Universalidmap Single thread";
		ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(3, new NamedThreadFactory(name));
		scheduler.scheduleAtFixedRate(new ScheduledTask("A"), SCANNER_DELAY, SCANNER_PERIOD, TimeUnit.SECONDS);

		// scheduler.submit(new ScheduledTask("B"));
	}

}

class ScheduledTask implements Runnable {

	private String name;

	public ScheduledTask(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// try {
		// Thread.sleep(5 * 1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		System.out.println("[" + name + "], time:[" + new Date() + "]");
	}

}

class NamedThreadFactory implements ThreadFactory {
	private static final AtomicInteger threadPoolNumber = new AtomicInteger(1);

	private final ThreadGroup group;

	private final AtomicInteger threadNumber = new AtomicInteger(1);

	private static final String NAME_PATTERN = "%s-%d-thread";

	private final String threadNamePrefix;

	/**
	 * Creates a new {@link NamedThreadFactory} instance
	 * 
	 * @param threadNamePrefix
	 *            the name prefix assigned to each thread created.
	 */
	public NamedThreadFactory(String threadNamePrefix) {
		final SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		this.threadNamePrefix = String.format(NAME_PATTERN, checkPrefix(threadNamePrefix),
				threadPoolNumber.getAndIncrement());
	}

	private static String checkPrefix(String prefix) {
		return prefix == null || prefix.length() == 0 ? "Engine" : prefix;
	}

	/**
	 * Creates a new {@link Thread}
	 * 
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	public Thread newThread(Runnable r) {
		final Thread t = new Thread(group, r,
				String.format("%s-%d", this.threadNamePrefix, threadNumber.getAndIncrement()), 0);
		t.setDaemon(false);
		t.setPriority(Thread.NORM_PRIORITY);
		return t;
	}

}
