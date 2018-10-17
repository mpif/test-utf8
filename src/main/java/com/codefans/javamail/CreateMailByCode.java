package com.codefans.javamail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*
 * @Author: Sean
 * @Time: 2015-06-12 11:33:04
 */
public class CreateMailByCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreateMailByCode cmbc = new CreateMailByCode();
		cmbc.create();
	}

	public void create() {
		createTextMsg();
		createMultiPartMsg();
	}

	public void createTextMsg() {
		MailMessage msg = new MailMessage();
		msg.setSubject("test mail create by code_from_caishengzhi_to_reci@eea.com");
		msg.setFrom("caishengzhi@messagesolution.cn");
		msg.setTo("reci@eea.mail");
		msg.setContentType("multipart/mixed");

		OutputStream os = null;
		// String f = "C:\\Temp\\mailtest_with_attachment.eml";
		String f = "C:\\Temp\\mailtest.eml";
		try {
			os = new FileOutputStream(new File(f));
			msg.writeTextMail(os);
			// msg.writeMailWithAttach(os);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
					os = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void createMultiPartMsg() {

	}

}

class MailMessage {

	private String subject;
	private String from;
	private String to;
	private List<String> recipients;
	private byte[] content;
	private InputStream contentStream;
	private List<InputStream> attachStream;
	private Date date;
	private final static String MIME_Version = "1.0";
	private String contentType;
	private String boundary = "----=_Part_6_1254691612.1433749721221";
	private String boundary_attach = "----=_Part_2_1697740411.1433067191885";
	private String Content_Transfer_Encoding;
	private String Content_Disposition;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public InputStream getContentStream() {
		return contentStream;
	}

	public void setContentStream(InputStream contentStream) {
		this.contentStream = contentStream;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getBoundary() {
		return boundary;
	}

	public void setBoundary(String boundary) {
		this.boundary = boundary;
	}

	public String getContent_Transfer_Encoding() {
		return Content_Transfer_Encoding;
	}

	public void setContent_Transfer_Encoding(String content_Transfer_Encoding) {
		Content_Transfer_Encoding = content_Transfer_Encoding;
	}

	public String getContent_Disposition() {
		return Content_Disposition;
	}

	public void setContent_Disposition(String content_Disposition) {
		Content_Disposition = content_Disposition;
	}

	public static String getMimeVersion() {
		return MIME_Version;
	}

	public List<InputStream> getAttachStream() {
		return attachStream;
	}

	public void setAttachStream(List<InputStream> attachStream) {
		this.attachStream = attachStream;
	}

	public void writeTextMail(OutputStream os) throws IOException {
		os.write(getBytes("Date: " + new Date().toString()));
		os.write(getBytes("\r\n"));
		os.write(getBytes("From: " + from));
		os.write(getBytes("\r\n"));
		os.write(getBytes("To: " + to));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Subject: " + subject));
		os.write(getBytes("\r\n"));
		os.write(getBytes("MIME-Version: " + MIME_Version));
		os.write(getBytes("\r\n"));

		os.write(getBytes("Content-Type: " + contentType));
		os.write(getBytes(";\r\n\t"));
		os.write(getBytes("boundary=\""));
		os.write(getBytes(boundary));
		os.write(getBytes("\"\r\n"));

		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));

		os.write(getBytes("--" + boundary));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Type: text/html; charset=\"utf-8\""));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Transfer-Encoding: base64"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("6K6+572u6YKu566x5YaF5a65XzQ="));
		os.write(getBytes("\r\n"));

		os.write(getBytes("--" + boundary + "--"));
		os.write(getBytes("\r\n"));

	}

	public void writeMailWithAttach(OutputStream os) throws IOException {
		os.write(getBytes("Date: " + new Date().toString()));
		os.write(getBytes("\r\n"));
		os.write(getBytes("From: " + from));
		os.write(getBytes("\r\n"));
		os.write(getBytes("To: " + to));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Subject: " + subject));
		os.write(getBytes("\r\n"));
		os.write(getBytes("MIME-Version: " + MIME_Version));
		os.write(getBytes("\r\n"));

		os.write(getBytes("Content-Type: " + contentType));
		os.write(getBytes(";\r\n\t"));
		os.write(getBytes("boundary=\""));
		os.write(getBytes(boundary));
		os.write(getBytes("\"\r\n"));

		os.write(getBytes("\r\n"));

		os.write(getBytes("--" + boundary));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Type: multipart/alternative"));
		os.write(getBytes(";\r\n\t"));
		os.write(getBytes("boundary=\""));
		os.write(getBytes(boundary_attach));
		os.write(getBytes("\"\r\n"));

		os.write(getBytes("\r\n"));

		os.write(getBytes("--" + boundary_attach));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Type: text/plain; charset=\"gb18030\""));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Transfer-Encoding: base64"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("tavKx8Tjw7vT0A=="));
		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));

		os.write(getBytes("--" + boundary_attach));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Type: text/html; charset=\"gb18030\""));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Transfer-Encoding: base64"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("PGRpdj61q8rHxOPDu9PQPC9kaXY+"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));

		os.write(getBytes("--" + boundary_attach + "--"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));

		os.write(getBytes("--" + boundary));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Type: application/octet-stream"));
		os.write(getBytes(";\r\n\t"));
		os.write(getBytes("charset=\"gb18030\""));
		os.write(getBytes(";\r\n\t"));
		os.write(getBytes("name=\"=?gb18030?B?tavKx8Tjw7vT0C5qcGc=?=\""));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Disposition: attachment; filename=\""));
		os.write(getBytes("=?gb18030?B?tavKx8Tjw7vT0C5qcGc=?=\""));
		os.write(getBytes("\r\n"));
		os.write(getBytes("Content-Transfer-Encoding: base64"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));

		String attachContent = "PGh0bWw+PGhlYWQ+CjxtZXRhIGh0dHAtZXF1aXY9IkNvbnRlbnQtVHlwZSIgY29udGVudD0idGV4"
				+ "dC9odG1sOyBjaGFyc2V0PXV0Zi04Ij4KPC9oZWFkPgo8Ym9keT4K5b2S5qGj5oql5ZGK5pe26Ze0"
				+ "77yaIDIwMTUtMDUtMjUKPHA+PC9wPgo8ZGl2PuW9k+WkqeWNs+aXtuW9kuaho+mCruS7tuaVsO+8"
				+ "miAxNjUxPC9kaXY+CjxkaXY+5b2T5aSp5a6a5pe25b2S5qGj6YKu5Lu25pWw77yaIDA8L2Rpdj4K"
				+ "PGRpdj7lvZPlpKnliYrlh4/pgq7ku7bmlbDvvJogMDwvZGl2Pgo8ZGl2PuW9k+WkqeaQnOe0ouas"
				+ "oeaVsO+8miAwPC9kaXY+CjxkaXY+5oC75a2Y5YKo5L2/55So77yaIDI5Ni4zMCBHQjwvZGl2Pgo8"
				+ "ZGl2PuWPr+eUqOWtmOWCqOepuumXtO+8miAxNjk1LjM1IEdCPC9kaXY+CjxwPjwvcD4KPGRpdj7l"
				+ "n5/lkI3vvJogbmljZW5lcmd5LmNvbTwvZGl2Pgo8ZGl2PuW9k+Wkqea3u+WKoOeahOeUqOaIt+aV"
				+ "sO+8miAwPC9kaXY+CjxkaXY+5b2T5aSp5Yig6Zmk55qE55So5oi35pWw77yaIDA8L2Rpdj4KPHA+"
				+ "PC9wPgo8ZGl2PuiwouiwojwvZGl2Pgo8ZGl2Pk1FU1NBR0VTT0xVVElPTjwvZGl2Pgo8ZGl2Pjxh"
				+ "IGhyZWY9Imh0dHA6Ly93d3cubWVzc2FnZXNvbHV0aW9uLmNvbS8iPmh0dHA6Ly93d3cubWVzc2Fn"
				+ "ZXNvbHV0aW9uLmNvbS88L2E+PGE+PC9kaXY+CjwvYT4KPHAgc3R5bGU9Im1hcmdpbjowaW47IG1h"
				+ "cmdpbi1ib3R0b206LjAwMDFwdDsgZm9udC1zaXplOjlwdDsgZm9udC1mYW1pbHk6Q2FsaWJyaSxz"
				+ "YW5zLXNlcmlmIj4KPHNwYW4gc3R5bGU9ImNvbG9yOiM4MDgyODUiPlRoZSBjb250ZW50cyBvZiB0"
				+ "aGlzIG1lc3NhZ2UgYXJlIGNvbmZpZGVudGlhbCBhbmQgaW50ZW5kZWQgZm9yIHRoZSByZWNpcGll"
				+ "bnQgb25seS4gU2hvdWxkIHlvdSByZWNlaXZlIHRoZSBtZXNzYWdlIGluIGVycm9yLCB3ZSB3b3Vs"
				+ "ZCBiZSBncmF0ZWZ1bCBpZiB5b3Ugd291bGQgZGVzdHJveSBpdCBhbmQgaXRzIGF0dGFjaG1lbnRz"
				+ "IGFuZCBhZHZpc2UKPGEgaHJlZj0ibWFpbHRvOmdlbmVyYWxfb2ZmaWNlQG5pY2VuZXJneS5jb20i"
				+ "PmdlbmVyYWxfb2ZmaWNlQG5pY2VuZXJneS5jb208L2E+IHRoYXQgeW91IGhhdmUgZG9uZSBzby48"
				+ "L3NwYW4+PC9wPgo8L2JvZHk+CjwvaHRtbD4K";
		// os.write(getBytes(attachContent));

		writeContent(os);

		// os.write(getBytes("\r\n"));
		//
		// os.write(getBytes("--" + boundary_attach + "--"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));

		os.write(getBytes("--" + boundary + "--"));
		os.write(getBytes("\r\n"));
		os.write(getBytes("\r\n"));

	}

	public void writeContent(OutputStream os) {
		String in = "C:\\Temp\\b.jpeg";
		// String in = "C:\\Temp\\b.jpeg.jpeg";
		String out = "C:\\Temp\\bb.jpeg";
		byte[] bytes = new byte[1024];
		int count = 0;
		InputStream is = null;
		try {
			is = new FileInputStream(new File(in));

			// os = new FileOutputStream(new File(out));

			BASE64Encoder encoder = new BASE64Encoder();
			encoder.encode(is, os);

			// BASE64Decoder decoder = new BASE64Decoder();
			// decoder.decodeBuffer(is, os);

			// while((count = is.read(bytes)) != -1) {
			// os.write(bytes, 0, count);
			// }
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public final static byte[] getBytes(String s) {
		char[] chars = s.toCharArray();
		int size = chars.length;
		byte[] bytes = new byte[size];

		for (int i = 0; i < size;)
			bytes[i] = (byte) chars[i++];
		return bytes;
	}

}
