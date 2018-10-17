package com.codefans.javamail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class NoAuthSendMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			long start = System.currentTimeMillis();

			// String smtphost = "10.0.0.209";
			// String smtphost = "10.0.0.122";
			// String smtphost = "10.0.0.26";
			String smtphost = "10.0.0.16";
			// String smtphost = "mail.messagesolution.cn";
			// String smtphost = "smtp.qq.com";
			String port = "25";
			// String sender = "caishengzhi@messagesolution.cn";
			String sender = "363874279@qq.com";
			// String sender = "caishengzhi";
			// String sender = "qa1@exchange2010a.com";
			// String receiver = "a@win2010.com";
			// String receiver = "363874279@qq.com";
			String receiver = "caiszf@163.com";
			// String receiver = "caishengzhi@messagesolution.cn";
			String suffix = "_10";
			String subject = "no auth mail sender不需要验证的邮件发送" + suffix;
			String content = "mail send success, 但你看见这封邮件的时候，说明邮件发送成功了" + suffix;
			Properties properties = new Properties();
			properties.put("mail.smtp.host", smtphost);
			properties.put("mail.port", port);
			Session session = Session.getInstance(properties);

			session.setDebug(true);
			MimeMessage mimemsg = new MimeMessage(session);
			// 设置发件人
			if (sender != null) {
				mimemsg.setFrom(new InternetAddress(MimeUtility.encodeText(sender)));
			}
			// 设置收件人
			if (receiver != null) {
				mimemsg.setRecipients(Message.RecipientType.TO,
						new InternetAddress[] { new InternetAddress(receiver) });
			}

			int mailCount = 10;
			int index = 6;

			for (int i = index; i < mailCount + index; i++) {

				subject = "no auth mail sender不需要验证的邮件发送_" + i;
				content = "mail send success, 但你看见这封邮件的时候，说明邮件发送成功了_" + i;

				// 设置主题
				if (subject != null) {
					subject = MimeUtility.encodeText(subject);
					mimemsg.setSubject(subject, "UTF-8");
				}
				// 设置邮件内容，即正文
				MimeBodyPart part = new MimeBodyPart();
				// part.setText(content, "UTF-8");
				part.setContent(content.toString(), "text/html;charset=UTF-8");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(part);

				// 设置附件
				BodyPart attachment = new MimeBodyPart();
				String path = "D:/tmp/";
				String name = "qrcode_news.jpg";
				String file = path + name;
				FileDataSource fileDataSource = new FileDataSource(file);
				attachment.setDataHandler(new DataHandler(fileDataSource));

				String fileName = MimeUtility.encodeText(name);
				System.out.println("fileName: " + fileName);
				attachment.setFileName(fileName);

				if (attachment != null) {
					multipart.addBodyPart(attachment);
				}

				mimemsg.setContent(multipart);
				mimemsg.setSentDate(new Date());
				System.out.println("sending mail");

				Transport.send(mimemsg);
				System.out.println("send success");

				long end = System.currentTimeMillis();

				System.out.println("cost:[" + (end - start) / 1000 + "s]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
