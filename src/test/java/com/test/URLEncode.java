package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class URLEncode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		p(System.getProperty("file.encoding"));
		p(Charset.defaultCharset().displayName());

		// String username = "Hlp, С����";
		String username = "Hlp,+%E5%B0%8F%EF%BC%8C%E4%B8%89";
		String showusername = "";
		try {

			// System.out.println("aaa%24".replaceAll("%24", "b"));

			// String encodeStr = URLEncoder.encode("标题 带空格", "utf-8");
			// String encodeStr = URLEncoder.encode("a20:b", "utf-8");
			// String encodeStr = URLEncoder.encode("a:b\"c<d>e*", "utf-8");
			// String encodeStr = URLEncoder.encode("-: $_", "utf-8");
			// String encodeStr = URLEncoder.encode("A.A-A+", "utf-8");
			// String encodeStr = URLEncoder.encode("标题 带空格");

			// String str = "#_%&+-/\=?[ ].:;'"|,~!@$^*(){}<>";
			String str = "#_%&+-/\\=?[ ].:;'\"|,~!@$^*(){}<>";
			String encodeStr = URLEncoder.encode(str, "utf-8");
			// System.out.println(encodeStr);

			String ss = "\\_/_:_*_?_\"_<_>_|";
			ss = filterCharWindowNotAllow(ss);
			System.out.println(ss);

			// encodeStr = filterSpecialChars(encodeStr);
			// System.out.println(encodeStr);

			String[] names = null;
			if (username.indexOf(",") >= 0) {
				names = username.split(",");
				for (int i = 0; i < names.length; i++) {
					// showusername += java.net.URLEncoder.encode(names[i] ,
					// "UTF-8") + ",";
					showusername += java.net.URLDecoder.decode(names[i], "UTF-8") + ",";
				}
			}

			// p(URLEncoder.encode(username, "UTF-8"));
			// p(showusername);

			String s = "%E5%86%92%E5%8F%B7:%E5%8F%8C%E5%BC%95%E5%8F%B7\"%E5%B7%A6%E4%B9%A6%E5%90%8D%E5%8F%B7<%E5%8F%B3%E4%B9%A6%E5%90%8D%E5%8F%B7>%E7%89%B9%E6%AE%8A%E5%AD%97%E7%AC%A6%E6%B5%8B%E8%AF%95";
			// System.out.println(URLDecoder.decode(s, "utf-8"));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String filterCharWindowNotAllow(String subject) {
		if (subject != null) {
			if (subject.indexOf("\"") >= 0) {
				subject = subject.replaceAll("\"", "＂");
			}
			if (subject.indexOf("\\") >= 0) {
				subject = subject.replaceAll("\\\\", "﹨");
			}
			if (subject.indexOf("/") >= 0) {
				subject = subject.replaceAll("/", "／");
			}
			if (subject.indexOf(":") >= 0) {
				subject = subject.replaceAll(":", "︰");
			}
			if (subject.indexOf("<") >= 0) {
				subject = subject.replaceAll("<", "﹤");
			}
			if (subject.indexOf(">") >= 0) {
				subject = subject.replaceAll(">", "﹥");
			}
			if (subject.indexOf("?") >= 0) {
				subject = subject.replaceAll("\\?", "？");
			}
			if (subject.indexOf("|") >= 0) {
				subject = subject.replaceAll("\\|", "︳");
			}
			if (subject.indexOf("*") >= 0) {
				subject = subject.replaceAll("\\*", "﹡");
			}
		}
		return subject;
	}

	protected static String filterSpecialChars(String subject) {
		if (subject != null) {
			// the blank ' ' will be encoded as '+' by URLEncoder.encode(str,
			// "utf-8") method,
			if (subject.indexOf("+") >= 0) {
				subject = subject.replaceAll("\\+", " ");
			}
			if (subject.indexOf("%21") >= 0) {
				subject = subject.replaceAll("%21", "!");
			}
			if (subject.indexOf("%22") >= 0) {
				subject = subject.replaceAll("%22", "\"");
			}
			if (subject.indexOf("%23") >= 0) {
				subject = subject.replaceAll("%23", "#");
			}
			if (subject.indexOf("%24") >= 0) {
				subject = subject.replaceAll("%24", "\\$");
			}
			if (subject.indexOf("%25") >= 0) {
				subject = subject.replaceAll("%25", "%");
			}
			if (subject.indexOf("%26") >= 0) {
				subject = subject.replaceAll("%26", "&");
			}
			if (subject.indexOf("%27") >= 0) {
				subject = subject.replaceAll("%27", "'");
			}
			if (subject.indexOf("%28") >= 0) {
				subject = subject.replaceAll("%28", "(");
			}
			if (subject.indexOf("%29") >= 0) {
				subject = subject.replaceAll("%29", ")");
			}
			if (subject.indexOf("%2B") >= 0) {
				subject = subject.replaceAll("%2B", "+");
			}
			if (subject.indexOf("%2C") >= 0) {
				subject = subject.replaceAll("%2C", ",");
			}
			if (subject.indexOf("%2F") >= 0) {
				subject = subject.replaceAll("%2F", "/");
			}
			if (subject.indexOf("%5C") >= 0) {
				subject = subject.replaceAll("%5C", "\\\\");
			}
			if (subject.indexOf("%3A") >= 0) {
				subject = subject.replaceAll("%3A", ":");
			}
			if (subject.indexOf("%3B") >= 0) {
				subject = subject.replaceAll("%3B", ";");
			}
			if (subject.indexOf("%3C") >= 0) {
				subject = subject.replaceAll("%3C", "<");
			}
			if (subject.indexOf("%3D") >= 0) {
				subject = subject.replaceAll("%3D", "=");
			}
			if (subject.indexOf("%3E") >= 0) {
				subject = subject.replaceAll("%3E", ">");
			}
			if (subject.indexOf("%3F") >= 0) {
				subject = subject.replaceAll("%3F", "?");
			}
			if (subject.indexOf("%40") >= 0) {
				subject = subject.replaceAll("%40", "@");
			}
			if (subject.indexOf("%5B") >= 0) {
				subject = subject.replaceAll("%5B", "[");
			}
			if (subject.indexOf("%5D") >= 0) {
				subject = subject.replaceAll("%5D", "]");
			}
			if (subject.indexOf("%5E") >= 0) {
				subject = subject.replaceAll("%5E", "^");
			}
			if (subject.indexOf("%7B") >= 0) {
				subject = subject.replaceAll("%7B", "{");
			}
			if (subject.indexOf("%7C") >= 0) {
				subject = subject.replaceAll("%7C", "|");
			}
			if (subject.indexOf("%7D") >= 0) {
				subject = subject.replaceAll("%7D", "}");
			}
			if (subject.indexOf("%7E") >= 0) {
				subject = subject.replaceAll("%7E", "~");
			}
		}
		return subject;
	}

	public static void p(Object o) {
		System.out.println(o);
	}
}
