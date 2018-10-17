package com.codefans.java.io.filecopy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileCopy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileCopy fc = new FileCopy();
		fc.copy();
	}

	public void copy() {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		int lineNumber = 0;
		try {
			// String s = "C:\\Documents and
			// Settings\\workbench\\桌面\\temp\\30M.rar";
			// String d = "C:\\Documents and Settings\\workbench\\桌面\\30M.rar";

			String s = "C:\\Documents and Settings\\workbench\\桌面\\temp\\createAttachment.eea";
			String d = "C:\\Documents and Settings\\workbench\\桌面\\createAttachment.eea";

			File df = new File(d);
			if (!df.exists()) {
				df.createNewFile();
			}

			InputStream is = new FileInputStream(s);
			OutputStream os = new FileOutputStream(d);

			reader = new BufferedReader(new InputStreamReader(is)); // , 2048
			writer = new BufferedWriter(new OutputStreamWriter(os));

			String line = null;
			char[] chars = new char[2048];
			int n = 0;
			while ((n = reader.read(chars)) != -1) {
				lineNumber++;
				System.out.println("lineNumber:" + lineNumber);
				writer.write(chars, 0, n);
			}
			writer.newLine();
			writer.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("aaaaaaaaaaaaaaaa");
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
				if (writer != null) {
					writer.close();
					writer = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("lineNumber:" + lineNumber);

	}
}
