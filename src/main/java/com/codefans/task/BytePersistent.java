package com.codefans.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author: csz
 * @date  : 2016年11月20日下午1:47:56
 * 
 * com.codefans.java.io.eea.Persistent.java
 * 
 */
public class BytePersistent {
	
	public static void main(String[] args) {
		BytePersistent bytePersistent = new BytePersistent();
		String outFilePath = "g:/byte_persistent_out.txt";
		String outStrContent = "hello 蔡盛智 world";
		System.out.println("outPutStr:" + outStrContent);
		System.out.println("byteCount:" + outStrContent.getBytes().length);
		OutputStream os = null;
		InputStream is = null;
		try {
			os = new FileOutputStream(new File(outFilePath));
			bytePersistent.writeString(os, outStrContent);
			is = new FileInputStream(new File(outFilePath));
			String readStr = bytePersistent.readString(is);
			System.out.println("rinPutStr:" + readStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// flat a integer value
	public void writeInt(OutputStream out, int v) throws IOException {
		out.write((v >>> 24) & 0xFF);
		out.write((v >>> 16) & 0xFF);
		out.write((v >>> 8) & 0xFF);
		out.write((v >>> 0) & 0xFF);

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

		StringBuffer sb = new StringBuffer(len);

		for (int i = 0; i < len; i++) {
			ch1 = in.read();
			ch2 = in.read();
			sb.append((char) ((ch1 << 8) + (ch2 << 0)));
		}
		return sb.toString();
	}

	// read a integer back
	public int readInt(InputStream in) throws IOException {
		int ch1 = in.read();
		int ch2 = in.read();
		int ch3 = in.read();
		int ch4 = in.read();
		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}

	// read a long back
	public long readLong(InputStream in) throws IOException {
		return ((long) (readInt(in)) << 32) + (readInt(in) & 0xFFFFFFFFL);
	}
		

	
}
