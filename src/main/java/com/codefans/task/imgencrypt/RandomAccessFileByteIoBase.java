package com.codefans.task.imgencrypt;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileByteIoBase {

	private static int MaxStringSizeMB5M = 5000000;

	public void writeInt(RandomAccessFile out, int v) throws IOException {
		out.write((v >>> 24) & 0xFF);
		out.write((v >>> 16) & 0xFF);
		out.write((v >>> 8) & 0xFF);
		out.write((v >>> 0) & 0xFF);
	}

	public int readInt(RandomAccessFile in) throws IOException {
		int ch1 = in.read();
		int ch2 = in.read();
		int ch3 = in.read();
		int ch4 = in.read();
		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}

	public void writeLong(RandomAccessFile out, long v) throws IOException {
		out.write((int) (v >>> 56) & 0xFF);
		out.write((int) (v >>> 48) & 0xFF);
		out.write((int) (v >>> 40) & 0xFF);
		out.write((int) (v >>> 32) & 0xFF);
		out.write((int) (v >>> 24) & 0xFF);
		out.write((int) (v >>> 16) & 0xFF);
		out.write((int) (v >>> 8) & 0xFF);
		out.write((int) (v >>> 0) & 0xFF);
	}

	public long readLong(RandomAccessFile in) throws IOException {
		return ((long) (readInt(in)) << 32) + (readInt(in) & 0xFFFFFFFFL);
	}

	public void writeString(RandomAccessFile out, String s) throws IOException {
		int len = -1;
		if (s != null)
			len = s.length();
		int v;

		// writeInt( out , len );
		for (int i = 0; i < len; i++) {
			v = s.charAt(i);
			out.write((v >>> 8) & 0xFF);
			out.write((v >>> 0) & 0xFF);
		}
	}

	public String readString(RandomAccessFile in, int length) throws IOException {
		int ch1;
		int ch2;
		int len = length;
		// int len = readInt( in );
		// if ( len < 0 )
		// return null;
		// if ( len > MaxStringSizeMB5M ) { throw new IOException( "Data too
		// long for persistent read," + len + "!" ); }
		StringBuffer sb = new StringBuffer(len);

		for (int i = 0; i < len; i++) {
			ch1 = in.read();
			ch2 = in.read();
			sb.append((char) ((ch1 << 8) + (ch2 << 0)));
		}
		return sb.toString();
	}

	public String readString(RandomAccessFile in, long length) throws IOException {
		int ch1;
		int ch2;
		StringBuffer sb = new StringBuffer((int) length);
		for (int i = 0; i < length; i++) {
			ch1 = in.read();
			ch2 = in.read();
			sb.append((char) ((ch1 << 8) + (ch2 << 0)));
		}
		return sb.toString();
	}

}
