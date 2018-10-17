package com.codefans.http.authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class AuthTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		test();

	}

	public static void test() {
		try {
			String endPointURL = "https://10.0.0.209:443/EWS/exchange.asmx";
			final String domain = "new2010.local";
			final String userName = "1";
			final String password = "1";

			Authenticator.setDefault(new Authenticator() {
				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(domain + "/" + userName, password.toCharArray());
				}
			});

			SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());

			URL urlRequest = new URL(endPointURL);
			HttpsURLConnection conn = (HttpsURLConnection) urlRequest.openConnection();
			// conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			InputStream stream = conn.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));
			String str = "";
			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
