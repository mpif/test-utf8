package com.codefans.httpclient.eea;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class SocketMessage {

	private SOAPHttpInfo httpInfo = null;

	private Map<String, String> requestHeader = new HashMap<String, String>();

	private String requestContent = "";

	private Map<String, String> responseHeader = new HashMap<String, String>();

	private String responseContent = "";

	private String requestMethod = "POST";

	private int responseCode = 404;

	private String responseMessage = "error";

	private HttpURLConnection urlConnection = null;

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage
	 *            the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the requestMethod
	 */
	public String getRequestMethod() {
		return requestMethod;
	}

	/**
	 * @param requestMethod
	 *            the requestMethod to set
	 */
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	/**
	 * @return the httpInfo
	 */
	public SOAPHttpInfo getHttpInfo() {
		return httpInfo;
	}

	/**
	 * @param httpInfo
	 *            the httpInfo to set
	 */
	public void setHttpInfo(SOAPHttpInfo httpInfo) {
		this.httpInfo = httpInfo;
	}

	/**
	 * @return the requestContent
	 */
	public String getRequestContent() {
		return requestContent;
	}

	/**
	 * @param requestContent
	 *            the requestContent to set
	 */
	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	/**
	 * @return the responseContent
	 */
	public String getResponseContent() {
		return responseContent;
	}

	/**
	 * @param responseContent
	 *            the responseContent to set
	 */
	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

	public void addRequestHeader(String key, String value) {
		requestHeader.put(key, value);
	}

	public void addResponseHeader(String key, String value) {
		responseHeader.put(key, value);
	}

	public Map<String, String> getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(Map<String, String> requestHeader) {
		this.requestHeader = requestHeader;
	}

	public Map<String, String> getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(Map<String, String> responseHeader) {
		this.responseHeader = responseHeader;
	}

	// public HttpURLConnection getUrlConnection() {
	// return urlConnection;
	// }

	// public void setUrlConnection(HttpURLConnection urlConnection) {
	// this.urlConnection = urlConnection;
	// }

	// public void close() {
	// if(urlConnection != null) {
	// urlConnection.disconnect();
	// urlConnection = null;
	// }
	// }
	//
}
