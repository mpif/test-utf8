package com.codefans.java.io.eea;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-3-18 Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public class Persistent {

	public static Persistent self = null;
	protected final int MagicNo = 88888;
	public transient long aposition = 0;

	// constructor
	public Persistent() {
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
			aposition += 2;
		}
		return sb.toString();
	}

	// read a integer back
	public int readInt(InputStream in) throws IOException {
		int ch1 = in.read();
		int ch2 = in.read();
		int ch3 = in.read();
		int ch4 = in.read();
		aposition += 4;
		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}

	// read a long back
	public long readLong(InputStream in) throws IOException {
		return ((long) (readInt(in)) << 32) + (readInt(in) & 0xFFFFFFFFL);
	}

	// write self out on output stream
	public void writeSelf(OutputStream out) throws IOException {
		writeInt(out, MagicNo);
	}

	// read object back from input stream
	public Object readSelf(InputStream in) throws IOException {
		int num = readInt(in);
		// if we read an invalid class type, just throw an exception
		if (MagicNo != num) {
			// Logger.getInstance().info("invalid MagicNo = " + num +
			// " expect MagicNo = " + MagicNo);
			throw new IOException("invalid MagicNo");
		}
		return null;
	}

}
