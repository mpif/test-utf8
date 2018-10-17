package com.test;

import java.nio.charset.Charset;
import java.util.Locale;

public class LocaleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileEncoding = System.getProperty("file.encoding");
		System.out.println("fileEncoding: " + fileEncoding);

		String defaultCharset = Charset.defaultCharset().displayName();
		System.out.println("defaultCharset: " + defaultCharset);

		Locale local = Locale.getDefault();
		System.out.println("defaultLocale:" + local.getDisplayName());
		System.out.println("defaultLocale:" + local.getLanguage());
		System.out.println("defaultLocale:" + local.getCountry());

	}

}
