package com.codefans.httpclient.eea;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthPolicy;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;

public class HttpClientConnection {

	/**
	 * record log
	 */
	private Logger logger = Logger.getLogger(HttpClientConnection.class);

	private static int socketConnectCount = 0;

	private static Object obj = new Object();

	private HttpURLConnection conn = null;
	private InputStream inputStream = null;

	/**
	 * set timeout for socket 30min
	 */
	private static int defaultConnectTimeout = 1800000;
	/**
	 * set default Read Timeout 5min
	 */
	private static int defaultReadTimeout = 300000;
	private boolean isEnableDebugPrint;

	public HttpClientConnection() {
	}

	public SocketMessage sendMessage(SocketMessage soapMessage) {
		if (soapMessage == null) {
			logger.error("sendMessage parameter is null");
			return null;
		}
		return connect(soapMessage);
	}

	public SocketMessage sendMessage(SocketMessage soapMessage, InputStream inputStream) {
		if (soapMessage == null) {
			logger.error("sendMessage parameter is null");
			return null;
		}
		return connect(soapMessage, inputStream);
	}

	/**
	 * connect remote webservice
	 * 
	 * @param soapMessage
	 * @return
	 */
	private SocketMessage connect(SocketMessage soapMessage) {
		SocketMessage responseMessage = null;

		if (soapMessage.getHttpInfo().isUseSSL()) {
			Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory(),
					soapMessage.getHttpInfo().getServerPort());
			Protocol.registerProtocol("https", myhttps);
		}
		responseMessage = requestByHttpClient(soapMessage);

		logger.debug("Http state:" + responseMessage.getResponseCode());
		return responseMessage;
	}

	private SocketMessage connect(SocketMessage soapMessage, InputStream inputStream) {
		SocketMessage responseMessage = null;

		if (soapMessage.getHttpInfo().isUseSSL()) {
			Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory(),
					soapMessage.getHttpInfo().getServerPort());
			Protocol.registerProtocol("https", myhttps);
		}
		responseMessage = requestByHttpClient(soapMessage, inputStream);

		logger.debug("Http state:" + responseMessage.getResponseCode());
		return responseMessage;
	}

	private SocketMessage requestByHttpClient(SocketMessage soapMessage) {
		SocketMessage responseMessage = new SocketMessage();

		String endPointURL = soapMessage.getHttpInfo().getAccessEWSURL();
		String domainName = soapMessage.getHttpInfo().getDomainName();
		String hostName = soapMessage.getHttpInfo().getIpAddress();
		String adUserName = soapMessage.getHttpInfo().getAdUserName();
		String adPassword = soapMessage.getHttpInfo().getAdPassword();

		debugPrint("endPointURL: " + endPointURL, new Throwable().getStackTrace()[0].getLineNumber());
		debugPrint("domainName: " + domainName, new Throwable().getStackTrace()[0].getLineNumber());
		debugPrint("hostName: " + hostName, new Throwable().getStackTrace()[0].getLineNumber());
		debugPrint("adUserName: " + adUserName, new Throwable().getStackTrace()[0].getLineNumber());
		// debugPrint("encoded adPassword: " +
		// HttpConstants.getString(Base64.encode(HttpConstants.getBytes(adPassword))),
		// new Throwable().getStackTrace()[0].getLineNumber());

		Map<String, String> requestHeader = soapMessage.getRequestHeader();
		InputStream inputStream = null;
		PostMethod postMethod = null;

		try {

			// if(soapMessage.getHttpInfo().isUseSSL()) {
			// Protocol myhttps = new Protocol("https", new
			// MySecureProtocolSocketFactory (),
			// soapMessage.getHttpInfo().getServerPort());
			// Protocol.registerProtocol("https", myhttps);
			// }

			AuthPolicy.registerAuthScheme(AuthPolicy.NTLM, JCIFSNTLMScheme.class);

			HttpClient httpClient = new HttpClient();
			List<Object> authPrefs = new ArrayList<Object>(2);
			authPrefs.add(AuthPolicy.NTLM);
			authPrefs.add(AuthPolicy.BASIC);
			authPrefs.add(AuthPolicy.DIGEST);
			httpClient.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, authPrefs);
			// httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
			// Charset.defaultCharset());
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

			httpClient.getState().setCredentials(AuthScope.ANY,
					new NTCredentials(adUserName, adPassword, hostName, domainName));

			if ("POST".equalsIgnoreCase(soapMessage.getRequestMethod())) {
				postMethod = new PostMethod(endPointURL);
				postMethod.setDoAuthentication(true);

				for (Map.Entry<String, String> header : requestHeader.entrySet()) {
					postMethod.addRequestHeader(header.getKey(), header.getValue());
					debugPrint("[HttpClientConnection]" + header.getKey() + ": " + header.getValue(),
							new Throwable().getStackTrace()[0].getLineNumber());
				}

				// RequestEntity requestEntity=new StringRequestEntity(new
				// String(soapMessage.getRequestContent().getBytes(ExchangeEWSConst.UTF_8),
				// ExchangeEWSConst.UTF_8));
				RequestEntity requestEntity = new StringRequestEntity(
						new String(soapMessage.getRequestContent().getBytes(Charset.defaultCharset()), "UTF-8"));
				postMethod.setRequestEntity(requestEntity);

				httpClient.executeMethod(postMethod);

				int statusCode = postMethod.getStatusCode();
				String statusText = postMethod.getStatusText();
				responseMessage.setResponseCode(statusCode);
				responseMessage.setResponseMessage(statusText);
				logger.info("statusCode:[" + statusCode + "]");
				logger.info("statusText:[" + statusText + "]");

				inputStream = postMethod.getResponseBodyAsStream();
				this.setInputStream(inputStream);

				String key = "";
				String value = "";
				HeaderElement[] headerElements = null;
				HeaderElement element = null;
				for (Header header : postMethod.getResponseHeaders()) {
					key = header.getName();
					headerElements = header.getElements();
					if (headerElements != null) {
						for (int i = 0; i < headerElements.length; i++) {
							element = headerElements[i];
							value += element.getValue();
						}
					}
					responseMessage.addResponseHeader(key, value);
					key = "";
					value = "";
				}

			} else if ("GET".equalsIgnoreCase(soapMessage.getRequestMethod())) {
				// getMethod = new GetMethod(endPointURL);
			}

		} catch (Exception e) {
			logger.error("httpsRequest_0", e);
			try {
				responseMessage.setResponseCode(postMethod.getStatusCode());
				responseMessage.setResponseMessage(postMethod.getStatusText());
			} catch (Exception e1) {
				logger.error("httpsRequest_1", e1);
			}
		} finally {
			// closeResource(inputStream, outputStream, bis, bos);
		}

		return responseMessage;
	}

	private SocketMessage requestByHttpClient(SocketMessage soapMessage, InputStream inputStream) {
		SocketMessage responseMessage = new SocketMessage();

		String endPointURL = soapMessage.getHttpInfo().getAccessEWSURL();
		String domainName = soapMessage.getHttpInfo().getDomainName();
		String hostName = soapMessage.getHttpInfo().getIpAddress();
		String adUserName = soapMessage.getHttpInfo().getAdUserName();
		String adPassword = soapMessage.getHttpInfo().getAdPassword();

		debugPrint("endPointURL: " + endPointURL, new Throwable().getStackTrace()[0].getLineNumber());
		debugPrint("domainName: " + domainName, new Throwable().getStackTrace()[0].getLineNumber());
		debugPrint("hostName: " + hostName, new Throwable().getStackTrace()[0].getLineNumber());
		debugPrint("adUserName: " + adUserName, new Throwable().getStackTrace()[0].getLineNumber());
		// debugPrint("encoded adPassword: " +
		// HttpConstants.getString(Base64.encode(HttpConstants.getBytes(adPassword))),
		// new Throwable().getStackTrace()[0].getLineNumber());

		Map<String, String> requestHeader = soapMessage.getRequestHeader();
		PostMethod postMethod = null;

		try {

			AuthPolicy.registerAuthScheme(AuthPolicy.NTLM, JCIFSNTLMScheme.class);

			HttpClient httpClient = new HttpClient();
			List<Object> authPrefs = new ArrayList<Object>(2);
			authPrefs.add(AuthPolicy.NTLM);
			authPrefs.add(AuthPolicy.BASIC);
			authPrefs.add(AuthPolicy.DIGEST);
			httpClient.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, authPrefs);
			httpClient.getState().setCredentials(AuthScope.ANY,
					new NTCredentials(adUserName, adPassword, hostName, domainName));

			if ("POST".equalsIgnoreCase(soapMessage.getRequestMethod())) {
				postMethod = new PostMethod(endPointURL);
				postMethod.setDoAuthentication(true);

				for (Map.Entry<String, String> header : requestHeader.entrySet()) {
					postMethod.addRequestHeader(header.getKey(), header.getValue());
					debugPrint("[HttpClientConnection]" + header.getKey() + ": " + header.getValue(),
							new Throwable().getStackTrace()[0].getLineNumber());
				}

				RequestEntity requestEntity = new InputStreamRequestEntity(inputStream);
				postMethod.setRequestEntity(requestEntity);

				httpClient.executeMethod(postMethod);

				int statusCode = postMethod.getStatusCode();
				String statusText = postMethod.getStatusText();
				responseMessage.setResponseCode(statusCode);
				responseMessage.setResponseMessage(statusText);
				logger.info("statusCode:[" + statusCode + "]");
				logger.info("statusText:[" + statusText + "]");

				inputStream.close();
				inputStream = null;
				inputStream = postMethod.getResponseBodyAsStream();
				this.setInputStream(inputStream);

				String key = "";
				String value = "";
				HeaderElement[] headerElements = null;
				HeaderElement element = null;
				for (Header header : postMethod.getResponseHeaders()) {
					key = header.getName();
					headerElements = header.getElements();
					if (headerElements != null) {
						for (int i = 0; i < headerElements.length; i++) {
							element = headerElements[i];
							value += element.getValue();
						}
					}
					responseMessage.addResponseHeader(key, value);
					key = "";
					value = "";
				}

			} else if ("GET".equalsIgnoreCase(soapMessage.getRequestMethod())) {
				// getMethod = new GetMethod(endPointURL);
			}
		} catch (Exception ex) {
			logger.error("httpsRequest_0", ex);
			try {
				responseMessage.setResponseCode(postMethod.getStatusCode());
				responseMessage.setResponseMessage(postMethod.getStatusText());
			} catch (Exception e) {
				logger.error("httpsRequest_1", e);
			}
		} finally {
			// closeResource(inputStream, outputStream, bis, bos);
		}

		return responseMessage;
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

	private void closeResource(BufferedReader reader, BufferedWriter writer) {
		try {
			if (reader != null) {
				reader.close();
				reader = null;
			}
			if (writer != null) {
				writer.close();
				writer = null;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void closeConnection() {
		if (conn != null) {
			conn.disconnect();
			conn = null;
		}
	}

	public HttpURLConnection getConnection() {
		return conn;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	private void debugPrint(String debugLog, int lineNumber) {
		if (isEnableDebugPrint) {
			System.out.println(debugLog);
			logger.info("[actually line: " + lineNumber + "] " + debugLog);
		}
	}

}
