package com.codefans.javamail.multisender;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Configuration {

	private static Document doc;

	public static List<String> emailAddresses = new ArrayList<String>();
	public static int mailNums;

	// smtp info
	public static String smtpAddress;
	public static String adUser;
	public static String adPass;
	public static String sender;
	public static String subject;
	public static String content;
	public static String attachment;

	public Configuration() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// doc = builder.parse( Configuration.class.getResourceAsStream(
			// "emails.xml" ) );
			doc = builder.parse(this.getClass().getResourceAsStream("emails.xml"));

			iteratorEmailTag("email");
			iteratorPropTag("prop");

			// mailNums = getNumberContent("mailNums");

			// smtpAddress = getTextContent("smtpAddress");
			// adUser = getTextContent("adUser");
			// adPass = getTextContent("adPass");
			// sender = getTextContent("sender");
			// subject = getTextContent("subject");
			// content = getTextContent("content");
			// attachment = getTextContent("attachment");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void iteratorTagName(String tagName) {
		if ("email".equalsIgnoreCase(tagName)) {
			iteratorEmailTag(tagName);
		} else if ("prop".equalsIgnoreCase(tagName)) {
			iteratorPropTag(tagName);
		}
	}

	public static void iteratorEmailTag(String tagName) {
		NodeList emailNodeList = doc.getElementsByTagName(tagName);
		String email = "";
		Node node = null;

		for (int i = 0; i < emailNodeList.getLength(); i++) {
			node = emailNodeList.item(i);
			email = node.getTextContent();
			if (parseAttributes(node)) {
				emailAddresses.add(email);
			}
		}
	}

	public static void iteratorPropTag(String tagName) {
		NodeList emailNodeList = doc.getElementsByTagName(tagName);
		String textContent = "";
		Node node = null;
		NamedNodeMap map = null;
		Node idNode = null;
		String idValue = null;

		for (int i = 0; i < emailNodeList.getLength(); i++) {
			node = emailNodeList.item(i);
			textContent = node.getTextContent();
			map = node.getAttributes();
			idNode = map.getNamedItem("id");
			idValue = idNode.getTextContent();
			if ("mailNums".equalsIgnoreCase(idValue)) {
				mailNums = Integer.parseInt(textContent);
			} else if ("smtpAddress".equalsIgnoreCase(idValue)) {
				smtpAddress = textContent;
			} else if ("adUser".equalsIgnoreCase(idValue)) {
				adUser = textContent;
			} else if ("adPass".equalsIgnoreCase(idValue)) {
				adPass = textContent;
			} else if ("sender".equalsIgnoreCase(idValue)) {
				sender = textContent;
			} else if ("subject".equalsIgnoreCase(idValue)) {
				subject = textContent;
			} else if ("content".equalsIgnoreCase(idValue)) {
				content = textContent;
			} else if ("attachment".equalsIgnoreCase(idValue)) {
				attachment = textContent;
			}
		}
	}

	public static String getTextContent(String id) {
		String textContent = "";
		Element elm = doc.getElementById(id);
		textContent = elm.getTextContent();
		return textContent;
	}

	public static int getNumberContent(String id) {
		Element elm = doc.getElementById(id);
		String mailNumsStr = elm.getTextContent();
		if (isNotEmpty(mailNumsStr)) {
			mailNums = Integer.parseInt(mailNumsStr);
		}
		return mailNums;
	}

	public static boolean isEmpty(String str) {
		boolean flag = false;
		if (str == null || str.trim().length() <= 0) {
			flag = true;
		}
		return flag;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public List<String> getEmailAddresses() {
		return emailAddresses;
	}

	public static boolean parseAttributes(Node node) {
		boolean flag = false;
		NamedNodeMap map = node.getAttributes();
		Node n = map.getNamedItem("enable");
		String attrValue = n.getTextContent();
		// System.out.println("attrValue:" + attrValue);
		if (attrValue != null && attrValue.trim().length() > 0) {
			if (attrValue.equalsIgnoreCase("true") || attrValue.equalsIgnoreCase("on")
					|| attrValue.equalsIgnoreCase("open") || attrValue.equalsIgnoreCase("1")) {
				flag = true;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		Configuration config = new Configuration();
		List<String> list = config.getEmailAddresses();
		String str = "";
		System.out.println("list.size():" + list.size());
		for (int i = 0; i < list.size(); i++) {
			str = list.get(i);
			System.out.println(str);
		}
	}

	public int getMailNums() {
		return mailNums;
	}
}
