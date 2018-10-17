package com.codefans.javamail;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

public class MultiMailSender {

	private String sender = "";// sender email address
	private String receiver = "";
	private String smtphost = "";// smtp host
	private String user = ""; // login username
	private String password = "";// login password

	private String subject = "";// mail subject
	private String content = "";
	private String port = "25";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MultiMailSender sender = new MultiMailSender();
		sender.send();
	}

	public void send() {
		// smtphost = "10.0.0.209";
		// sender = "aaa@bbb.com";
		// receiver = "a@new2010.local";
		// user = "1";
		// password = "1";

		smtphost = "10.0.0.193";
		sender = "wang1@ex2010.com";
		receiver = "wang2@ex2010.com";
		user = "wang1";
		password = "2";

		subject = "bbbbbbbbbbbbbbbb";
		content = "aaaaaaaaaaaaaaaa";

		List att = new ArrayList();
		// String filename = "C:\\Documents and
		// Settings\\workbench\\桌面\\temp\\6M附件.rar";
		// String dispFilename = "6M_FuJian.rar";
		// String filename =
		// "D:\\dev\\Hosting-RB-1.2.x\\attatchment\\aaaa2.eml";
		String filename = "C:\\Users\\Administrator\\Desktop\\abc.docx";
		String dispFilename = "abc.eml";
		String encoding = "UTF-8";
		BodyPart part = newAttachment(filename, dispFilename, encoding);
		att.add(part);

		try {
			smtp(receiver, content, att);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public BodyPart newAttachment(String filename, String dispFilename, String encoding) {
		MimeBodyPart mbp = new MimeBodyPart();
		File f = new File(filename);
		DataSource ds = new FileDataSource(f);
		try {
			mbp.setDataHandler(new DataHandler(ds));
			// mbp.setFileName(com.messagesolution.message.util.MimeUtility.encodeText(dispFilename,
			// "UTF-8", null));
			mbp.setFileName(dispFilename);
		} catch (Exception e) {
			p("Exception occured when Sendmail newAttachment");

		}
		return mbp;

	}

	public void smtp(String receiver, String content, List att) throws MessagingException {
		if (smtphost == null)
			throw new MessagingException("smtphost not found");
		if (user == null)
			throw new MessagingException("user not found");
		if (password == null)
			throw new MessagingException("password not found");
		p("send email user is:" + user);
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.port", port);
		properties.put("mail.smtp.auth", "true");// esmtp

		MailAuthenticator ma = new MailAuthenticator(user, password);

		Session session1 = Session.getInstance(properties, ma);

		// session1.setDebug(true);
		MimeMessage mimemsg = new MimeMessage(session1);// create mime object
		if (sender != null) {
			mimemsg.setFrom(new InternetAddress(sender));
		}
		p("sender is:" + sender.toString() + ",recipient is:" + receiver.toString());

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
			p("authorization is success.");
			transport.sendMessage(mimemsg, mimemsg.getAllRecipients());
			transport.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			p("error" + ex);
		}
		p("send success");
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

	public void p(String str) {
		System.out.println(str);
	}
}

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
