package com.codefans.httpclient;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class AccessAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AccessAction accessAction = new AccessAction();
		// accessAction.access();

		// accessAction.currentIp();

		// accessAction.login();

		accessAction.loginUseHTTPURLConnection();
	}

	private void loginUseHTTPURLConnection() {
		try {
			String url = "https://10.0.0.70:8888/Login.jp?ifCheck=true&userName=administrator&password=admin";
			SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());

			URL httpUrl = new URL(url);
			HttpsURLConnection.setFollowRedirects(false);
			HttpsURLConnection httpConn = (HttpsURLConnection) httpUrl.openConnection();
			httpConn.setSSLSocketFactory(sc.getSocketFactory());
			httpConn.setHostnameVerifier(new TrustAnyHostnameVerifier());

			httpConn.setRequestMethod("GET");
			httpConn.setUseCaches(false);
			httpConn.setRequestProperty("Accept-Language", "en-us");
			// httpConn.setRequestProperty("Host", "10.0.0.70:8888");
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)");
			httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			httpConn.setRequestProperty("Connection", "Keep-Alive");

			// httpConn.setRequestProperty("userName", "administrator");
			// httpConn.setRequestProperty("password", "admin");

			String referer = url.indexOf('?') == -1 ? url : url.substring(0, url.indexOf('?'));
			// httpConn.setRequestProperty("referer", referer);
			int responseCode = httpConn.getResponseCode();
			if (responseCode == 200) {
				System.out.println("OK");
			} else {
				System.out.println("responseCode:" + responseCode);
			}
		} catch (Exception e) {
			System.out.println("Fail");
		}
	}

	private void login() {
		try {
			HttpClient httpClient = new HttpClient();
			// httpClient.getHostConfiguration().setProxy("localhost", 8080);
			// httpClient.getState().setProxyCredentials("my-proxy-realm",
			// "10.0.0.70", new UsernamePasswordCredentials("administrator",
			// "admin"));
			StringBuilder url = new StringBuilder();
			url.append("https://10.0.0.70:8888/Login.jp?ifCheck=true");

			// PostMethod postMethod = new PostMethod(url.toString());
			// //"http://localhost:8080/Login.jp?ifCheck=true"
			// String adminName = "administrator";
			// String password = "admin";
			// postMethod.addParameter(new NameValuePair("userName",
			// adminName));
			// postMethod.addParameter(new NameValuePair("password", password));
			// int responseStatusCode = httpClient.executeMethod(postMethod);
			// System.out.println("responseStatusCode: " + responseStatusCode);
			// postMethod.releaseConnection();

			GetMethod getMethod = new GetMethod(url.toString());
			// getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
			// new DefaultHttpMethodRetryHandler());
			int responseStatusCode = httpClient.executeMethod(getMethod);
			System.out.println("responseStatusCode: " + responseStatusCode);
			getMethod.releaseConnection();

			// BufferedReader br = new BufferedReader(new
			// InputStreamReader(postMethod.getResponseBodyAsStream()));
			// String temp = "";
			// temp = br.readLine();
			// if(temp != null && !"".equals(temp)) {
			// if(temp.equalsIgnoreCase("serviceAlive")) {
			// System.out.println("response content: " + temp);
			//// System.out.println("service is alive");
			// }
			// }
			//
			// if(responseStatusCode != 200) {
			//// result = "loginFail";
			// }
			//

		} catch (Exception e) {
			e.printStackTrace();
		}
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
