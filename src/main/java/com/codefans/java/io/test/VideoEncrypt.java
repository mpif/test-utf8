package com.codefans.java.io.test;

import java.io.File;
import java.io.RandomAccessFile;

public class VideoEncrypt {

	private String path;
	private String sourceFile;
	private String destFile;
	private String sourceName;
	private String destName;

	private RandomAccessFile file;

	public String encodeFileName() {
		String name = null;

		return name;
	}

	public String decodeFileName() {
		String name = null;

		return name;
	}

	public void encode() {
		encodeFileName();

	}

	public void decode() {
		decodeFileName();

	}

	public byte[] encode(byte[] b) {
		byte[] bb = null;

		return bb;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
		System.out.println("sourceFile:[" + sourceFile + "]");
		try {
			path = sourceFile.substring(0, sourceFile.lastIndexOf(File.separator));
			sourceName = sourceFile.substring(sourceFile.lastIndexOf(File.separator));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDestFile() {
		return destFile;
	}

	public void setDestFile(String destFile) {
		this.destFile = destFile;
	}

	public RandomAccessFile getFile() {
		return file;
	}

	public void setFile(RandomAccessFile file) {
		this.file = file;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

}
