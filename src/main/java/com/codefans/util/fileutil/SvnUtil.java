package com.codefans.util.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-4-22 Time: 下午3:31 To
 * change this template use File | Settings | File Templates.
 */
public class SvnUtil {

	private static String toDel = ".svn";
	private static long totalReleaseSize = 0;
	private static long unsuccessSize = 0;

	public static void main(String[] args) {
		System.out.println("Hello World!");

		SvnUtil svnUtil = new SvnUtil();

		// String path = "D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.1";
		// String path = "D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.8";
		// String path = "D:\\dev\\MT-2.1.15-fx - 副本\\dist";
		// String path = "D:\\dev\\M-2.1.15";
		// String path = "D:\\dev\\REL-2.1.16 - backup";
		// String path = "D:\\dev\\FB-ClusterEAS - 副本";
		// String path = "D:\\dev\\MT-2.1.18-fx_2014-11-17_42179_All";
		// String path = "D:\\dev\\MT-2.1.19-fx_2014-11-20_42209_All";
		// String path = "D:\\dev\\HostingTrunk - 副本";
		String path = "D:\\dev\\document\\EWS归档\\ews--建伟交接资料\\SteveZhang\\社交网站归档\\Facebook java api\\facebook-java-api";
		// String path =
		// "D:\\360云盘\\MessageSolution\\项目\\EAS_SVN_Backup\\2011-12-14\\EAS_SVN";

		svnUtil.delete(path);
		System.out.println("totalReleaseSize:[" + svnUtil.FormetFileSize(totalReleaseSize) + "]");
		System.out.println("unsuccessSize:[" + svnUtil.FormetFileSize(unsuccessSize) + "]");

	}

	public void delete(String path) {
		File direct = new File(path);
		String name = "";
		if (direct.isDirectory()) {
			File[] files = direct.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				name = file.getName();
				if (name.equals(toDel)) {
					deleteFolder(path + File.separator + name);
					deleteFile(path + File.separator + name);
				} else {
					if (file.isDirectory()) {
						delete(path + File.separator + name);
					}
				}
			}
		}
	}

	public void deleteFolder(String path) {
		File direct = new File(path);
		String name = "";
		if (direct.isDirectory()) {
			File[] files = direct.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				name = file.getName();
				if (file.isDirectory()) {
					deleteFolder(path + File.separator + name);
				}
				deleteFile(path + File.separator + name);
			}
		}
		deleteFile(path);
	}

	public void deleteFile(String path) {
		File file = new File(path);
		long size = file.length();
		if (file.exists()) {
			boolean flag = file.delete();
			if (flag) {
				totalReleaseSize += size;
				System.out.println("Success!, path=" + path);
			} else {
				unsuccessSize += size;
				System.out.println("Fail!, path=" + path);
			}
		}
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

}
