package com.codefans.java.concurrent.eea;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * @Author: Sean
 * @Time: 2015-05-25 17:31:43
 */
public class NamedThreadFactory implements ThreadFactory {
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
