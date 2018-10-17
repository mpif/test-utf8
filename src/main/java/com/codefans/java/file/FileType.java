package com.codefans.java.file;

import java.io.File;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

public class FileType {

	/**
	 * @param args
	 * @throws MagicException
	 * @throws MagicMatchNotFoundException
	 * @throws MagicParseException
	 */
	public static void main(String[] args) {
		printType("D:\\csz\\资料\\24种设计模式介绍与6大设计原则.pdf");
		// printType("D:\\csz\\资料\\《Agile Java》高清中文版.rar");
		printType("D:\\csz\\资料\\2012-1-9.docx");
		printType("D:\\csz\\资料\\SNMP-MIB.doc");
		// printType("D:\\csz\\资料\\界面色彩&色系.chm");
		printType("D:\\csz\\资料\\错误800.PNG");
	}

	public static void printType(String file) {
		File f = new File(file);
		Magic parser = new Magic();
		MagicMatch match = null;
		try {
			match = parser.getMagicMatch(f, false);
		} catch (MagicParseException e) {
			e.printStackTrace();
		} catch (MagicMatchNotFoundException e) {
			e.printStackTrace();
		} catch (MagicException e) {
			e.printStackTrace();
		}
		System.out.println(match.getMimeType());
	}

}
