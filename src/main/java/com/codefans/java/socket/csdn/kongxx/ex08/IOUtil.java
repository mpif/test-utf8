package com.codefans.java.socket.csdn.kongxx.ex08;

/*
 * @Author: Sean
 * @Time: 2015-05-18 14:55:56
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IOUtil {

	public static void closeQuietly(InputStream is) {
		try {
			is.close();
		} catch (Exception e) {
		}
	}

	public static void closeQuietly(OutputStream os) {
		try {
			os.close();
		} catch (Exception e) {
		}
	}

	public static void closeQuietly(Socket socket) {
		try {
			socket.close();
		} catch (Exception e) {
		}
	}
}
