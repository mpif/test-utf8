package com.codefans.java.socket;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;

import javax.net.ssl.SSLSocketFactory;

import com.sun.net.ssl.SSLContext;
import com.sun.net.ssl.TrustManager;

public class FirstSocket {

	protected Socket socket;
	protected DataInputStream dis;
	protected DataOutputStream dos;

	protected int SocketTimeOut = 3 * 60 * 1000; // 3min

	protected String ip;
	protected int port;

	public FirstSocket() {
		ip = "10.0.0.209";
		port = 443;
	}

	private void connect() {
		try {
			SSLContext context = null;

			context = SSLContext.getInstance("SSL");
			context.init(null, new TrustManager[] { new SSLClass(null) }, null);

			SSLSocketFactory sslF = context.getSocketFactory();

			URL serverurl = new URL("https://" + ip);
			InetAddress server = InetAddress.getByName(serverurl.getHost());
			socket = sslF.createSocket(server, port);
			// socket = new Socket(ip, 443);

			socket.setTcpNoDelay(true);
			socket.setSoTimeout(SocketTimeOut);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

			// InputStream is = socket.getInputStream();
			// System.out.println(is.available());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FirstSocket soc = new FirstSocket();
		soc.test();
	}

	public void test() {
		String ip = "10.0.0.127";
		int port = 4000;

		try {
			Socket socket = new Socket(ip, port);
			boolean isConn = socket.isConnected();
			System.out.println("isConn:" + isConn);

			boolean auth = true;

			System.out.println(auth ? "successful" : "fail");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
