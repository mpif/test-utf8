package com.test;

import java.io.File;

/**
 * Created by IntelliJ IDEA. User: bobo Date: 2006-12-12 Time: 14:13:53 To
 * change this template use File | Settings | File Templates.
 */
public class GetDiskStatus {
	public GetDiskStatus() {

	}

	public static float getSimpleStatus(String path) {
		try {
			// float freespace = DiskUtil.getFreeSpace(path);
			// float totalspace = DiskUtil.getTotalSpace(path);
			float freespace = getFreeSpace(path);
			float totalspace = getTotalSpace(path);
			if (totalspace == 0)
				return 0;
			float percent = (totalspace - freespace) * 100 / totalspace;
			return percent;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	// 获取磁盘的可用空间，只要path在同一个磁盘下，获取到的值都是一样的
	public static float getFreeSpace(String path) throws Exception {
		File file = new File(path);
		return file.getFreeSpace();
	}

	// 获取磁盘的总空间大小，只要path在同一个磁盘下，获取到的值都是一样的
	public static float getTotalSpace(String path) throws Exception {
		File file = new File(path);
		return file.getTotalSpace();
	}

	public static void main(String[] args) {
		// String path = "D:\\dev\\EAS_SVN";
		String path = "C:";
		System.out.println(path + " used space: " + getSimpleStatus(path) + "%");

		String path2 = "D:\\dev\\RB-2.1.x-gx";
		System.out.println(path + " used space: " + getSimpleStatus(path2) + "%");
	}
}
