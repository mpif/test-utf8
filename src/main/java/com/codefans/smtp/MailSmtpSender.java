package com.codefans.smtp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.codefans.java.io.LineInputStream;

import sun.misc.BASE64Encoder;

/*
 * User: Sean
 * Date: 2015-4-13
 * Time: 下午5:13:47
 */

public class MailSmtpSender {

	private static int dataTimeout = 60000;
	static int greetingTimeout = 60000;
	private static int heloTimeout = 60000;
	private static int mailFromTimeout = 60000;
	private static int rcptToTimeout = 60000;
	private static int quitTimeout = 10000;
	private static int noopTimeout = 60000;

	private String lastServerResponse = "";
	private int lastServerResponseCode = -1;
	private long lastAccessTime;

	private Socket socket;
	private LineInputStream lis;
	private BufferedOutputStream bos;

	private byte[] buff;

	public MailSmtpSender() {
		buff = new byte[16384];
	}

	// private Logger logger;
	// private static int dataTimeout = 60000;
	// static int greetingTimeout = 60000;
	// private static int heloTimeout = 60000;
	// private static int mailFromTimeout = 60000;
	// private static int rcptToTimeout = 60000;
	// private static int quitTimeout = 10000;
	// private static int noopTimeout = 60000;
	// private static int maxSent = 50;
	// private static String localHostName = "127.0.0.1";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MailSmtpSender smtpSender = new MailSmtpSender();
		smtpSender.send();
	}

	public void send() {
		try {
			String smtpHost = "10.0.0.16";
			// String smtpHost = "10.0.0.26";
			// String smtpHost = "10.0.0.122";
			// String smtpHost = "win2010.com.";
			// String smtpHost = "smtpbg333.qq.com";
			// String smtpHost = "smtp.qq.com";
			// String smtpHost = "smtp2911-210.mail.sina.com.cn";
			// String smtpHost = "smtp.sina.com.cn";
			// String smtpHost = "m13-127.163.com";
			// String smtpHost = "smtp.163.com";
			// String smtpHost = "163mx01.mxmail.netease.com";
			// String smtpHost = "163mx02.mxmail.netease.com";
			// String smtpHost = "163mx03.mxmail.netease.com";
			// String smtpHost = "163mx00.mxmail.netease.com";

			// String smtpHost = "freemx1.sinamail.sina.com.cn";
			// String smtpHost = "freemx2.sinamail.sina.com.cn";
			// String smtpHost = "freemx3.sinamail.sina.com.cn";
			// String smtpHost = "irtj11-87.sinamail.sina.com.cn";

			// String smtpHost = "mx3.qq.com"; //10
			// String smtpHost = "mx2.qq.com"; //20
			// String smtpHost = "mx1.qq.com"; //30
			// String smtpHost = "newmx.qq.com"; //

			// String smtpHost = "mail.messagesolution.cn";

			int smtpPort = 25;
			String sender = "abc@efg.com";
			// String sender = "caiszf@163.com";
			// String sender = "363874279@qq.com";
			// String sender = "a@win2010.com";
			// String sender = "b@win2010.com";
			// String sender = "caishengzhi@messagesolution.cn";
			List<String> recipients = new ArrayList<String>();
			// recipients.add("363874279@qq.com");
			recipients.add("reci@eea.mail");
			// recipients.add("caishengzhi@messagesolution.cn");
			// recipients.add("csz363874279@sina.cn");
			// recipients.add("caiszf@163.com");
			// recipients.add("a@win2010.com");

			// String folder = "C:\\Temp\\emls\\";
			String folder = "D:\\tmp\\emails\\";
			// String folder =
			// "D:\\dev\\RB-2.1.x-gx\\web\\messagesolutionTemp\\preAudit\\";
			// String eml = folder + "a send on behalf of b, to
			// c-0_1_20150311_102400.eml";
			// String eml = folder + "[慕课网]激活邮箱账号-.eml";
			// String eml = folder + "您的4月积分月结单，欢迎点击查看！.eml";
			// String eml = folder + "test mail from
			// caishengzhi@messagesolution.cn.eml";
			// String eml = folder + "test mail from qq to sina.eml";
			// String eml = folder + "test mail from qq to 163.eml";
			// String eml = folder + "hello 163, i am from qq.eml";
			// String eml = folder + "hello sina, i am from qq.eml";
			// String eml = folder + "归档报告 2015-05-25-0_1_20150526_128726.eml";
			// String eml = folder + "no auth mail
			// sender不需要验证的邮件发送5_741d382b-8c18-4d4e-8ebd-aafa8c32911e.eml";
			// String eml = folder + "mailtest_with_attachment.eml";
			// String eml = folder + "mailtest.eml";
			String eml = folder + "no auth mail sender不需要验证的邮件发送_8.eml";
			// String eml = "C:\\Temp\\emls\\test2.eml";

			InputStream dataStream = new FileInputStream(new File(eml));

			socket = new Socket(smtpHost, smtpPort);

			lis = new LineInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(socket.getOutputStream());

			if (greeting() != 220) {
				throw new IOException(lastServerResponse);
			}

			int responseCode = this.helo();
			if (responseCode != 250) {
				throw new IOException(lastServerResponse);
			}

			// String username = sender;
			//// String username = "caiszf@163.com";
			// String password = "******";
			// int authResponseCode = auth(username, password);
			// if(authResponseCode != 235) {
			// System.out.println("smpt authentication fail, response code:[" +
			// 235 + "]");
			// }
			// System.out.println("authResponseCode:[" + authResponseCode +
			// "]");

			int smtpCode = mailFrom(sender); // Return-Path
			if (smtpCode != 250) {
				System.out.println("smtp code != 250");
			}
			int size = recipients.size();
			for (int i = 0; i < size; i++) {
				smtpCode = rcptTo(recipients.get(i).toString());
				if (smtpCode != 250 && smtpCode != 251) {
					System.out.println("");
				}
			}

			int rtcode = data(dataStream);
			System.out.println("rtcode:" + rtcode);

			dataStream.close();
			dataStream = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int auth(String userName, String password) throws IOException, Exception {

		if (userName == null) {
			throw new IllegalArgumentException("userName can not be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("password can not be null");
		}

		socket.setSoTimeout(heloTimeout);
		int result = 0;
		// if ((result = ehlo()) != 250) {
		// //this server maybe not support esmtp
		// return result;
		// }
		sendCommand("AUTH LOGIN");
		result = readServerResponse();
		if (result != 334) {
			return result;
		}
		BASE64Encoder encoder = new BASE64Encoder();
		sendCommand(encoder.encode(userName.getBytes()));
		result = readServerResponse();
		if (result != 334) {
			return result;
		}
		sendCommand(encoder.encode(password.getBytes()));
		return readServerResponse();
	}

	/**
	 * read remote mta response
	 */
	private int readServerResponse() throws Exception {
		String responseString = "";
		int rtCode = 0;
		StringBuffer sbuf = new StringBuffer();
		// read the server response line(s) and add them to the buffer
		// that stores the response
		String line = null;
		int loopNum = 0;
		do {
			line = lis.readLine();
			if (line == null) {
				System.out.println("cannot read mta response string");
				throw new Exception("cannot read mta response string");
			}

			System.out.println("SMTP Reply: " + line);

			sbuf.append(line);
			// rfc only define each reply line not exceed 512 character, not
			// define max lines
			// here, we only alow 100 lines for one reply, or we will abort this
			// session.
			if (line.length() > 512 || loopNum++ > 100) {
				// logger it, should never happen
				System.out.println("response string long: " + sbuf.toString());
				throw new Exception("response string too long");
			}
		} while (isNotLastLine(line));
		responseString = sbuf.toString();
		// response format: XXX xxxxxx...
		lastServerResponse = responseString;
		lastServerResponseCode = rtCode = smtpCode(responseString);
		lastAccessTime = System.currentTimeMillis();
		return rtCode;
	}

	/**
	 * analysis smtp receiver response string to figure out smtp code
	 */
	public int smtpCode(String smtpReplyString) throws Exception {
		if (smtpReplyString != null && smtpReplyString.length() >= 3) {
			try {
				return Integer.parseInt(smtpReplyString.substring(0, 3));
			} catch (Exception e) {
				// logger it, never happened, violate rfc821
				throw new Exception("cannot parse smtp code:" + smtpReplyString);
			}
		} else {
			// never go to here, remote mta crazy, drop this connection
			throw new Exception("smtp reply string len< 3:" + smtpReplyString);
		}
	}

	/**
	 * see rfc821 APPENDIX E
	 */
	private boolean isNotLastLine(String line) {
		if (line != null && line.length() > 4) {
			return line.charAt(3) == '-';
		} else {
			return false;
		}
	}

	/**
	 * read greeting message
	 */
	private int greeting() throws IOException, Exception {
		socket.setSoTimeout(greetingTimeout);
		return readServerResponse();
	}

	/**
	 * rfc821 helo response code
	 *
	 * S: 250 E: 500, 501, 504, 421
	 */
	private int helo() throws IOException, Exception {
		socket.setSoTimeout(heloTimeout);
		// String localHostName = "mail.messagesolution.cn";
		// String localHostName = "124.193.147.246";
		String localHostName = "localHostName222";
		sendCommand("HELO " + localHostName);
		return readServerResponse();
	}

	/**
	 * rfc 821 mail response code
	 *
	 * S: 250 F: 552, 451, 452 E: 500, 501, 421
	 */
	private int mailFrom(String sender) throws IOException, Exception {
		socket.setSoTimeout(mailFromTimeout);
		sendCommand("MAIL FROM:" + normalizeAddress(sender));
		return readServerResponse();
	}

	/**
	 * rfc 821 rcpt response code
	 *
	 * S: 250, 251 F: 550, 551, 552, 553, 450, 451, 452 E: 500, 501, 503, 421
	 */
	private int rcptTo(String recipient) throws IOException, Exception {
		socket.setSoTimeout(rcptToTimeout);
		sendCommand("RCPT TO:" + normalizeAddress(recipient));
		return readServerResponse();
	}

	private String normalizeAddress(String address) {
		if (address == null || address.length() == 0)
			return "<>";
		if (address.startsWith("<") && address.endsWith(">")) {
			return address;
		}
		return "<" + address + ">";
	}

	/**
	 * rfc821 data response code
	 *
	 * I: 354 -> data -> S: 250 F: 552, 554, 451, 452 F: 451, 554 E: 500, 501,
	 * 503, 421
	 */
	private int data(InputStream min) throws IOException, Exception {
		sendCommand("DATA");
		int rtCode = readServerResponse();
		// if (rtCode != 354) {
		// if (rtCode < 300) {
		// rtCode = 500;
		// }
		// return rtCode;
		// }
		socket.setSoTimeout(dataTimeout);
		SmtpOutputStream sos = new SmtpOutputStream(bos);
		int readlen = 0;
		while ((readlen = min.read(buff)) >= 0) {
			lastAccessTime = System.currentTimeMillis();
			sos.write(buff, 0, readlen);
		}
		sos.dataComplete();
		return readServerResponse();
	}

	/**
	 * rfc821 section 4.1
	 */
	private void sendCommand(String cmd) throws IOException {
		System.out.println("SMTP Send command: " + cmd);
		bos.write(getBytes(cmd + "\r\n"));
		bos.flush();
	}

	public byte[] getBytes(String s) {
		char[] chars = s.toCharArray();
		int size = chars.length;
		byte[] bytes = new byte[size];

		for (int i = 0; i < size;)
			bytes[i] = (byte) chars[i++];
		return bytes;
	}

}
