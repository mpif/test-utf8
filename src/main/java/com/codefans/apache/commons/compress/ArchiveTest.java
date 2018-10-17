package com.codefans.apache.commons.compress;

import java.io.File;

import org.apache.commons.compress.Archive;
import org.apache.commons.compress.ArchiveException;
import org.apache.commons.compress.ArchiverFactory;

public class ArchiveTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArchiveTest archTest = new ArchiveTest();
		archTest.unpack();
	}

	public void unpack() {
		// String zip = "D:\\dev\\Hosting-RB-1.2.x\\dist\\lib\\eas.jar";
		// String dest = "D:\\dev\\Hosting-RB-1.2.x\\dist\\lib";
		String zip = "C:\\attachments\\code2.zip";
		// String zip = "C:\\attachments\\code2.rar";
		String dest = "C:\\attachments\\code2";
		File zipFile = new File(zip);
		File destDirectory = new File(dest);
		this.unpack(zipFile, destDirectory);
	}

	/**
	 * Unpack to dest dir. only can unpack zip files, not rar and jar.
	 * 
	 * @param zipfile
	 *            the zipfile
	 * @param destDirectory
	 *            the dest directory
	 */
	private void unpack(File zipfile, File destDirectory) {
		try {
			Archive archiver = ArchiverFactory.getInstance(zipfile);
			archiver.unpack(destDirectory);
		} catch (ArchiveException e) {
			e.printStackTrace();
		}
	}

}
