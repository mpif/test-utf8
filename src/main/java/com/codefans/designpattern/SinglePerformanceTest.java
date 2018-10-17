package com.codefans.designpattern;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SinglePerformanceTest {

	static public class CountInc {
		private volatile long count = 0;

		public void action() {
			count++;
		}
	}

	static public class LazySingle extends CountInc {

		private static LazySingle instance;

		private LazySingle() {

		}

		public static synchronized LazySingle getInstance() {
			if (instance == null) {
				instance = new LazySingle();
			}
			return instance;
		}
	}

	static public class StarveSingle extends CountInc {

		private static StarveSingle instance = new StarveSingle();

		private StarveSingle() {

		}

		public static StarveSingle getInstance() {

			return instance;
		}

	}

	static class Test {
		public Test(String id, CyclicBarrier barrier, long count, int threadNum, ExecutorService executor) {

		}

		public void startTest() {
			test();
		}

		public void test() {

		}
	}

	static enum SINGLE {
		INSTANCE;

		private static volatile long count = 0;

		public void action() {
			count++;
		}
	}

	static class LazySingleTest extends Test {

		public LazySingleTest(String id, CyclicBarrier barrier, long count, int threadNum, ExecutorService executor) {
			super(id, barrier, count, threadNum, executor);
		}

		@Override
		public void test() {
			LazySingle.getInstance().action();

		}
	}

	static class StarveSingleTest extends Test {

		public StarveSingleTest(String id, CyclicBarrier barrier, long count, int threadNum, ExecutorService executor) {
			super(id, barrier, count, threadNum, executor);
		}

		@Override
		public void test() {
			StarveSingle.getInstance().action();

		}

	}

	static class EnumSingleTest extends Test {

		public EnumSingleTest(String id, CyclicBarrier barrier, long count, int threadNum, ExecutorService executor) {
			super(id, barrier, count, threadNum, executor);
		}

		public void test() {
			SINGLE.INSTANCE.action();
		}

	}

	static void testPerformanceTemplate(String id, long count, int threadNum, final ExecutorService executor) {
		final CyclicBarrier barrier = new CyclicBarrier(threadNum + 1, new Thread() {

			@Override
			public void run() {
				// System.err.println("-----------------");
			}
		});

		System.out.println("==============================");
		System.out.println("count = " + count + "/t" + "Thread Count = " + threadNum);

		new LazySingleTest("LazySingleTest ", barrier, count, threadNum, executor).startTest();
		new StarveSingleTest("StarveSingleTest ", barrier, count, threadNum, executor).startTest();
		new EnumSingleTest("EnumSingleTest ", barrier, count, threadNum, executor).startTest();

		executor.shutdownNow();

		System.out.println("==============================");
	}

	private static int COUNT = 1000000;
	private static int THREAD_NUM = 10;

	public static void main(String[] args) {
		for (int i = 1; i < 5; i++) {
			ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM * i);
			testPerformanceTemplate(null, COUNT * i, THREAD_NUM * i, executor);
		}
	}
}
