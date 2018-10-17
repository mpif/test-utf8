package com.codefans.java.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class MimeEncoding {
	/**
	 * Decode the given input stream. The Input stream returned is the decoded
	 * input stream. All the encodings defined in RFC 2045 are supported here.
	 * They include "base64", "quoted-printable", "7bit", "8bit", and "binary".
	 * In addition, "uuencode" is also supported.
	 *
	 * @param is
	 *            input stream
	 * @param encoding
	 *            the encoding of the stream.
	 * @return decoded input stream.
	 */
	public static InputStream decode(InputStream is, String encoding) throws IOException {
		if (encoding == null)
			return is;
		if (encoding.equalsIgnoreCase("base64"))
			return new BASE64DecoderStream(is);
		else if (encoding.equalsIgnoreCase("quoted-printable"))
			return new QPDecoderStream(is);
		else if (encoding.equalsIgnoreCase("uuencode") || encoding.equalsIgnoreCase("x-uuencode"))
			return new UUDecoderStream(is);
		// else if (encoding.equalsIgnoreCase("binary") ||
		// encoding.equalsIgnoreCase("7bit") ||
		// encoding.equalsIgnoreCase("8bit"))
		// return is;
		else
			return is;
	}

	/**
	 * Wrap an encoder around the given output stream. All the encodings defined
	 * in RFC 2045 are supported here. They include "base64",
	 * "quoted-printable", "7bit", "8bit" and "binary". In addition, "uuencode"
	 * is also supported.
	 *
	 * @param os
	 *            output stream
	 * @param encoding
	 *            the encoding of the stream.
	 * @return output stream that applies the specified encoding.
	 */
	public static OutputStream encode(OutputStream os, String encoding) throws IOException {
		if (encoding == null)
			return os;
		else if (encoding.equalsIgnoreCase("base64"))
			return new BASE64EncoderStream(os);
		else if (encoding.equalsIgnoreCase("quoted-printable"))
			return new QPEncoderStream(os);
		else if (encoding.equalsIgnoreCase("uuencode") || encoding.equalsIgnoreCase("x-uuencode"))
			return new UUEncoderStream(os);
		// else if (encoding.equalsIgnoreCase("binary") ||
		// encoding.equalsIgnoreCase("7bit") ||
		// encoding.equalsIgnoreCase("8bit"))
		// return os;
		else
			return os;
	}

	/**
	 * Wrap an encoder around the given output stream. All the encodings defined
	 * in RFC 2045 are supported here. They include "base64",
	 * "quoted-printable", "7bit", "8bit" and "binary". In addition, "uuencode"
	 * is also supported. The <code>filename</code> parameter is used with the
	 * "uuencode" encoding and is included in the encoded output.
	 *
	 * @param os
	 *            output stream
	 * @param encoding
	 *            the encoding of the stream.
	 * @param filename
	 *            name for the file being encoded (only used with uuencode)
	 * @return output stream that applies the specified encoding.
	 * @since JavaMail 1.2
	 */
	public static OutputStream encode(OutputStream os, String encoding, String filename) throws IOException {
		if (encoding == null)
			return os;
		else if (encoding.equalsIgnoreCase("base64"))
			return new BASE64EncoderStream(os);
		else if (encoding.equalsIgnoreCase("quoted-printable"))
			return new QPEncoderStream(os);
		else if (encoding.equalsIgnoreCase("uuencode") || encoding.equalsIgnoreCase("x-uuencode"))
			return new UUEncoderStream(os, filename);
		// else if (encoding.equalsIgnoreCase("binary") ||
		// encoding.equalsIgnoreCase("7bit") ||
		// encoding.equalsIgnoreCase("8bit"))
		// return os;
		else
			return os;
	}

}
