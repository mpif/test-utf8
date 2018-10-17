package com.codefans.xml.sax;

import java.io.File;

/***
 * EWS Attachment Type
 * 
 * @author Administrator
 * 
 */
public class EWSItemAttachment {

	private File mimeContentFile;

	private String characterSet;

	public File getMimeContentFile() {
		return mimeContentFile;
	}

	public void setMimeContentFile(File mimeContentFile) {
		this.mimeContentFile = mimeContentFile;
	}

	public String getCharacterSet() {
		return characterSet;
	}

	public void setCharacterSet(String characterSet) {
		this.characterSet = characterSet;
	}

}
