package com.codefans.util;

import java.io.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-4-10 Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesUtil {

	public static void main(String[] args) {
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		propertiesUtil.updateOneLine();
		// propertiesUtil.updateAll();

	}

	public void updateOneLine() {

		try {
			String conf = "C:/eas.conf";
			File confFile = new File(conf);
			RandomAccessFile randomAccessFile = new RandomAccessFile(confFile, "rw");

			String temp = "";
			long filePointer = 0;
			// temp = randomAccessFile.readLine();

			while ((temp = randomAccessFile.readLine()) != null) {
				if (temp.trim().length() > 0) {
					if (temp.startsWith("/mail/config/system/ews/serverVersion=")) {
						filePointer = randomAccessFile.getFilePointer();
						filePointer = (filePointer - (temp.length() + 2));
						System.out.println(temp);
						System.out.println("filePointer:[" + filePointer + "]");
					}
				}
			}

			randomAccessFile.seek(0);
			randomAccessFile.skipBytes((int) filePointer);
			temp = "/mail/config/system/ews/serverVersion=Exchange2010;Exchange2010_SP1\r\n";
			byte[] bytes = temp.getBytes("UTF-8");
			randomAccessFile.write(bytes, 0, bytes.length);

			// #whether print\r\n

			randomAccessFile.seek(0);
			randomAccessFile.skipBytes((int) filePointer);
			temp = randomAccessFile.readLine();
			System.out.println("temp:[" + temp + "]");

			System.out.println("\r\n#whether print".length());
			System.out.println(";Exchange2010_SP1".length());
			System.out.println("\r\n#whether print".length() == ";Exchange2010_SP1".length());

		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	public void updateAll() {
		try {
			Properties prop = new Properties();// 属性集合对象
			FileInputStream fis = new FileInputStream("C:/test.properties");// 属性文件输入流
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流

			// 获取属性值，sitename已在文件中定义
			System.out.println("获取属性值:password=" + prop.getProperty("password"));
			// 获取属性值，country未在文件中定义，将在此程序中返回一个默认值，但并不修改属性文件
			// System.out.println("获取属性值：country=" + prop.getProperty("country",
			// "中国"));

			// 修改sitename的属性值
			prop.setProperty("password", "heihei");
			// 文件输出流
			FileOutputStream fos = new FileOutputStream("C:/test.properties");
			// 将Properties集合保存到流中
			prop.store(fos, "Copyright (c) Boxcode Studio");
			fos.close();// 关闭流
			System.out.println("获取修改后的属性值：password=" + prop.getProperty("password"));
			System.out.println("获取属性值：username=" + prop.getProperty("username"));
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

}
