package com.test;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class EnglishSystemTest {

	private FileWriter fw;
	private BufferedWriter bw;
	private ZipOutputStream out;

	public EnglishSystemTest() {
		try {
			fw = new FileWriter(new File("c:/EnglishSystemTest.txt"), true);
			bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File("c:/EnglishSystemTest_UTF8.txt")), "UTF-8"));

			out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("c:/中文.zip")));
			ZipEntry entry = new ZipEntry("第一个文件.txt");
			entry.setMethod(ZipEntry.DEFLATED);
			out.putNextEntry(entry);
			out.write("我是第一个文件，哈哈".getBytes());
			out.closeEntry();

			entry = new ZipEntry("第二个文件.txt");
			entry.setMethod(ZipEntry.DEFLATED);
			out.putNextEntry(entry);
			out.write("我是第二个文件，哈哈".getBytes());
			out.closeEntry();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EnglishSystemTest test = new EnglishSystemTest();

		String fileEncoding = System.getProperty("file.encoding");
		String defaultCharset = Charset.defaultCharset().displayName();
		test.writeTo("fileEncoding: " + fileEncoding);
		test.writeTo("defaultCharset: " + defaultCharset);

		test.close();
	}

	public void writeTo(String str) {
		try {
			fw.write(str + "\r\n");
			bw.write(str);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeTo(int n) {
		try {
			fw.write(n);
			bw.write(n);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			fw.flush();
			fw.close();
			fw = null;

			bw.flush();
			bw.close();
			bw = null;

			out.flush();
			out.close();
			out = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
