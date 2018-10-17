package com.codefans.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

	private HttpURLConnection conn = null;
	/**
	 * set timeout for socket 30min
	 */
	private int defaultConnectTimeout = 1800000;
	/**
	 * set default Read Timeout 5min
	 */
	private int defaultReadTimeout = 300000;

	private void httpRequest() {
		String endPointURL = "http://localhost:8080/Login.jp?userName=administrator&password=admin";
		OutputStream outputStream = null;
		InputStream inputStream = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		URL url = null;
		try {
			url = new URL(endPointURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(defaultConnectTimeout);
			conn.setReadTimeout(defaultReadTimeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			// conn.setRequestProperty(header.getKey(), header.getValue());

			conn.setRequestMethod("GET");
			conn.connect();

			// outputStream = conn.getOutputStream();
			// bos = new BufferedOutputStream(outputStream);
			// bos.write(soapMessage.getRequestContent().getBytes(ExchangeEWSConst.UTF_8));
			// bos.flush();

			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			// responseMessage.setUrlConnection(conn);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) {
		HttpRequest req = new HttpRequest();
		req.httpRequest();
	}
}
