package com.codefans.javamail.eeasendmail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

public class RealSendMail {

	private static String tmppath;

	public RealSendMail() {

		File file = new File(tmppath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static void sendmail_custom(MailUtil mu) {
		try {
			new EasSendMail().smtp_custom(mu);
			// if it has attachment,may it's a temp file,delete it.
			if (mu.getAtt() != null) {
				MimeBodyPart bp = (MimeBodyPart) mu.getAtt();
				DataSource ds = bp.getDataHandler().getDataSource();
				if (ds instanceof FileDataSource) {
					FileDataSource ds1 = (FileDataSource) ds;
					File tmpfile = ds1.getFile();
					removeFile(tmpfile.getName());
				}
			}
		} catch (Exception ex) {
			System.err.println("ex44:" + ex.toString());
		}
	}

	// protected String addBreak(String content) {
	// if (StringToolKit.isNotEmpty(content) && !HtmlUtil.containsHtml(content))
	// content = content.replaceAll("\n", "<br>");
	// return content;
	// }
	//
	// public void forwardmail_msg(Message msg, String sender, String receiver)
	// {
	// sendmsg(msg, null, sender, receiver, true);
	// }
	//
	// private String getMsgContent(Message msg, MessageViewer viewer) {
	// GetMessageContent gmc = new GetMessageContent();
	// String content = gmc.getContentStr(viewer, ContentType.TEXT_HTML); //
	// MimeUtility.decodeText(sb.toString()); //get content text
	// if (content == null || content.trim().length() == 0) {
	// content = gmc.getContentStr(viewer, ContentType.TEXT_PLAIN);
	// content = addBreak(content);
	// }
	// return content;
	// }
	//
	// private HashMap<String, BodyPart> setAttachment(Message msg,
	// MessageViewer viewer) throws Exception {
	// RealSendMail rsm = new RealSendMail();
	// MessageSummary summary = msg.getMessageSummary();
	// System.out.println("in realsendmail mailtype is:" +
	// summary.getAttribute("mailtype"));
	// if (MessageFilter.escapeMessage(msg)) {
	// viewer.removeAttachment(viewer.getAttachments());
	// }
	// AttachmentViewer[] attachmentViewers = viewer.getAttachments();
	// HashMap<String, BodyPart> attHm = new HashMap<String, BodyPart>();
	// String filename = "";
	//
	// for (int i = 0; i < attachmentViewers.length; i++) {
	// AttachmentViewer atViewer = attachmentViewers[i];
	// InputStream is = (InputStream) atViewer.getContent();
	// filename = atViewer.getFileName();
	// filename = StringUtils.halfCharToFullChar(filename);
	// writeTo(is, tmppath + filename);
	// javax.mail.BodyPart attachment = rsm.newAttachment(tmppath + filename,
	// filename, "base64");
	// attHm.put(filename, attachment);
	// }
	// return attHm;
	// }
	//
	// public void sendmail_msg(Message msg, SystemInfo info, String sender,
	// String receiver) {
	// sendmsg(msg, info, sender, receiver, false);
	// }
	//
	// public void sendmsg(Message msg, SystemInfo info, String sender, String
	// receiver, boolean ifforward) {
	// //for hosting we get the systeminfo from HostingGlobalSystemInfo
	//
	// HostingGlobalSystemInfo gloInfo =
	// HostingGlobalsystemInfoCache.getHostingGlobalSystemInfo();
	// RelayConnectionInfo mta = gloInfo.getRelayConnectionInfo();
	// Sendmail mail = new Sendmail(mta, sender, receiver);
	// try {
	// MessageViewer viewer = new MessageViewer(msg);
	// String content = getMsgContent(msg, viewer);
	// String subject = msg.getSubject();
	// if (ifforward) {
	// subject = "FW:" + subject;
	// String fromHeader = viewer.getHeader(Headers.FROM);
	// if (StringToolKit.isEmpty(fromHeader)) {
	// fromHeader = viewer.getHeader("from");
	// }
	// String toHeader = viewer.getHeader(Headers.TO);
	// if (StringToolKit.isEmpty(toHeader))
	// toHeader = viewer.getHeader("to");
	// String receivedate = viewer.getReceivedDate().toString();
	//// content = "<br><hr>" +
	//// "<b>From:</b>" + fromHeader + "<br>" +
	//// "<b>Sent:</b>" + receivedate + "<br>" +
	//// "<b>To:</b>" + toHeader + "<br>" +
	//// "<b>Subject:</b>" + subject +
	//// "<br>" + content;
	// }
	// mail.setsubject(subject);
	// List att = new ArrayList();
	// List<String> tmpfiles = new ArrayList<String>();
	// HashMap<String, BodyPart> attHM = setAttachment(msg, viewer);
	// Set keyset = attHM.keySet();
	// Iterator it = keyset.iterator();
	// while (it.hasNext()) {
	// String key = (String) it.next();
	// tmpfiles.add(key);
	// BodyPart part = attHM.get(key);
	// att.add(part);
	// }
	// if (mta.isIfEsmtp())
	// mail.sendmails(receiver, content, att);
	// else
	// mail.sendmails_noauth(receiver, content, att);
	// for (String filename : tmpfiles)
	// removeFile(filename);
	// } catch (Exception ex) {
	// Logger.getInstance().error("ex33:" + ex.toString(), ex);
	// }
	// }

	public static String getStr(InputStream in) {
		try {
			if (in == null)
				return null;
			StringBuffer temp = new StringBuffer();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
			String tempstr = "";

			while ((tempstr = buffer.readLine()) != null) {
				// strsum = strsum + tempstr;
				temp.append(tempstr);

			}
			buffer.close();
			return temp.toString();
		} catch (Exception e) {
			System.out.println("InputStream getStr error");
			return in.toString();
		}
	}

	public static void writeTo(InputStream is, String path) {
		try {
			FileOutputStream out = new FileOutputStream(path);
			byte b[] = new byte[1024 * 8];
			int n = 0;
			while ((n = is.read(b)) != -1) {
				out.write(b, 0, n);
			}
			out.close();
		} catch (Exception e) {

		} finally {

		}

	}

	public static void removeFile(String filename) {
		File f = new File(tmppath, filename);

		if (f.exists()) {
			f.delete();
		}
	}

	public static BodyPart newAttachment(String filename, String dispFilename, String encoding) {
		MimeBodyPart mbp = new MimeBodyPart();
		File f = new File(filename);
		DataSource ds = new FileDataSource(f);
		try {
			mbp.setDataHandler(new DataHandler(ds));
			mbp.setFileName(MimeUtility.encodeText(dispFilename, "UTF-8", null));
		} catch (Exception e) {
			System.out.println("Exception occured when Sendmail newAttachment");

		}
		return mbp;

	}

	public static void main(String[] args) {
		// RealSendMail rsm = new RealSendMail();
		// BodyPart att = rsm.newAttachment("E:\\test\\test.rar", "test.rar",
		// "base64");
		// com.messagesolution.comm.relaymessage.RelayMessage msg = new
		// com.messagesolution.comm.relaymessage.RelayMessage();
		// msg.setSubject("test");
		// msg.setContent("test mail 11111111111111111111111111111111");
		// rsm.sendmail(msg, att, null);

	}

	// public void notRelaySendmail(MessageSummary s, String tmp, SystemInfo
	// info) {
	// if (info == null)
	// info = SystemInfoCache.getSysteminfo(this.companyBean);
	//
	// try {
	// Address[] addresses = s.getRecipient();
	// String rcpt = "";
	// for (int i = 0; i < addresses.length; i++) {
	// rcpt += addresses[i]+",";
	// }
	// Address sender = new Address(s.getSender().getAddress());
	// Address[] reci = new Address[1];
	// reci[0] = new Address(info.getAdminEmailAddress());
	// RelayMessage rm = new RelayMessage();
	// String username = new Admin(companyBean).getUserName(tmp);
	// if (username == null || username.equals("null")) username = "other
	// people";
	// String subject = "Alert:Administrator has read " + username + "'s
	// email.";
	// //String content = "sender:" + s.getSender().getUser() + "-" +
	// s.getSender().getAddress() + ",<br>receiver:" + rcpt + ",<br>subject is:"
	// + s.getSubject() + "<br> time is:" + new Date();
	// String content = "<html>Sender:" + s.getSender() + ",<br>Recipient:" +
	// rcpt + "<br>Subject:" + s.getSubject() + ",<br> Time:" + new Date() +
	// "</html>";
	// SendLog sl = new SendLog();
	// String[] receiver = {info.getAdminEmailAddress()};
	// Message mm = sl.newMessage(s.getSender().getUser(), receiver, null,
	// subject, content, null);
	// sl.sendMessage(mm, sender, reci, true);
	// } catch (Exception e) {
	// Logger.getInstance().info("Exceptin occured when send alert mail " + e);
	//
	// }
	// }
	//
	// public void relayConnection_admin(MessageSummary s, String tmp) {
	// Address[] addresses = s.getRecipient();
	//
	// String rcpt = "";
	// for (int i = 0; i < addresses.length; i++) {
	// rcpt +=""+ addresses[i]+",";
	// }
	//
	// RelayMessage rm = new RelayMessage();
	// String username = new Admin(companyBean).getUserName(tmp);
	// if (username == null || username.equals("null")) username = "other
	// people";
	// rm.setSubject("Alert:Administrator has read " + addresses[0].getAddress()
	// + "'s email.");
	//
	// //rm.setContent("sender:" + s.getSender().getUser() + "-" +
	// s.getSender().getAddress() + ",<br>receiver:" + rcpt + ",<br>subject is:"
	// + s.getSubject() + "<br> time is:" + new Date());
	// rm.setContent("<html>Sender:" + s.getSender() + ",<br>Recipient:" + rcpt
	// + "<br>Subject:" + s.getSubject() + ",<br> Time:" + new Date() +
	// "</html>");
	//
	// new RealSendMail(companyBean).sendmail(rm, null, null);
	// }
	//
	// public void relay_super_sender(MessageSummary s, LoginUser login, String
	// tmp) {
	// Address[] addresses = s.getRecipient();
	// String rcpt = "";
	// for (int i = 0; i < addresses.length; i++) {
	// rcpt +=addresses[i]+",";
	// }
	//
	// logger.info("Receiver:"+rcpt);
	//
	// RelayMessage rm = new RelayMessage();
	// String username = new Admin(companyBean).getUserName(tmp);
	// if (username == null || username.equals("null")) username = "other
	// people";
	// rm.setSubject("Alert:" + login.getUserName() + " has read " + username +
	// "'s email.");
	// rm.setContent("Sender:" + s.getSender().getAddress() + ",<br> Recipient:"
	// + rcpt + "<br> Subject:" + s.getSubject() + ",<br> Time:" + new Date());
	// new RealSendMail(companyBean).sendmail(rm, null, null);
	// }
	//
	// public void relay_super_reci(MessageSummary s, LoginUser login, String
	// tmp, String rcpt1) {
	// Address[] addresses = s.getRecipient();
	// String rcpt = "";
	// for (int i = 0; i < addresses.length; i++) {
	// rcpt += addresses[i]+",";
	// }
	// RelayMessage rm = new RelayMessage();
	// String username = new Admin(companyBean).getUserName(tmp);
	// if (username == null || username.equals("null")) username = "other
	// people";
	// rm.setSubject("Alert:" + login.getUserName() + " has read " + rcpt1 + "'s
	// email.");
	// rm.setContent("Sender:" + s.getSender().getAddress() + ",<br> Recipient:"
	// + rcpt + "<br> Subject:" + s.getSubject() + ",<br> Time:" + new Date());
	// new RealSendMail(companyBean).sendmail(rm, null, null);
	// }
	//
	// public static BodyPart getbodypart(Message msg) {
	// try {
	// InputStream is = msg.getContentStream();
	// MessageViewer viewer = new MessageViewer(msg,
	// MessageViewer.TEXT_OVER_HTML);
	// String filename = viewer.getSubject();
	// writeTo(is, tmppath + filename);
	// return newAttachment(tmppath + filename, filename, "base64");
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	//
	// }
	// }
	//
	// public void send(RelayMessage msg, String address, SystemInfo info,
	// BodyPart att) {
	// if (info == null) {
	// logger.error("SystemInfo is null for " + address);
	// return;
	// }
	//
	// HostingGlobalSystemInfo gloInfo =
	// HostingGlobalsystemInfoCache.getHostingGlobalSystemInfo();
	// RelayConnectionInfo mta = gloInfo.getRelayConnectionInfo();
	//
	// Sendmail mail = new Sendmail(mta, address);
	// String content = msg.getContent();
	// String subject = msg.getSubject();
	// mail.setsubject(subject);
	// try {
	// RelayConnectionInfo relayConnectionInfo = info.getRelayConnectionInfo();
	// if (relayConnectionInfo.isIfRelay())
	// mail.sendmails(address, content, null);
	// else mail.sendmails_noauth(address, content, att);
	// } catch (Exception ex) {
	// logger.error("exception occoured while send mail to " + address, ex);
	// }
	// }
	//
	// public void send(RelayMessage msg, String address, RelayConnectionInfo
	// info, BodyPart att) {
	// Sendmail mail = new Sendmail(info, address);
	// String content = msg.getContent();
	// String subject = msg.getSubject();
	// mail.setsubject(subject);
	// try {
	// if (info.isIfRelay())
	// mail.sendmails(address, content, null);
	// else mail.sendmails_noauth(address, content, att);
	// } catch (Exception ex) {
	// logger.error("exception occoured while send mail to " + address, ex);
	// }
	// }
}
