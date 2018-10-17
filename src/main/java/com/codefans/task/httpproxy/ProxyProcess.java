package com.codefans.task.httpproxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import org.apache.commons.httpclient.HttpException;

import com.codefans.task.httpproxy.http.HttpRequest;
import com.codefans.task.httpproxy.http.HttpResponse;

public class ProxyProcess {

	private HttpRequest httpRequest;
	private HttpResponse httpResponse;
	private String uri;
	
	public ProxyProcess(InputStream is, OutputStream os) {
		httpRequest = new HttpRequest(is);
		httpResponse = new HttpResponse(os);
	}
	
	public void process() {
		
		uri = httpRequest.getUri();
		
		if(uri != null) {
			String suffix = uri.substring(uri.lastIndexOf("."));
            if (FileHelper.isVideos(suffix)) {
            	transferFileHandle();
//              transferFileHandle("videos/test.mkv", client);
//              continue;
            } else if (FileHelper.isImages(suffix)) {
            	transferFileHandle();
//              transferFileHandle("C:\\Users\\Sean\\Pictures\\抓包抓取到的图片\\274499.jpg", client);
//              transferNetFileHandle("images/test.jpg", client);
//              continue;
            } else {
            	httpResponse.responseNotFound();
            }
        }
		
		
		
	}
	
	private void transferFileHandle() {
		
//		HttpRequest request = new HttpRequest();
		try {
			HttpResponse response = httpRequest.execute();
			if(response != null) {
				httpResponse.setHeaders(response.getHeaders());
				httpResponse.setContent(response.getContent());
				httpResponse.setFileName(response.getFileName());
				httpResponse.setContentLength(response.getContentLength());
				httpResponse.execute();
				
				InputStream is = httpResponse.getContent();
				IOHelper.close(is);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		new Thread(new DownloadThread(uri)).start();
//		httpResponse.responseContent(httpRequest);
		
	}
	
}
