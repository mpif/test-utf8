package com.codefans.ntlm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthPolicy;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class HttpClientNTLM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClientNTLM hcn = new HttpClientNTLM();
		hcn.retrieveFolders();
	}

	private void retrieveFolders() {
		try {

			// 209
			// String primaryEmailAddress = "f@new2010.local";
			// String endPointURL = "https://10.0.0.209:443/EWS/exchange.asmx";
			// String serverVersion = "Exchange2010_SP1";
			// String domainName = "new2010.local";
			// String host = "10.0.0.209";
			// String adUserName = "1";
			// String adPassword = "1";
			// String authorizationPass = "";
			// authorizationPass = "Basic " +
			// getString(Base64.encode(getBytes(domainName + "\\" + adUserName +
			// ":" + adPassword)));
			// authorizationPass = "NTLM " +
			// getString(Base64.encode(getBytes(domainName + "\\" + adUserName +
			// ":" + adPassword)));

			// 68 custmos
			String primaryEmailAddress = "creighton.boyd@neptunegroup.com";
			String endPointURL = "https://site2.exch500.serverdata.net/EWS/exchange.asmx";
			String serverVersion = "Exchange2010";
			String domainName = "exch500.msoutlookonline.net";
			String host = "site2.exch500.serverdata.net";
			String adUserName = "Arch_User_UK-Neptune";
			// String adUserName = "EXCH500\\Arch_User_UK-Neptune";
			String adPassword = "Sun10wexo!";

			Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", myhttps);

			AuthPolicy.registerAuthScheme(AuthPolicy.NTLM, EwsJCIFSNTLMScheme.class);

			HttpClient httpClient = new HttpClient();

			httpClient.getState().setCredentials(AuthScope.ANY,
					new NTCredentials(adUserName, adPassword, host, domainName));

			String url = endPointURL;
			// 创建POST方法的实例
			PostMethod postMethod = new PostMethod(url);
			postMethod.addRequestHeader("SOAPAction",
					"http://schemas.microsoft.com/exchange/services/2006/messages/FindFolder");
			postMethod.addRequestHeader("Content-Type", "text/xml");
			postMethod.setDoAuthentication(true);

			String findFolderRequest = this.getFindFolderRequest(serverVersion, primaryEmailAddress);
			RequestEntity requestEntity = new StringRequestEntity(findFolderRequest);
			postMethod.setRequestEntity(requestEntity);

			// 填入各个表单域的值
			// NameValuePair[] data = { new NameValuePair("userName",
			// "administrator"),
			//// new NameValuePair("password", "admin"),
			// new NameValuePair("password", "admin")
			// };
			// // 将表单的值放入postMethod中
			// postMethod.setRequestBody(data);

			httpClient.executeMethod(postMethod);

			int statusCode = postMethod.getStatusCode();
			System.out.println("statusCode:[" + statusCode + "]");

			InputStream is = postMethod.getResponseBodyAsStream();

			// File file = new File("C://result.txt");
			// if(!file.exists()) {
			// file.createNewFile();
			// }
			// FileOutputStream fos = new FileOutputStream(file);
			// byte[] buffer = new byte[4096];
			// int len;
			// while ((len = is.read(buffer)) > 0) {
			// fos.write(buffer, 0, len);
			// }
			// is.close();
			// fos.close();

			String str = readResponseBodyAsString(is);
			System.out.println(str);
			System.out.println(this.formatXml(str));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFindFolderRequest(String serverVersion, String primarySMTPAddress) {
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
		sb.append("        <t:SmtpAddress>" + primarySMTPAddress + "</t:SmtpAddress>");
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

	private String readResponseBodyAsString(InputStream inputStream) {
		try {
			int contentLength = inputStream.available();
			ByteArrayOutputStream outstream = new ByteArrayOutputStream(contentLength > 0 ? contentLength : 4096);

			byte[] buffer = new byte[4096];
			int len;
			byte[] rawdata = null;
			while ((len = inputStream.read(buffer)) > 0) {
				outstream.write(buffer, 0, len);
			}
			rawdata = outstream.toByteArray();
			outstream.close();

			if (rawdata != null) {
				return new String(rawdata);
			} else {
				return null;
			}

		} catch (IOException ex) {
			System.out.println("[Attachment] Exception in readResponseBodyAsString method " + ex);
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
			} catch (IOException ex) {
			}
		}
		return null;
	}

	public String formatXml(String str) throws Exception {
		Document document = null;
		document = DocumentHelper.parseText(str);
		//
		OutputFormat format = OutputFormat.createPrettyPrint();
		// format.setEncoding("utf-8");
		// format.setEncoding("GBK");
		StringWriter writer = new StringWriter();
		//
		XMLWriter xmlWriter = new XMLWriter(writer, format);
		//
		xmlWriter.write(document);
		xmlWriter.close();
		//
		System.out.println(writer.toString());
		return writer.toString();
	}

}
