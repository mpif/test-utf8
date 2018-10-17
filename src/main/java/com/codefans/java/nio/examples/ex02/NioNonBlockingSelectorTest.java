package com.codefans.java.nio.examples.ex02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.junit.Test;

/*
 * @Author: Sean
 * @Time: 2015-07-13 16:56:28
 */
public class NioNonBlockingSelectorTest {

	Selector selector;
	private ByteBuffer receivebuffer = ByteBuffer.allocate(1024);

	public static void main(String[] args) throws Throwable {
		NioNonBlockingSelectorTest test = new NioNonBlockingSelectorTest();
		test.testNioNonBlockingSelector();
	}

	// @Test
	public void testNioNonBlockingSelector() throws Exception {
		selector = Selector.open();
		SocketAddress address = new InetSocketAddress(10002);
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.socket().bind(address);
		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();
				handleKey(selectionKey);
			}
		}
	}

	private void handleKey(SelectionKey selectionKey) throws IOException {
		ServerSocketChannel server = null;
		SocketChannel client = null;
		if (selectionKey.isAcceptable()) {
			server = (ServerSocketChannel) selectionKey.channel();
			client = server.accept();
			System.out.println("客户端： " + client.socket().getRemoteSocketAddress().toString());
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		}
		if (selectionKey.isReadable()) {
			client = (SocketChannel) selectionKey.channel();
			receivebuffer.clear();
			int count = client.read(receivebuffer);
			if (count > 0) {
				String receiveText = new String(receivebuffer.array(), 0, count);
				System.out.println("服务器端接受客户端数据--:" + receiveText);
				client.register(selector, SelectionKey.OP_READ);
			}
		}
	}
}
