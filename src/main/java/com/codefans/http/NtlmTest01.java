package com.codefans.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class NtlmTest01 {

	public static void main(String[] args) throws Exception {

		// endPointURL=https://10.0.0.209:443/EWS/exchange.asmx
		// emailAddress=f@new2010.local
		// serverVersion=Exchange2010
		//
		// domainName=new2010.local
		// adUserName=1
		// adPassword=1

		String urlStr = "https://10.0.0.209:443/EWS/exchange.asmx";
		String domain = ""; // May also be referred as realm
		String userName = "1";
		String password = "1";

		String responseText = getAuthenticatedResponse(urlStr, domain, userName, password);

		System.out.println("response: " + responseText);
	}

	private static String getAuthenticatedResponse(final String urlStr, final String domain, final String userName,
			final String password) throws IOException {

		StringBuilder response = new StringBuilder();

		Authenticator.setDefault(new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(domain + "\\" + userName, password.toCharArray());
			}
		});

		URL urlRequest = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("GET");

		InputStream stream = conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		String str = "";
		while ((str = in.readLine()) != null) {
			response.append(str);
		}
		in.close();

		return response.toString();
	}

}
