package com.codefans.javamail;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class TestJavaMail {

	@Test
	public void run() {
		// sendLocalMail();
		// sendMail();

		// String host = "smtp.sina.com.cn";
		// String from = "mtests@sina.cn";
		// String from_pwd = "mstest";
		//
		// String to = "caishengzhi@messagesolution.cn";
		// String subject = "My first Java Mail";
		// String msg = "mail from class TestJavaMail.java";

		// String host = "10.0.0.215";
		// String from = "1@exchange2k3mail.com";
		// String from_pwd = "1";
		//
		// String to = "363874279@qq.com";
		// String subject = "My first Java Mail";
		// String msg = "mail from class TestJavaMail.java";

		String host = "mail.messagesolution.net";
		String from = "caishengzhi@messagesolution.cn";
		String from_pwd = "";
		String to = "caishengzhi@messagesolution.cn";

		// String to = "1@exchange2k3mail.com";
		// String subject = "My first Java Mail";
		// String msg = "mail from class TestJavaMail.java";

		// String host = "10.0.0.236";
		// String from = "test001@exchange.com";
		// String from_pwd = "1";
		// String to = "1@exchange.com";
		// String subject = "My first Java Mail";
		// String msg = "mail from class TestJavaMail.java";

		// String host = "10.0.0.226";
		// String from = "admin中国@dominotesting";
		// try {
		// from = URLEncoder.encode(from, "GBK");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		// String from_pwd = "123456";
		// String to = "a001@dominotesting";
		// String subject = "My first Java Mail";
		// String msg = "mail from class TestJavaMail.java";

		// String host = "10.0.0.236";
		// String from = "1@exchange.com";
		// String from_pwd = "1";
		// String to = "user1@new2010.local";

		String subject = "exchange.com to new2010.local";
		String msg = "send mail across domain";

		// this.sendMail(host, from, from_pwd, to, subject, msg);

		sendMail();

	}

	public void sendLocalMail() {

		String host = "smtp.sina.com.cn";
		String from = "mtests@sina.cn";
		String to = "caishengzhi@messagesolution.cn";
		// String host = "smtp.sina.com.cn";
		// String from = "1@messagesolution.cn";
		// String to = "caishengzhi@messagesolution.cn";
		String subject = "My first Java Mail";
		// String proxyIP = "10.0.0.215";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		// props.setProperty("proxySet", "true");
		// props.setProperty("http.proxyHost", proxyIP);
		// props.setProperty("http.proxyPort", "808");
		// props.put("mail.smtp.host", host);
		try {
			Session mailSession = Session.getDefaultInstance(props);

			mailSession.setDebug(true);

			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject(subject);
			message.setText("mail from class TestJavaMail.java");
			message.saveChanges();

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, "mtests@sina.cn", "mstest");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMail() {

		// String host = "smtp.sina.com.cn";
		// String from = "mtests@sina.cn";
		// String from_pwd = "mstest";
		// String to = "caishengzhi@messagesolution.cn";

		// String host = "mail.messagesolution.cn";
		String host = "10.0.0.7";
		String from = "caishengzhi";// @messagesolution.cn
		String from_pwd = "";

		String to = "363874279@qq.com";

		String subject = "My first Java Mail";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		try {
			Session mailSession = Session.getDefaultInstance(props);

			mailSession.setDebug(true);

			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject(subject);
			message.setText("mail from class TestJavaMail.java");
			message.saveChanges();

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, "caishengzhi@messagesolution.cn", from_pwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMail(String host, String from, String from_pwd, String to, String subject, String msg) {

		// String host = "smtp.sina.com.cn";
		// String from = "mtests@sina.cn";
		// String from_pwd = "mstest";
		//
		// String to = "caishengzhi@messagesolution.cn";
		// String subject = "My first Java Mail";
		// String msg = "mail from class TestJavaMail.java";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		try {

			// Session mailSession = Session.getDefaultInstance(props);

			MyAuthenticator myauth = new MyAuthenticator(from, from_pwd);
			Session mailSession = Session.getDefaultInstance(props, myauth);

			mailSession.setDebug(true);

			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject(subject);
			message.setText(msg);
			message.saveChanges();

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, from, from_pwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
