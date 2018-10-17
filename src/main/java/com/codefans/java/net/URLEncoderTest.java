package com.codefans.java.net;

import java.net.URLEncoder;

public class URLEncoderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String name = "邮件标题名超长下载测试--邮件标题名超长下载测试--邮件标题名超长下载测试--——邮件标题名超长下载测试--  邮件标题名超长下载测试--  邮件标题名超长下载测试--  邮件标题名超长下载测试--  邮件标题名超长下载测试--@#￥%……&邮件标题名超长下载测试--";
			String str = URLEncoder.encode(name, "utf-8");
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
