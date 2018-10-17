package com.codefans.javamail;

import java.io.UnsupportedEncodingException;

public class MimeUtilityTest {

	public static void main(String args[]) {
		String source = "各位认识我的同事和朋友，我将离开国信，向大家道别啦！下周休假";
		try {
			String encodeStr = javax.mail.internet.MimeUtility.encodeText(source);
			System.out.println("encode str:" + encodeStr);
			String decodeStr = javax.mail.internet.MimeUtility.decodeText(encodeStr);
			System.out.println("decode str:" + decodeStr);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
