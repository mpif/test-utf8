package com.codefans.java.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLDecoderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		encode();
		// decode();
	}

	public static void encode() {
		// String path = "收件箱"; // %E6%94%B6%E4%BB%B6%E7%AE%B1
		// String path = "发件箱"; // %E5%8F%91%E4%BB%B6%E7%AE%B1
		String path = "a%"; // %E7%A0%94%E7%A9%B6%E6%8A%A5%E5%91%8A
		// String path = "外部研究报告"; //
		// %E5%A4%96%E9%83%A8%E7%A0%94%E7%A9%B6%E6%8A%A5%E5%91%8A
		try {
			System.out.println(URLEncoder.encode(path, "UTF-8"));

			// String str = "afdfd%2bdfdfd%2bdfdfd%2bdfdf.xlsx";
			// String str = "中华人民共和国%2b%2b固定队dfdfd%2bdfdf.xlsx";
			String str = "方式--方%2b%2b式方式—方）（式方式&……￥#方式方。，式方式、、方式：“方式方”式方式方式方式方式方.xlsx";
			System.out.println(URLDecoder.decode(str, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void decode() {
		// String path =
		// "/Inbox/%EC%A4%91%EA%B5%AD%EC%97%90%EC%84%9C%EB%8F%84/%D1%82%D0%B0%D0%B9%D0%BC%D0%B0%20%D0%BC%D1%8B%20%D0%B8%D1%81%D0%BF%D1%8B%D1%82%D1%8B%D0%B2%D0%B0%D0%BB%D0%B8/%E3%81%AF%E3%82%8B%E3%81%8B%E3%81%A1%E3%82%83%E3%82%93%E3%81%AF%E3%81%88%E3%82%89%E3%81%84%E3%81%AD/%E5%90%9B%E4%B8%8D%E8%A7%81%E9%BB%84%E6%B2%B3%E4%B9%8B%E6%B0%B4%E5%A4%A9%E4%B8%8A%E6%9D%A5/";
		String path = "/%E6%94%B6%E4%BB%B6%E7%AE%B1/";
		try {
			System.out.println(URLDecoder.decode(path, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
