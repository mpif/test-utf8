package com.codefans.java.text;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageFormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("com.messagesolution.java.text.myResources");
		String pattern = bundle.getString("TEST.MESSAGE");
		String result = MessageFormat.format(pattern, new String[] { "world" });

		System.out.println("result: " + result);
	}

}
