package com.codefans.java.lang.String;

/*
 * @Author: Sean
 * @Time: 2015-06-29 14:14:45
 */
public class StringAndASCII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringAndASCII saa = new StringAndASCII();

		// saa.charToAscii();
		saa.asciiToChar();

		// saa.stringToAscii();
		// saa.asciiToString();

	}

	public void stringToAscii() {

		// String str = "新年快乐！";//字符串
		String str = "hello圣诞快乐world";// 字符串
		String result = stringToAscii(str);
		System.out.println("result of Ascii:" + result);
	}

	public String stringToAscii(String str) {
		StringBuffer asciiCode = new StringBuffer();
		char[] chars = str.toCharArray(); // 把字符中转换为字符数组
		int ascii = 0;
		System.out.println("\n\n汉字 ASCII\n----------------------");
		for (int i = 0; i < chars.length; i++) {// 输出结果
			ascii = (int) chars[i];
			if (i == 0) {
				asciiCode.append(ascii);
			} else {
				asciiCode.append(" ");
				asciiCode.append(ascii);
			}
			System.out.println(" " + chars[i] + " " + ascii);
		}
		return asciiCode.toString();
	}

	public void asciiToString() {
		String str = "22307 35806 24555 20048";// ASCII码
		String result = asciiToString(str);
		System.out.println("result of String:" + result);
	}

	public String asciiToString(String asciiCode) {
		StringBuffer result = new StringBuffer();
		String[] codes = asciiCode.split(" ");
		char c = '\t';
		System.out.println("ASCII 汉字 \n----------------------");
		for (int i = 0; i < codes.length; i++) {
			c = (char) Integer.parseInt(codes[i]);
			if (i == 0) {
				result.append(c);
			} else {
				result.append(' ');
				result.append(c);
			}
			System.out.println(codes[i] + " " + c);
		}

		return result.toString();
	}

	public void charToAscii() {

	}

	public void asciiToChar() {
		try {
			// int min = 0;
			// int max = 127;
			// StringBuffer allChars = new StringBuffer();
			// char c = ' ';
			// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new
			// FileOutputStream(new File("C:/ascii.txt")), "GBK"));
			// for(int i = min; i <= max; i ++) {
			// c = (char)Integer.parseInt(String.valueOf(i));
			// bw.append(c);
			// if(i == min) {
			// allChars.append(c);
			// } else {
			// allChars.append(", ");
			// allChars.append(c);
			// }
			// }
			// System.out.println(allChars.toString());
			//
			// bw.close();

			// char a=1;
			// while(a<128){
			// a++;
			// System.out.println(a+""+(int)a);
			// }

			int ascii = 0;
			System.out.println((char) ascii);
			ascii = 1;
			System.out.println((char) ascii);
			ascii = 2;
			System.out.println((char) ascii);
			ascii = 3;
			System.out.println((char) ascii);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
