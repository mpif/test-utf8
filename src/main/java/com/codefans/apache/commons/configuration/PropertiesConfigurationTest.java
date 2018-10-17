package com.codefans.apache.commons.configuration;

import java.io.File;
import java.net.URL;

import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesConfigurationTest {

	/**
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {
		PropertiesConfigurationTest pct = new PropertiesConfigurationTest();
		pct.test();
	}

	public void test() throws Throwable {
		PropertiesConfiguration config = new PropertiesConfiguration();
		String fileName = "141889943211520000.m";
		URL url = PropertiesConfigurationTest.class.getResource(fileName);
		config = new PropertiesConfiguration(url);
		String uidKey = config.getString("streams.uidkey");
		int streamsCount = config.getInt("streams.count");
		String signature = config.getString("stream.1.signature");
		long size = config.getLong("stream.1.size");

		System.out.println("uidKey:" + uidKey);
		System.out.println("streamsCount:" + streamsCount);
		System.out.println("signature:" + signature);
		System.out.println("size:" + size);

		String filePath = "D:\\git\\test-utf8\\src\\com\\messagesolution\\apache\\commons\\configuration\\141889943211520000.m";
		File f = new File(filePath);
		boolean success = f.delete();
		System.out.println("delete success:[" + success + "]");

	}

}
