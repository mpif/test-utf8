package com.codefans.java.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * This class is to support reading CRLF terminated lines that contain only
 * US-ASCII characters from an input stream. Provides functionality that is
 * similar to the deprecated <code>DataInputStream.readLine()</code>. Expected
 * use is to read lines as String objects from a RFC822 stream.
 *
 * It is implemented as a FilterInputStream, so one can just wrap this class
 * around any input stream and read bytes from this filter.
 */

public class LineInputStream extends FilterInputStream {

	private char[] lineBuffer = null; // reusable byte buffer

	public LineInputStream(InputStream in) {
		super(in);
	}

	/**
	 * Read a line containing only ASCII characters from the input stream. A
	 * line is terminated by a CR or NL or CR-NL sequence. The line terminator
	 * is not returned as part of the returned String. Returns null if no data
	 * is available.
	 * <p>
	 *
	 * This class is similar to the deprecated
	 * <code>DataInputStream.readLine()</code>
	 */
	public String readLine() throws IOException {
		InputStream in = this.in;
		char[] buf = lineBuffer;

		if (buf == null)
			buf = lineBuffer = new char[128];

		int c1;
		int room = buf.length;
		int offset = 0;

		while ((c1 = in.read()) != -1) {
			if (c1 == '\n') // Got NL, outa here.
				break;
			else if (c1 == '\r') {
				// Got CR, is the next char NL ?
				int c2 = in.read();
				if (c2 != '\n') {
					// If not NL, push it back
					if (!(in instanceof PushbackInputStream))
						in = this.in = new PushbackInputStream(in);
					((PushbackInputStream) in).unread(c2);
				}
				break; // outa here.
			}
			// change by Jeff
			if (c1 == '\\') {
				room -= 2;
				if (room < 0) { // No room, need to grow.
					buf = new char[offset * 2];
					// we replace "+128" with "*2" to speed the memory
					// allocation when need large one
					room = buf.length - offset - 1;
					System.arraycopy(lineBuffer, 0, buf, 0, offset);
					lineBuffer = buf;
				}
				buf[offset++] = (char) c1;
				buf[offset++] = (char) c1;
			} else {
				// Not CR, NL or CR-NL ...
				// .. Insert the byte into our byte buffer
				if (--room < 0) { // No room, need to grow.
					buf = new char[offset * 2];
					// we replace "+128" with "*2" to speed the memory
					// allocation when need large one
					room = buf.length - offset - 1;
					System.arraycopy(lineBuffer, 0, buf, 0, offset);
					lineBuffer = buf;
				}
				buf[offset++] = (char) c1;
			}
		}

		if ((c1 == -1) && (offset == 0))
			return null;

		return String.copyValueOf(buf, 0, offset);
	}
}
