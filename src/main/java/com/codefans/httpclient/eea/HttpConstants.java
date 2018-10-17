package com.codefans.httpclient.eea;

import java.io.UnsupportedEncodingException;

public class HttpConstants {

	public HttpConstants() {
	}

	public static byte[] getBytes(String data) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		try {
			return data.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			return data.getBytes();
		}
	}

	public static String getString(byte data[], int offset, int length) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		try {
			return new String(data, offset, length, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			return new String(data, offset, length);
		}
	}

	public static String getString(byte data[]) {
		return getString(data, 0, data.length);
	}

	public static byte[] getContentBytes(String data, String charset) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		if (charset == null || charset.equals(""))
			charset = "ISO-8859-1";
		try {
			return data.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
		}
		try {
			return data.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e2) {
			return data.getBytes();
		}
	}

	public static String getContentString(byte data[], int offset, int length, String charset) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		if (charset == null || charset.equals(""))
			charset = "ISO-8859-1";
		try {
			return new String(data, offset, length, charset);
		} catch (UnsupportedEncodingException e) {
		}
		try {
			return new String(data, offset, length, "ISO-8859-1");
		} catch (UnsupportedEncodingException e2) {
			return new String(data, offset, length);
		}
	}

	public static String getContentString(byte data[], String charset) {
		return getContentString(data, 0, data.length);
	}

	public static byte[] getContentBytes(String data) {
		return getContentBytes(data, null);
	}

	public static String getContentString(byte data[], int offset, int length) {
		return getContentString(data, offset, length, null);
	}

	public static String getContentString(byte data[]) {
		return getContentString(data, null);
	}

	public static byte[] getAsciiBytes(String data) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		try {
			return data.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("HttpClient requires ASCII support");
		}
	}

	public static String getAsciiString(byte data[], int offset, int length) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		try {
			return new String(data, offset, length, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("HttpClient requires ASCII support");
		}
	}

	public static String getAsciiString(byte data[]) {
		return getAsciiString(data, 0, data.length);
	}

	public static final String HTTP_ELEMENT_CHARSET = "US-ASCII";
	public static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
}
