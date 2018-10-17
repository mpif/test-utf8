package com.codefans.java.rtjar.sun.misc;

public class TestBASE64Encoder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] cIndexByte03 = new byte[] { 1, -50, -121, -114, -104, 90, -97, -87, -74, -34, -35, 17, 70, -94, -76, -55,
				-104, 118, 27, -95, 7, -13, 0, 0, 6, -94, -42 };

		BASE64Encoder encoder = new BASE64Encoder();
		String str03 = encoder.encode(cIndexByte03);
		System.out.println(str03);
	}

}
