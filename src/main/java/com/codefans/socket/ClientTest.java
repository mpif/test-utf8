package com.codefans.socket;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	/**
	 * @author caisz
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 8889);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			int n = 5;
			for (int i = 0; i < n; i++) {
				dos.writeUTF("'" + i + "'" + i + "'" + i + "'");
			}

			// for(int i = 0; i < 100000; i ++) {
			// }

			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
