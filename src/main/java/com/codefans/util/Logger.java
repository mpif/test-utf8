package com.codefans.util;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.codefans.java.util.StringUtils;

public class Logger {

	private String path;
	private RandomAccessFile raf;
	private String pattern = "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat sdf = new SimpleDateFormat(pattern);

	public Logger() {

	}

	private Logger(String path) {
		this.path = path;
		File f = new File(this.path);
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			raf = new RandomAccessFile(f, "rw");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Logger logger = null;

	public static Logger getInstance(String path) {
		if (logger == null) {
			logger = new Logger(path);
		}
		return logger;
	}

	public void info(Object o) {
		try {
			StackTraceElement elm = new Throwable().getStackTrace()[1];
			String className = elm.getClassName();
			className = className.substring(className.lastIndexOf(".") + 1) + ".java";
			StringBuilder sb = new StringBuilder();
			if (StringUtils.isNotEmpty(this.path)) {
				long num = raf.length();
				raf.seek(num);
				raf.writeBytes(sb.toString());
			}
			sb.append(sdf.format(new Date())).append(" (").append(className).append(":").append(elm.getLineNumber())
					.append(") ::  ").append(o).append("\n");
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
