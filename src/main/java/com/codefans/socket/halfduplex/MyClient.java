package com.codefans.socket.halfduplex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {
	/**
	 * 欢迎大家使用这个源码 如有疑问请加qq群：151648295
	 * 
	 * 半双工通讯
	 *
	 * 先是客户端从控制台给服务器发送消息 然后服务器回复消息
	 *
	 */

	public MyClient() {
		try {
			// 获得连接
			Socket s = new Socket("127.0.0.1", 9999);

			// 读取控制台的输入信息
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			// 向服务器发送消息
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

			// 读取服器发送过来的消息
			InputStreamReader isr2 = new InputStreamReader(s.getInputStream());
			BufferedReader br2 = new BufferedReader(isr2);
			while (true) {
				System.out.println("请输入你对服务器的消息：");

				String send = br.readLine();

				pw.println("客户端说：" + send);

				System.out.println(br2.readLine());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MyClient();
	}

}
