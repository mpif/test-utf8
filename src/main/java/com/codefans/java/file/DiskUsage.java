package com.codefans.java.file;

import java.io.File;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-28 Time: 上午11:09
 * To change this template use File | Settings | File Templates.
 */
public class DiskUsage {
	public static void main(String[] args) {
		File file = new File("C:");
		System.out.println(file.getTotalSpace());
		System.out.println(file.getUsableSpace());
		System.out.println(file.getFreeSpace());
	}
}
