package com.codefans.java.io.eea;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import com.codefans.util.MD5Util;

public class ResettableFileInputStream extends InputStream {

	RandomAccessFile raFile;
	String filepath;
	long mark;

	public ResettableFileInputStream(File file) throws IOException {
		if (!file.exists())
			throw new IOException("file not exists");
		this.raFile = new RandomAccessFile(file, "r");
		this.filepath = file.getCanonicalPath();
	}

	public int read() throws IOException {
		return raFile.read();
	}

	public synchronized void mark(int readlimit) {
		try {
			mark = raFile.getFilePointer();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean markSupported() {
		return true;
	}

	public int read(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		return raFile.read(b, off, len);
	}

	public synchronized void reset() throws IOException {
		raFile.seek(this.mark);
	}

	public int available() throws IOException {
		// TODO Auto-generated method stub
		return (int) (raFile.length() - raFile.getFilePointer());
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub
		raFile.close();
	}

	public long skip(long n) throws IOException {
		// TODO Auto-generated method stub
		long s = raFile.getFilePointer() + n;
		if (raFile.getFilePointer() + n > raFile.length())
			n = raFile.length() - raFile.getFilePointer();
		raFile.seek(raFile.getFilePointer() + n);
		return n;
	}

	public String getFilepath() {
		return filepath;
	}

	public static void main(String[] args) {
		try {
			File file = new File("C:/axis2-1.6.2-bin.zip");
			ResettableFileInputStream rfis = new ResettableFileInputStream(file);
			rfis.mark(0);
			MD5Util md5Util = new MD5Util();
			String hashcodeFirst = md5Util.getHashCodeStr(rfis);
			rfis.reset(); // reset file pointer to beginning of the file
			String hashcodeSecond = md5Util.getHashCodeStr(rfis);
			System.out.println(hashcodeFirst);
			System.out.println(hashcodeSecond);
			System.out.println(hashcodeFirst.equals(hashcodeSecond));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
