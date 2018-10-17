package com.codefans.performance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * User: Sean
 * Date: 2015-3-30
 * Time: 下午4:40:36
 */

public class MultiThreadFileCopy {

	private ExecutorService executor;
	private ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
	private String dataPath;
	private String destPath;
	public static int threadIndex = 0;
	public static long taskStartTime = 0;
	public static long taskEndTime = 0;

	public static Object obj = new Object();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MultiThreadFileCopy mtfc = new MultiThreadFileCopy();
		mtfc.copy();
	}

	public void copy() {
		// this.initFiles();
		// this.normalMultiThreadCopy();
		this.javaConcurrentCopy();
	}

	public void initFiles() {
		dataPath = "C:\\tmp2\\fourth";
		int fileCount = 30000;
		FileWriter fw = null;
		long start = System.currentTimeMillis();

		for (int i = 1; i <= fileCount; i++) {
			try {
				String fileName = i + ".txt";
				File f = new File(dataPath + File.separator + fileName);
				if (!f.exists()) {
					f.createNewFile();
				}
				fw = new FileWriter(f);
				fw.write(i + "_" + i + "_" + i + "_" + i);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fw != null) {
						fw.flush();
						fw.close();
						fw = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		long end = System.currentTimeMillis();
		System.out.println(fileCount + " files created, total cost:[" + (end - start) / 1000 + "s]");

	}

	public void normalMultiThreadCopy() {

		try {

			// dataPath = "C:/tmp2/second";
			// destPath = "F:/temp/second";
			dataPath = "C:/tmp2/third";
			destPath = "F:/temp/third";

			File dir = new File(destPath);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}

			long start = System.currentTimeMillis();
			this.collectData();
			long end = System.currentTimeMillis();
			System.out.println("data total collect:[" + queue.size() + "], time cost:[" + (end - start) / 1000 + "s].");

			start = System.currentTimeMillis();
			Iterator<String> iter = queue.iterator();
			final List<String> items = new ArrayList<String>();
			final String innerDataPath = dataPath;
			final String innerDestPath = destPath;

			CountDownLatch cdl = new CountDownLatch(3);

			int itemCount = 0;
			int num = 0;
			while (iter.hasNext()) {
				itemCount++;
				items.add(iter.next());

				if (itemCount == 10000) {
					num++;
					List<String> allItems = new ArrayList<String>();
					allItems.addAll(items);
					new Thread(new FileCopyRunnable(allItems, innerDataPath, innerDestPath, cdl)).start();
					itemCount = 0;
					items.clear();
					if (num == 3) {
						break;
					}
				}

			}

			cdl.await();

			System.out.println("items.size():[" + items.size() + "]");
			end = System.currentTimeMillis();

			System.out.println("file copy finished, time cost:[" + (end - start) / 1000 + "s].");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class FileCopyRunnable implements Runnable {

		List<String> items;
		String innerDataPath = dataPath;
		String innerDestPath = destPath;
		CountDownLatch countDownLatch;

		public FileCopyRunnable(List<String> items, String innerDataPath, String innerDestPath,
				CountDownLatch countDownLatch) {
			this.items = items;
			this.innerDataPath = innerDataPath;
			this.innerDestPath = innerDestPath;
			this.countDownLatch = countDownLatch;
			synchronized (MultiThreadFileCopy.obj) {
				MultiThreadFileCopy.threadIndex++;
			}
		}

		@Override
		public void run() {
			// long start = System.currentTimeMillis();
			// synchronized(MultiThreadFileCopy.obj) {
			//
			// if(MultiThreadFileCopy.threadIndex == 1) {
			// MultiThreadFileCopy.taskStartTime = start;
			// }
			for (int i = 0; i < items.size(); i++) {
				String item = items.get(i);
				copy(innerDataPath + File.separator + item, innerDestPath + File.separator + item);
			}
			// long end = System.currentTimeMillis();
			// if(MultiThreadFileCopy.threadIndex == 3) {
			// MultiThreadFileCopy.taskStartTime = end;
			// System.out.println("task total cost:[" +
			// (MultiThreadFileCopy.taskStartTime -
			// MultiThreadFileCopy.taskStartTime) / 1000 + "s]");
			// System.out.println("thread index:[" +
			// MultiThreadFileCopy.threadIndex + "] finished, time cost:[" +
			// (end - start) / 1000 + "s]");
			// }
			// }

			countDownLatch.countDown();

		}

	}

	public void copy(String sourceFile, String destFile) {
		this.copy(new File(sourceFile), new File(destFile));
	}

	public void copy(File sourceFile, File destFile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			if (!destFile.exists()) {
				destFile.createNewFile();
			}
			fis = new FileInputStream(sourceFile);
			fos = new FileOutputStream(destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.copy(fis, fos);
	}

	public void copy(InputStream in, OutputStream out) {
		int len = 0;
		byte[] b = new byte[1024];
		try {
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void javaConcurrentCopy() {

		this.executeCopy();
	}

	public void collectData() {
		this.collectData(dataPath);
	}

	public void collectData(String path) {
		File dir = new File(path);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					this.collectData(path + File.separator + f.getName());
				} else {
					// queue.add(f.getAbsolutePath());
					System.out.println("collect: " + f.getAbsolutePath());
					queue.add(f.getName());
				}
			}
		}
	}

	public void executeCopy() {
		try {
			int processors = Runtime.getRuntime().availableProcessors();
			int threadPoolSize = processors * 5;
			executor = Executors.newFixedThreadPool(threadPoolSize);
			System.out.println("processors:[" + processors + "]");
			System.out.println("threadPoolSize:[" + threadPoolSize + "]");

			dataPath = "C:/tmp2/third";
			destPath = "F:/temp/third";

			File dir = new File(destPath);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}

			long start = System.currentTimeMillis();
			this.collectData();
			long end = System.currentTimeMillis();
			System.out.println("data total collect:[" + queue.size() + "], time cost:[" + (end - start) / 1000 + "s].");

			start = System.currentTimeMillis();
			Iterator<String> iter = queue.iterator();
			final List<String> items = new ArrayList<String>();
			final String innerDataPath = dataPath;
			final String innerDestPath = destPath;

			FileCopy fc = null;
			Future<FileCopyTaskResult> ft = null;
			List<Future<FileCopyTaskResult>> resultList = new ArrayList<Future<FileCopyTaskResult>>();

			start = System.currentTimeMillis();

			int itemCount = 0;
			int num = 0;
			int taskIndex = 1;
			while (iter.hasNext()) {
				itemCount++;
				items.add(iter.next());

				if (itemCount == 10000) {
					num++;
					List<String> allItems = new ArrayList<String>();
					allItems.addAll(items);
					// new Thread(new FileCopyRunnable(allItems, innerDataPath,
					// innerDestPath, cdl)).start();
					fc = new FileCopy(allItems, innerDataPath, innerDestPath, taskIndex);
					System.out.println("before submit, taskIndex:[" + taskIndex + "], time:" + new Date());
					taskIndex++;
					ft = executor.submit(fc);
					resultList.add(ft);

					itemCount = 0;
					items.clear();
					if (num == 3) {
						break;
					}
				}

			}

			// CountDownLatch cdl = new CountDownLatch(3);

			FileCopyTaskResult result = null;
			Future<FileCopyTaskResult> future = null;
			for (int i = 0; i < resultList.size(); i++) {
				future = resultList.get(i);
				result = future.get();
				System.out.println("instance of FileCopyTaskResult:[" + result + "], task index:["
						+ result.getTaskIndex() + "], status:[" + result.getStatus() + "], time:" + new Date());
			}

			end = System.currentTimeMillis();
			System.out.println("time cost:[" + (end - start) / 1000 + "s]");

			executor.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class FileCopy implements Callable<FileCopyTaskResult> {

		List<String> items;
		String innerDataPath = dataPath;
		String innerDestPath = destPath;
		CountDownLatch countDownLatch;
		int taskIndex;

		public FileCopy(List<String> items, String innerDataPath, String innerDestPath) {
			this.items = items;
			this.innerDataPath = innerDataPath;
			this.innerDestPath = innerDestPath;
		}

		public FileCopy(List<String> items, String innerDataPath, String innerDestPath, int taskIndex) {
			this.items = items;
			this.innerDataPath = innerDataPath;
			this.innerDestPath = innerDestPath;
			this.taskIndex = taskIndex;
		}

		public FileCopy(List<String> items, String innerDataPath, String innerDestPath, CountDownLatch countDownLatch) {
			this.items = items;
			this.innerDataPath = innerDataPath;
			this.innerDestPath = innerDestPath;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public FileCopyTaskResult call() throws Exception {
			// String result = "hello world!";

			System.out.println("task be called, taskIndex:[" + taskIndex + "], time:" + new Date());

			FileCopyTaskResult result = new FileCopyTaskResult(taskIndex);

			for (int i = 0; i < items.size(); i++) {
				String item = items.get(i);
				copy(innerDataPath + File.separator + item, innerDestPath + File.separator + item);
			}

			result.setStatus("success");
			return result;
		}

	}

	class FileCopyTaskResult {

		int taskIndex;
		String status = "fail";

		public FileCopyTaskResult(int taskIndex) {
			this.taskIndex = taskIndex;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getTaskIndex() {
			return taskIndex;
		}

		public void setTaskIndex(int taskIndex) {
			this.taskIndex = taskIndex;
		}

	}
}
