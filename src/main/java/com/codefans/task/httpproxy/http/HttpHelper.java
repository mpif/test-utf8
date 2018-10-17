package com.codefans.task.httpproxy.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpHelper {
	
//	Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//	Accept-Encoding:gzip, deflate, sdch
//	Accept-Language:zh-CN,zh;q=0.8
//	Cache-Control:max-age=0
//	Connection:keep-alive
//	Cookie:chinaz_csrf_cookie_name=671f625e61b6a86636a9c1fe70840de9
//	Host:pic1.desk.chinaz.com
//	If-Modified-Since:Fri, 14 May 2010 01:12:00 GMT
//	If-None-Match:"0704a742f3ca1:553"
//	Referer:http://desk.chinaz.com/jingpin/221733953.htm
//	Upgrade-Insecure-Requests:1
//	User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36
	
	public static final String Accept = "Accept";
	public static final String Accept_Encoding = "Accept-Encoding";
	public static final String Accept_Language = "Accept-Language";
	public static final String Cache_Control = "Cache-Control";
	public static final String Connection = "Connection";
	public static final String Cookie = "Cookie";
	public static final String Host = "Host";
	public static final String User_Agent = "User-Agent";
	public static final String If_Modified_Since = "If-Modified-Since";
	public static final String If_None_Match = "If-None-Match";
	public static final String Referer = "Referer";
	public static final String Upgrade_Insecure_Requests = "Upgrade-Insecure-Requests";

	public static String[] canAccessHost = new String[] {
			"pic1.desk.chinaz.com",
			"cache3.onlyimg.com",
			"1122cu.com",
			"www.baidu.com"
	};
	
	public static String[] filtedHeaders = new String[] {
			"If-Modified-Since",
			"If-None-Match",
			"Last-Modified",
			"ETag",
			"If-Range",
			"Range",
			"Upgrade-Insecure-Requests"
	};
	
	public static InputStream getInputStream(String urlString) throws IOException {
		// 构造URL
	    URL url = new URL(urlString);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    // 输入流
	    InputStream is = con.getInputStream();
	    
	    return is;
	    
	}
	
	public static String getUrlHost(String url){
		if(url==null||url.trim().equals("")){
			return "";
		}
		String host = "";
		Pattern p =  Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		Matcher matcher = p.matcher(url);  
		if(matcher.find()){
			host = matcher.group();  
		}
		return host;
	}
	
	public static String getUrlFileName(String url){
		if(url==null||url.trim().equals("")){
			return "";
		}
		String fileName = "";
		fileName = url.substring(url.lastIndexOf("/") + 1);
		return fileName;
	}
	
	public static boolean canAccess(String url) {
		boolean flag = false;
		
		if(url != null && !url.trim().equals("")) {
			for(int i = 0; i < canAccessHost.length; i ++) {
				if(url.contains(canAccessHost[i])) {
					flag = true;
				}
			}
		}
		
		return flag;
	}
	
	public static boolean isFilterHeader(String key) {
		boolean flag = false;
		
		if(key == null || key.trim().equals("")) {
			flag = true;
		} else {
			for(int i = 0; i < filtedHeaders.length; i ++) {
				if(key.contains(filtedHeaders[i])) {
					flag = true;
				}
			}
		}
		
		return flag;
	}
 	
	public static void main(String[] args) {
		String host = getUrlHost("http://pic1.desk.chinaz.com/file/10.03.10/5/rrgaos56_p.jpg");
		System.out.println(host);
		host = getUrlHost("http://124.202.164.12/mp4files/4158000008C5875E/md.v2she.com/2016-07-29/key-MjAxNi0wOC0wOC0xMC0wNjpkQVJkY1NWSTU0YmNiS0pBZW5yb1cwY2Fkb0c4T0Z6NQ==/fSkNZ5d2m32CiL3xhmjg_ND.mp4");
		System.out.println(host);
		
		try {
//			URL url = new URL("http://pic1.desk.chinaz.com/file/10.03.10/5/rrgaos56_p.jpg"); 
			URL url = new URL("http://124.202.164.12/mp4files/4158000008C5875E/md.v2she.com/2016-07-29/key-MjAxNi0wOC0wOC0xMC0wNjpkQVJkY1NWSTU0YmNiS0pBZW5yb1cwY2Fkb0c4T0Z6NQ==/fSkNZ5d2m32CiL3xhmjg_ND.mp4"); 
			
			//获得此 URL 的内容。 
			Object obj = url.getContent(); 
			System.out.println(obj.getClass().getName());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
	}
	
	
}
