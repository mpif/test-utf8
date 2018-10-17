package com.codefans.java.io;

import java.io.*;

public class StreamUtility {

	public static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buf = new byte[1024 * 10];
		int readed;
		while ((readed = in.read(buf)) != -1) {
			out.write(buf, 0, readed);
		}
		out.flush();
	}

	public static void copy(Reader in, Writer out) throws IOException {
		char[] buf = new char[1024];
		int readed;
		while ((readed = in.read(buf)) != -1) {
			out.write(buf, 0, readed);
		}
		out.flush();
	}

	public static String getString(Reader in) throws IOException {
		StringWriter out = new StringWriter();
		copy(in, out);
		return out.toString();
	}

	public static char[] getChars(Reader in) throws IOException {
		CharArrayWriter out = new CharArrayWriter();
		copy(in, out);
		return out.toCharArray();
	}

	public static byte[] getBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(in.available());
		copy(in, out);
		in.close();
		return out.toByteArray();
	}

	public static boolean equals(InputStream in1, InputStream in2) throws IOException {
		InputStream bin1 = new BufferedInputStream(in1);
		InputStream bin2 = new BufferedInputStream(in2);

		int ch = bin1.read();
		while (-1 != ch) {
			int ch2 = bin2.read();
			if (ch != ch2) {
				return false;
			}
			ch = bin1.read();
		}

		int ch2 = bin2.read();
		if (ch2 != -1) {
			return false;
		} else {
			return true;
		}
	}

}
