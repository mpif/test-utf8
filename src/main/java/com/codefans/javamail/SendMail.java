package com.codefans.javamail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
 * from:    http://www.blogjava.net/rocky/archive/2005/11/03/18001.html
 * from:    http://blog.csdn.net/sunyujia/article/details/2528696
 * 邮件头(参见RFC822，RFC2047)只能包含US-ASCII字符。
邮件头中任何包含非US-ASCII字符的部分必须进行编码，使其只包含US-ASCII字符。
但是java mail可以根据JVM发送中文邮件自行编码，，用它自带的MimeUtility类的encodeText方法对中文信息进行编码也可以。
邮件正文必须有charset=gb2312否则为
Content-Type: text/html; charset=us-ascii
Content-Transfer-Encoding: 7bit
打开邮件为乱码,设置charset=gb2312后
Content-Type: text/html;charset=gb2312
Content-Transfer-Encoding: quoted-printable
它不能用MimeUtility里的方法来编码。
邮件正文的编码方式的信息是要放在Content-Transfer-Encoding这个邮件头参数中的，
而MimeUtility里面的方法是将编码方式的信息放在编码后的正文内容中。
所以如果你对正文也用MimeUtility进行处理，那么其他邮件程序就不会正常显示你编码的邮件，
因为其他邮件软件如outlook,foxmail只会根据Content-Transfer-Encoding这个里面的信息来对邮件正文进行解码。*/

/**
 * <P>
 * Title:用java发送邮件的例子
 * </P>
 *
 * <P>
 * Description:发送图片附件并在html中使用该图片
 * </P>
 *
 * <P>
 * Copyright: Copyright (c) 2007
 * </P>
 *
 * @author 孙钰佳
 * @blog http://blog.csdn.net/sunyujia/
 * @main sunyujia@yahoo.cn
 * @date Jun 10, 2008 12:35:26 AM
 */
public class SendMail {
	private static String username = "1";
	private static String password = "1";
	private static String smtpServer = "10.0.0.209";
	private static String fromMailAddress = "user1@new2010.local";
	private static String toMailAddress = "f@new2010.local";

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpServer);
		// 获得邮件会话对象
		Session session = Session.getDefaultInstance(props, new SmtpAuthenticator(username, password));
		/** *************************************************** */
		// 创建MIME邮件对象
		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(fromMailAddress));// 发件人
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toMailAddress));// 收件人
		mimeMessage.setSubject("主题");
		mimeMessage.setSentDate(new Date());// 发送日期
		Multipart mp = new MimeMultipart("related");// related意味着可以发送html格式的邮件
		/** *************************************************** */
		BodyPart bodyPart = new MimeBodyPart();// 正文
		bodyPart.setDataHandler(new DataHandler("测<img src=\"cid:IMG1\" />试", "text/html;charset=UTF8"));// 网页格式
		/** *************************************************** */
		BodyPart attachBodyPart = new MimeBodyPart();// 普通附件
		FileDataSource fds = new FileDataSource("C:\\subject.txt");
		attachBodyPart.setDataHandler(new DataHandler(fds));
		attachBodyPart.setFileName("=?GBK?B?" + new sun.misc.BASE64Encoder().encode(fds.getName().getBytes()) + "?=");// 解决附件名中文乱码
		mp.addBodyPart(attachBodyPart);
		/** *************************************************** */
		MimeBodyPart imgBodyPart = new MimeBodyPart(); // 附件图标
		byte[] bytes = readFile("C:\\1.gif");
		ByteArrayDataSource fileds = new ByteArrayDataSource(bytes, "application/octet-stream");
		imgBodyPart.setDataHandler(new DataHandler(fileds));
		imgBodyPart.setFileName("1.gif");
		imgBodyPart.setHeader("Content-ID", "<IMG1></IMG1>");// 在html中使用该图片方法src="cid:IMG1"
		mp.addBodyPart(imgBodyPart);
		/** *************************************************** */
		mp.addBodyPart(bodyPart);
		mimeMessage.setContent(mp);// 设置邮件内容对象
		Transport.send(mimeMessage);// 发送邮件

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
