package com.codefans.xml.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class LargeXmlUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LargeXmlUtil util = new LargeXmlUtil();

		// String sf = "C:\\Documents and
		// Settings\\workbench\\桌面\\GetAttachmentResponse.txt";
		// String df = "C:\\Documents and
		// Settings\\workbench\\桌面\\GetAttachmentResponse_MimeContent.txt";

		// String sf = "C:\\Documents and
		// Settings\\workbench\\桌面\\getLargeAttachment-a8034de6-8e1c-4c2e-aee6-18c2789c5c4261119.eea";
		String sf = "D:\\git\\test-utf8\\src\\com\\messagesolution\\xml\\sax\\GetAttachmentResponse.xml";
		String df = "C:\\Users\\Administrator\\Desktop\\GetAttachmentResponse_Content.txt";

		File srcFile = new File(sf);
		File destFile = new File(df);

		util.parseLargeFileAttachment(srcFile, destFile);
		// util.parseLargeItemAttachment(srcFile, destFile);
	}

	public void parseLargeFileAttachment(File srcFile, File desFile) {
		if (srcFile == null || desFile == null) {
			return;
		}
		System.setProperty("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		FileAttachmentSAXHandle handle = new FileAttachmentSAXHandle(desFile);

		try {
			parser = factory.newSAXParser();
			parser.parse(srcFile, handle);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return;
	}

	public EWSItemAttachment parseLargeItemAttachment(File srcFile, File desFile) {
		if (srcFile == null || desFile == null) {
			return null;
		}
		System.setProperty("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		EWSItemAttachment itemAttachment = new EWSItemAttachment();
		itemAttachment.setMimeContentFile(desFile);
		ItemAttachmentSAXHandle handle = new ItemAttachmentSAXHandle(itemAttachment);

		try {
			parser = factory.newSAXParser();
			parser.parse(srcFile, handle);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return itemAttachment;
	}

}
