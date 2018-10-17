package com.codefans.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConvertFileContent2String {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InputStream is = ConvertFileContent2String.class.getResourceAsStream("CreateItemRequest.xml");
		String str = convertFileContent2String(is);
		System.out.println(str);

		System.out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>".replaceAll("\"", "\\\\\""));
	}

	public static String convertFileContent2String(File file) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			return convertFileContent2String(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String convertFileContent2String(InputStream is) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				sb.append("\"").append(line.replaceAll("\"", "\\\\\"")).append("\" + ").append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
