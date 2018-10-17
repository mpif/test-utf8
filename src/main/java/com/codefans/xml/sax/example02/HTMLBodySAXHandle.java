package com.codefans.xml.sax.example02;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HTMLBodySAXHandle extends DefaultHandler {

	private StringBuffer htmlBody;
	private String preTag = null;// 作用是记录解析时的上一个节点名称

	@Override
	public void startDocument() throws SAXException {
		htmlBody = new StringBuffer();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("t:Body".equals(qName)) {
			// System.out.println(qName);
		}
		preTag = qName;// 将正在解析的节点名称赋给preTag
		// super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// if("t:Body".equals(qName)){
		// htmlBody.append(bodyText);
		// bodyText = null;
		// }
		preTag = null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (preTag != null) {
			String content = new String(ch, start, length);
			if ("t:Body".equals(preTag)) {
				htmlBody.append(content);
			}
		}
	}

	public String getHtmlBody() {
		return htmlBody.toString();
	}

}
