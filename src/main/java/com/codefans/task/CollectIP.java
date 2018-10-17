package com.codefans.task;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class CollectIP {

	// private String ip = "222.35.87.89";
	private String ip = "222.35.82.190";

	private void collect() {

		boolean change = false;
		while (true) {
			change = checkIP();
			if (change) {
				email();
			}
			try {
				Thread.currentThread().sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean checkIP() {
		System.out.println("check ip, time: " + now());
		DefaultHttpClient httpclient = new DefaultHttpClient();
		boolean change = false;
		try {
			String URL = "http://iframe.ip138.com/city.asp";
			HttpPost httpost = new HttpPost(URL);
			HttpResponse response = httpclient.execute(httpost);

			// System.out.println("Login form get: " +
			// response.getStatusLine());
			// System.out.println("response toString(): " +
			// response.toString());

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();

			BufferedInputStream bis = new BufferedInputStream(is);
			int n = 0;
			byte[] b = new byte[1024];
			StringBuilder sb = new StringBuilder();

			while ((n = bis.read(b)) != -1) {
				sb.append(new String(b, 0, b.length));
			}

			String content = sb.toString();
			// System.out.println("content: " + sb.toString());

			Pattern p = Pattern.compile("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}");
			Matcher matcher = p.matcher(content);
			String ip = "";
			while (matcher.find()) {
				ip = matcher.group();
				if (ip != null && !ip.equals(this.ip)) {
					this.ip = ip;
					change = true;
				}
			}
			System.out.println("ip: " + ip);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return change;
	}

	private void email() {

		String host = "smtp.sina.com.cn";
		String from = "mtests@sina.cn";
		String from_pwd = "mstest";

		// String to = "caishengzhi@messagesolution.cn";
		String to = "363874279@qq.com";
		String subject = "IP Collection";

		String msg = "IP: " + ip + ", time: " + now();

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

	public String now() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	public static void main(String[] args) {
		CollectIP collect = new CollectIP();
		collect.collect();
	}
}

class MyAuthenticator extends Authenticator {
	private String strUser;
	private String strPwd;

	public MyAuthenticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}
