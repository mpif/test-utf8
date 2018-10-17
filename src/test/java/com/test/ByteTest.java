package com.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class ByteTest {

	@Test
	public void test() {
		testBytes();
		
	}
	
	public void testBytes() {
		try {
			String str = "奢侈品";
			byte[] strBytes = str.getBytes();
			byte[] bytes = new byte[1024];
			for(int i = 0; i < strBytes.length; i ++) {
				bytes[i] = strBytes[i];
			}
			String text = new String(bytes, "UTF-8");
//			text = "\"奢侈品";
			System.out.println(text); //奢侈品
			text = "[" +( text +"]999");
			System.out.println(text); // [奢侈品
//			System.out.println(text); // [奢侈品
//			System.out.println("999" + text +"]999");
//			String text2 = "[" + text + "]";
//			System.out.println(text2);
//			StringBuffer text3 = new StringBuffer();
//			text3.append("[").append(text).append("]");
//			System.out.println(text3.toString());
			
			System.out.println("end!");
			
			byte[] bs = new byte[3];
			bs[0] = (byte)'1';
			bs[2] = (byte)'3';
			System.out.println(new String(bs)); //输出1 3
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
}
