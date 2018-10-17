package com.codefans.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ASCIIAndChar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "d--";
		// char[] cs = str.toCharArray();
		byte[] cs = str.getBytes();
		for (int i = 0; i < cs.length; i++) {
			// System.out.println((int)cs[i]);
			System.out.println(cs[i]);
		}

		System.out.println("a.Eml".endsWith(".eml"));
		String s = ".Eml";
		System.out.println(s.substring(s.length() - 4));

		String f = "C:\\Users\\Administrator\\Desktop\\sent to self   测试删除带附件的问题2.eml";
		String f2 = "C:\\Users\\Administrator\\Desktop\\sent to self   测试删除带附件的问题20_output.eml";
		try {
			InputStream is = new FileInputStream(f);
			writeToForEml(is, f2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void writeToForEml(InputStream is, String path) {
		try {
			FileOutputStream out = new FileOutputStream(path);
			byte b[] = new byte[1024 * 8];
			int n = 0;
			int last = 0;
			while ((n = is.read(b)) != -1) {
				out.write(b, 0, n);
				last = n;
			}

			/*
			 * (1) eml must end with boundary + "--" if it contains attachment
			 * (2) 45 correspond to '-'
			 */
			if (path != null) {
				if (path.length() >= 4) {
					String extension = path.substring(path.length() - 4);
					if (extension != null && extension.equalsIgnoreCase(".eml")) {
						if (b[last - 1] != 45 && b[last - 2] != 45) {
							out.write(45);
							out.write(45);
						}
					}
				}
			}
			out.close();
		} catch (Exception e) {

		} finally {

		}
	}

}
