/*
 * @(#)ASCIIUtility.java	1.7 00/05/22
 *
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */
package com.codefans.java.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ASCIIUtility {

	// Private constructor so that this class is not instantiated
	private ASCIIUtility() {
	}

	/**
	 * Convert the bytes within the specified range of the given byte array into
	 * a signed integer in the given radix . The range extends from
	 * <code>start</code> till, but not including <code>end</code>.
	 * <p>
	 *
	 * Based on java.lang.Integer.parseInt()
	 */
	public final static int parseInt(byte[] b, int start, int end, int radix) throws NumberFormatException {
		if (b == null)
			throw new NumberFormatException("null");

		int result = 0;
		boolean negative = false;
		int i = start;
		int limit;
		int multmin;
		int digit;

		if (end > start) {
			if (b[i] == '-') {
				negative = true;
				limit = Integer.MIN_VALUE;
				i++;
			} else {
				limit = -Integer.MAX_VALUE;
			}
			multmin = limit / radix;
			if (i < end) {
				digit = Character.digit((char) b[i++], radix);
				if (digit < 0) {
					throw new NumberFormatException("illegal number: " + toString(b, start, end));
				} else {
					result = -digit;
				}
			}
			while (i < end) {
				// Accumulating negatively avoids surprises near MAX_VALUE
				digit = Character.digit((char) b[i++], radix);
				if (digit < 0) {
					throw new NumberFormatException("illegal number");
				}
				if (result < multmin) {
					throw new NumberFormatException("illegal number");
				}
				result *= radix;
				if (result < limit + digit) {
					throw new NumberFormatException("illegal number");
				}
				result -= digit;
			}
		} else {
			throw new NumberFormatException("illegal number");
		}
		if (negative) {
			if (i > start + 1) {
				return result;
			} else { /* Only got "-" */
				throw new NumberFormatException("illegal number");
			}
		} else {
			return -result;
		}
	}

	/**
	 * Convert the bytes within the specified range of the given byte array into
	 * a signed integer . The range extends from <code>start</code> till, but
	 * not including <code>end</code>.
	 * <p>
	 */
	public final static int parseInt(byte[] b, int start, int end) throws NumberFormatException {
		return parseInt(b, start, end, 10);
	}

	/**
	 * Convert the bytes within the specified range of the given byte array into
	 * a signed long in the given radix . The range extends from
	 * <code>start</code> till, but not including <code>end</code>.
	 * <p>
	 *
	 * Based on java.lang.Long.parseLong()
	 */
	public final static long parseLong(byte[] b, int start, int end, int radix) throws NumberFormatException {
		if (b == null)
			throw new NumberFormatException("null");

		long result = 0;
		boolean negative = false;
		int i = start;
		long limit;
		long multmin;
		int digit;

		if (end > start) {
			if (b[i] == '-') {
				negative = true;
				limit = Long.MIN_VALUE;
				i++;
			} else {
				limit = -Long.MAX_VALUE;
			}
			multmin = limit / radix;
			if (i < end) {
				digit = Character.digit((char) b[i++], radix);
				if (digit < 0) {
					throw new NumberFormatException("illegal number: " + toString(b, start, end));
				} else {
					result = -digit;
				}
			}
			while (i < end) {
				// Accumulating negatively avoids surprises near MAX_VALUE
				digit = Character.digit((char) b[i++], radix);
				if (digit < 0) {
					throw new NumberFormatException("illegal number");
				}
				if (result < multmin) {
					throw new NumberFormatException("illegal number");
				}
				result *= radix;
				if (result < limit + digit) {
					throw new NumberFormatException("illegal number");
				}
				result -= digit;
			}
		} else {
			throw new NumberFormatException("illegal number");
		}
		if (negative) {
			if (i > start + 1) {
				return result;
			} else { /* Only got "-" */
				throw new NumberFormatException("illegal number");
			}
		} else {
			return -result;
		}
	}

	/**
	 * Convert the bytes within the specified range of the given byte array into
	 * a signed long . The range extends from <code>start</code> till, but not
	 * including <code>end</code>.
	 * <p>
	 */
	public final static long parseLong(byte[] b, int start, int end) throws NumberFormatException {
		return parseLong(b, start, end, 10);
	}

	/**
	 * Convert the bytes within the specified range of the given byte array into
	 * a String. The range extends from <code>start</code> till, but not
	 * including <code>end</code>.
	 * <p>
	 */
	public final static String toString(byte[] b, int start, int end) {
		int size = end - start;
		char[] theChars = new char[size];

		for (int i = 0, j = start; i < size;)
			theChars[i++] = (char) b[j++];

		return new String(theChars);
	}

	public final static String toString(ByteArrayInputStream is) {
		int size = is.available();
		char[] theChars = new char[size];
		byte[] bytes = new byte[size];

		is.read(bytes, 0, size);
		for (int i = 0; i < size;)
			theChars[i] = (char) bytes[i++];

		return new String(theChars);
	}

	public final static byte[] getBytes(String s) {
		char[] chars = s.toCharArray();
		int size = chars.length;
		byte[] bytes = new byte[size];

		for (int i = 0; i < size;)
			bytes[i] = (byte) chars[i++];
		return bytes;
	}

	public final static byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}
}
