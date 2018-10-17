package com.codefans.java.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * similar with Properties, ResourceBundle is generally use for
 * internationalization
 * 
 * @author caisz
 *
 */
public class ResourceBundleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// relative to classpath
		String baseName = "com/messagesolution/java/util/ResourceBundle";
		ResourceBundle rb = ResourceBundle.getBundle(baseName, new Locale("en_US"));
		String str = rb.getString("hello");
		System.out.println("str: " + str);

		ResourceBundle rb2 = ResourceBundle.getBundle(baseName, new Locale("zh_CN"));
		String str2 = rb2.getString("hello");
		System.out.println("str2: " + str2);
	}

}
