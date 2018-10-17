package com.codefans.java.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-5-16 Time: 上午10:07
 * To change this template use File | Settings | File Templates.
 */
public class FileWriteUtil {

	private static File file = null;
	private static FileWriter fw = null;

	private static FileWriteUtil fwu = null;

	public static FileWriteUtil getInstance(String path) {
		try {
			file = new File(path);
			fw = new FileWriter(file, true);
			if (fwu == null) {
				fwu = new FileWriteUtil();
			}
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return fwu;
	}

	private FileWriteUtil() {

	}

	public static void append(String line) {
		try {
			fw.append(line);
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	public static void close() {
		try {
			if (fw != null) {
				fw.flush();
				fw.close();
				fw = null;
			}
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}
}
