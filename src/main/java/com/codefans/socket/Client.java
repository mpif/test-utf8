package com.codefans.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	public static void main(String args[]) {
		int port = 2222;
		try {
			// Socket socket = new Socket("127.0.0.1", 2222);
			Socket socket = new Socket("192.168.253.130", 2222);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("cccc");

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
