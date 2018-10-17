package com.codefans.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64Coder {

	private byte[] bytes;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BASE64Coder coder = new BASE64Coder();
		coder.decode();
		coder.encode();
	}

	public void encode() {
		// byte[] bytes = new byte[]{-123, -23, 101, -93, 10, 43, -107, -42,
		// -85};
		BASE64Encoder encoder = new BASE64Encoder();
		String encodeSource = encoder.encode(bytes);
		System.out.println(encodeSource);
	}

	public void decode() {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			// String source = "helloworlda";
			String source = "aaaaaaa";
			// byte[] bytes = decoder.decodeBuffer(source);
			bytes = decoder.decodeBuffer(source);
			print(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void print(byte[] bytes) {
		System.out.print("[");
		for (int i = 0; i < bytes.length; i++) {
			if (i != bytes.length - 1) {
				System.out.print(bytes[i] + ", ");
			} else {
				System.out.println(bytes[i] + "]");
			}
		}
	}

}
