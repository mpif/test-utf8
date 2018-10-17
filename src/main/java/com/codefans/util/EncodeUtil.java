package com.codefans.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeUtility;

import com.sun.xml.internal.messaging.saaj.util.Base64;

public class EncodeUtil {

	public void p(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {

		EncodeUtil util = new EncodeUtil();
		util.test();

		// util.base64encode();
		// util.base64decode();

		// util.urlEncode();

	}

	public void urlEncode() {
		String url = "f d>3";
		String u = URLEncoder.encode(url);
		p("u: " + u);

		String f = "D:\\dev\\Hosting-RB-1.2.x\\attatchment\\%3E6M+.xml";
		File ff = new File(f);
		if (ff.exists()) {
			System.out.println("[" + f + "] exists.");
		}
	}

	public void base64encode() {
		// String fileName = ">5M .xml";
		String fileName = "1";
		String f = getString(Base64.encode(getBytes(fileName)));
		p("f: " + f);
	}

	public void base64decode() {
		String fileName = "MQ==";
		String f = Base64.base64Decode(fileName);
		p("f: " + f);
	}

	public static String getString(byte data[]) {
		return getString(data, 0, data.length);
	}

	public static String getString(byte data[], int offset, int length) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		try {
			// return new String(data, offset, length, "US-ASCII");
			return new String(data, offset, length, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return new String(data, offset, length);
		}
	}

	public static byte[] getBytes(String data) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		try {
			// return data.getBytes("US-ASCII");
			return data.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return data.getBytes();
		}
	}

	public void test() {
		// String pwd = "MQ==";
		// String pwd = "UEBzc3cwcmQ==";
		String pwd = "gxwu1uY6MHU2k";
		// String pwd = null;
		System.out.println(Base64.base64Decode(pwd));

		String str = "&#x1";

		String source = "aaaaa中华人民共和国bbbbb";
		try {
			System.out.println(URLEncoder.encode("收件箱", "utf-8")); // %E6%94%B6%E4%BB%B6%E7%AE%B1
			// System.out.println(URLEncoder.encode("已发送邮件", "utf-8"));
			// //%E5%B7%B2%E5%8F%91%E9%80%81%E9%82%AE%E4%BB%B6
			// System.out.println(URLEncoder.encode("发件箱", "utf-8"));
			// //%E5%8F%91%E4%BB%B6%E7%AE%B1
			System.out.println("str: " + MimeUtility.decodeText(str));
			// util.p("source: " + MimeUtility.encodeText(source));
			p("source: " + MimeUtility.decodeText(source));

			String s0 = "蒙牛冰品--杨德勇 <dodgeyang911@163.com>";
			p("s0: " + MimeUtility.encodeText(s0, "utf-8", "B"));
			// String s =
			// "=?utf-8?B?6JKZ54mb5Yaw5ZOBLS3mnajlvrfli4cgPGRvZGdl?=\r\n\t=?utf-8?B?eWFuZzkxMUAxNjMuY29tPg==?=";
			String s = "=?utf-8?B?6JKZ54mb5Yaw5ZOBLS3mnajlvrfli4cgPGRvZGdl?= =?utf-8?B?eWFuZzkxMUAxNjMuY29tPg==?=";
			// String s = "=?utf-8?B?eWFuZzkxMUAxNjMuY29tPg==?=";
			p("s: " + MimeUtility.decodeText(s));
			// p(s.substring(s.indexOf("?=") + 2).indexOf("?=") >= 0);
			p(s.substring(s.indexOf("?=") + 2));
			p(s.indexOf("?=") + 2);
			p(s.substring(36));
			p(s.substring(36).indexOf("?=") >= 0);
			p("".indexOf("?=") >= 0);
			p(s.substring(s.indexOf("?=") + 2).equals(""));

			String ss = "=?UTF-8?B?5Z+66YeR6aKE57qm6ZSA5ZSu?=";
			p(MimeUtility.decodeText(ss));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		try {

			// System.out.println("default charset: " +
			// Charset.defaultCharset());
			// Map<String, Charset> map = Charset.availableCharsets();
			// Set<String> set = map.keySet();
			// Iterator iter = set.iterator();
			// Charset charset = null;
			// while(iter.hasNext()) {
			// charset = map.get(iter.next());
			// System.out.println("charset: " + charset.name());
			// }

			System.out.println(MimeUtility.encodeText("�� ־ΰ<�� ־ΰ/messagesolution>"));

			// String address = "�� ־ΰ<=?GBK?Q?=B5=CB_=D6=BE=CE=B0?=>";
			String address = "=?utf-8?B?5pu556GVIDEyMy9kb21pbm90ZXN0aW5n?=";

			if (address.indexOf("<") > 0 && address.indexOf(">") > 0) {
				Pattern pattern = Pattern.compile("(.*?)<(.*?)>");
				Matcher matcher = pattern.matcher(address);
				String addr = "";
				String temp = "";
				while (matcher.find()) {
					addr = matcher.group();
					temp = addr.substring(addr.indexOf("<") + 1, addr.lastIndexOf(">"));
					if (temp.startsWith("=?") && temp.endsWith("?=")) {
						temp = MimeUtility.decodeText(temp);
					}
				}
				address = addr.substring(0, addr.indexOf("<")) + "<" + temp + addr.substring(addr.indexOf(">"));
				System.out.println("address: " + address);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String str2 = "��˶ 123 <=?utf-8?B?5pu556GVIDEyMy9kb21pbm90ZXN0aW5n?=>, ��˶ 123 <=?utf-8?B?5pu556GVIDEyMy9kb21pbm90ZXN0aW5n?=>";
		str2 = transferedAddress(str2);
		System.out.println("str2: " + str2);
	}

	// this method deal with the special char '<', '>' in address for html
	// output.
	private String transferedAddress(String address) {
		try {
			if (address.indexOf("<") > 0 && address.indexOf(">") > 0) {
				Pattern pattern = Pattern.compile("(.*?)<(.*?)>");
				Matcher matcher = pattern.matcher(address);
				StringBuilder sb = new StringBuilder();
				String addr = "";
				String temp = "";
				while (matcher.find()) {
					addr = matcher.group();
					temp = addr.substring(addr.indexOf("<") + 1, addr.lastIndexOf(">"));
					if (temp.startsWith("=?") && temp.endsWith("?=")) {
						temp = MimeUtility.decodeText(temp);
					}
					sb.append(addr.substring(0, addr.indexOf("<")) + "<" + temp + addr.substring(addr.indexOf(">")));
				}
				// address = addr.substring(0, addr.indexOf("<")) + "<" + temp +
				// addr.substring(addr.indexOf(">"));
				address = sb.toString();
			}
			if (address != null && !address.equals("")) {
				address = address.replace("<", "&lt;");
				address = address.replace(">", "&gt;");
			}
		} catch (Exception ex) {
			System.out.println(" transfered address exception in  EmailSamplingSubmitter.transferedAddress()." + ex);
			return "";
		}
		return address;
	}
}
