package com.codefans.task.httpproxy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AcceptedSocketThread implements Runnable {

	private Socket client;
	private ProxyProcess proxyProcess;
	
	public AcceptedSocketThread(Socket socket) {
		this.client = socket;
	}
	
	@Override
	public void run() {
		try {
			proxyProcess = new ProxyProcess(client.getInputStream(), client.getOutputStream());
			proxyProcess.process();
			
			
			
//			ps = new PrintStream(os);
//			br = new BufferedReader(new InputStreamReader(is));
			
//			CONNECT www.baidu.com:443 HTTP/1.1
//			Host: www.baidu.com:443
//			Proxy-Connection: keep-alive
//			User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36

//			String line = "";
//			int count = 1;
////			while(sc.hasNextLine()) {
//			while(count < 12) {
//				line = sc.nextLine();
//				System.out.println(line);
//				count++;
//			}
//			sc.close();
//			sc = null;
			
			
			
//            if(resource != null) {
//	            if (resource.endsWith(".mkv")) {
//	//              transferFileHandle("videos/test.mkv", client);
//	              closeSocket(client);
//	//              continue;
//	            } else if (resource.endsWith(".jpg")) {
//	//              transferFileHandle("images/test.jpg", client);
//	              transferFileHandle("C:\\Users\\Sean\\Pictures\\抓包抓取到的图片\\274499.jpg", client);
//	//              transferNetFileHandle("images/test.jpg", client);
//	              closeSocket(client);
//	//              continue;
//	            } else if (resource.endsWith(".rmvb")) {
//	//              transferFileHandle("videos/test.rmvb", client);
//	              closeSocket(client);
//	//              continue;
//	            } else {
//	              PrintStream writer = new PrintStream(
//	                  client.getOutputStream(), true);
//	              writer.println("HTTP/1.0 404 Not found");// 返回应答消息,并结束应答
//	              writer.println();// 根据 HTTP 协议, 空行将结束头信息
//	              writer.close();
//	              closeSocket(client);
//	//              continue;
//	            }
//            }
			
			//data need to response
//			Accept-Ranges:bytes
//			Content-Length:54353
//			Content-Type:image/jpeg
//			Date:Sat, 23 Jul 2016 06:21:45 GMT
//			ETag:"b958b6b64fe4d11:0"
//			Last-Modified:Fri, 22 Jul 2016 19:31:58 GMT
//			Server:Microsoft-IIS/7.5
//			X-Powered-By:ASP.NET
			String content = "Welcome to visit ProxyApp!";
			String responseStr = "";
			responseStr += "HTTP/1.1 200 OK\r\n";
			responseStr += "Date: Sat, 23 Jul 2016 14:41:59 GMT\r\n";
			responseStr += "Content-Type: text/html;charset=ISO-8859-1\r\n";
			responseStr += "Content-Length: " + content.getBytes().length + "\r\n";
			responseStr += "\r\n" + content;
			byte[] bytes = responseStr.getBytes();
//			bos.write(bytes, 0, bytes.length);
//			bos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
//			close(is, bos, client);
			IOHelper.close(client);
		}
		
	}
	
	
	
	private void transferNetFileHandle(String path, Socket client) {
//	    URL url = new URL(path);
//	    InputStream is = url.openStream();
	    File fileToSend = new File(path);
	    if (fileToSend.exists() && !fileToSend.isDirectory()) {
	      try {
	        PrintStream writer = new PrintStream(client.getOutputStream());
	        writer.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
	        writer.println("Content-Type:application/binary");
	        writer.println("Content-Length:" + fileToSend.length());// 返回内容字节数
	        writer.println();// 根据 HTTP 协议, 空行将结束头信息

	        FileInputStream fis = new FileInputStream(fileToSend);
	        byte[] buf = new byte[fis.available()];
	        fis.read(buf);
	        writer.write(buf);
	        writer.close();
	        fis.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	
	public void close(InputStream is, OutputStream os, Socket socket) {
		try {
			if(is != null) {
				is.close();
				is = null;
			}
			if(os != null) {
				os.flush();
				os.close();
				os = null;
			}
			if(socket != null) {
				socket.close();
				socket = null;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeSocket(Socket socket) {
		try {
			if(socket != null) {
				socket.close();
				socket = null;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
