package com.codefans.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) {
		try {
			String name = InetAddress.getLocalHost().getHostName();
			System.out.println(name);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
