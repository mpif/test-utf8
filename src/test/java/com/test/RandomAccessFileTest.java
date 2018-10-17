package com.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

public class RandomAccessFileTest {

	@Test
	public void test() {
		testRead();
	}
	
	public void testRead() {
		RandomAccessFile raf = null;
		try {
			
			String filePath = "F:/tmp/data/test/TESTING.txt";
			File file = new File(filePath);
			String text = "奢侈品";
			raf = new RandomAccessFile(file, "r");
			byte[] bytes = new byte[1024];
			long length = raf.length();
			String readText = "";
			int readBytes = 0;
			
			while(raf.getFilePointer() < length) {
				readBytes = raf.read(bytes, 0, (int)length);
				byte b = bytes[0];
//				System.out.println("b:" + b);
//				System.out.println("(char)b:" + (char)b);
				if(bytes[0] == -17 && bytes[1] == -69 && bytes[2] == -65) { //utf-8
					readText = new String(bytes, "UTF-8");
					System.out.println("charset is UTF-8, 3 bytes a Chinese, total bytes:" + readBytes);
				} else if(b == 104) { //ANSI
					readText = new String(bytes, "GB2312");
					System.out.println("charset is GB2312, 2 bytes a Chinese, total bytes:" + readBytes);
				} else {
					byte[] bs = new byte[2];
					bs[0] = bytes[0];
					bs[1] = bytes[1];
					System.out.println(new String(bs, "GBK"));
					System.out.println(bytes[1]);
					System.out.println(bytes[2]);
					readText = new String(bytes, 0, (int)length, "GBK");
//					readText = new String(bytes, "GBK");
					System.out.println("charset is GBK, 2 bytes a Chinese, total bytes:" + readBytes);
					System.out.println(readText);
					readText = "aaa[" + readText + "]bbb";
				}
				System.out.println(readText);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(raf);
		}
	}
	
	public void close(RandomAccessFile raf) {
		try {
			if(raf != null) {
				raf.close();
				raf = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
