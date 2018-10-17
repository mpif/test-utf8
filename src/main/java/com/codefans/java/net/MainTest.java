package com.codefans.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class MainTest {

	@Test
	public void run() {
		testInetAddress();
	}

	public void testInetAddress() {
		String host = "www.baidu.com";
		try {
			InetAddress inetAddress = InetAddress.getByName(host);
			String ip = inetAddress.toString();
			System.out.println("ip: " + ip);

			String hostName = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			System.out.println("hostName: " + hostName + ", hostAddress: " + hostAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
