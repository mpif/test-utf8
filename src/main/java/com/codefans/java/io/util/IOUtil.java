package com.codefans.java.io.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class IOUtil {

	private String path;
	private FileWriter fileWriter;

	public IOUtil(String path) {
		this.path = path;
		try {

			File f = new File(path);
			if (!f.exists()) {
				f.createNewFile();
			}

			fileWriter = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static IOUtil ioUtil = null;

	public static IOUtil getInstance(String path) {
		if (ioUtil == null) {
			ioUtil = new IOUtil(path);
		}
		return ioUtil;
	}

	public void append(String str) {
		try {
			fileWriter.append(str + "\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void append(List<String> strs) {
		if (strs == null || strs.size() == 0) {
			System.out.println("strs is empty.");
		}
		try {
			for (int i = 0; i < strs.size(); i++) {
				fileWriter.append(strs.get(i) + "\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (fileWriter != null) {
				fileWriter.close();
				fileWriter = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close(InputStream is, OutputStream os) {

	}

	public void close(InputStream is) {
		try {
			if (is != null) {
				is.close();
				is = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close(OutputStream os) {
		try {
			if (os != null) {
				os.close();
				os = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
