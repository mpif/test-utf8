package com.codefans.java.util;

import com.sun.xml.internal.messaging.saaj.util.Base64;

public class EncodeUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String source = "김 승 연중국어김 승 연중국어김 승 연중국어김 승 연중국어김 승 연중국어김 승 연중국어김 승 연중국어김 승 연중국어김 승 연중국어김 승 연중국어김 승 연중국어김 승 연중국어";
		try {
			byte[] b = source.getBytes();
			byte[] eb = Base64.encode(b);
			String encode = new String(eb);
			System.out.println(encode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
