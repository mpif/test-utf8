package com.codefans.xml.sax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ItemAttachmentSAXHandle extends DefaultHandler {

	private EWSItemAttachment ewsAttachment = null;

	public ItemAttachmentSAXHandle(EWSItemAttachment itemAttachment) {
		this.ewsAttachment = itemAttachment;
	}

	private Stack<String> tagsStack = new Stack<String>();

	private boolean isDebugPrint = false;

	PrintWriter printWriter = null;

	// private static final String URI_TYPE =
	// "http://schemas.microsoft.com/exchange/services/2006/types";
	// private static final String MIME_CONTENT = "MimeContent";
	// private static final String ITEM_MIME_CONTENT = URI_TYPE + ":" +
	// MIME_CONTENT;
	// private static final String File_Content = "t:Content";
	private static final String File_Content = "t:MimeContent";
	private static final String CHARACTER_SET = "CharacterSet";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		try {

			printWriter = new PrintWriter(new BufferedWriter(new FileWriter(ewsAttachment.getMimeContentFile())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		// process start element
		if (File_Content.equals(name)) {
			tagsStack.push(name);
			int len = attributes.getLength();
			for (int i = 0; i < len; i++) {
				if (CHARACTER_SET.equals(attributes.getQName(i))) {
					ewsAttachment.setCharacterSet(attributes.getValue(i));
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// print character data content of element
		String str = new String(ch, start, length);

		// process character data
		if (!tagsStack.empty()) {
			String tag = tagsStack.peek();
			if (File_Content.equals(tag)) {
				printWriter.write(str);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		tagsStack.clear();
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		// process end element
		if (!tagsStack.empty()) {
			String tag = tagsStack.pop();
			// item attachment
			if (File_Content.equals(tag)) {
				if (printWriter != null) {
					printWriter.flush();
					printWriter.close();
					printWriter = null;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xml.sax.helpers.DefaultHandler#endPrefixMapping(java.lang.String)
	 */
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		super.endPrefixMapping(prefix);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
	 */
	@Override
	public void error(SAXParseException e) throws SAXException {
		debugPrintln("[Error]" + getLocationString(e) + ":" + e.getMessage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#fatalError(org.xml.sax.
	 * SAXParseException)
	 */
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		debugPrintln("[Fatal Error]" + getLocationString(e) + ":" + e.getMessage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#ignorableWhitespace(char[], int,
	 * int)
	 */
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		super.ignorableWhitespace(ch, start, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#notationDecl(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void notationDecl(String name, String publicId, String systemId) throws SAXException {
		super.notationDecl(name, publicId, systemId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#processingInstruction(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// print process instruction in the document
		debugPrintln("<?" + target + " " + data + "?");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#resolveEntity(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
		return super.resolveEntity(publicId, systemId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#setDocumentLocator(org.xml.sax.
	 * Locator)
	 */
	@Override
	public void setDocumentLocator(Locator locator) {
		super.setDocumentLocator(locator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#skippedEntity(java.lang.String)
	 */
	@Override
	public void skippedEntity(String name) throws SAXException {
		super.skippedEntity(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xml.sax.helpers.DefaultHandler#startPrefixMapping(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		super.startPrefixMapping(prefix, uri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xml.sax.helpers.DefaultHandler#unparsedEntityDecl(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName)
			throws SAXException {
		super.unparsedEntityDecl(name, publicId, systemId, notationName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
	 */
	@Override
	public void warning(SAXParseException e) throws SAXException {
		debugPrintln("[Warning]" + getLocationString(e) + ":" + e.getMessage());
	}

	public void debugPrint(String str) {
		if (isDebugPrint) {
			// Logger.getInstance().debug(str);
		}
	}

	public void debugPrintln(String str) {
		if (isDebugPrint) {
			// Logger.getInstance().debug(str);
		}
	}

	private String getLocationString(SAXParseException ex) {
		StringBuffer str = new StringBuffer();

		String publicId = ex.getPublicId();
		if (publicId != null) {
			str.append(publicId);
			str.append(" ");
		}

		String systemId = ex.getSystemId();
		if (systemId != null) {
			str.append(systemId);
			str.append(":");
		}

		str.append(ex.getLineNumber());
		str.append(":");
		str.append(ex.getColumnNumber());

		return str.toString();
	}

}
