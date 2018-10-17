package com.codefans.httpclient.eea;

import com.sun.xml.messaging.saaj.util.Base64;

/**
 * connect EWS's http info
 * 
 * @author ZhangJianwei
 *
 */
public class SOAPHttpInfo {

	/**
	 * login EWS username
	 */
	private String adUserName = "";

	/**
	 * login EWS password
	 */
	private String adPassword = "";

	/**
	 * the user of primary smtp address
	 */
	private String primarySMTPAddress = "";

	/**
	 * ews ip address
	 */
	private String ipAddress = "";

	/**
	 * domain name
	 */
	private String domainName = "";

	/**
	 * whhether use ssl
	 */
	private boolean isUseSSL = false;

	/**
	 * EWS server port
	 */
	private int serverPort = 80;

	/**
	 * authorization type
	 */
	private String authorizationType = "Basic";

	/**
	 * authorization password
	 */
	private String authorizationPass = "";

	/**
	 * access EWS href path
	 */
	private String accessEWSURL = "";

	/**
	 * http protocol
	 */
	private String protocol = "http";

	private String serverVersion;

	/**
	 * construct method
	 */
	public SOAPHttpInfo(String ipAddress, String domainName, boolean isUseSSL, int serverPort, String adUserName,
			String adPassword, String primarySMTPAddress) {
		this.ipAddress = ipAddress;
		this.domainName = domainName;
		this.isUseSSL = isUseSSL;
		this.serverPort = serverPort;
		this.adUserName = adUserName;
		this.adPassword = adPassword;
		this.primarySMTPAddress = primarySMTPAddress;
		if (isUseSSL) {
			protocol = "https";
		}
		// String authorizationPass = "Basic " +
		// HttpConstants.getString(Base64.encode(HttpConstants.getBytes(domainName
		// + "/" + adUserName + ":" + adPassword)));
		if (adUserName != null && adUserName.indexOf("\\") > 0) {
			authorizationPass = "Basic "
					+ HttpConstants.getString(Base64.encode(HttpConstants.getBytes(adUserName + ":" + adPassword)));
		} else {
			authorizationPass = "Basic " + HttpConstants
					.getString(Base64.encode(HttpConstants.getBytes(domainName + "/" + adUserName + ":" + adPassword)));
		}

	}

	/**
	 * @return the adUserName
	 */
	public String getAdUserName() {
		return adUserName;
	}

	/**
	 * @param adUserName
	 *            the adUserName to set
	 */
	public void setAdUserName(String adUserName) {
		this.adUserName = adUserName;
	}

	/**
	 * @return the adPassword
	 */
	public String getAdPassword() {
		return adPassword;
	}

	/**
	 * @param adPassword
	 *            the adPassword to set
	 */
	public void setAdPassword(String adPassword) {
		this.adPassword = adPassword;
	}

	/**
	 * @return the primarySMTPAddress
	 */
	public String getPrimarySMTPAddress() {
		return primarySMTPAddress;
	}

	/**
	 * @param primarySMTPAddress
	 *            the primarySMTPAddress to set
	 */
	public void setPrimarySMTPAddress(String primarySMTPAddress) {
		this.primarySMTPAddress = primarySMTPAddress;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * @param domainName
	 *            the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	/**
	 * @return the isUseSSL
	 */
	public boolean isUseSSL() {
		return isUseSSL;
	}

	/**
	 * @param isUseSSL
	 *            the isUseSSL to set
	 */
	public void setUseSSL(boolean isUseSSL) {
		this.isUseSSL = isUseSSL;
	}

	/**
	 * @return the authorizationType
	 */
	public String getAuthorizationType() {
		return authorizationType;
	}

	/**
	 * @param authorizationType
	 *            the authorizationType to set
	 */
	public void setAuthorizationType(String authorizationType) {
		this.authorizationType = authorizationType;
	}

	/**
	 * @return the authorizationPass
	 */
	public String getAuthorizationPass() {
		return authorizationPass;
	}

	/**
	 * @param authorizationPass
	 *            the authorizationPass to set
	 */
	public void setAuthorizationPass(String authorizationPass) {
		this.authorizationPass = authorizationPass;
	}

	/**
	 * @return the accessEWSURL
	 */
	public String getAccessEWSURL() {
		// accessEWSURL = this.protocol + "://" + ipAddress + ":" + serverPort +
		// "/EWS/Services.wsdl";
		accessEWSURL = this.protocol + "://" + ipAddress + ":" + serverPort + "/EWS/exchange.asmx";

		return accessEWSURL;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
}
