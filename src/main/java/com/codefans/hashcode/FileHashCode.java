package com.codefans.hashcode;

import java.io.File;

public class FileHashCode {

	/**
	 * @return void
	 * @author caisz
	 */
	public static void main(String[] args) {
		String root = System.getProperty("user.dir");
		String path = root + File.separator + "src" + File.separator + "test.txt";
		File file = new File(path);
		// System.out.println("file.length: " + file.length());
		System.out.println("file.hashcode: " + file.hashCode());

		String path2 = root + File.separator + "src" + File.separator + "test_.txt";
		File file2 = new File(path2);
		System.out.println("file2.hashcode: " + file2.hashCode());

	}

}
