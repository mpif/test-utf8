package com.codefans.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTest {

	/**
	 * @return void
	 * @author caisz
	 */
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(
				"\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");

		// String ip = "127.400.600.2";
		// String ip = "227.100.100";
		// String ip = "1w7.100.200.2";
		String ip = "127.200.200.2";
		Matcher matcher = pattern.matcher(ip); // 浠ラ獙璇�27.400.600.2涓轰緥

		System.out.println(matcher.matches());

		// String str = " ???ab？cdefg";
		// if(str.indexOf("?") >= 0) {
		// System.out.println(str.replaceAll("\\?", "nbsp;"));
		// }
		// if(str.indexOf("？") >= 0) {
		// System.out.println(str.replaceAll("？", "nbsp;"));
		// }

		String str = "<font face=\"Default Sans Serif,Verdana,Arial,Helvetica,sans-serif\" size=\"2\"><DIV>郭铁刚id号：89261</DIV>\r\n<DIV>&nbsp;</DIV>\r\n<DIV>代小芹12.28退休，请关闭相关账号<BR><BR><BR><BR>愿：平安喜乐<BR><BR>*****************************************<BR>王宇 人力资源部<BR><BR>同方威视技术股份有限公司<BR><BR>Address:中国北京市海淀区双清路同方大厦A座2层<BR>Aip Code:100084<BR>Tel:(86-10)83186566<BR>Fax:(86-10)62773651<BR>E-mail:wangyu@nuctech.com<BR>*****************************************</DIV>\r\n<DIV></DIV>\r\n<DIV></DIV></font> ?????";
		System.out.println(str.indexOf("?"));
		System.out.println(str.indexOf("？"));
		// System.out.println(filterInvalidChars(" ????fdjfjdfjdkfjdk
		// ???\r\n"));
		// System.out.println(filterInvalidChars(str));

		String s = " fjfj?dfd？";// ? 63
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			System.out.println((int) cs[i]);
		}
		System.out.println((int) '?');
		System.out.println((char) 0);
		System.out.println((char) 65533);
	}

	private static String filterInvalidChars(String source) {
		if (source.indexOf("\\?") >= 0) {
			source = source.replaceAll("\\?", "nbsp;");
		}
		source = source.replaceAll("\\？", "nbsp;");
		return source;
	}

}
