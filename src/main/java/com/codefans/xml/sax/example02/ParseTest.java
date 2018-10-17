package com.codefans.xml.sax.example02;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();

			InputStream xmlStream = ParseTest.class.getResourceAsStream("GetItem_Response_XML.xml");
			HTMLBodySAXHandle handler = new HTMLBodySAXHandle();
			parser.parse(xmlStream, handler);
			System.out.println(handler.getHtmlBody());

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
