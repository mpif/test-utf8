package com.codefans.smtp;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * User: Sean
 * Date: 2015-4-13
 * Time: 下午6:04:42
 */

/**
 * escapes the "." by adding another "." to any "." that appears in the
 * beginning of a line. See RFC821 section 4.5.2.
 */
public class SmtpOutputStream extends FilterOutputStream {
	private int last2b, lastb;

	public SmtpOutputStream(OutputStream os) {
		super(os);
		last2b = '\r';
		lastb = '\n';
	}

	/**
	 * this method cost expensive
	 */
	public void write(int b) throws IOException {
		// if that last character was a newline, the the curr
		// character is ".", we always right out an extra "."
		if (b == '\n' && lastb != '\r') {
			out.write('\r');
			out.write('\n');
		} else if (b == '.' && lastb == '\n') {
			out.write('.');
		}
		lastb = b;
		out.write(b);
	}

	/**
	 * This method is more efficient than write(int b)
	 */
	public void write(byte b[], int off, int len) throws IOException {
		int start = off;
		len += off;
		for (int i = off; i < len; i++) {
			if (b[i] == '\n' && lastb != '\r') {
				out.write(b, start, i - start);
				out.write('\r');
				start = i;
			} else if (b[i] == '.' && lastb == '\n') {
				out.write(b, start, i - start);
				out.write('.');
				start = i;
			}
			lastb = b[i];
		}
		if ((len - start) > 0)
			out.write(b, start, len - start);
	}

	public void dataComplete() throws IOException {
		if (lastb == '\n')
			out.write(endData0);
		else
			out.write(endData1);
		out.flush();
	}

	private static final byte[] endData0 = ".\r\n".getBytes();
	private static final byte[] endData1 = "\r\n.\r\n".getBytes();
}