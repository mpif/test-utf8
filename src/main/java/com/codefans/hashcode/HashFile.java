package com.codefans.hashcode;

import java.io.*;
import java.security.*;

public class HashFile {
	public static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static void main(String[] args) throws Exception {
		String path = System.getProperty("user.dir"); // D:\dev\test
		System.out.println("path: " + path);

		String fileName = "GoodbyeWorld-colliding.exe";
		String fileName2 = "HelloWorld-colliding.exe";

		String file = path + File.separator + "md5" + File.separator + fileName;
		String file2 = path + File.separator + "md5" + File.separator + fileName2;

		String hashType = "MD5";
		System.out.println(hashType + " " + fileName + " == " + getHash(file, hashType));
		System.out.println(hashType + " " + fileName2 + " == " + getHash(file2, hashType));

		// hashType = "SHA1";
		// System.out.println(hashType + " == " +
		// getHash(fileName, hashType));
		// hashType = "SHA-256";
		// System.out.println(hashType + " == " +
		// getHash(fileName, hashType));
		// hashType = "SHA-384";
		// System.out.println(hashType + " == " +
		// getHash(fileName, hashType));
		// hashType = "SHA-512";
		// System.out.println(hashType + " == " +
		// getHash(fileName, hashType));
		//
	}

	public static String getHash(String fileName, String hashType) throws Exception {
		InputStream fis;
		fis = new FileInputStream(fileName);
		byte[] buffer = new byte[1024];
		MessageDigest md5 = MessageDigest.getInstance(hashType);
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			md5.update(buffer, 0, numRead);
		}
		fis.close();
		return toHexString(md5.digest());
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}
}
