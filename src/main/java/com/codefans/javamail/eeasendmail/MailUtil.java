package com.codefans.javamail.eeasendmail;

import javax.mail.BodyPart;

public class MailUtil {
	// SystemInfo info,String content,BodyPart att,String sender,String
	// username,String passwd,String receiver
	private String smtphost = ""; // smtp host
	private String content = ""; // mail content
	private BodyPart att; // mail attachment
	private String sender = ""; // sender's emailaddress
	private String username = ""; // logon sender's mailbox ,username
	private String passwd = ""; // logon sender's mailbox ,password
	private String receiver = ""; // receiver's emailaddress
	private String subject = "";

	public MailUtil() {

	}

	public String getSmtphost() {
		return smtphost;
	}

	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BodyPart getAtt() {
		return att;
	}

	public void setAtt(BodyPart att) {
		this.att = att;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
