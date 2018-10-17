package com.codefans.java.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class GetFileEncode {

	private static String path = "D:\\dev\\test-utf8\\src\\com\\messagesolution\\java\\file\\";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// parseEncode("ANSI.txt");
		// parseEncode("UTF-8.txt");

		// parseEncode(createFile("default.txt"));
		// parseEncode(createFile("default.txt", "UTF-8"));
		parseEncode("default.txt");
	}

	public static void parseEncode(String fileName) {
		InputStream is = GetFileEncode.class.getResourceAsStream(fileName);
		parseCharset(is);
	}

	public static void parseEncode(File file) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			parseCharset(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void parseCharset(InputStream is) {
		byte[] bytes = new byte[3];
		int n = 0;
		try {
			n = is.read(bytes);
			if (bytes[0] == -17 && bytes[1] == -69 && bytes[2] == -65) {
				System.out.println("UTF-8");
				System.out.println("bytes[0]:" + bytes[0]);
				System.out.println("bytes[1]:" + bytes[1]);
				System.out.println("bytes[2]:" + bytes[2]);
			} else {
				System.out.println("ANSI");
				System.out.println("bytes[0]:" + bytes[0]);
				System.out.println("bytes[1]:" + bytes[1]);
				System.out.println("bytes[2]:" + bytes[2]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static File createFile(String fileName) {
		File f = null;
		try {
			f = new File(path + fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	public static File createFile(String fileName, String charset) {
		File f = new File(path + fileName);
		OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(new FileOutputStream(f), charset);
			osw.write("中文字符");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (osw != null) {
					osw.flush();
					osw.close();
					osw = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return f;
	}

}
