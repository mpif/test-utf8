package com.codefans.task.httpproxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通过java的socket模拟http请求与http响应, 来实现代理功能。
 * 给浏览器设置一个代理, 代理地址为ServerSocket监听的地址和端口. 
 * 这样我们在浏览器地址栏中请求一个资源, 浏览器会将请求发给代理, 然后代理再去请求网络上的资源, 并将请求到的资源返回给浏览器。
 * 通过代理转发请求, 目的在于: 所有的资源都得经过代理, 代理可以在用户不知道的情况下做一些额外的处理。
 * @author Sean
 *
 */
public class ProxyApp {

	public static void main(String[] args) {
		ProxyApp proxyApp = new ProxyApp();
		proxyApp.startup();
	}
	
	public void startup() {
		try {
			int port = 9999;
			ServerSocket serSocket = new ServerSocket(port);
			Socket socket = null;
			System.out.println("ProxyApp is listening to port 9999.");
			while((socket = serSocket.accept()) != null) {
				new Thread(new AcceptedSocketThread(socket)).start();;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
