package com.codefans.java.socket.ex01;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

/*
 * @Author: Sean
 * @Time: 2015-05-15 10:30:48
 */
public class SocketClient {

	private String ip;
	private int port;

	public SocketClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String ip = "10.0.0.26";
		int port = 6668;
		SocketClient sc = new SocketClient(ip, port);
		// sc.send();
		sc.sendObjects();
	}

	public void send() throws Exception {
		Socket sck = null;
		// Socket sck = new Socket("localhost", 6668);
		OutputStream os = null;
		ObjectOutputStream oos = null;
		TransferObject to = null;

		for (int i = 0; i < 5000; i++) {
			sck = new Socket("10.0.0.26", 6668);
			os = sck.getOutputStream();
			oos = new ObjectOutputStream(os);
			to = new TransferObject();
			to.setUsername("zhangsan_" + i);
			to.setPassword("123_" + i);
			oos.writeObject(to);

			oos.flush();
			oos.close();
			oos = null;
		}

	}

	public void sendObjects() throws Exception {
		TransferObject to = null;
		long start = System.currentTimeMillis();
		System.out.println("submit objects in client begin...");
		int itemCount = 5000;
		for (int i = 0; i < itemCount; i++) {
			to = new TransferObject();
			to.setUsername("zhangsan_" + i);
			to.setPassword("123_" + i);
			sendObject(to);
		}
		long end = System.currentTimeMillis();
		System.out.println("submit objects in client end. total items:[" + itemCount + "], total time cost:["
				+ (end - start) / 1000 + "s]");
	}

	public void sendObject(Object obj) throws Exception {
		Socket sck = null;
		OutputStream os = null;
		ObjectOutputStream oos = null;

		sck = new Socket(ip, port);
		os = sck.getOutputStream();
		oos = new ObjectOutputStream(os);
		oos.writeObject(obj);

		oos.flush();
		oos.close();
		oos = null;

		sck.close();
		sck = null;

	}

}
