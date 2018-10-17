package com.codefans.javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LocalMail {
	public static void main(String[] argv) {
		new LocalMail(argv);
	}

	public LocalMail(String[] argv) {
		String to = null;
		String from = null;
		String subject = null;
		String cc = null;
		String bcc = null;
		String mailhost = null;
		String content = null;
		MimeMessage mimeMsg = null;
		Session session = null;

		try {
			// mailhost="mail.messagesolution.net";
			//// from="1@messagesolution.cn";
			// from="caishengzhi";
			// to="1@exchange2k3mail.com";

			// mailhost="10.0.0.201";
			// mailhost="10.0.0.209";
			// from="user2@new2010.local";
			// to="user1@new2010.local";

			mailhost = "10.0.0.236";
			from = "1@exchange.com";
			to = "test001@exchange.com";

			// String host = "mail.messagesolution.net";
			// String from = "caishengzhi";
			// String from_pwd = "i0e7Ih$A";

			subject = "hello";
			content = "this is a JavaMail using proxy";
			Properties props = System.getProperties();
			// props.put("http.proxySet", "true");
			// props.put("http.proxyHost", "10.0.0.236");
			// props.put("http.proxyPort", "85");
			props.put("mail.smtp.host", mailhost);
			session = Session.getDefaultInstance(props, null);
			session.setDebug(true);
			mimeMsg = new MimeMessage(session);
			mimeMsg.setFrom(new InternetAddress(from));
			if (to != null) {
				mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			}
			if (cc != null) {
				mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			}
			if (bcc != null) {
				mimeMsg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
			}
			mimeMsg.setSubject(subject);
			mimeMsg.setText(content);
			mimeMsg.setSentDate(new Date());

			Transport.send(mimeMsg);
			System.out.println("email send!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
