package com.codefans.algorithm;

import java.io.UnsupportedEncodingException;

/*
 * @Author: Sean
 * @Time: 2015-06-29 13:51:08
 */
public class CutChineseEnglishStringByByte {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CutChineseEnglishStringByByte ccesbb = new CutChineseEnglishStringByByte();
		ccesbb.cut();
	}

	public void cut() {
		String str = "12abAB中c国";
		System.out.println((int) '0');
		System.out.println((char) 0);
		int byteCount = 10;
		String newStr = cut(str, byteCount);
		System.out.println("newStr:" + newStr);
	}

	public String cut(String str, int byteCount) {
		return cut(str, byteCount, "GBK");
	}

	public String cut(String str, int byteCount, String charsetName) {
		String result = "";
		byte[] bytes = null;
		try {

			bytes = str.getBytes(charsetName);
			// System.out.println("str.getBytes().length:" +
			// str.getBytes("GBK").length);
			// System.out.println("str.getBytes().length:" +
			// str.getBytes("UTF-8").length);

			byte b = 0;
			for (int i = 0; i < bytes.length; i++) {
				b = bytes[i];
				System.out.println(b);
				if (i == byteCount - 1) {
					if (b < 0 || b > 255) {
						byteCount--;
						break;
					}
				}
			}
			result = new String(bytes, 0, byteCount, charsetName);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

}
