package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CollectDirectory {

	private static int count = 0;
	private static BufferedWriter bw = null;
	private static String destDirectory;
	private static String listFile = "paths";
	private static String flag = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		args = new String[] { "D:\\csz\\test\\source", "D:\\csz\\test\\dest" };
		destDirectory = args[1];

		if (args.length == 0) {
			throw new Exception("请输入你要备份的目录，以及存放备份的地址！");
		} else if (args.length == 1) {
			throw new Exception("请输入存放备份的地址！");
		} else {
			// System.out.println(args[0]);
			File source = new File(args[0]);
			File dest = new File(args[1]);
			if (!source.isDirectory()) {
				throw new Exception("您输入的源目录不是一个目录，可能是一个文件，请重新输入！");
			}
			if (!dest.exists()) {
				dest.mkdir();
			}
		}

		// System.out.println("0000" + Integer.parseInt("0"));

		// System.out.println("path: " + (searchFile() + ".txt") + ", line 37");

		init();
		backup(args[0], args[1]);
		destroy();
	}

	public static void backup(String sourcePath, String destPath) {
		File source = new File(sourcePath);
		if (source.isDirectory()) {
			File[] files = source.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file.isDirectory()) {
					String name = file.getName();
					String sorPath = sourcePath + File.separator + name;
					String dstPath = destPath + File.separator + name;
					mkdir(dstPath);
					backup(sorPath, dstPath);
				}
			}
		}
	}

	public static void mkdir(String path) {
		File file = new File(path);
		if (!file.exists() || !file.isDirectory()) {
			if ("File".equals(flag)) {
				pf("new path " + (++count) + ": " + path);
			} else {
				ps("new path " + (++count) + ": " + path);
			}
			file.mkdir();
		}
	}

	public static void ps(Object obj) {
		System.out.println(obj);
	}

	public static String searchFile() {
		String name = "";
		int index = -1;
		boolean isnew = false;

		File direct = new File(destDirectory);
		File[] files = direct.listFiles();
		// System.out.println("files.length: " + files.length);
		if (files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				String temp = file.getName();
				// System.out.println("temp: " + temp);
				if (temp.equalsIgnoreCase((listFile + ".txt"))) {
					isnew = true;
					continue;
				} else if (temp.contains(listFile)) {
					String ch = temp.substring(temp.indexOf('_') + 1, temp.indexOf('.'));
					if ("0".equals(ch)) {
						index = 0;
					} else {
						int n = Integer.parseInt(ch);
						if (n >= index) {
							index = n;
						}
					}
				} else {
					name = listFile;
				}
			}
		} else {
			name = listFile;
		}

		if (isnew) {
			name = listFile + "_0";
		}

		if (index >= 0) {
			name = listFile + "_" + (++index);
		}

		return name;
	}

	public static void init() {

		flag = "File";

		String path = destDirectory + File.separator + searchFile() + ".txt";
		System.out.println("path: " + path);
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void pf(String str) {
		try {
			bw.write(str);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void destroy() {
		try {
			if (bw != null) {
				bw.close();
				bw = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
