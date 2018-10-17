package com.codefans.task.imgencrypt;

import java.security.MessageDigest;

public abstract class FileEncrypt {

	public void encrypt() {
	};

	public void decrypt() {
	};

	public String computeStringMD5HashCode(String str) {
		String md5HashCode = "";
		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			md5HashCode = this.computeStringHashCode(str, mDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5HashCode;

	}

	public String computeStringHashCode(String str, MessageDigest mDigest) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = str.getBytes();
			mDigest.update(strTemp);
			byte[] md = mDigest.digest();
			int j = md.length;
			char strs[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
				strs[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(strs);
		} catch (Exception e) {
			return null;
		}
	}

}
