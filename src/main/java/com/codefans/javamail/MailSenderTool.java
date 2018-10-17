package com.codefans.javamail;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.codefans.javamail.ByteArrayDataSource;
import com.codefans.javamail.SmtpAuthenticator;

public class MailSenderTool {

	private String smtpHost = "";
	private String port = "";
	private String user = "";
	private String password = "";
	private String sender = "";
	private String receiver = "";

	public static void main(String[] args) {
		MailSenderTool mailTool = new MailSenderTool();
		mailTool.send();
	}

	public void send() {
		sendMailText();
		// sendWithAttachment();
	}

	public void get209() {
		this.smtpHost = "10.0.0.209";
		this.port = "25";
		this.user = "1";
		this.password = "1";
		this.sender = "1@new2010.local";
		this.receiver = "f@new2010.local";
		// this.receiver = "user1@new2010.local";
	}

	public void get246() {
		this.smtpHost = "10.0.0.246";
		this.port = "25";
		this.user = "1";
		this.password = "1";
		this.sender = "1@ee.com";
		this.receiver = "new0010@new2013test.com";
		// this.receiver = "user1@new2010.local";
	}

	public void get200() {
		this.smtpHost = "10.0.0.200";
		this.port = "25";
		this.user = "eeadmin";
		this.password = "1";
		this.sender = "eeadmin@exchange13.com";
		this.receiver = "cai@exchange13.com";
		// this.receiver = "user1@new2010.local";
	}

	public void getVirtual_122() {
		this.smtpHost = "10.0.0.122";
		this.port = "25";
		this.user = "admin";
		this.password = "1";
		this.sender = "admin@win2010.com";
		// this.receiver = "a@win2010.com";
		this.receiver = "b@win2010.com";
	}

	public void sendMailText() {

		try {
			// this.get209();
			// this.get246();
			this.get200();
			// this.getVirtual_122();

			String subject = "sendByMailTool testing mail, No.";
			String content = "sendByMailTool testing mail content, ";
			int startIndex = 1051;
			int sendMailCount = 1000;

			Properties properties = new Properties();
			properties.put("mail.smtp.host", this.smtpHost);
			properties.put("mail.smtp.auth", "true");// esmtp
			properties.put("mail.port", this.port);

			MyAuthenticator ma = new MyAuthenticator(this.user, this.password);

			Session session1 = Session.getInstance(properties, ma);

			session1.setDebug(true);
			MimeMessage mimemsg = new MimeMessage(session1);// create mime
															// object
			if (this.sender != null) {
				mimemsg.setFrom(new InternetAddress(this.sender));
			}
			if (this.receiver != null) {
				mimemsg.setRecipients(Message.RecipientType.TO, parse(this.receiver));
			}

			Transport transport = session1.getTransport("smtp");
			transport.connect(this.smtpHost, this.user, this.password);

			long start = System.currentTimeMillis();
			for (int i = startIndex; i < startIndex + sendMailCount; i++) {
				if (subject != null) {
					mimemsg.setSubject(subject + i, "UTF-8");
				}
				mimemsg.setText(content + i);
				mimemsg.setContent(content + i, "text/html;charset=UTF-8");
				mimemsg.setSentDate(new Date());
				transport.sendMessage(mimemsg, mimemsg.getAllRecipients());
			}
			long end = System.currentTimeMillis();

			System.out.println("total cost:[" + (end - start) / 1000 + "s]");
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendWithAttachment() {
		try {

			// this.get209();
			// this.get246();
			this.get200();
			// this.getVirtual_122();

			String subject = "sendByMailTool testing mail, No.";
			String content = "sendByMailTool testing mail content, ";
			int startIndex = 1;
			int sendMailCount = 120;

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", this.smtpHost);
			// 获得邮件会话对象
			Session session = Session.getDefaultInstance(props, new SmtpAuthenticator(this.user, this.password));
			/** *************************************************** */
			// 创建MIME邮件对象
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(this.sender));// 发件人
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(this.receiver));// 收件人
			// mimeMessage.setSubject(subject);
			mimeMessage.setSentDate(new Date());// 发送日期

			for (int i = startIndex; i < startIndex + sendMailCount; i++) {
				mimeMessage.setSubject(subject + i);

				Multipart mp = new MimeMultipart("related");// related意味着可以发送html格式的邮件
				/** *************************************************** */
				// BodyPart bodyPart = new MimeBodyPart();// 正文
				// bodyPart.setDataHandler(new DataHandler("测<img
				// src=\"cid:IMG1\" />试",
				// "text/html;charset=UTF8"));// 网页格式
				// mp.addBodyPart(bodyPart);

				BodyPart bodyPart = new MimeBodyPart();
				bodyPart.setText(content + i);
				mp.addBodyPart(bodyPart);

				/** *************************************************** */
				// BodyPart attachBodyPart = new MimeBodyPart();// 普通附件
				// FileDataSource fds = new
				// FileDataSource("C:/attachments/log.txt");
				// attachBodyPart.setDataHandler(new DataHandler(fds));
				// attachBodyPart.setFileName("=?GBK?B?"
				// + new
				// sun.misc.BASE64Encoder().encode(fds.getName().getBytes())
				// + "?=");// 解决附件名中文乱码
				// mp.addBodyPart(attachBodyPart);
				/** *************************************************** */
				MimeBodyPart imgBodyPart = new MimeBodyPart(); // 附件图标
				byte[] bytes = readFile("C:/attachments/a.png");
				ByteArrayDataSource fileds = new ByteArrayDataSource(bytes, "application/octet-stream");
				imgBodyPart.setDataHandler(new DataHandler(fileds));
				imgBodyPart.setFileName("a.png");
				imgBodyPart.setHeader("Content-ID", "<IMG1></IMG1>");// 在html中使用该图片方法src="cid:IMG1"
				mp.addBodyPart(imgBodyPart);
				/** *************************************************** */
				mimeMessage.setContent(mp);// 设置邮件内容对象

				Transport.send(mimeMessage);// 发送邮件
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private InternetAddress[] parse(String addressset) throws AddressException {
		ArrayList list = new ArrayList();
		StringTokenizer tokens = new StringTokenizer(addressset, ";");
		while (tokens.hasMoreTokens()) {
			list.add(new InternetAddress(tokens.nextToken().trim()));
		}
		InternetAddress[] addressarray = new InternetAddress[list.size()];
		list.toArray(addressarray);
		return addressarray;
	}

	/**
	 * 读取文件
	 *
	 * @param file
	 *            文件路径
	 * @return 返回二进制数组
	 */
	public static byte[] readFile(String file) {
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			int bytesRead;
			byte buffer[] = new byte[1024 * 1024];
			while ((bytesRead = fis.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
				Arrays.fill(buffer, (byte) 0);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (bos != null)
					bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bos.toByteArray();
	}

}
