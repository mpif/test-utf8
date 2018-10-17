package com.codefans.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchIP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Pattern p =
		// Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
		//// Pattern p =
		// Pattern.compile("([1-9]|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])(//.(//d|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])){3}");
		// Matcher matcher = p.matcher("[gfgfg3310.0.0.50]gfgfdg");
		// while(matcher.find()) {
		// System.out.println("ip: " + matcher.group());
		// }

		// String accessIp = "172.24.230.8";
		// String accessIp = "61.141.193.164";
		// String ipSection = "172.24.1.1-172.24.255.255";
		// boolean flag = ipIsValid(ipSection, accessIp);
		// System.out.println(flag);

		String str = "https://10.0.0.209:443/EWS/exchange.asmx";
		System.out.println(parseIp(str));

	}

	public static String parseIp(String str) {
		// Pattern pattern =
		// Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
		// Pattern pattern =
		// Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Pattern pattern = Pattern.compile(
				"\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");
		Matcher matcher = pattern.matcher(str);
		String ip = "";
		if (matcher.find()) {
			ip = matcher.group();
		}
		return ip;
	}

	public static boolean validateIp(String ip) {
		Pattern pattern = Pattern.compile(
				"\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(ip); // 以验证127.400.600.2为例
		return matcher.matches();
	}

	public static boolean ipIsValid(String ipSection, String ip) {
		if (ipSection == null) {
			System.out.println("-- ipSection is null!!");
			return false;
		}
		if (ip == null) {
			System.out.println("-- ip is null!!");
			return false;
		}
		ipSection = ipSection.trim();
		ip = ip.trim();
		final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
		final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
		if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP))
			return false;
		int idx = ipSection.indexOf('-');
		String[] sips = ipSection.substring(0, idx).split("\\.");
		String[] sipe = ipSection.substring(idx + 1).split("\\.");
		String[] sipt = ip.split("\\.");
		long ips = 0L, ipe = 0L, ipt = 0L;
		for (int i = 0; i < 4; ++i) {
			ips = ips << 8 | Integer.parseInt(sips[i]);
			ipe = ipe << 8 | Integer.parseInt(sipe[i]);
			ipt = ipt << 8 | Integer.parseInt(sipt[i]);
		}
		if (ips > ipe) {
			long t = ips;
			ips = ipe;
			ipe = t;
		}
		return ips <= ipt && ipt <= ipe;
	}
}
