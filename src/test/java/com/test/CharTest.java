package com.test;

public class CharTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "=?utf-8?B?6JKZ54mb5Yaw5ZOBLS3mnajlvrfli4cgPGRvZGdl?= =?utf-8?B?eWFuZzkxMUAxNjMuY29tPg==?=";
		char[] cs = str.toCharArray();
		char c;
		for (int i = 0; i < cs.length; i++) {
			c = cs[i];
			if (c <= 040 || c >= 0177) {
				System.out.println("[" + cs[i - 1] + "]");
				System.out.println(i + ":[" + c + "]");
				System.out.println("[" + cs[i + 1] + "]");
			}
		}

		System.out.println((char) 0);
		System.out.println((char) 65533);
	}

}
