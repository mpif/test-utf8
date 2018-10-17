package com.codefans.task.httpproxy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

import com.codefans.task.httpproxy.http.HttpHelper;

public class DownloadThread implements Runnable {

	private String url;
	private String host;
	private String fileName;
	private String filePath;
	private int buffSize = 2048;
	
	public DownloadThread(String url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			host = HttpHelper.getUrlHost(url);
			fileName = url.substring(url.lastIndexOf("/") + 1);
			
			String root = FileHelper.getProxyDownloadRoot();
			String dirStr = root + File.separator + host;
			File dir = new File(dirStr);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			filePath = dirStr + File.separator + fileName;
			File file = new File(filePath);
			if(!file.exists()) {
				file.createNewFile();
			}
			IOHelper.writeUrlToFile(url, filePath);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOHelper.close(bis, bos);
			
		}
		
	}

}
