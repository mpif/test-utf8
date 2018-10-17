package com.codefans.socket.halfduplex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	/**
	 * 欢迎大家使用这个源码 如有疑问请加qq群：151648295
	 * 
	 * 半双工通讯
	 *
	 * 先是客户端从控制台给服务器发送消息 然后服务器回复消息
	 *
	 */

	public MyServer() {
		try {
			// 监听9999端口
			ServerSocket ss = new ServerSocket(9999);
			Socket s = ss.accept();
			// 读取客户端发送过来的消息
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);

			// 读取控制台输入的消息
			InputStreamReader isr2 = new InputStreamReader(System.in);
			BufferedReader br2 = new BufferedReader(isr2);

			// 向客户端发送消息 注意 true 这个参数如果没有讲无法发送消息
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

			while (true) {

				String accept = br.readLine();
				System.out.println(accept);
				System.out.println("请输入你的回复");

				String accept2 = br2.readLine();

				pw.println("服务器说：" + accept2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new MyServer();
	}

}
