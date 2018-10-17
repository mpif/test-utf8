package com.codefans.task.httpproxy.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.impl.client.DefaultHttpClient;

import com.codefans.task.httpproxy.FileHelper;
import com.codefans.task.httpproxy.IOHelper;

/**
 * @author caishengzhi
 * @date 2016年8月13日上午9:21:31
 * 
 * 根据HTTP标准，HTTP请求可以使用多种请求方法。 
HTTP1.0定义了三种请求方法： GET, POST 和 HEAD方法。 
HTTP1.1新增了五种请求方法：OPTIONS, PUT, DELETE, TRACE 和 CONNECT 方法。
共15中方法
 *
 */
public class HttpRequest {
	
	private InputStream is;
	private BufferedReader br;
	private Map<String, String> headers = new HashMap<String, String>();
	
	private String method;
	private String uri;
	private String host;
	private String fileName;
	private Long contentLength;
	
	private InputStream content;
	
	public HttpRequest(InputStream is) {
		try {
			this.is = is;
			br = new BufferedReader(new InputStreamReader(is));
			
			// GET /test.jpg /HTTP1.1
			String line = br.readLine();
//			String method = null;
//			String resource = null;
			
			if(line != null) {
//			    System.out.println("line: " + line);
			    uri = line.substring(line.indexOf("GET ") + 4,
			        line.indexOf(" HTTP/1.1"));
//			    System.out.println("the resource you request is: "
//			        + uri);
			    uri = URLDecoder.decode(uri, "UTF-8");
			    
			    if(!HttpHelper.canAccess(uri)) {
			    	return;
			    }
			    
			    host = HttpHelper.getUrlHost(uri);
			    fileName = HttpHelper.getUrlFileName(uri);
			    String root = FileHelper.getProxyDownloadRoot();
			    String filePath = root + File.separator + fileName;
			    File file = new File(filePath);
			    if(file.exists()) {
			    	return;
			    }
//			    content = HttpHelper.getInputStream(uri);
			    
			    method = new StringTokenizer(line).nextElement()
			        .toString();
//			    System.out.println("the request method you send is: "
//			        + method);
			}
			String[] keyVal = null;
			while ((line = br.readLine()) != null) {
			  if (line.equals("")) {
			    break;
			  }
//			  System.out.println("the Http Header is : " + line);
			  keyVal = line.split(": ");
			  headers.put(keyVal[0], keyVal[1]);
			  
			}
			if(method != null) {
			    if ("post".equals(method.toLowerCase())) {
//			      System.out.println("the post request body is: "
//			          + br.readLine());
			    }
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOHelper.close(is);
		}
		
	}
	
	public HttpRequest(String url) {
		
	}
	
	public HttpResponse execute() throws HttpException, IOException {
		
		HttpResponse response = null;
		if(method != null) {
			if(method.equalsIgnoreCase("GET")) {
//				response = httpClientGet();
				response = httpUrlConnGet();
			} else if(method.equalsIgnoreCase("POST")) {
				response = post();
			}
		}
	
		return response;
	}
	
	public HttpResponse httpClientGet() throws HttpException, IOException {
		
		HttpClient httpClient = new HttpClient();
		uri = uri + "?params=123";
		GetMethod getMethod = new GetMethod(uri);
//		HttpMethodParams methodParams = new HttpMethodParams();
//		getMethod.setParams(methodParams);
		
		Iterator<String> iter = headers.keySet().iterator();
		String key = "";
		String val = "";
		System.out.println("print request headers:");
		while(iter.hasNext()) {
			key = iter.next();
			
			if(HttpHelper.isFilterHeader(key)) {
				continue;
			}
			
			val = headers.get(key);
			if(key.equals("Cache-Control")) {
				val = "no-cache";
			}
			System.out.println(key + ":" + val);
			getMethod.addRequestHeader(key,val);
		}
		
		int statusCode = httpClient.executeMethod(getMethod);
		Long responseContentLength = getMethod.getResponseContentLength();
		
		System.out.println("statusCode:" + statusCode);
		System.out.println("contentLength:" + responseContentLength);
		
		Header[] headers = getMethod.getResponseHeaders();
		HttpResponse response = new HttpResponse();
		if(headers != null) {
			Map<String, String> headerMap = new HashMap<String, String>();
			Header header = null;
			for(int i = 0; i < headers.length; i ++) {
				header = headers[i];
				headerMap.put(header.getName(), header.getValue());
			}
			response.setHeaders(headerMap);
		}
		
		InputStream content = getMethod.getResponseBodyAsStream();
		response.setContent(content);
		return response;
		
	}
	
	public HttpResponse httpUrlConnGet() throws HttpException, IOException {
		
		HttpResponse response = null;
		try {
			uri = uri + "?params=123";
			
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//		conn.setDoInput(true);
//			conn.setConnectTimeout(60 * 60 * 1000);
//			conn.setReadTimeout(60 * 60 * 1000);
			conn.setRequestMethod("GET");
			
			Iterator<String> iter = headers.keySet().iterator();
			String key = "";
			String val = "";
			System.out.println("print request headers:");
			while(iter.hasNext()) {
				key = iter.next();
				
				if(HttpHelper.isFilterHeader(key)) {
					continue;
				}
				
				val = headers.get(key);
				if(key.equals("Cache-Control")) {
					val = "no-cache";
				}
				System.out.println(key + ":" + val);
				conn.setRequestProperty(key, val);
			}
//			conn.connect();
			
			int statusCode = conn.getResponseCode();
			int responseContentLength = conn.getContentLength();
			this.setContentLength(Long.parseLong(String.valueOf(responseContentLength)));
			System.out.println("statusCode:" + statusCode);
			System.out.println("contentLength:" + responseContentLength);
			
			Map<String, List<String>> headers = conn.getHeaderFields();
			response = new HttpResponse();
			if(headers != null) {
				Map<String, String> headerMap = new HashMap<String, String>();
				Header header = null;
				Iterator<Entry<String, List<String>>>  iterator = headers.entrySet().iterator();
				Entry<String, List<String>> entry = null;
				key = "";
				val = "";
				while(iterator.hasNext()) {
					entry = iterator.next();
					key = entry.getKey();
					for(String value : entry.getValue()) {
						val += value;
					}
					headerMap.put(key, val);
					val = "";
				}
				response.setHeaders(headerMap);
			}
			
			InputStream content = conn.getInputStream();
			response.setFileName(fileName);
			response.setContentLength(this.getContentLength());
			response.setContent(content);
		} catch (Exception e) {
			System.out.println("exception for download uri:" + uri);
			e.printStackTrace();
		}
		return response;
		
	}
	
	public HttpResponse post() {
		
		return null;
	}
	public void put() {
		
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getHeader(String key) {
		return headers.get(key);
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getContent() {
		return content;
	}

	public void setContent(InputStream content) {
		this.content = content;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}
	
	
	

}
