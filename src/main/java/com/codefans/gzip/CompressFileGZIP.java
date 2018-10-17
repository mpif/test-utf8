package com.codefans.gzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class CompressFileGZIP {
	private static void doCompressFile(String inFileName) {

		try {

			System.out.println("Creating the GZIP output stream.");
			// String outFileName = inFileName + ".ggzz";
			// String outFileName = inFileName + "_gzip";
			String outFileName = inFileName + ".zip";
			// String outFileName = inFileName + "_2.txt";
			GZIPOutputStream out = null;
			try {
				out = new GZIPOutputStream(new FileOutputStream(outFileName));
			} catch (FileNotFoundException e) {
				System.err.println("Could not create file: " + outFileName);
				System.exit(1);
			}

			System.out.println("Opening the input file.");
			FileInputStream in = null;
			try {
				in = new FileInputStream(inFileName);
			} catch (FileNotFoundException e) {
				System.err.println("File not found. " + inFileName);
				System.exit(1);
			}

			System.out.println("Transfering bytes from input file to GZIP Format.");
			byte[] buf = new byte[1024];
			int len;

			// byte[] buf2 = new byte[22];
			// String str = "fjfjsadfoigjewfge";
			// buf2 = str.getBytes();
			// out.write(buf2, 0, buf2.length);
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();

			System.out.println("Completing the GZIP file");
			out.finish();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Sole entry point to the class and application.
	 * 
	 * @param args
	 *            Array of String arguments.
	 */
	public static void main(String[] args) {
		String root = System.getProperty("user.dir");
		String path = root + File.separator + "src" + File.separator;
		// String fileName = "test.txt";
		String fileName = "jarUtil.log";
		doCompressFile(path + fileName);

	}

	public static void doCompressFiles(String dest, String[] sources) {

	}

}
