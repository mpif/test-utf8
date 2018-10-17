package com.codefans.util.dbutil;

public class CompanyInfoBean {

	// table companyinfo
	private int id;
	private String ip;
	private int companyId;
	private String name;
	private String companyAdmin;
	private int userNum;
	private int scheduleAlert;
	private int stubAlert;
	private int retentionAlert;
	private int realtimeAlert;

	// table hostingsmtpinfo
	private String smtpAddress;
	private String port;
	private int isSSL;
	private String username;
	private String password;
	private String sender;
	private String receiver;
	private String uniqueStr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyAdmin() {
		return companyAdmin;
	}

	public void setCompanyAdmin(String companyAdmin) {
		this.companyAdmin = companyAdmin;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public int getScheduleAlert() {
		return scheduleAlert;
	}

	public void setScheduleAlert(int scheduleAlert) {
		this.scheduleAlert = scheduleAlert;
	}

	public int getStubAlert() {
		return stubAlert;
	}

	public void setStubAlert(int stubAlert) {
		this.stubAlert = stubAlert;
	}

	public int getRetentionAlert() {
		return retentionAlert;
	}

	public void setRetentionAlert(int retentionAlert) {
		this.retentionAlert = retentionAlert;
	}

	public int getRealtimeAlert() {
		return realtimeAlert;
	}

	public void setRealtimeAlert(int realtimeAlert) {
		this.realtimeAlert = realtimeAlert;
	}

	public String getSmtpAddress() {
		return smtpAddress;
	}

	public void setSmtpAddress(String smtpAddress) {
		this.smtpAddress = smtpAddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getIsSSL() {
		return isSSL;
	}

	public void setIsSSL(int isSSL) {
		this.isSSL = isSSL;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getUniqueStr() {
		return uniqueStr;
	}

	public void setUniqueStr(String uniqueStr) {
		this.uniqueStr = uniqueStr;
	}

	public void print() {
		p("id: " + id);
		p("ip: " + ip);
		p("companyId: " + companyId);
		p("name: " + name);
		p("companyAdmin: " + companyAdmin);
		p("userNum: " + userNum);
		p("scheduleAlert: " + scheduleAlert);
		p("stubAlert: " + stubAlert);
		p("retentionAlert: " + retentionAlert);
		p("realtimeAlert: " + realtimeAlert);

		p("smtpAddress: " + smtpAddress);
		p("port: " + port);
		p("isSSL: " + isSSL);
		p("username: " + username);
		p("password: " + password);
		p("sender: " + sender);
		p("receiver: " + receiver);
		p("uniqueStr: " + uniqueStr);
	}

	public void p(Object o) {
		System.out.println(o);
	}
}
