package com.codefans.javamail.eeasendmail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

//import org.xbill.DNS.Lookup;
//import org.xbill.DNS.MXRecord;
//import org.xbill.DNS.Record;
//import org.xbill.DNS.Type;

public class EasSendMail {
	private String errmsg = "";

	private String sender = "";// sender email address
	private String receiver = "";
	private String smtphost = "";// smtp host
	private String user = ""; // login username
	private String password = "";// login password

	private String subject = "";// mail subject
	private String port = "25";

	public EasSendMail() {

	}

	public static String getURL() throws Exception {
		String emailtemplatepath = "";

		// ServerProperties sp = new ServerProperties(MSolUtil.CONF_PATH +
		// "/eas.conf");
		// String emailtemplatepath =
		// sp.getProperty("/mail/config/system/emailtemplatepath");
		// if(emailtemplatepath==null||emailtemplatepath.length()==0 ||
		// emailtemplatepath.isEmpty()|| emailtemplatepath==""){
		// emailtemplatepath=MSolUtil.ROOT_PATH+"/eas/web/template/EmailTemplate";
		// System.out.println("emailtemplatepath:"+emailtemplatepath);
		// return emailtemplatepath;
		// }

		System.out.println("emailtemplatepath:" + emailtemplatepath);
		return emailtemplatepath;
	}

	public static String getHtmlContent() {
		String lineTXT = null;
		String lineTXT2 = "";

		try {
			String encoding = "GBK";
			System.out.println("URL IS:" + getURL());
			File file = new File(getURL() + "/EmailTemplate.htm");
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);

				while ((lineTXT = bufferedReader.readLine()) != null) {
					lineTXT2 = lineTXT2 + lineTXT;
				}
				read.close();
			} else {
				System.out.println("WARNING:System can not find the EmailTemplate.htm file!!");
			}
		} catch (Exception e) {
			System.out.println("WARNIGN:Read the file content error!!");
			System.out.println("error" + e);
		}

		return lineTXT2;
	}

	public void setsender(String sender) {
		this.sender = sender;
	}

	public String getsender() {
		return sender;
	}

	public void setsmtphost(String smtphost) {
		this.smtphost = smtphost;
	}

	public String getsmtphost() {
		return smtphost;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setuser(String user) {
		this.user = user;
	}

	public String getuser() {
		return user;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getpassword() {
		return password;
	}

	public void setsubject(String subject) {
		this.subject = subject;
	}

	public String getsubject() {
		return subject;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * main class
	 */
	class MailAuthenticator extends Authenticator {
		String authenName;
		String authenPass;

		public MailAuthenticator(String authenName, String authenPass) {
			super();
			this.authenName = authenName;
			this.authenPass = authenPass;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(authenName, authenPass);
		}
	}

	public void smtp(String receiver, String content, BodyPart att) throws MessagingException {
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		if (user == null)
			throw new MessagingException("user not found");
		if (password == null)
			throw new MessagingException("password not found");

		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.smtp.auth", "true");// esmtp
		properties.put("mail.port", port);

		MailAuthenticator ma = new MailAuthenticator(user, password);

		Session session1 = Session.getInstance(properties, ma);
		// Session session=Session.getInstance(properties);

		/*
		 * Session session = Session.getDefaultInstance(properties, new
		 * Authenticator() { public PasswordAuthentication
		 * getpasswordauthentication() { return new PasswordAuthentication(user,
		 * password); } });
		 */

		session1.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session1);// create mime object
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		if (receiver != null) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(this.receiver));
		}
		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}
		/*
		 * MimeBodyPart part = new MimeBodyPart();//mail content part
		 * part.setText(content == null ? "content is null" : content, "gbk");
		 * part.setContent(content.toString(), "text/html;charset=gbk");
		 */

		Multipart multipart = new MimeMultipart();
		if (att != null)
			multipart.addBodyPart(att);

		mimemsg.setText(content);
		mimemsg.setContent(content.toString(), "text/html;charset=UTF-8");
		mimemsg.setSentDate(new Date());
		System.out.println("sending mail");

		Transport transport = session1.getTransport("smtp");
		transport.connect(smtphost, user, password);
		transport.sendMessage(mimemsg, mimemsg.getAllRecipients());
		transport.close();

		System.out.println("send success");
	}

	public void smtp(String receiver, String content, List att) throws MessagingException {
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		if (user == null)
			throw new MessagingException("user not found");
		if (password == null)
			throw new MessagingException("password not found");
		System.out.println("send email user is:" + user);
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.port", port);
		properties.put("mail.smtp.auth", "true");// esmtp

		MailAuthenticator ma = new MailAuthenticator(user, password);

		Session session1 = Session.getInstance(properties, ma);
		// Session session=Session.getInstance(properties);

		/*
		 * Session session = Session.getDefaultInstance(properties, new
		 * Authenticator() { public PasswordAuthentication
		 * getpasswordauthentication() { return new PasswordAuthentication(user,
		 * password); } });
		 */

		// session1.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session1);// create mime object
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		System.out.println("sender is:" + sender.toString() + ",recipient is:" + receiver.toString());

		// modify by zxq,for send mail about schedule search results to someone
		if (receiver != null && !receiver.equals("")) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(receiver));
		} else if (receiver == null || receiver.equals("")) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(this.receiver));
		}
		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}

		MimeBodyPart part = new MimeBodyPart();// mail body
		part.setText(content == null ? "content is null" : content, "UTF-8");
		part.setContent(content.toString(), "text/html;charset=UTF-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(part);
		if (att != null)
			for (int i = 0; i < att.size(); i++) {
				if (att.get(i) != null)
					multipart.addBodyPart((BodyPart) att.get(i));// attachment
			}

		mimemsg.setContent(multipart);
		mimemsg.setSentDate(new Date());
		try {
			Transport transport = session1.getTransport("smtp");
			transport.connect(smtphost, user, password);
			System.out.println("authorization is success.");
			transport.sendMessage(mimemsg, mimemsg.getAllRecipients());
			transport.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("send success");
	}

	// add by feng
	public void smtp(String receiver, String content) throws MessagingException {
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		if (user == null)
			throw new MessagingException("user not found");
		if (password == null)
			throw new MessagingException("password not found");
		System.out.println("send email user is:" + user);
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.port", port);
		properties.put("mail.smtp.auth", "true");// esmtp

		MailAuthenticator ma = new MailAuthenticator(user, password);

		Session session1 = Session.getInstance(properties, ma);
		// Session session=Session.getInstance(properties);

		/*
		 * Session session = Session.getDefaultInstance(properties, new
		 * Authenticator() { public PasswordAuthentication
		 * getpasswordauthentication() { return new PasswordAuthentication(user,
		 * password); } });
		 */

		// session1.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session1);// create mime object
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		System.out.println("sender is:" + sender.toString() + ",recipient is:" + receiver.toString());

		// modify by zxq,for send mail about schedule search results to someone
		if (receiver != null && !receiver.equals("")) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(receiver));
		} else if (receiver == null || receiver.equals("")) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(this.receiver));
		}
		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}

		MimeBodyPart part = new MimeBodyPart();// mail body
		part.setText(content == null ? "content is null" : content, "UTF-8");
		part.setContent(content.toString(), "text/html;charset=UTF-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(part);

		mimemsg.setContent(multipart);
		mimemsg.setSentDate(new Date());
		try {
			Transport transport = session1.getTransport("smtp");
			transport.connect(smtphost, user, password);
			System.out.println("authorization is success.");
			transport.sendMessage(mimemsg, mimemsg.getAllRecipients());
			transport.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("send success");
	}

	public void getFileSource(String receiver, String content) throws Exception {
		FileDataSource fds = new FileDataSource(getURL() + "/EmailHeader.jpg");
		MimeBodyPart mbp2 = new MimeBodyPart();
		mbp2.setFileName(fds.getName());
		mbp2.setText("EmailHeader.jpg");
		mbp2.setDataHandler(new DataHandler(fds));
		mbp2.setHeader("Content-ID", fds.getName());

		FileDataSource fds2 = new FileDataSource(getURL() + "/EmailFooter.jpg");
		MimeBodyPart mbp3 = new MimeBodyPart();
		mbp3.setFileName(fds2.getName());
		mbp3.setText("EmailFooter.jpg");
		mbp3.setDataHandler(new DataHandler(fds2));
		mbp3.setHeader("Content-ID", fds2.getName());

		if (!fds.getFile().exists() || !fds2.getFile().exists()) {
			System.out.println("WARNING:System can not find the EmailHeader.jpg or EmailFooter.jpg!!");
			this.smtp(receiver, content);
			return;
		}
	}

	// add by feng
	public void smtpAlert(String receiver, String content) throws Exception {
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		if (user == null)
			throw new MessagingException("user not found");
		if (password == null)
			throw new MessagingException("password not found");
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", port);
		System.out.println("OK!!");
		MailAuthenticator auth = new MailAuthenticator(user, password);
		Session session = Session.getInstance(properties, auth);

		session.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session);
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		if (receiver != null) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(receiver));
		}
		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}
		MimeBodyPart part = new MimeBodyPart();
		part.setText(content == null ? "content is null" : content, "UTF-8");

		String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">" + "<html>"
				+ "<head><title></title></head>" + "<body>" + "<IMG SRC=cid:EmailHeader.jpg /><br>" + content.toString()
				+ "<br>" + getHtmlContent() + "<br>" + "<IMG SRC=cid:EmailFooter.jpg /><br>" + "</body>" + "</html>";

		part.setContent(html, "text/html;charset=UTF-8");
		System.out.println("Alert URL:" + getURL());

		FileDataSource fds = new FileDataSource(getURL() + "/EmailHeader.jpg");
		MimeBodyPart mbp2 = new MimeBodyPart();
		mbp2.setFileName(fds.getName());
		mbp2.setText("EmailHeader.jpg");
		mbp2.setDataHandler(new DataHandler(fds));
		mbp2.setHeader("Content-ID", fds.getName());

		FileDataSource fds2 = new FileDataSource(getURL() + "/EmailFooter.jpg");
		MimeBodyPart mbp3 = new MimeBodyPart();
		mbp3.setFileName(fds2.getName());
		mbp3.setText("EmailFooter.jpg");
		mbp3.setDataHandler(new DataHandler(fds2));
		mbp3.setHeader("Content-ID", fds2.getName());

		if (!fds.getFile().exists() || !fds2.getFile().exists()) {
			System.out.println("WARNING:System can not find the EmailHeader.jpg or EmailFooter.jpg!!");
			this.smtp(receiver, content);
			return;
		}

		MimeMultipart mp = new MimeMultipart("related");// alternative
		mp.addBodyPart(part);
		mp.addBodyPart(mbp2);
		mp.addBodyPart(mbp3);
		mimemsg.setContent(mp);

		try {
			Transport transport = session.getTransport("smtp");
			transport.connect(smtphost, user, password);
			transport.sendMessage(mimemsg, mimemsg.getAllRecipients());
			transport.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// feng
	public void smtp_noAuth(String receiver, String content, BodyPart att) throws Exception {
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.port", port);
		Session session = Session.getInstance(properties);

		session.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session);
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		if (receiver != null) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(receiver));
		}
		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}
		MimeBodyPart part = new MimeBodyPart();
		part.setText(content == null ? "content is null" : content, "UTF-8");

		String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">" + "<html>"
				+ "<head><title></title></head>" + "<body>" + "<IMG SRC=cid:EmailHeader.jpg /><br>" + content.toString()
				+ "<br>" + getHtmlContent() + "<br>" + "<IMG SRC=cid:EmailFooter.jpg /><br>" + "</body>" + "</html>";

		part.setContent(html, "text/html;charset=UTF-8");
		System.out.println("noAuth:" + getURL());
		FileDataSource fds = new FileDataSource(getURL() + "/EmailHeader.jpg");
		MimeBodyPart mbp2 = new MimeBodyPart();
		mbp2.setFileName(fds.getName());
		mbp2.setText("EmailHeader.jpg");
		mbp2.setDataHandler(new DataHandler(fds));
		mbp2.setHeader("Content-ID", fds.getName());

		FileDataSource fds2 = new FileDataSource(getURL() + "/EmailFooter.jpg");
		MimeBodyPart mbp3 = new MimeBodyPart();
		mbp3.setFileName(fds2.getName());
		mbp3.setText("EmailFooter.jpg");
		mbp3.setDataHandler(new DataHandler(fds2));
		mbp3.setHeader("Content-ID", fds2.getName());

		if (!fds.getFile().exists() || !fds2.getFile().exists()) {
			System.out.println("WARNING:System can not find the EmailHeader.jpg or EmailFooter.jpg!!");
			this.smtp_noAuth(receiver, content);
			return;
		}

		MimeMultipart mp = new MimeMultipart("related");// alternative
		mp.addBodyPart(part);
		mp.addBodyPart(mbp2);
		mp.addBodyPart(mbp3);
		mimemsg.setContent(mp);

		System.out.println("sending mail");
		Transport.send(mimemsg);

		System.out.println("send success");
	}

	// add by feng
	public void smtp_noAuthAlert(String receiver, String content, List att) throws Exception {
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.port", port);
		Session session = Session.getInstance(properties);

		session.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session);
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		if (receiver != null) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(receiver));
		}
		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}
		MimeBodyPart part = new MimeBodyPart();
		part.setText(content == null ? "content is null" : content, "UTF-8");

		String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">" + "<html>"
				+ "<head><title></title></head>" + "<body>" + "<IMG SRC=cid:EmailHeader.jpg /><br>" + content.toString()
				+ "<br>" + getHtmlContent() + "<br>" + "<IMG SRC=cid:EmailFooter.jpg /><br>" + "</body>" + "</html>";

		part.setContent(html, "text/html;charset=UTF-8");
		System.out.println("noAuthAlert URL:" + getURL());
		FileDataSource fds = new FileDataSource(getURL() + "/EmailHeader.jpg");
		MimeBodyPart mbp2 = new MimeBodyPart();
		mbp2.setFileName(fds.getName());
		mbp2.setText("EmailHeader.jpg");
		mbp2.setDataHandler(new DataHandler(fds));
		mbp2.setHeader("Content-ID", fds.getName());

		FileDataSource fds2 = new FileDataSource(getURL() + "/EmailFooter.jpg");
		MimeBodyPart mbp3 = new MimeBodyPart();
		mbp3.setFileName(fds2.getName());
		mbp3.setText("EmailFooter.jpg");
		mbp3.setDataHandler(new DataHandler(fds2));
		mbp3.setHeader("Content-ID", fds2.getName());

		if (!fds.getFile().exists() || !fds2.getFile().exists()) {
			System.out.println("WARNING:System can not find the EmailHeader.jpg or EmailFooter.jpg!!");
			this.smtp_noAuth(receiver, content);
			return;
		}

		MimeMultipart mp = new MimeMultipart("related");// alternative
		mp.addBodyPart(part);
		mp.addBodyPart(mbp2);
		mp.addBodyPart(mbp3);
		mimemsg.setContent(mp);

		System.out.println("sending mail");
		Transport.send(mimemsg);

		System.out.println("send success");
	}

	public void smtp_noAuth(String receiver, String content, List att) throws MessagingException {
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.port", port);
		Session session = Session.getInstance(properties);

		session.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session);
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		if (receiver != null) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(receiver));
		}
		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}
		MimeBodyPart part = new MimeBodyPart();
		part.setText(content == null ? "content is null" : content, "UTF-8");

		part.setContent(content.toString(), "text/html;charset=UTF-8");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(part);
		if (att != null)
			for (int i = 0; i < att.size(); i++)
				multipart.addBodyPart((BodyPart) att.get(i));
		mimemsg.setContent(multipart);
		mimemsg.setSentDate(new Date());
		System.out.println("sending mail");
		Transport.send(mimemsg);

		System.out.println("send success");

	}

	// add by feng
	public void smtp_noAuth(String receiver, String content) throws MessagingException {
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.port", port);
		Session session = Session.getInstance(properties);

		session.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session);
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		if (receiver != null) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(receiver));
		}
		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}
		MimeBodyPart part = new MimeBodyPart();
		part.setText(content == null ? "content is null" : content, "UTF-8");

		part.setContent(content.toString(), "text/html;charset=UTF-8");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(part);

		mimemsg.setContent(multipart);
		mimemsg.setSentDate(new Date());
		System.out.println("sending mail");
		Transport.send(mimemsg);

		System.out.println("send success");

	}

	// add by feng
	public void smtp_replyMsg(String sender1, String receiver, String cc, String subject, String content,
			String filename) throws MessagingException {

		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.port", port);
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session);
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender1));
		}
		if (receiver != null) {
			mimemsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver, false));
		}
		if (cc != null)
			mimemsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));

		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}

		System.out.println("mail's subject:" + subject);
		MimeBodyPart part = new MimeBodyPart();
		part.setText(content == null ? "content is null" : content, "UTF-8");
		part.setContent(content.toString(), "text/html;charset=UTF-8");

		if (filename != null) {
			FileDataSource fds = new FileDataSource(filename);
			part.setDataHandler(new DataHandler(fds));
			part.setFileName(fds.getName());
		}

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(part);

		mimemsg.setContent(multipart);
		mimemsg.setSentDate(new Date());
		System.out.println("sending mail");

		Transport.send(mimemsg);

		System.out.println("send success");
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

	public boolean sendmails(String mail, String content, List att) {

		if (mail == null || content == null) {
			return false;
		}

		try {
			this.smtp(mail, content, att);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return true;
	}

	// add by feng
	public boolean sendmailsAlert(String mail, String content, List att) {
		if (mail == null || content == null) {
			return false;
		}
		try {
			this.smtpAlert(mail, content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return true;
	}

	public boolean sendmails_noauth(String mail, String content, BodyPart att) {
		if (mail == null || content == null) {
			return false;
		}

		try {
			this.smtp_noAuth(mail, content, att);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return true;
	}

	// add by feng
	public boolean sendmails_noauthAlert(String mail, String content, List att) {
		if (mail == null || content == null) {
			return false;
		}

		try {
			this.smtp_noAuthAlert(mail, content, att);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return true;
	}

	public boolean sendmails_noauth(String mail, String content, List att) {
		if (mail == null || content == null) {
			return false;
		}

		try {
			this.smtp_noAuth(mail, content, att);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ex3 in sendmail.java:" + ex.toString());
		}

		return true;
	}

	public void smtp_custom(MailUtil mu) throws MessagingException {
		String smtphost = mu.getSmtphost();
		String username = mu.getUsername();
		String password = mu.getPasswd();
		String sender = mu.getSender();
		String receiver = mu.getReceiver();
		String content = mu.getContent();
		BodyPart att = mu.getAtt();
		String subject = mu.getSubject();
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		if (username == null)
			throw new MessagingException("user not found");
		if (password == null)
			throw new MessagingException("password not found");

		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.port", port);
		properties.put("mail.smtp.auth", "true");// esmtp

		MailAuthenticator ma = new MailAuthenticator(username, password);

		Session session1 = Session.getInstance(properties, ma);
		// Session session=Session.getInstance(properties);

		/*
		 * Session session = Session.getDefaultInstance(properties, new
		 * Authenticator() { public PasswordAuthentication
		 * getpasswordauthentication() { return new PasswordAuthentication(user,
		 * password); } });
		 */

		session1.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session1);// create mime object
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		if (receiver != null) {
			mimemsg.setRecipients(Message.RecipientType.TO, parse(receiver));
		}
		if (subject != null) {
			mimemsg.setSubject(subject, "UTF-8");
		}
		/*
		 * MimeBodyPart part = new MimeBodyPart();//mail content part
		 * part.setText(content == null ? "content is null" : content, "gbk");
		 * part.setContent(content.toString(), "text/html;charset=gbk");
		 */

		Multipart multipart = new MimeMultipart();
		if (att != null)
			multipart.addBodyPart(att);// attachment

		mimemsg.setText(content);
		mimemsg.setContent(content, "text/html;charset=UTF-8");

		mimemsg.setSentDate(new Date());
		System.out.println("sending mail");

		Transport transport = session1.getTransport("smtp");
		transport.connect(smtphost, username, password);
		transport.sendMessage(mimemsg, mimemsg.getAllRecipients());
		transport.close();

		System.out.println("send success");
	}

	public static void main(String[] args) {

		EasSendMail mail = new EasSendMail();

		// String receiver = "a@new2010.local";
		// String content = "aaaaaaaaaaa";
		// List att = new ArrayList();
		// String filename = "C:\\Documents and
		// Settings\\workbench\\桌面\\temp\\6M附件.rar";
		// String dispFilename = "6M附件.rar";
		// String encoding = "UTF-8";
		// BodyPart part = RealSendMail.newAttachment(filename, dispFilename,
		// encoding);
		// att.add(part);
		// mail.sendmails(receiver, content, att);

		MailUtil mu = new MailUtil();
		mu.setSmtphost("mail.messagesolution.net");
		mu.setUsername("caishengzhi");
		mu.setPasswd("mypass"); // mypass is not the real password, it just for
								// test
		mu.setSender("caishengzhi@messagesolution.cn");
		mu.setReceiver("caishengzhi@messagesolution.cn");
		mu.setSubject("hello world 2!");
		mu.setContent("hello hello 2!");

		try {
			mail.smtp_custom(mu);
		} catch (Exception e) {
			e.printStackTrace();

		}

		// mail.realSendMail(msg);
	}

}
