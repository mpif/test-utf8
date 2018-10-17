package com.codefans.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Server {

	public static void main(String args[]) {
		Socket socket;
		java.net.ServerSocket server;
		int port = 2222;
		DataInputStream dis = null;
		String temp = "";
		try {
			server = new java.net.ServerSocket(port);
			while (true) {
				socket = server.accept();
				dis = new DataInputStream(socket.getInputStream());
				temp = dis.readUTF();
				System.out.println(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
