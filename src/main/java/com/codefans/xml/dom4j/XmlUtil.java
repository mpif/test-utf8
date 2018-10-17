package com.codefans.xml.dom4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.util.EncodingUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XmlUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Document doc = createNewDocument(getStr());
	}

	private static String getStr() {
		InputStream is = XmlUtil.class.getResourceAsStream("FW.eml");
		ByteArrayOutputStream outstream = new ByteArrayOutputStream(4096);

		byte[] buffer = new byte[4096];
		int len;
		byte[] rawdata = null;
		try {
			while ((len = is.read(buffer)) > 0) {
				outstream.write(buffer, 0, len);
			}
			rawdata = outstream.toByteArray();

			outstream.close();

			if (rawdata != null) {
				return new String(EncodingUtil.getString(rawdata, "UTF-8"));
			} else {
				return null;
			}

		} catch (IOException ex) {
			System.out.println("[Attachment] Exception in readResponseBodyAsString method " + ex);
			ex.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException ex) {
			}
		}
		return null;
	}

	private static Document createNewDocument(String xmlContent) {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(new ByteArrayInputStream(xmlContent.getBytes("UTF-8")));
		} catch (DocumentException e) {
			e.printStackTrace();
			p("create xml document error" + e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			p("create xml document error, UnsupportedEncodingException" + e);
		}
		return document;
	}

	public static void p(Object o) {
		System.out.println(o);
	}
}
