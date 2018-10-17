package com.codefans.java.file;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-28 Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public class GetFileAndDirSize {
	public static void main(String[] args) {
		GetFileAndDirSize g = new GetFileAndDirSize();
		long startTime = System.currentTimeMillis();
		try {
			long l = 0;
			// String path = "C:\\a";
			// String path = "D:\\360云盘\\MessageSolution";
//			 String path = "D:\\GitHub\\test-utf8\\src\\com\\messagesolution2";
//			 文件个数          582
//			 目录
//			 D:\GitHub\test-utf8\src\com\messagesolution2目录的大小为：2686087B
//			 D:\GitHub\test-utf8\src\com\messagesolution2目录的大小为：2.56M
			 String path = "D:\\GitHub\\test-utf8\\src\\com\\codefans";
//			String path = "C:";
			File ff = new File(path);
			if (ff.isDirectory()) { // 如果路径是文件夹的时候
				System.out.println("文件个数          " + g.getlist(ff));
				System.out.println("目录");
				l = g.getFileSize(ff);
				System.out.println(path + "目录的大小为：" + l + "B");
				System.out.println(path + "目录的大小为：" + g.FormetFileSize(l));
			} else {
				System.out.println("    文件个数          1");
				System.out.println("文件");
				l = g.getFileSizes(ff);
				System.out.println(path + "文件的大小为：" + g.FormetFileSize(l));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("总共花费时间为：" + (endTime - startTime) + "毫秒...");
	}

	public long getFileSizes(File f) throws Exception {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
		return s;
	}

	// 递归
	public long getFileSize(File f) throws Exception {// 取得文件夹大小
		long size = 0;
		File flist[] = f.listFiles();
		if (flist != null) {
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getFileSize(flist[i]);
				} else {
					size = size + flist[i].length();
				}
			}
		}
		return size;
	}

	public String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public long getlist(File f) {// 递归求取目录文件个数
		long size = 0;
		File flist[] = f.listFiles();
		if (flist != null) {
			size = flist.length;
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getlist(flist[i]);
					size--;
				}
			}
		}
		return size;
	}

}
