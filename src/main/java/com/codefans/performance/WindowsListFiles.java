package com.codefans.performance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * User: Sean
 * Date: 2015-3-27
 * Time: 下午5:42:27
 */

//total files:[1177770]
//大小：3.46 GB (3,722,182,527 字节)
//占用空间：6.62 GB (7,110,737,920 字节)
//
//===================test data one=================
//copy from to directories(two net directories) to one directory:
//time cost:8506s.
//totalMemoryBegin:[188547072]
//freeMemoryBegin:[187564008]
//totalMemoryEnd:[142671872]
//freeMemoryEnd:[138893712]
//freeMemoryBegin-freeMemoryEnd:[46]MB
//totalMemoryBegin-totalMemoryEnd:[43]MB
//===================test data one=================
//
//===================test data two=================
//copy from one directory to multi directories(three local directories)
//time cost:3155s.
//totalMemoryBegin:[188547072]
//freeMemoryBegin:[187564008]
//totalMemoryEnd:[144637952]
//freeMemoryEnd:[139549216]
//freeMemoryBegin-freeMemoryEnd:[45]MB
//totalMemoryBegin-totalMemoryEnd:[41]MB
//totalMemoryBegin-freeMemoryBegin:[0]MB
//totalMemoryEnd-freeMemoryEnd:[4]MB
//===================test data two=================
//
//===================test data three=================
//list multi path files(three paths)
//time cost:208s.
//totalMemoryBegin:[188547072]
//freeMemoryBegin:[187564008]
//totalMemoryEnd:[519962624]
//freeMemoryEnd:[199009608]
//freeMemoryBegin-freeMemoryEnd:[-10]MB
//totalMemoryBegin-totalMemoryEnd:[-316]MB
//totalMemoryBegin-freeMemoryBegin:[0]MB
//totalMemoryEnd-freeMemoryEnd:[306]MB
//===================test data three=================
//
//===================test data fourth=================
//list one directory
//total [1177770] in path:[C:/tmp].
//time cost:14s.
//totalMemoryBegin:[188547072]
//freeMemoryBegin:[187564008]
//totalMemoryEnd:[575864832]
//freeMemoryEnd:[228648840]
//freeMemoryBegin-freeMemoryEnd:[-39]MB
//totalMemoryBegin-totalMemoryEnd:[-369]MB
//totalMemoryBegin-freeMemoryBegin:[0]MB
//totalMemoryEnd-freeMemoryEnd:[331]MB
//===================test data fourth=================

public class WindowsListFiles {

	private File[] files;
	private String outFile;
	private String[] dirs;
	private List<String> multiPathFiles;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WindowsListFiles wlf = new WindowsListFiles();
		wlf.list();

		// File f = new
		// File("\\\\10.0.0.47\\temp\\streamming\\email\\attachment\\20150205\\142565013053130000.3");
		// System.out.println(f.exists());
	}

	public void list() {

		long start = System.currentTimeMillis();
		long totalMemoryBegin = Runtime.getRuntime().totalMemory();
		long freeMemoryBegin = Runtime.getRuntime().freeMemory();

		// total files:[1177770]
		// 大小：3.46 GB (3,722,182,527 字节)
		// 占用空间：6.62 GB (7,110,737,920 字节)

		// list one directory
		// total [1177770] in path:[C:/tmp].
		// time cost:14s.
		this.listFilesInOneFolder();

		// copy from to directories(two net directories) to one directory:
		// time cost:8506s.
		// this.moveFilesToOneFolder();

		// copy from one directory to multi directories(three local directories)
		// time cost:3155s.
		// this.moveFilesToMultiFolders();

		// list multi path files(three paths)
		// time cost:208s.
		// this.listFilesInMultiFolders();

		long end = System.currentTimeMillis();
		long totalMemoryEnd = Runtime.getRuntime().totalMemory();
		long freeMemoryEnd = Runtime.getRuntime().freeMemory();

		System.out.println("time cost:" + (end - start) / 1000 + "s.");
		System.out.println("totalMemoryBegin:[" + totalMemoryBegin + "]");
		System.out.println("freeMemoryBegin:[" + freeMemoryBegin + "]");
		System.out.println("totalMemoryEnd:[" + totalMemoryEnd + "]");
		System.out.println("freeMemoryEnd:[" + freeMemoryEnd + "]");

		System.out.println("freeMemoryBegin-freeMemoryEnd:[" + (freeMemoryBegin - freeMemoryEnd) / 1024 / 1024 + "]MB");
		System.out.println(
				"totalMemoryBegin-totalMemoryEnd:[" + (totalMemoryBegin - totalMemoryEnd) / 1024 / 1024 + "]MB");
		System.out.println(
				"totalMemoryBegin-freeMemoryBegin:[" + (totalMemoryBegin - freeMemoryBegin) / 1024 / 1024 + "]MB");
		System.out.println("totalMemoryEnd-freeMemoryEnd:[" + (totalMemoryEnd - freeMemoryEnd) / 1024 / 1024 + "]MB");

		print();

	}

	public void listFilesInOneFolder() {
		String folderPath = "\\\\10.0.0.47\\temp\\streamming\\email\\attachment\\20150205";
		// String folderPath =
		// "\\\\10.0.0.47\\temp\\streamming\\email\\attachment\\20150206";
		// String folderPath = "C:/tmp";
		File file = new File(folderPath);
		files = file.listFiles();
		System.out.println("total [" + files.length + "] in path:[" + folderPath + "].");
	}

	public void moveFilesToOneFolder() {
		BufferedReader br = null;
		String destRoot = "C:/tmp";
		String path = "";
		String fileName = "";
		String[] files = new String[] { "C:/paths_20150205_641212.txt", "C:/paths_20150206_536558.txt" };
		;
		String file = "";
		try {
			for (int i = 0; i < files.length; i++) {
				file = files[i];
				br = new BufferedReader(new FileReader(file));
				String line = "";
				while ((line = br.readLine()) != null) {
					line = line.trim();
					if (line.length() > 0) {
						line = line.substring(0, line.lastIndexOf(";"));
						fileName = line.substring(line.lastIndexOf(File.separator));
						path = destRoot + File.separator + fileName;
						this.copy(line, path);
						System.out.println("file [" + path + "] copy success.");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void moveFilesToMultiFolders() {
		BufferedReader br = null;
		String destRoot = "C:/tmp2";
		int fileCount = 0;
		String fileName = "";
		String path = "";
		try {
			outFile = "C:/paths.txt";
			br = new BufferedReader(new FileReader(outFile));
			String line = "";
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.length() > 0) {
					line = line.substring(0, line.lastIndexOf(";"));
					fileName = line.substring(line.lastIndexOf(File.separator));

					if (fileCount < 500000) {
						if (fileCount == 0) {
							path = destRoot + File.separator + "first";
							this.mkDir(path);
						}
					} else if (fileCount >= 500000 && fileCount < 1000000) {
						if (fileCount == 500000) {
							path = destRoot + File.separator + "second";
							this.mkDir(path);
						}
					} else if (fileCount >= 1000000) {
						if (fileCount == 1000000) {
							path = destRoot + File.separator + "third";
							this.mkDir(path);
						}
					}

					this.copy(line, path + File.separator + fileName);

					fileCount++;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void mkDir(String file) {
		try {
			File one = new File(file);
			if (!one.exists()) {
				boolean flag = one.mkdir();
				if (!flag) {
					System.out.println("dir:[" + file + "] create fail.");
				}
			} else {
				System.out.println("dir:[" + file + "] already exists.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copy(String sourceFile, String destFile) {
		this.copy(new File(sourceFile), new File(destFile));
	}

	public void copy(File sourceFile, File destFile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			if (!destFile.exists()) {
				destFile.createNewFile();
			}
			fis = new FileInputStream(sourceFile);
			fos = new FileOutputStream(destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.copy(fis, fos);
	}

	public void copy(InputStream in, OutputStream out) {
		int len = 0;
		byte[] b = new byte[1024];
		try {
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void listFilesInMultiFolders() {
		dirs = new String[] { "C:\\tmp2" };
		multiPathFiles = new ArrayList<String>();

		for (int i = 0; i < dirs.length; i++) {
			String dir = dirs[i];
			this.collectFiles(dir);
		}

	}

	public void collectFiles(String dir) {
		File d = new File(dir);
		if (d.isDirectory()) {
			File[] files = d.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					this.collectFiles(dir + File.separator + f.getName());
				} else {
					multiPathFiles.add(f.getAbsolutePath());
				}
			}
		}
	}

	public void print() {
		FileWriter fw = null;
		try {
			outFile = "C:/paths.txt";
			fw = new FileWriter(new File(outFile));
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					fw.append(f.getAbsolutePath()).append(";\r\n");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.flush();
					fw.close();
					fw = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
