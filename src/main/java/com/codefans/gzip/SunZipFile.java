package com.codefans.gzip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SunZipFile {

	private String[] sources;
	private String deCompressSource;
	private String dest;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SunZipFile szf = new SunZipFile();

		String zip = "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\mails.zip";
		String[] files = new String[] { "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\1.eml",
				"C:\\Users\\Administrator\\Downloads\\20131030_12590876\\2.eml",
				"C:\\Users\\Administrator\\Downloads\\20131030_12590876\\3.eml"

				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\jarUtil.log"

				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\第1封邮件.eml",
				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\第2封邮件.eml",
				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\第3封邮件.eml"
		};

		szf.setSources(files);
		szf.setDest(zip);

		szf.compress();
	}

	public void compress() {
		compress(this.getSources(), this.getDest());
	}

	public void compress(String[] sources, String dest) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(dest));
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));

			OutputStreamWriter osw = new OutputStreamWriter(zos);

			FileInputStream fis = null;
			byte[] bytes = new byte[1024];
			int n = 0;

			ZipEntry zipEntry = null;
			String name = "";
			for (int i = 0; i < sources.length; i++) {
				name = sources[i];
				name = name.substring(name.lastIndexOf("\\") + 1);
				zipEntry = new ZipEntry(name);
				zos.putNextEntry(zipEntry);
				fis = new FileInputStream(new File(sources[i]));
				while ((n = fis.read(bytes)) != -1) {
					// osw.write(new String(bytes));
					zos.write(bytes, 0, n);
				}
				zos.closeEntry();
			}

			zos.flush();
			zos.finish();
			zos.close();

			System.out.println("create finished.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deCompress() {

	}

	public void deCompress(String source, String dest) {

	}

	public String[] getSources() {
		return sources;
	}

	public void setSources(String[] sources) {
		this.sources = sources;
	}

	public String getDeCompressSource() {
		return deCompressSource;
	}

	public void setDeCompressSource(String deCompressSource) {
		this.deCompressSource = deCompressSource;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

}
