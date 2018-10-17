package com.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			p("ip: " + ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static void p(Object o) {
		System.out.println(o);
	}

}
