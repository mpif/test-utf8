package com.codefans.task.imgencrypt;

public class Encryptor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String srcPath = "";
		String destPath = "";
		FileEncrypt encryptor = new ImageEncrypt(srcPath, destPath);
		encryptor.encrypt();
		encryptor.decrypt();
	}

}
