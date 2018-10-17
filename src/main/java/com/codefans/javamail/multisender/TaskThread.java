package com.codefans.javamail.multisender;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.codefans.java.util.StringUtils;

public class TaskThread implements Runnable {

	public String emailAddress;

	public String smtpServer;
	public String adUser;
	public String adPass;
	public String sender;

	public boolean ifAuth;

	public String attachFilePath;

	public String subject;
	public String content;

	public int mailNums = 1000;
	public int hasSendedCount;

	public String S209 = "new2010.local";
	public String S236 = "exchange.com";

	public String attachment;

	public TaskThread(String emailAddress) {
		this.emailAddress = emailAddress;
		init();
		// smtpServerAdapter(emailAddress);

		// attachment = "C:\\2M附件.rar";
		// mailNums = 1;

		// subject = "同时归档削减测试邮件: Mails come from JavaMailTool";
		// content = "this is mail body. 这是邮件的正文. ";

	}

	public void init() {
		mailNums = Configuration.mailNums;
		smtpServer = Configuration.smtpAddress;
		adUser = Configuration.adUser;
		adPass = Configuration.adPass;
		sender = Configuration.sender;
		subject = Configuration.subject;
		content = Configuration.content;
		attachment = Configuration.attachment;
	}

	public void smtpServerAdapter(String emailAddress) {
		if (StringUtils.isNotEmpty(emailAddress)) {
			if (emailAddress.endsWith(S209)) {
				smtpServer = "10.0.0.209";
				adUser = "1@new2010.local";
				adPass = "1";
			} else if (emailAddress.endsWith(S236)) {
				smtpServer = "10.0.0.236";
				adUser = "1@exchange.com";
				adPass = "1";
			}
		} else {
			System.out.println("EmailAddress:[" + emailAddress + "] is empty");
		}
	}

	@Override
	public void run() {
		long start, end;
		start = System.currentTimeMillis();
		for (int i = 0; i < mailNums; i++) {
			send();
			hasSendedCount++;
			System.out.println("[" + emailAddress + "] has sended No." + hasSendedCount + " email.");
		}
		end = System.currentTimeMillis();
		System.out.println("[" + emailAddress + "] send successfully, send " + mailNums + " mails, total cost:["
				+ (end - start) / 1000 + "s]");
	}

	public void send() {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", smtpServer);
			// 获得邮件会话对象
			Session session = Session.getDefaultInstance(props, new SmtpAuthenticator(adUser, adPass));
			/** *************************************************** */
			// 创建MIME邮件对象
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(sender));// 发件人
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));// 收件人
			mimeMessage.setSubject(subject);
			mimeMessage.setSentDate(new Date());// 发送日期
			Multipart mp = new MimeMultipart();

			/** *************************************************** */
			BodyPart part = new MimeBodyPart(); // 邮件正文
			part.setContent(content.toString(), "text/html;charset=UTF-8");
			mp.addBodyPart(part);
			/** *************************************************** */

			/** *************************************************** */
			BodyPart attachBodyPart = new MimeBodyPart();// 普通附件
			FileDataSource fds = new FileDataSource(attachment);
			attachBodyPart.setDataHandler(new DataHandler(fds));
			attachBodyPart.setFileName(
					"=?UTF-8?B?" + new sun.misc.BASE64Encoder().encode(fds.getName().getBytes("UTF-8")) + "?=");// 解决附件名中文乱码
			mp.addBodyPart(attachBodyPart);
			/** *************************************************** */

			mimeMessage.setContent(mp);// 设置邮件内容对象

			Transport transport = session.getTransport("smtp");
			transport.connect(smtpServer, adUser, adPass);
			transport.sendMessage(mimeMessage, parse(emailAddress));
			transport.close();

			// Transport.send(mimeMessage);// 发送邮件
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
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

/**
 * Smtp认证
 */
class SmtpAuthenticator extends Authenticator {
	String username = null;
	String password = null;

	// SMTP身份验证
	public SmtpAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password);
	}
}

class ByteArrayDataSource implements DataSource {

	private final String contentType;
	private final byte[] buf;
	private final int len;

	public ByteArrayDataSource(byte[] buf, String contentType) {
		this(buf, buf.length, contentType);
	}

	public ByteArrayDataSource(byte[] buf, int length, String contentType) {
		this.buf = buf;
		this.len = length;
		this.contentType = contentType;
	}

	public String getContentType() {
		if (contentType == null)
			return "application/octet-stream";
		return contentType;
	}

	public InputStream getInputStream() {
		return new ByteArrayInputStream(buf, 0, len);
	}

	public String getName() {
		return null;
	}

	public OutputStream getOutputStream() {
		throw new UnsupportedOperationException();
	}
}
