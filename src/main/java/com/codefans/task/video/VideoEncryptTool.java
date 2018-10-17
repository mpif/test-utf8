package com.codefans.task.video;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class VideoEncryptTool {

	private static String source = "D:\\Media\\波多野結衣_00;46;30-00;57;45.rmvb";

	private static String dest = "D:\\Media\\波多野結衣_00;46;30-00;57;45_encrypt.rmvb";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// encrypt2();

		// encrypt();
		// decrypt();
	}

	public static void encrypt2() {
		BufferedOutputStream bos = null;
		try {
			File d = new File(dest);
			if (!d.exists()) {
				d.createNewFile();
			}

			RandomAccessFile raf = new RandomAccessFile(source, "r");
			long len = raf.length();

			bos = new BufferedOutputStream(new FileOutputStream(d));
			p("len:" + len);
			p("len/4:" + len / 4);

			long part = 11879936;
			int l = 11879936;
			byte[] b = new byte[l];
			byte[] bb = new byte[l];
			// for(int i = 0; i < b.length; i ++) {
			// bb[i] = b[i] >>> 2 & 0xf;
			// }
			int n = 0;
			raf.seek(part * 2);
			n = raf.read(b);
			bos.write(b, 0, n);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void decrypt2() {
		BufferedOutputStream bos = null;
		try {
			File d = new File(source);
			if (!d.exists()) {
				d.createNewFile();
			}

			RandomAccessFile raf = new RandomAccessFile(dest, "r");
			long len = raf.length();

			bos = new BufferedOutputStream(new FileOutputStream(d));
			p("len:" + len);
			p("len/4:" + len / 4);

			long part = 11879936;
			int l = 11879936;
			byte[] b = new byte[l];
			int n = raf.read(b);
			bos.write(b, 0, n);

			raf.seek(part * 2);
			n = raf.read(b);
			bos.write(b, 0, n);

			raf.seek(part);
			n = raf.read(b);
			bos.write(b, 0, n);

			raf.seek(part * 3);
			n = raf.read(b);
			bos.write(b, 0, n);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void encrypt() {
		BufferedOutputStream bos = null;
		try {
			File d = new File(dest);
			if (!d.exists()) {
				d.createNewFile();
			}

			RandomAccessFile raf = new RandomAccessFile(source, "r");
			long len = raf.length();

			bos = new BufferedOutputStream(new FileOutputStream(d));
			p("len:" + len);
			p("len/4:" + len / 4);

			long part = 11879936;
			int l = 11879936;
			byte[] b = new byte[l];
			int n = raf.read(b);
			bos.write(b, 0, n);

			raf.seek(part * 2);
			n = raf.read(b);
			bos.write(b, 0, n);

			raf.seek(part);
			n = raf.read(b);
			bos.write(b, 0, n);

			raf.seek(part * 3);
			n = raf.read(b);
			bos.write(b, 0, n);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void decrypt() {
		BufferedOutputStream bos = null;
		try {
			File d = new File(source);
			if (!d.exists()) {
				d.createNewFile();
			}

			RandomAccessFile raf = new RandomAccessFile(dest, "r");
			long len = raf.length();

			bos = new BufferedOutputStream(new FileOutputStream(d));
			p("len:" + len);
			p("len/4:" + len / 4);

			long part = 11879936;
			int l = 11879936;
			byte[] b = new byte[l];
			int n = raf.read(b);
			bos.write(b, 0, n);

			raf.seek(part * 2);
			n = raf.read(b);
			bos.write(b, 0, n);

			raf.seek(part);
			n = raf.read(b);
			bos.write(b, 0, n);

			raf.seek(part * 3);
			n = raf.read(b);
			bos.write(b, 0, n);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
					bos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void p(String str) {
		System.out.println(str);
	}
}
