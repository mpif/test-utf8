package com.codefans.java.io.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-3-17 Time: 下午4:22 To
 * change this template use File | Settings | File Templates.
 */
public class JavaByteAndFileByteTest {

	private static int MaxStringSizeMB5M = 5000000;

	public static void main(String args[]) {
		JavaByteAndFileByteTest test = new JavaByteAndFileByteTest();
		// test.write();
		test.read();

		// System.out.println(12 & 0xFF);
		// System.out.println(22 & 0xFF);
		// System.out.println(32 & 0xFF);

		int n = 16843009;// 00000001 00000001 00000001 00000001 -->
							// 16777216+65536+256+1=16843009
		int a = (n >>> 24) & 0xFF;// 0xFF --> 11111111
		int b = (n >>> 16) & 0xFF;
		int c = (n >>> 8) & 0xFF;
		int d = (n >>> 0) & 0xFF;
		// System.out.println(a);
		// System.out.println(b);
		// System.out.println(c);
		// System.out.println(d);
		//
		// System.out.println(n >>> 24);
		// System.out.println(n >>> 16);
		// System.out.println(n >>> 8);
		// System.out.println(n >>> 0);
		//
		// int ch1 = (a << 24);
		// int ch2 = (b << 16);
		// int ch3 = (c << 8);
		// int ch4 = (d << 0);
		// System.out.println(ch1);
		// System.out.println(ch2);
		// System.out.println(ch3);
		// System.out.println(ch4);
		// int result = (ch1 + ch2 + ch3 + ch4);
		// System.out.println(result);
		//
		// System.out.println(0xFF);
	}

	public void write() {
		String path = "C://images/a.txt";
		// String path = "C:\\images\\1\\e400e1bceab437d0de3c32b850204a1b_tmb";
		try {
			RandomAccessFile raf = new RandomAccessFile(path, "rw");

			// raf.writeByte(12); // 1 byte
			// raf.write(1222); // 1 byte
			// raf.writeInt(12); // 4 byte

			// int n = 12;
			int n = 16843009;
			raf.write((n >>> 24) & 0xFF);
			raf.write((n >>> 16) & 0xFF);
			raf.write((n >>> 8) & 0xFF);
			raf.write((n >>> 0) & 0xFF);
			// raf.writeInt(n);

			// raf.writeUTF("fkdjfdfkdfdkfdkfdjf");
			// raf.writeLong(12);
			//
			raf.close();
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	public void read() {
		// String path = "D://tmp/a.txt";
		String path = "C:\\images\\1\\e400e1bceab437d0de3c32b850204a1b_tmb";
		try {
			RandomAccessFile raf = new RandomAccessFile(path, "rw");

			int ch1 = raf.read();
			int ch2 = raf.read();
			int ch3 = raf.read();
			int ch4 = raf.read();
			int result = ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));

			// int result = raf.readInt();

			System.out.println(result);

			raf.seek(0);
			int re = raf.readInt();
			System.out.println("re:[" + re + "]");

			raf.seek(0);
			long lon = raf.readLong();
			System.out.println("lon:[" + lon + "]");

			// System.out.println(raf.read());

			raf.close();
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	// flat a integer value
	public void writeInt(OutputStream out, int v) throws IOException {
		out.write((v >>> 24) & 0xFF);
		out.write((v >>> 16) & 0xFF);
		out.write((v >>> 8) & 0xFF);
		out.write((v >>> 0) & 0xFF);

	}

	// read a integer back
	public int readInt(InputStream in) throws IOException {
		int ch1 = in.read();
		int ch2 = in.read();
		int ch3 = in.read();
		int ch4 = in.read();
		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}

	public int readInt(RandomAccessFile in) throws IOException {
		int ch1 = in.read();
		int ch2 = in.read();
		int ch3 = in.read();
		int ch4 = in.read();
		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}

	// flat a long value
	public void writeLong(OutputStream out, long v) throws IOException {
		out.write((int) (v >>> 56) & 0xFF);
		out.write((int) (v >>> 48) & 0xFF);
		out.write((int) (v >>> 40) & 0xFF);
		out.write((int) (v >>> 32) & 0xFF);
		out.write((int) (v >>> 24) & 0xFF);
		out.write((int) (v >>> 16) & 0xFF);
		out.write((int) (v >>> 8) & 0xFF);
		out.write((int) (v >>> 0) & 0xFF);
	}

	// read a long back
	public long readLong(InputStream in) throws IOException {
		return ((long) (readInt(in)) << 32) + (readInt(in) & 0xFFFFFFFFL);
	}

	public long readLong(RandomAccessFile in) throws IOException {
		return ((long) (readInt(in)) << 32) + (readInt(in) & 0xFFFFFFFFL);
	}

	// flat a string
	public void writeString(OutputStream out, String s) throws IOException {
		int len = -1;
		if (s != null)
			len = s.length();
		int v;

		writeInt(out, len);
		for (int i = 0; i < len; i++) {
			v = s.charAt(i);
			out.write((v >>> 8) & 0xFF);
			out.write((v >>> 0) & 0xFF);
		}
	}

	// read a string back
	public String readString(InputStream in) throws IOException {
		int ch1;
		int ch2;
		int len = readInt(in);
		if (len < 0)
			return null;
		if (len > MaxStringSizeMB5M) {
			throw new IOException("Data too long for persistent read," + len + "!");
		}
		StringBuffer sb = new StringBuffer(len);

		for (int i = 0; i < len; i++) {
			ch1 = in.read();
			ch2 = in.read();
			sb.append((char) ((ch1 << 8) + (ch2 << 0)));
		}
		return sb.toString();
	}

	public String readString(RandomAccessFile in) throws IOException {
		int ch1;
		int ch2;
		int len = readInt(in);
		if (len < 0)
			return null;
		if (len > MaxStringSizeMB5M) {
			throw new IOException("Data too long for persistent read," + len + "!");
		}
		StringBuffer sb = new StringBuffer(len);

		for (int i = 0; i < len; i++) {
			ch1 = in.read();
			ch2 = in.read();
			sb.append((char) ((ch1 << 8) + (ch2 << 0)));
		}
		return sb.toString();
	}

}
