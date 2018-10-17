/*
 * @(#)LineOutputStream.java	1.5 99/12/06
 *
 * Copyright 1997-1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */

package com.codefans.java.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This class is to support writing out Strings as a sequence of bytes
 * terminated by a CRLF sequence. The String must contain only US-ASCII
 * characters.
 * <p>
 *
 * The expected use is to write out RFC822 style headers to an output stream.
 * <p>
 *
 * @author John Mani
 */

public class LineOutputStream extends FilterOutputStream {
	private static byte[] newline;

	static {
		newline = new byte[2];
		newline[0] = (byte) '\r';
		newline[1] = (byte) '\n';
	}

	public LineOutputStream(OutputStream out) {
		super(out);
	}

	public void writeln(String s) throws IOException {
		byte[] bytes = ASCIIUtility.getBytes(s);
		out.write(bytes);
		out.write(newline);
	}

	public void writeln() throws IOException {
		out.write(newline);
	}
}
