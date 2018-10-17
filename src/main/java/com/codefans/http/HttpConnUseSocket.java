package com.codefans.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpConnUseSocket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket s = new Socket("10.0.0.50", 8080);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
