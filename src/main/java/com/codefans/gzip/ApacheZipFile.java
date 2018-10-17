package com.codefans.gzip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ApacheZipFile {

	private String[] sources;
	private String deCompressSource;
	private String dest;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApacheZipFile azf = new ApacheZipFile();

		// String zip =
		// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\mails.zip";
		String zip = "C:\\Users\\Administrator\\Downloads\\emls\\emls.zip";
		String[] files = new String[] {
				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\1.eml",
				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\2.eml",
				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\3.eml"

				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\jarUtil.log"

				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\第1封邮件.eml",
				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\第2封邮件.eml",
				// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\第3封邮件.eml"

				"C:\\Users\\Administrator\\Downloads\\emls\\FW__Deep_dive_into_c..._0_1_20130319_6407561.eml",
				"C:\\Users\\Administrator\\Downloads\\emls\\RE__MedSynergies_-_S..._0_1_20130404_3992464.eml",
				"C:\\Users\\Administrator\\Downloads\\emls\\RE__OrgChart_2.15.20..._0_1_20130313_7301400.eml" };

		azf.setSources(files);
		azf.setDest(zip);

		// azf.compress();
		azf.deCompress();
	}

	public void compress() {
		compress(this.getSources(), this.getDest());
	}

	public void compress(String[] sources, String dest) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(dest));
			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));

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
					if (i == 1) {
						zos.write(bytes, 0, 100);
						// zos.write(100);
					} else {
						zos.write(bytes, 0, n);
					}
				}
				zos.closeEntry();
			}

			zos.flush();
			zos.finish();
			zos.close();

			System.out.println("ApacheZipFile.create finished.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deCompress() {
		String source = "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\mails_error.zip";
		// String source =
		// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\mails.zip";
		// String source =
		// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\加密的压缩文件.rar";
		// String source =
		// "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\加密的压缩ZIP文件.zip";
		String dest = "C:\\Users\\Administrator\\Downloads\\20131030_12590876\\";

		deCompress(source, dest);

	}

	public void deCompress(String source, String dest) {
		try {

			String folder = source.substring(source.lastIndexOf("\\") + 1, source.lastIndexOf("."));

			org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(source);
			ZipEntry entry = null;
			Enumeration<ZipEntry> iter = zipFile.getEntries();
			byte[] bytes = new byte[1024];
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			String name = "";
			File file = null;
			int n = 0;
			boolean flag = false;
			while (iter.hasMoreElements()) {
				if (!flag) {
					File direct = new File(dest + folder);
					if (!direct.exists()) {
						direct.mkdir();
					}
					flag = true;
				}
				entry = iter.nextElement();
				name = entry.getName();
				file = new File(dest + folder + File.separator + name);
				if (!file.exists()) {
					file.createNewFile();
				}
				bis = new BufferedInputStream(zipFile.getInputStream(entry));
				bos = new BufferedOutputStream(new FileOutputStream(file));
				while ((n = bis.read(bytes)) != -1) {
					bos.write(bytes, 0, n);
				}
				bos.flush();
				bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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
