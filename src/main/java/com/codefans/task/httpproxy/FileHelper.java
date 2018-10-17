package com.codefans.task.httpproxy;

import java.io.File;

public class FileHelper {

	public static boolean isVideos(String suffix) {
		boolean flag = false;
		
		if(".mkv".equalsIgnoreCase(suffix)) {
			flag = true;
		} else if(".rm".equalsIgnoreCase(suffix)) {
			flag = true;
		} else if(".mp4".equalsIgnoreCase(suffix)) {
			flag = true;
		} else if(".rmvb".equalsIgnoreCase(suffix)) {
			flag = true;
		} else if(".flv".equalsIgnoreCase(suffix)) {
			flag = true;
		}
		
		return flag;
	}
	
	public static boolean isImages(String suffix) {
		boolean flag = false;
		
		if(".jpg".equalsIgnoreCase(suffix)) {
			flag = true;
		} else if(".jpeg".equalsIgnoreCase(suffix)) {
			flag = true;
		} else if(".png".equalsIgnoreCase(suffix)) {
			flag = true;
		} else if(".gif".equalsIgnoreCase(suffix)) {
			flag = true;
		} else if(".bmp".equalsIgnoreCase(suffix)) {
			flag = true;
		}
		
		return flag;
	}
	
	public static String getProxyDownloadRoot() {
		String rootDir = "";
		
		rootDir = System.getProperty("user.dir") + File.separator + "ProxyDownload";
//		System.out.println(rootDir);
		
		File dir = new File(rootDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		return rootDir;
	}
	
	public static void main(String[] args) {
		getProxyDownloadRoot();
	}
	
}
