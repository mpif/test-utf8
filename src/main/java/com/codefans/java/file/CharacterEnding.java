package com.codefans.java.file;

import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;

import java.io.File;
import java.nio.charset.Charset;

public class CharacterEnding {

	public static String getFileCharacterEnding(String filePath) {

		File file = new File(filePath);

		return getFileCharacterEnding(file);
	}

	/**
	 * Try to get file character ending.
	 * </p>
	 * <strong>Warning: </strong>use cpDetector to detect file's encoding.
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileCharacterEnding(File file) {

		String fileCharacterEnding = "UTF-8";

		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(JChardetFacade.getInstance());

		Charset charset = null;

		// File f = new File(filePath);

		try {
			charset = detector.detectCodepage(file.toURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (charset != null) {
			fileCharacterEnding = charset.name();
		}

		return fileCharacterEnding;
	}

	private static String path = "D:\\dev\\test-utf8\\src\\com\\messagesolution\\java\\file\\";

	public static void main(String[] args) {
		String fileName = "default.txt";
		// String fileName = "ANSI.txt";
		// String fileName = "UTF-8.txt";
		String charset = getFileCharacterEnding(path + fileName);
		System.out.println(charset);

	}
}
