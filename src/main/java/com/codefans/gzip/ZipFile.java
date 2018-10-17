package com.codefans.gzip;

public class ZipFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ZipFile file = new ZipFile();
		file.create();
	}

	public void create() {
		String zip = "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\mails.zip";
		// String zip = "C:\\mails.zip";
		String[] files = new String[] { "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\1.eml",
				"C:\\Users\\Administrator\\Downloads\\20131030_12590876\\2.eml",
				"C:\\Users\\Administrator\\Downloads\\20131030_12590876\\3.eml"

				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\jarUtil.log"

				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\第1封邮件.eml",
				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\第2封邮件.eml",
				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\第3封邮件.eml"
		};
		this.create(files, zip);
		// this.createApacheZip(files, zip);
	}

	public void create(String[] sources, String dest) {
		new SunZipFile().compress(sources, dest);
	}

	public void createApacheZip(String[] sources, String dest) {
		new ApacheZipFile().compress(sources, dest);
	}

}
