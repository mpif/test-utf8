package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

import com.codefans.java.io.MimeEncoding;

public class JavaIOTest {

	private String contextPath;

	@Before
	public void init() {
		contextPath = System.getProperty("user.dir") + File.separator + "src";
		System.out.println("contextPath: " + contextPath);
	}

	@Test
	public void test() {

		// encodeFile();

		decodeFile();

	}

	public void encodeFile() {
		String filePath = contextPath + File.separator + "test.txt";
		String fileEncode = contextPath + File.separator + "test_encode.txt";
		InputStream fis = null;
		OutputStream fos = null;
		File file = new File(fileEncode);

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fis = new FileInputStream(new File(filePath));
			fos = new FileOutputStream(file);
			fos = MimeEncoding.encode(fos, "base64");

			byte[] bytes = new byte[45];
			int n = 0;
			while ((n = fis.read(bytes)) != -1) {
				fos.write(bytes, 0, bytes.length);
			}
			// StreamUtility.copy(MimeEncoding.decode(fis, "uuencode"), fos);
			fos.flush();
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
					fos = null;
				}
				if (fis != null) {
					fis.close();
					fis = null;
				}
				// if(us != null) {
				// us.close();
				// us = null;
				// }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void decodeFile() {
		String filePath = contextPath + File.separator + "test_encode.txt";
		String fileDecode = contextPath + File.separator + "test_decode.txt";

		InputStream fis = null;
		OutputStream fos = null;
		try {
			File file = new File(fileDecode);
			if (!file.exists()) {
				file.createNewFile();
			}
			fis = new FileInputStream(new File(filePath));
			fis = MimeEncoding.decode(fis, "base64");// base64

			fos = new FileOutputStream(file);
			byte[] bytes = new byte[45];
			int n = 0;
			while ((n = fis.read(bytes)) != -1) {
				fos.write(bytes, 0, bytes.length);
			}
			fos.flush();
			fis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
					fos = null;
				}
				if (fis != null) {
					fis.close();
					fis = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
