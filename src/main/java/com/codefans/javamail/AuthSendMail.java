package com.codefans.javamail;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
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

public class AuthSendMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		calTimeSend();
	}

	public static void calTimeSend() {
		long start = System.currentTimeMillis();
		send();
		long end = System.currentTimeMillis();
		System.out.println("cost:[" + (end - start) / 1000 + "s]");
	}

	public static void send() {

		try {

			// String smtpServer = "10.0.0.209"; //类似smtp.sina.com.cn
			// String authUser = "1";//也可以写成1@new2010.local,
			// 这个用户必须是smtpServer上真实存在的用户
			// String authPass = "1";
			// String sender = "test@test.com";
			// //只要是符合邮件地址格式，不一定非要是smtpServer上存在的真实用户。
			// String receiver = "a@new2010.local";
			String smtpServer = "mail.messagesolution.cn"; // 类似smtp.sina.com.cn
			String authUser = "caishengzhi";// 也可以写成1@new2010.local,
											// 这个用户必须是smtpServer上真实存在的用户
			String authPass = "i0e7Ih$A";
			String sender = "test@test.com"; // 只要是符合邮件地址格式，不一定非要是smtpServer上存在的真实用户。
			String receiver = "caiszf@163.com";
			String subject = "这是主题";
			String content = "这是正文";
			String attachment = "C:/Temp/bb.jpeg";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", smtpServer);
			// 获得邮件会话对象
			Session session = Session.getDefaultInstance(props, new SmtpAuthenticator(authUser, authPass));

			/** *************************************************** */
			// 创建MIME邮件对象
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(sender));// 发件人
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));// 收件人
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

			// 解决附件名中文乱码 start
			String charset = Charset.defaultCharset().displayName(); // 如果在eclipse开发环境，这个由项目Project的字符编码决定
			System.out.println("charset:" + charset);
			System.out.println("attachName:" + fds.getName());
			attachBodyPart
					.setFileName("=?" + charset + "?B?" + new sun.misc.BASE64Encoder().encode(fds.getName().getBytes())

			// 或直接指定UTF-8字符编码,这种方式还要注意, getBytes("UTF-8")也要指定字符编码
			// attachBodyPart.setFileName("=?UTF-8?B?"
			// + new
			// sun.misc.BASE64Encoder().encode(fds.getName().getBytes("UTF-8"))
							+ "?=");
			// 解决附件名中文乱码 end

			mp.addBodyPart(attachBodyPart);
			/** *************************************************** */

			mimeMessage.setContent(mp);// 设置邮件内容对象

			Transport transport = session.getTransport("smtp");
			transport.connect(smtpServer, authUser, authPass);
			transport.sendMessage(mimeMessage, parse(receiver));
			transport.close();

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private static InternetAddress[] parse(String addressset) throws AddressException {
		ArrayList list = new ArrayList();
		StringTokenizer tokens = new StringTokenizer(addressset, ";");
		while (tokens.hasMoreTokens()) {
			list.add(new InternetAddress(tokens.nextToken().trim()));
		}
		InternetAddress[] addressarray = new InternetAddress[list.size()];
		list.toArray(addressarray);
		return addressarray;
	}
}

/**
 * Smtp认证
 */
// class SmtpAuthenticator extends Authenticator {
// String username = null;
// String password = null;
//
// // SMTP身份验证
// public SmtpAuthenticator(String username, String password) {
// this.username = username;
// this.password = password;
// }
//
// public PasswordAuthentication getPasswordAuthentication() {
// return new PasswordAuthentication(this.username, this.password);
// }
// }
