package com.codefans.ntlm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class J2SE6NTML {

	private String endPointURL;
	private String domain;
	private String adUserName;
	private String adPassword;
	private String primaryEmailAddress;
	private String serverVersion;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		J2SE6NTML test = new J2SE6NTML();
		test.retrieveFolders();
	}

	private void init() {

		// 209
		// endPointURL = "https://10.0.0.209:443/EWS/exchange.asmx";
		// //method one
		//// domain = "new2010"; //完整的dommain为new2010.local
		//// adUserName = "1";
		//
		// //method two, not use domain
		//// domain = "new2010.local";
		//// adUserName = "new2010\\1";
		//
		// adPassword = "1";
		// primaryEmailAddress = "f@new2010.local";
		// serverVersion = "Exchange2010_SP1";

		// endPointURL =
		// "https://site2.exch500.serverdata.net/EWS/exchange.asmx";
		//// endPointURL = "https://205.217.20.190:443/EWS/exchange.asmx";
		// domain = "exch500.msoutlookonline.net";
		//// adUserName = "Arch_User";
		//// adUserName = "Arch_User@neptunegroup.com";
		//// domain = "EXCH500";
		// adUserName = "Arch_User_UK-Neptune";
		//// adUserName = "EXCH500\\Arch_User_UK-Neptune";
		// adPassword = "Sun10wexo!";
		// primaryEmailAddress = "creighton.boyd@neptunegroup.com";
		// serverVersion = "Exchange2010";

	}

	private void retrieveFolders() {
		init();
		httpsRequest();
	}

	private void httpsRequest() {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		HttpsURLConnection conn = null;
		try {

			endPointURL = "https://site2.exch500.serverdata.net/EWS/exchange.asmx";
			adPassword = "Sun10wexo!";
			primaryEmailAddress = "creighton.boyd@neptunegroup.com";
			serverVersion = "Exchange2010";

			// method one:
			Authenticator.setDefault(new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					domain = "exch500.msoutlookonline.net"; // or domain =
															// "EXCH500";
					adUserName = "Arch_User_UK-Neptune";
					String usernamewithdomain = domain + "\\" + adUserName;
					return (new PasswordAuthentication(usernamewithdomain, adPassword.toCharArray()));
				}
			});

			// method two:
			// Authenticator.setDefault (new Authenticator () {
			// public PasswordAuthentication getPasswordAuthentication() {
			// //method two, not used domain
			// adUserName = "EXCH500\\Arch_User_UK-Neptune";
			//// or
			//// adUserName =
			// "exch500.msoutlookonline.net\\Arch_User_UK-Neptune";
			// return (new PasswordAuthentication(adUserName,
			// adPassword.toCharArray()));
			// }
			// });

			SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			URL url = new URL(endPointURL);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.addRequestProperty("SOAPAction",
					"http://schemas.microsoft.com/exchange/services/2006/messages/FindFolder");
			conn.addRequestProperty("Content-Type", "text/xml");

			conn.setRequestMethod("POST");
			conn.connect();

			outputStream = conn.getOutputStream();
			bos = new BufferedOutputStream(outputStream);
			bos.write(this.findFolderRequest().getBytes("UTF-8"));
			bos.flush();

			int responseCode = conn.getResponseCode();
			String responseMessage = conn.getResponseMessage();
			System.out.println("responseCode:[" + responseCode + "]");
			System.out.println("responseMessage:[" + responseMessage + "]");

			inputStream = conn.getInputStream();
			String xmlstring = readResponseBodyAsString(inputStream, inputStream.available(), "utf-8");
			println("result:[" + xmlstring + "]");

			String key = "";
			String value = "";
			for (Map.Entry<String, List<String>> entry : conn.getHeaderFields().entrySet()) {
				key = entry.getKey();
				for (int i = 0; i < entry.getValue().size(); i++) {
					value += entry.getValue().get(i);
					System.out.println(key + ":" + value + "");
					key = "";
					value = "";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeResource(inputStream, outputStream, bis, bos);
		}
	}

	private String findFolderRequest() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" ");
		sb.append("		xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		sb.append("		xmlns:m=\"http://schemas.microsoft.com/exchange/services/2006/messages\" ");
		sb.append("		xmlns:t=\"http://schemas.microsoft.com/exchange/services/2006/types\">");
		sb.append("  <soap:Header>");
		sb.append("    <t:RequestServerVersion Version=\"" + serverVersion + "\"/>");
		sb.append("    <t:ExchangeImpersonation>");
		sb.append("      <t:ConnectingSID>");
		sb.append("        <t:SmtpAddress>" + primaryEmailAddress + "</t:SmtpAddress>");
		sb.append("      </t:ConnectingSID>");
		sb.append("    </t:ExchangeImpersonation>");
		sb.append("  </soap:Header>");
		sb.append("  <soap:Body>");
		sb.append("    <m:FindFolder Traversal=\"Shallow\">");
		sb.append("      <m:FolderShape>");
		sb.append("        <t:BaseShape>AllProperties</t:BaseShape>");
		sb.append("      </m:FolderShape>");
		sb.append("      <m:IndexedPageFolderView MaxEntriesReturned=\"1000\" Offset=\"0\" BasePoint=\"Beginning\"/>");
		sb.append("      <m:ParentFolderIds>");
		sb.append("        <t:DistinguishedFolderId Id=\"msgfolderroot\"/>");
		sb.append("      </m:ParentFolderIds>");
		sb.append("    </m:FindFolder>");
		sb.append("  </soap:Body>");
		sb.append("</soap:Envelope>");
		return sb.toString();
	}

	public byte[] getBytes(String data) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		try {
			return data.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			return data.getBytes();
		}
	}

	public String getString(byte data[], int offset, int length) {
		if (data == null)
			throw new IllegalArgumentException("Parameter may not be null");
		try {
			return new String(data, offset, length, "US-ASCII");
		} catch (UnsupportedEncodingException e) {
			return new String(data, offset, length);
		}
	}

	public String getString(byte data[]) {
		return getString(data, 0, data.length);
	}

	private String readResponseBodyAsString(InputStream inputstream, int contentLength, String charset) {

		ByteArrayOutputStream outstream = new ByteArrayOutputStream(contentLength > 0 ? (int) contentLength : 4096);

		byte[] buffer = new byte[4096];
		int len;
		byte[] rawdata = null;
		try {
			while ((len = inputstream.read(buffer)) > 0) {
				outstream.write(buffer, 0, len);
			}
			rawdata = outstream.toByteArray();

			outstream.close();

			if (rawdata != null) {
				return new String(rawdata, charset);
			} else {
				return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outstream != null) {
					outstream.close();
					outstream = null;
				}
				if (inputstream != null) {
					inputstream.close();
					inputstream = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * close connect resource
	 * 
	 * @param is
	 * @param os
	 * @param bos
	 */
	private void closeResource(InputStream is, OutputStream os, BufferedInputStream bis, BufferedOutputStream bos) {
		try {
			if (bos != null) {
				bos.close();
				bos = null;
			}
			if (bis != null) {
				bis.close();
				bis = null;
			}
			if (os != null) {
				os.close();
				os = null;
			}
			if (is != null) {
				is.close();
				is = null;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void println(String str) {
		System.out.println(str);
	}

}

class TrustAnyHostnameVerifier implements HostnameVerifier {
	public boolean verify(String hostname, SSLSession session) {
		return true;
	}
}

/**
 * trust any TrustManger
 */
class TrustAnyTrustManager implements X509TrustManager {
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[] {};
	}
}
