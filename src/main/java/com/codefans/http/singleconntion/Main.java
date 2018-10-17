package com.codefans.http.singleconntion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class Main {

	private OutputStream os;
	private InputStream is;
	private HttpURLConnection conn;

	public static void main(String[] args) {
		Main m = new Main();
		m.test();
	}

	public void test() {
		multiConn();
	}

	public void multiConn() {
		int connTimes = 3;

		String username = "administrator";
		String password = "admin";
		conn = getConnection();
		// init(conn);
		// for(int i = 0; i < connTimes; i ++) {
		String params = "ifCheck=true&userName=" + username + "&password=" + password;
		String result = connOnce(params);
		p("result:[" + result + "]");
		// }
	}

	public void init(HttpURLConnection conn) {
		try {
			os = conn.getOutputStream();
			is = conn.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String connOnce(String params) {
		BufferedReader br = null;
		String returnStr = "";
		try {
			// HttpURLConnection conn = getConnection();

			// String content = "ifCheck=true&userName=" + username +
			// "&password=" + password;
			// p("content:[" + content + "]");

			// bw = new BufferedWriter(new
			// OutputStreamWriter(conn.getOutputStream()));
			// bw.write(content);
			// conn.setRequestProperty("Content-Length",
			// String.valueOf(params.length()));

			os = conn.getOutputStream();

			os.write(params.getBytes());

			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				p("httpsRequest, responseCode:" + responseCode);
			} else {
				p("reponseCode:[" + responseCode + "]");
			}

			// br = new BufferedReader(new InputStreamReader(is));
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String temp = "";
			temp = br.readLine();
			// p("temp:[" + temp + "]");
			if (temp != null && !"".equals(temp)) {
				if (temp.equalsIgnoreCase("serviceAlive")) {
					returnStr = "serviceAlive";
					// p("httpsRequest, response content: " + temp);
				}
			}
			// conn.getInputStream().reset();
			// os = null;
			conn.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			// try {
			// if(br != null) {
			// br.close();
			// br = null;
			// }
			// if(bw != null) {
			// bw.flush();
			// bw.close();
			// bw = null;
			// }
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
		return returnStr;
	}

	public HttpURLConnection getConnection() {
		HttpURLConnection conn = null;
		URL url = null;
		try {

			String endPointURL = "http://10.0.0.50:8080/Login.jp"; // ?ifCheck=true&userName=administrator&password=admin

			url = new URL(endPointURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();

		} catch (Exception ex) {
			if (ex instanceof java.net.ConnectException) {
				p("java.net.ConnectionException, Exception Message:" + ex.getMessage());
			} else if (ex instanceof java.net.SocketTimeoutException) {
				p("java.net.SocketTimeoutException: Read timed out, defaultConnectionTimeout:[" + (33 / 60 / 1000)
						+ "min], defaultReadTimeout:[" + (33 / 60 / 1000) + "min]");
			} else if (ex instanceof javax.net.ssl.SSLHandshakeException) {
				p("javax.net.ssl.SSLHandshakeException: Remote host closed connection during handshake!");
			} else {
				ex.printStackTrace();
			}
		} finally {
		}

		return conn;
	}

	public void p(Object o) {
		System.out.println(o);
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
