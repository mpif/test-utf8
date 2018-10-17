package com.codefans.task.httpproxy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.codefans.task.httpproxy.http.HttpHelper;

public class IOHelper {

	public static void writeUrlToFile(String url, String outFile) throws IOException {
		InputStream is = HttpHelper.getInputStream(url);
		OutputStream os = new FileOutputStream(outFile);
		write(is, os);
	}
	
	public static void write(InputStream is, OutputStream os) throws IOException {
		byte[] buff = new byte[2048];
		int len = 0;
		while((len = is.read(buff)) != -1) {
			os.write(buff, 0, len);
		}
		os.flush();
		IOHelper.close(is, os);
	}
	
	public static void buffWrite(InputStream is, OutputStream os) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(os);
		byte[] buff = new byte[2048];
		int len = 0;
		while((len = bis.read(buff)) != -1) {
			bos.write(buff, 0, len);
		}
		bos.flush();
		IOHelper.close(bis, bos);
	}
	
	public static void close(InputStream is) {
		try {
			if(is != null) {
				is.close();
				is = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void close(OutputStream os) {
		try {
			if(os != null) {
				os.close();
				os.flush();
				os = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void close(Socket socket) {
		try {
			if(socket != null) {
				socket.close();
				socket = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(InputStream is, OutputStream os) {
		close(is);
		close(os);
	}
	
	public static void close(InputStream is, OutputStream os, Socket socket) {
		close(is);
		close(os);
		close(socket);
	}
	
}
