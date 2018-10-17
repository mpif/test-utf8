package com.codefans.util;

import org.dom4j.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class XmlUtil {

	public static void main(String[] args) {
		XmlUtil util = new XmlUtil();

		// util.logAnalysis();

		util.test();

	}

	public void logAnalysis() {
		String logFile = "C:\\Users\\Administrator\\Desktop\\2014——2\\2014-05-05\\eas.log";
		Scanner sc = new Scanner(this.getResource(logFile));
		String line = "";
		Set set = new HashSet();
		String text = "";
		while (sc.hasNextLine()) {
			line = sc.nextLine().trim();
			if (line.startsWith("<m:FindFolderResponse")) {

				String responseCode = "ErrorNonExistentMailbox";
				String responseCodeXPath = "//m:FindFolderResponse/m:ResponseMessages/m:FindFolderResponseMessage/m:ResponseCode";
				String messageTextXPath = "//m:FindFolderResponse/m:ResponseMessages/m:FindFolderResponseMessage/m:MessageXml/t:Value[@Name='SmtpAddress']";

				text = this.find(line, responseCode, responseCodeXPath, messageTextXPath);
				if (text != null && text.trim().length() > 0) {
					set.add(text);
				}
			}
		}
		print(set);
		print("find total:[" + set.size() + "] records!");
	}

	public InputStream getResource(String filePath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return inputStream;
	}

	public void test() {
		String source = this.getTestXmlString();
		Document document = createDocument(source); // "/root/book[@type='society']"
		// String responseCode = this.getText(document,
		// "//m:FindFolderResponse/m:ResponseMessages/m:FindFolderResponseMessage/m:ResponseCode");
		// if(responseCode.equalsIgnoreCase("ErrorNonExistentMailbox")) {
		// String messageText = this.getText(document,
		// "//m:FindFolderResponse/m:ResponseMessages/m:FindFolderResponseMessage/m:MessageXml/t:Value[@Name='SmtpAddress']");
		// print(responseCode + " || " + messageText);
		// }

		// String responseCode = this.getText(document,
		// "//s:Envelope/s:Body/s:Fault/detail/e:ResponseCode");
		// String responseCode = this.getText(document,
		// "//Envelope/Body/Fault/detail/ResponseCode");
		// String responseCode = this.getSingleNodeText(document,
		// "//ResponseCode");
		String responseCode = this.getNamespaceNodeText(document, "e",
				"http://schemas.microsoft.com/exchange/services/2006/errors", "//e:ResponseCode");
		if (responseCode.equalsIgnoreCase("ErrorNonExistentMailbox")) {
			// String messageText = this.getText(document,
			// "//s:Envelope/s:Body/s:Fault/detail/t:MessageXml/t:Value[@Name='SmtpAddress']");
			// String messageText = this.getText(document,
			// "//Envelope/Body/Fault/detail/MessageXml/Value[@Name='SmtpAddress']");
			// String messageText = this.getSingleNodeText(document, "//Value");
			String messageText = this.getNamespaceNodeText(document, "t",
					"http://schemas.microsoft.com/exchange/services/2006/types", "//t:Value");
			print(responseCode + " || " + messageText);
		}

		// findNodeByIterator(document,"e:ResponseCode");
	}

	public String find(String line, String responseCode, String responseCodeXPath, String messageTextXPath) {
		boolean flag = false;
		String result = "";
		Document document = createDocument(line);
		String resCode = this.getText(document, responseCodeXPath);
		// String resCode = this.getText(document,
		// "//m:FindFolderResponse/m:ResponseMessages/m:FindFolderResponseMessage/m:ResponseCode");
		// if(resCode.equalsIgnoreCase("ErrorNonExistentMailbox")) {
		if (resCode.equalsIgnoreCase(responseCode)) {
			// String messageText = this.getText(document,
			// "//m:FindFolderResponse/m:ResponseMessages/m:FindFolderResponseMessage/m:MessageXml/t:Value[@Name='SmtpAddress']");
			String messageText = this.getText(document, messageTextXPath);
			// print(responseCode + " || " + messageText);
			result = messageText;
		}

		return result;
	}

	public String findNamespaceNodeText(String line, String responseCode, String nameNamespaceKey,
			String nameNamespaceValue, String nameXPath, String textNamespaceKey, String textNamespaceValue,
			String textXPath) {
		String messageText = "";
		Document document = createDocument(line);
		String resCode = this.getNamespaceNodeText(document, nameNamespaceKey, nameNamespaceValue, nameXPath);
		if (resCode.equalsIgnoreCase(responseCode)) {
			messageText = this.getNamespaceNodeText(document, textNamespaceKey, textNamespaceValue, textXPath);
		}
		return messageText;
	}

	public Document createDocument(String source) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(source);
		} catch (DocumentException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return document;
	}

	public String getText(Document document, String xPath) {
		Element selectedNode = (Element) document.selectSingleNode(xPath);
		return selectedNode.getText();
	}

	public String getNamespaceNodeText(Document document, String namespaceKey, String namespaceValue, String xPath) {

		HashMap<String, String> xmlMap = new HashMap<String, String>();
		// xmlMap.put("tns","http://www.99bill.com/schema/fo/settlement");
		xmlMap.put(namespaceKey, namespaceValue);

		// XPath xpath=document.createXPath("//tns:status"); //要获取哪个节点，改这里就可以了
		XPath xpath = document.createXPath(xPath); // 要获取哪个节点，改这里就可以了
		xpath.setNamespaceURIs(xmlMap);
		Element element = (Element) xpath.selectSingleNode(document);

		return element.getText();
	}

	public String getTestXmlString() {
		StringBuffer sb = new StringBuffer();

		// sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		// sb.append("<m:FindFolderResponse
		// xmlns:m=\"http://schemas.microsoft.com/exchange/services/2006/messages\"
		// xmlns:t=\"http://schemas.microsoft.com/exchange/services/2006/types\">");
		// sb.append("<m:ResponseMessages>");
		// sb.append("<m:FindFolderResponseMessage ResponseClass=\"Error\">");
		// sb.append("<m:MessageText>SMTP 地址没有与其关联的邮箱。</m:MessageText>");
		// sb.append("<m:ResponseCode>ErrorNonExistentMailbox</m:ResponseCode>");
		// sb.append("<m:DescriptiveLinkKey>0</m:DescriptiveLinkKey>");
		// sb.append("<m:MessageXml>");
		// sb.append("<t:Value
		// Name=\"SmtpAddress\">rongwang.sun@cimc.com</t:Value>");
		// sb.append("</m:MessageXml>");
		// sb.append("</m:FindFolderResponseMessage>");
		// sb.append("</m:ResponseMessages>");
		// sb.append("</m:FindFolderResponse>");

		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		sb.append("    <s:Body>");
		sb.append("        <s:Fault>");
		sb.append(
				"            <faultcode xmlns:a=\"http://schemas.microsoft.com/exchange/services/2006/types\">a:ErrorNonExistentMailbox</faultcode>");
		sb.append("            <faultstring xml:lang=\"zh-CN\">SMTP 地址没有与其关联的邮箱。</faultstring>");
		sb.append("            <detail>");
		sb.append(
				"                <e:ResponseCode xmlns:e=\"http://schemas.microsoft.com/exchange/services/2006/errors\">ErrorNonExistentMailbox</e:ResponseCode>");
		sb.append(
				"                <e:Message xmlns:e=\"http://schemas.microsoft.com/exchange/services/2006/errors\">SMTP 地址没有与其关联的邮箱。</e:Message>");
		sb.append(
				"                <t:MessageXml xmlns:t=\"http://schemas.microsoft.com/exchange/services/2006/types\">");
		sb.append("                    <t:Value Name=\"SmtpAddress\">wei.zuo@cimc-raffles.com</t:Value>");
		sb.append("                </t:MessageXml>");
		sb.append("            </detail>");
		sb.append("        </s:Fault>");
		sb.append("    </s:Body>");
		sb.append("</s:Envelope>");

		return sb.toString();
	}

	public void print(Object obj) {
		System.out.println(obj);
	}

	public void print(Set set) {
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			print(iter.next());
		}
	}

}
