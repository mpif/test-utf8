package com.codefans.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsRequest {

	private HttpURLConnection conn = null;
	/**
	 * set timeout for socket 30min
	 */
	private int defaultConnectTimeout = 1800000;
	/**
	 * set default Read Timeout 5min
	 */
	private int defaultReadTimeout = 300000;

	private void httpsRequest() {
		String endPointURL = "https://10.0.0.70:8888/Login.jp?userName=administrator&password=admin";
		// OutputStream outputStream = null;
		// BufferedOutputStream bos = null;
		// BufferedInputStream bis = null;
		// BufferedReader reader = null;
		// BufferedWriter writer = null;
		try {
			SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			URL url = new URL(endPointURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setConnectTimeout(defaultConnectTimeout);
			conn.setReadTimeout(defaultReadTimeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestMethod("GET");
			conn.connect();

			// outputStream = conn.getOutputStream();
			// bufferedReader
			// reader = new BufferedReader(new InputStreamReader(inputStream,
			// ExchangeEWSConst.UTF_8));
			// writer = new BufferedWriter(new OutputStreamWriter(outputStream,
			// ExchangeEWSConst.UTF_8));
			//
			// String line = null;
			// while((line=reader.readLine()) != null) {
			// writer.write(line);
			// writer.newLine();
			// }
			// writer.flush();

			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) {
		HttpsRequest req = new HttpsRequest();
		req.httpsRequest();
	}
}

class TrustAnyTrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[] {};
	}
}

class TrustAnyHostnameVerifier implements HostnameVerifier {

	public boolean verify(String hostname, SSLSession session) {
		return true;
	}
}
