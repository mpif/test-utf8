package com.codefans.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String args[]) {
		try {
			ServerSocket serverSocket = new ServerSocket(8889);
			Socket socket = null;
			while (true) {
				System.out.println("Server Ready...");
				socket = serverSocket.accept();
				String temp = "";
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				while ((temp = dis.readUTF()) != null) {
					System.out.println(temp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
