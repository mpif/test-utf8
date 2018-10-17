package com.test.installservice;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

public class Start implements Daemon {

	// private static Thread thread = null;
	//
	// public static void systemExit(String[] args) {
	// System.out.println("System Exit");
	// thread.interrupt();
	// }
	// /**
	// * @param args
	// */
	// public static void main(String[] args) {
	// System.out.println("Start");
	// thread = new Thread(new Service());
	// thread.start();
	// }

	public Start() {
		super();
		// try {
		// raf = new RandomAccessFile("C:\\services.log", "rw");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
	}

	private RandomAccessFile raf;

	@Override
	public void init(DaemonContext context) throws DaemonInitException, Exception {
		System.out.println("service init");
		// this.writeTo("service start");
	}

	@Override
	public void start() throws Exception {
		System.out.println("service start");
		// this.writeTo("service start");
	}

	@Override
	public void stop() throws Exception {
		System.out.println("service stop");
		// this.writeTo("service stop");
	}

	@Override
	public void destroy() {
		System.out.println("service destroy");
		// try {
		// raf = new RandomAccessFile("C:\\services.log", "rw");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		// this.writeTo("service destroy");
	}

	public void writeTo(String log) {
		try {
			long len = raf.length();
			if (len > 0) {
				raf.seek(len);
			}
			raf.writeBytes(log + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void newLine() {
		try {
			long len = raf.length();
			if (len > 0) {
				raf.seek(len);
			}
			raf.writeBytes("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Start start = new Start();
		try {
			start.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
