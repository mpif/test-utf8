package com.codefans.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

public class HttpUtil {
	private HttpClient httpClient;
	private HttpResponse response;

	public HttpUtil() {
		httpClient = new DefaultHttpClient();
	}

	public HttpUtil(String ip, int port) {
		httpClient = new DefaultHttpClient();
		this.setProxy(ip, port);
	}

	public String getMethodHt(String url) {
		HttpGet get = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String html = "";
		try {
			html = httpClient.execute(get, responseHandler);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			get.abort();
		}
		return html;
	}

	public HttpResponse getMethodRe(String url) {
		HttpGet get = new HttpGet(url);
		try {
			response = httpClient.execute(get);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			get.abort();
		}
		return response;
	}

	public String getMethodHt(String url, Map<String, String> params) {
		HttpGet get = new HttpGet(url);
		Set<String> set = params.keySet();
		HttpParams basicParams = new BasicHttpParams();
		for (String key : set) {
			basicParams.setParameter(key, params.get(key));
		}
		get.setParams(basicParams);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String html = "";
		try {
			html = httpClient.execute(get, responseHandler);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			get.abort();
		}
		return html;
	}

	public HttpResponse getMethodRe(String url, Map<String, String> params) {

		HttpGet get = new HttpGet(url);
		Set<String> set = params.keySet();
		HttpParams basicParams = new BasicHttpParams();
		for (String key : set) {
			basicParams.setParameter(key, params.get(key));
		}
		get.setParams(basicParams);
		try {
			response = httpClient.execute(get);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			get.abort();
		}
		return response;
	}

	public String postMethodHt(String url) {
		HttpPost post = new HttpPost(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String html = "";
		try {
			html = httpClient.execute(post, responseHandler);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			post.abort();
		}
		return html;
	}

	public HttpResponse postMethodRe(String url) {
		HttpPost post = new HttpPost(url);
		try {
			response = httpClient.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			post.abort();
		}
		return response;
	}

	public String postMethodHt(String url, Map<String, String> params) {
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> qparams = new ArrayList<BasicNameValuePair>();
		Set<String> set = params.keySet();
		for (String key : set) {
			qparams.add(new BasicNameValuePair(key, params.get(key)));
		}
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String html = "";
		try {
			post.setEntity(new UrlEncodedFormEntity(qparams, HTTP.UTF_8));
			html = httpClient.execute(post, responseHandler);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			post.abort();
		}
		return html;
	}

	public HttpResponse postMethodRe(String url, Map<String, String> params) {
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> qparams = new ArrayList<BasicNameValuePair>();
		Set<String> set = params.keySet();
		for (String key : set) {
			qparams.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(qparams, HTTP.UTF_8));
			response = httpClient.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			post.abort();
		}
		return response;
	}

	public void setProxy(String ip, int port) {
		HttpHost proxy = new HttpHost(ip, port);
		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}

	public void close() {
		if (httpClient != null)
			httpClient.getConnectionManager().shutdown();
	}
}
