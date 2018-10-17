package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class FileUtil {

	private static String toDel = ".svn";
	// private static String toDel = "a";

	public static void main(String[] args) {
		System.out.println("Hello World!");

		// String path = "D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.1";
		// String path = "D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.8";
		// String path = "D:\\dev\\MT-2.1.15-fx - 副本\\dist";
		// String path = "D:\\dev\\M-2.1.15";
		// String path = "D:\\dev\\REL-2.1.16 - backup";
		String path = "D:\\dev\\FB-ClusterEAS - 副本";
		// String path =
		// "D:\\360云盘\\MessageSolution\\项目\\EAS_SVN_Backup\\2011-12-14\\EAS_SVN";

		// delete(path);

		// count();

		// fileToStr();

		renameTo();

	}

	public static void renameTo() {
		String file = "C:\\Temp\\a.txt";
		String dest = file + ".lock";
		File srcFile = new File(file);
		File destFile = new File(dest);
		srcFile.renameTo(destFile);
	}

	public static void fileToStr() {
		String file = System.getProperty("user.dir") + File.separator + "src/com/test/f.txt";
		System.out.println("file:" + file);

		try {
			Scanner sc = new Scanner(new FileInputStream(new File(file)));
			while (sc.hasNextLine()) {
				System.out.println("\"" + sc.nextLine() + "\" + ");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void count() {
		String str = "the value of property";
		try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\thread index\\eas.log.2");
			BufferedReader br = new BufferedReader(new FileReader(file));

			String temp = "";
			int index = 0;
			Map<String, String> vals = new HashMap<String, String>();

			while ((temp = br.readLine()) != null) {
				if (temp.contains(str)) {
					temp = temp.substring(temp.indexOf("the value is [") + 14);
					temp = temp.substring(0, temp.indexOf("]"));
					vals.put(temp, temp);
					// System.out.println(temp);
					index++;
				}
			}
			System.out.println(index);

			Iterator iter = vals.keySet().iterator();
			while (iter.hasNext()) {
				System.out.println(vals.get(iter.next()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delete(String path) {
		File direct = new File(path);
		String name = "";
		if (direct.isDirectory()) {
			File[] files = direct.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				name = file.getName();
				if (name.equals(toDel)) {
					deleteFolder(path + File.separator + name);
					deleteFile(path + File.separator + name);
				} else {
					if (file.isDirectory()) {
						delete(path + File.separator + name);
					}
				}
			}
		}
	}

	public static void deleteFolder(String path) {
		File direct = new File(path);
		String name = "";
		if (direct.isDirectory()) {
			File[] files = direct.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				name = file.getName();
				if (file.isDirectory()) {
					deleteFolder(path + File.separator + name);
				}
				deleteFile(path + File.separator + name);
			}
		}
		deleteFile(path);
	}

	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			boolean flag = file.delete();
			if (flag) {
				System.out.println("Success!, path=" + path);
			} else {
				System.out.println("Fail!, path=" + path);
			}
		}
	}
}