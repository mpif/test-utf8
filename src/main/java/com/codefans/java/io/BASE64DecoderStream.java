/*
 * @(#)BASE64DecoderStream.java	1.5 00/02/19
 *
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */

package com.codefans.java.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class implements a BASE64 Decoder. It is implemented as a
 * FilterInputStream, so one can just wrap this class around any input stream
 * and read bytes from this filter. The decoding is done as the bytes are read
 * out.
 */

public class BASE64DecoderStream extends FilterInputStream {

	private static final char[] pem_array = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '+', '/' };

	// A mapping between char values and six-bit integers
	private static final int[] pem_convert_array = new int[256];

	static {
		for (int i = 0; i < 255; i++) {
			pem_convert_array[i] = -1;
		}
		for (int i = 0; i < pem_array.length; i++) {
			pem_convert_array[pem_array[i]] = (byte) i;
		}
	}

	private int charCount;
	private int carryOver;

	public BASE64DecoderStream(InputStream in) {
		super(in);
	}

	/**
	 * Read the next decoded byte from this input stream. The byte is returned
	 * as an <code>int</code> in the range <code>0</code> to <code>255</code>.
	 * If no byte is available because the end of the stream has been reached,
	 * the value <code>-1</code> is returned. This method blocks until input
	 * data is available, the end of the stream is detected, or an exception is
	 * thrown.
	 *
	 * @return next byte of data, or <code>-1</code> if the end of the stream is
	 *         reached.
	 * @exception java.io.IOException
	 *                if an I/O error occurs.
	 * @see java.io.FilterInputStream#in
	 */
	public int read() throws IOException {
		// Read the next non-whitespace character
		int x;
		do {
			x = in.read();
			if (x == -1) {
				return -1;
			}
		} while (pem_convert_array[x] == -1 && x != '=');
		charCount++;

		// The '=' sign is just padding
		if (x == '=') {
			return -1; // effective end of stream
		}

		// Convert from raw form to 6-bit form
		x = pem_convert_array[x];

		// Calculate which character we're decoding now
		int mode = (charCount - 1) % 4;

		// First char save all six bits, go for another
		if (mode == 0) {
			carryOver = x & 63;
			return read();
		}
		// Second char use previous six bits and first two new bits,
		// save last four bits
		else if (mode == 1) {
			int decoded = ((carryOver << 2) + (x >> 4)) & 255;
			carryOver = x & 15;
			return decoded;
		}
		// Third char use previous four bits and first four new bits,
		// save last two bits
		else if (mode == 2) {
			int decoded = ((carryOver << 4) + (x >> 2)) & 255;
			carryOver = x & 3;
			return decoded;
		}
		// Fourth char use previous two bits and all six new bits
		else if (mode == 3) {
			int decoded = ((carryOver << 6) + x) & 255;
			return decoded;
		}
		return -1; // can't actually reach this line
	}

	/**
	 * Reads up to <code>len</code> decoded bytes of data from this input stream
	 * into an array of bytes. This method blocks until some input is available.
	 * <p>
	 *
	 * @param buf
	 *            the buffer into which the data is read.
	 * @param off
	 *            the start offset of the data.
	 * @param len
	 *            the maximum number of bytes read.
	 * @return the total number of bytes read into the buffer, or
	 *         <code>-1</code> if there is no more data because the end of the
	 *         stream has been reached.
	 * @exception java.io.IOException
	 *                if an I/O error occurs.
	 */
	public int read(byte[] buf, int off, int len) throws IOException {
		if (buf.length < (len + off - 1)) {
			throw new IOException("The input buffer is too small.");
		}

		// This could of course be optimized
		int i;
		for (i = 0; i < len; i++) {
			int x = read();
			if (x == -1 && i == 0) { // an immediate -1 returns -1
				return -1;
			} else if (x == -1) { // a later -1 returns the chars read so far
				break;
			}
			buf[off + i] = (byte) x;
		}
		return i;
	}

	/**
	 * Tests if this input stream supports marks. Currently this class does not
	 * support marks
	 */
	public boolean markSupported() {
		return false; // Maybe later ..
	}

	/**
	 * Returns the number of bytes that can be read from this input stream
	 * without blocking. However, this figure is only a close approximation in
	 * case the original encoded stream contains embedded CRLFs; since the CRLFs
	 * are discarded, not decoded
	 */
	public int available() throws IOException {
		// This is only an estimate, since in.available()
		// might include CRLFs too ..
		return ((in.available() * 3) / 4);
	}
}
