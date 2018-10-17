package com.codefans.java.io.eea;

import java.io.*;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-3-18 Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
public class MessageMetaInfoTest {

	private MessageMetaInfo messageMetaInfo;
	private String dataFile = "";

	public MessageMetaInfoTest() {
		this.messageMetaInfo = new MessageMetaInfo();
		dataFile = "D://tmp//data.db";
	}

	public static void main(String[] args) {
		MessageMetaInfoTest t = new MessageMetaInfoTest();
		// t.write();
		t.read();
	}

	public void write() {
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(dataFile));
			this.messageMetaInfo.writeSelf(os);
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
					os = null;
				}
			} catch (IOException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
		}
	}

	public void read() {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(dataFile));
			this.messageMetaInfo.readSelf(is);
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
		}
	}
}
