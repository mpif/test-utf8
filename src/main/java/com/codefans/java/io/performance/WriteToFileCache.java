package com.codefans.java.io.performance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
 * @Author: Sean
 * @Time: 2015-05-26 17:15:54
 */
public class WriteToFileCache {

	private String rootDir;
	private String sourceFileRootDir;
	private List<String> datas;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WriteToFileCache wtfc = new WriteToFileCache();
		wtfc.writePreformanceTest();
	}

	public void writePreformanceTest() {
		rootDir = "C:\\Temp\\performance\\";
		sourceFileRootDir = rootDir + "sLib\\";
		// sourceFileRootDir = "D:\\dev\\RB-2.1.x-gx\\build\\lib\\";

		// datas = new ArrayList<String>();
		// datas.add(rootDir + "mod_perl-2.0-current.tar.gz");
		// datas.add(rootDir + "commons-pool2-2.1-bin.zip");
		// datas.add(rootDir + "activemq-core-5.1.0.jar");
		datas = gatherSourceFiles();

		long begin = System.currentTimeMillis();
		write();
		long end = System.currentTimeMillis();
		System.out.println("write cost:" + (end - begin) / 1000 + "s");

		// createCacheFile();

		begin = System.currentTimeMillis();
		writeToFileCache();
		end = System.currentTimeMillis();
		System.out.println("writeToFileCache cost:" + (end - begin) / 1000 + "s");

	}

	public List<String> gatherSourceFiles() {
		List<String> datas = new ArrayList<String>();

		File dir = new File(sourceFileRootDir);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.endsWith(".jar") || name.endsWith(".rar") || name.endsWith(".zip")
							|| name.endsWith(".pdf")) {
						return true;
					}
					return false;
				}
			});

			for (int i = 0; i < files.length; i++) {
				datas.add(files[i].getAbsolutePath());
			}

		}

		return datas;
	}

	public void write() {
		String destFile = "";
		String tmpFile = "";
		File file = null;
		InputStream is = null;
		OutputStream os = null;
		int len = 0;
		byte[] bytes = new byte[1024];
		String destDir = rootDir + "dLib\\";
		try {
			long totalBytes = 0;

			for (int i = 0; i < datas.size(); i++) {
				tmpFile = datas.get(i);
				destFile = destDir + tmpFile.substring(tmpFile.lastIndexOf(File.separator))
						+ tmpFile.substring(tmpFile.lastIndexOf("."));
				is = new FileInputStream(new File(tmpFile));
				file = new File(destFile);
				if (!file.exists()) {
					file.createNewFile();
				}
				os = new FileOutputStream(file);
				while ((len = is.read(bytes)) != -1) {
					totalBytes = totalBytes + len;
					os.write(bytes, 0, len);
				}
				os.flush();
			}

			System.out.println("write totalBytes:" + totalBytes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
				if (os != null) {
					os.close();
					os = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void createCacheFile() {
		RandomAccessFile raf = null;
		try {
			long fileSize = 1024;
			String cacheFileName = fileSize + "M";
			String cacheFilePath = rootDir + cacheFileName;
			File cacheFile = new File(cacheFilePath);

			if (!cacheFile.exists()) {
				boolean createNewFile = cacheFile.createNewFile();
				if (createNewFile) {
					System.out.println("new file create success!");
				} else {
					System.out.println("new file create fail!");
				}
				System.out.println("file:[" + cacheFilePath + "]");

				raf = new RandomAccessFile(cacheFile, "rw");
				long cacheFileLength = fileSize * 1024 * 1024; // 200M
				raf.setLength(cacheFileLength);

				System.out.println("length of cache file is:" + fileSize + "M");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeToFileCache() {
		SequenceInputStream sis = null;
		RandomAccessFile raf = null;
		try {
			long fileSize = 1024;
			String cacheFileName = fileSize + "M";
			String cacheFilePath = rootDir + cacheFileName;
			File cacheFile = new File(cacheFilePath);

			if (!cacheFile.exists()) {
				createCacheFile();
			} else {
				raf = new RandomAccessFile(cacheFile, "rw");
			}

			String tmpFile = "";
			InputStream is = null;
			int len = 0;
			byte[] bytes = new byte[1024];

			Vector<InputStream> streams = new Vector<InputStream>();

			for (int i = 0; i < datas.size(); i++) {
				tmpFile = datas.get(i);
				is = new FileInputStream(new File(tmpFile));
				streams.add(is);
			}
			sis = new SequenceInputStream(streams.elements());

			long totalBytes = 0;
			while ((len = sis.read(bytes)) != -1) {
				totalBytes = totalBytes + len;
				raf.write(bytes, 0, len);
			}

			System.out.println("writeToFileCache totalBytes:" + totalBytes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (sis != null) {
					sis.close();
					sis = null;
				}
				if (raf != null) {
					raf.close();
					raf = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
