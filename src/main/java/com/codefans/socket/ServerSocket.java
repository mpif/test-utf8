package com.codefans.socket;

import java.io.IOException;
import java.net.Socket;

public class ServerSocket implements Runnable {

	private String ip;
	private int port;
	private java.net.ServerSocket server;

	public ServerSocket(String ip, int port) {
		this.ip = ip;
		this.port = port;
		try {
			server = new java.net.ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Socket socket;
		while (true) {
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public java.net.ServerSocket getServer() {
		return server;
	}

	public void setServer(java.net.ServerSocket server) {
		this.server = server;
	}

}
