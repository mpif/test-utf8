package com.codefans.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import com.codefans.java.io.eea.ResettableFileInputStream;

public class MD5Util {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MD5Util util = new MD5Util();
		util.compute();
	}

	public void compute() {
		try {

			String original = "";
			String download = "";
			// compute(original, download);

			// File file = new File("C:/axis2-1.6.2-bin.zip");
			// String md5 = "0972c1ec4f1cd10931c8de5e62a7f655";
			// md5Equals(file, md5);

			File file = new File("D:/tmp/logistics_address.xls");
			ResettableFileInputStream rfis = new ResettableFileInputStream(file);
			rfis.mark(0);
			String hashcodeFirst = this.getHashCodeStr(rfis);
			rfis.reset();
			String hashcodeSecond = this.getHashCodeStr(rfis);
			System.out.println(hashcodeFirst);
			System.out.println(hashcodeSecond);
			System.out.println(hashcodeFirst.equals(hashcodeSecond));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean md5Equals(File file, String md5) throws FileNotFoundException {

		Assert.assertFileExists(file, "file is null or file do not exists.");

		boolean equals = false;
		InputStream is = new FileInputStream(file);
		String hashCodeStr01 = getHashCodeStr(is);
		System.out.println(hashCodeStr01);

		equals = hashCodeStr01.equals(md5);
		System.out.println(equals);
		return equals;
	}

	public boolean md5Equals(File oriFile, File destFile) throws FileNotFoundException {

		Assert.assertFileExists(oriFile, "oriFile is null or oriFile do not exists.");
		Assert.assertFileExists(destFile, "destFile is null or destFile do not exists.");

		boolean equals = false;
		InputStream is = new FileInputStream(oriFile);
		InputStream is2 = new FileInputStream(destFile);

		String hashCodeStr01 = getHashCodeStr(is);
		String hashCodeStr02 = getHashCodeStr(is2);

		System.out.println(hashCodeStr01);
		System.out.println(hashCodeStr02);

		equals = hashCodeStr01.equals(hashCodeStr02);

		System.out.println(equals);
		return equals;
	}

	public void md5Equals(String oriFile, String destFile) throws FileNotFoundException {

		Assert.assertNotNull(oriFile, "oriFile can not be null.");
		Assert.assertNotNull(destFile, "destFile can not be null.");

		File file = new File(oriFile);
		File dFile = new File(destFile);
		md5Equals(file, dFile);
	}

	public String getHashCodeStr(InputStream in) {
		try {
			byte[] strTemp = getHashCodeBytes(in);
			return getHashCodeStr(strTemp);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getHashCodeStr(String source) {
		try {
			byte[] strTemp = source.getBytes();
			return getHashCodeStr(strTemp);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getHashCodeStr(byte[] sBytes) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			// MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			// mdTemp.update(sBytes);
			// byte[] md = mdTemp.digest();
			// int j = md.length;

			int j = sBytes.length;

			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = sBytes[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);

		} catch (Exception e) {
			return null;
		}
	}

	public byte[] getHashCodeBytes(InputStream in) {
		byte[] bytes = new byte[8192];
		int read = 0;
		byte[] hashCodeBytes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			int totalBytes = 0;
			while ((read = in.read(bytes, 0, 8192)) > 0) {
				totalBytes = totalBytes + read;
				md5.update(bytes, 0, read);
			}
			System.out.println("totalBytes:[" + totalBytes + "]");
			hashCodeBytes = md5.digest();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hashCodeBytes;
	}

}
