package com.codefans.task.httpproxy.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.codefans.task.httpproxy.FileHelper;
import com.codefans.task.httpproxy.IOHelper;

public class HttpResponse {

	private OutputStream os;
	private PrintStream ps;
	private Map<String, String> headers = new HashMap<String, String>();
	private boolean autoFlush = true;
	private InputStream content;
	private String fileName;
	private Long contentLength;
	
	public HttpResponse(OutputStream os) {
		this.os = os;
		ps = new PrintStream(os, autoFlush);
		
	}
	
	public HttpResponse() {
		
	}
	
	public void responseHeaders(HttpRequest httpRequest) {
		
        ps.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
//        writer.println("Content-Type:application/binary");
        ps.println("Content-Disposition:attachment;filename="+httpRequest.getFileName());
        ps.println("Content-Type:image/webp");
//        ps.println("Content-Length:" + fileToSend.length());// 返回内容字节数
        ps.println();// 根据 HTTP 协议, 空行将结束头信息
		
		
	}
	
	public void responseContent(HttpRequest httpRequest) {
		try {
			
			File file = new File("G:\\GitHub\\test-utf8\\ProxyDownload\\pic1.desk.chinaz.com\\a.jpg");
			
//			responseHeaders(httpRequest);
			ps.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
//	        writer.println("Content-Type:application/binary");
	        ps.println("Content-Disposition:attachment;filename="+httpRequest.getFileName());
	        ps.println("Content-Type:image/webp");
	        ps.println("Content-Length:" + file.length());// 返回内容字节数
	        ps.println();
//			InputStream is = httpRequest.getContent();
//			InputStream is = new FileInputStream(file);
//			OutputStream os = new FileOutputStream(new File("G:\\GitHub\\test-utf8\\ProxyDownload\\pic1.desk.chinaz.com\\a.jpg"));
//			byte[] buff = new byte[2048];
//			int len = 0;
//			while((len = is.read(buff)) != -1) {
//				ps.write(buff, 0, len);
//			}
			FileInputStream fis = new FileInputStream(file);
	        byte[] buf = new byte[(int)file.length()];
	        fis.read(buf);
	        ps.write(buf);
	        ps.close();
	        fis.close();
	        
//			IOHelper.close(ps);
//			IOHelper.close(is);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void execute() throws IOException {
		
		String tmpFile = FileHelper.getProxyDownloadRoot() + File.separator + this.getFileName();
		File file = new File(tmpFile);
		boolean downloadBefore = false;
		if(file.exists()) {
			downloadBefore = true;
			System.out.println("[" + tmpFile + "] already download before.");
		} else {
			FileOutputStream fos = new FileOutputStream(new File(tmpFile));
			IOHelper.write(content, fos);
			System.out.println("[" + tmpFile + "] download complete.");
		}
		
		
		ps.println("HTTP/1.0 200 OK");// 返回应答消息,并结束应答
		
		if(headers != null) {
			Iterator<Map.Entry<String,String>> iter = headers.entrySet().iterator();
			Map.Entry<String,String> element = null;
			String key = "";
			String value = "";
			
			while(iter.hasNext()) {
				element = iter.next();
				key = element.getKey();
				
				if(HttpHelper.isFilterHeader(key)) {
					continue;
				}
				
				value = element.getValue();
				System.out.println(key + ":" + value);
				ps.println(key + ":" + value);
			}
			
		}
        ps.println("Content-Disposition:attachment;filename=" + this.getFileName());
        ps.println("Content-Type:image/webp");
//        application/x-jpg
        ps.println("Content-Length:" + this.getContentLength());// 返回内容字节数
        ps.println();
        
//        FileInputStream fis = new FileInputStream(file);
        
        
        byte[] buf = new byte[1024];
        int n = 0;
        if(content != null) {
//	        while((n = content.read(buf)) != -1) {
//		        ps.write(buf, 0, n);
//	        }
//	        ps.close();
//        	content.close();
        	
//        	IOHelper.write(content, fos);
        	
//        	IOHelper.write(content, ps);
	        
        }
        
//        IOHelper.close(content);
        
        FileInputStream is = new FileInputStream(new File(tmpFile));
        IOHelper.write(is, ps);
		
	}
	
	public void responseNotFound() {
		ps.println("HTTP/1.0 404 Not found");// 返回应答消息,并结束应答
		ps.println();// 根据 HTTP 协议, 空行将结束头信息
		ps.close();
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public InputStream getContent() {
		return content;
	}

	public void setContent(InputStream content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

}
