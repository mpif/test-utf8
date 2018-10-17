package com.codefans.util.fileutil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PackageMerge {

	private List<String> fileList = new ArrayList<String>();
	private Set<String> noDuplicateFiles = new HashSet<String>();
	private String rootPath;
	private String destRootPath;
	private String destFolderName;
	private String packagePrefix;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PackageMerge packageMerge = new PackageMerge();
		packageMerge.merge();
	}

	public void merge() {
		// rootPath = "C:\\attachments\\jar and class";
		rootPath = "C:\\Temp\\allJars";
		destRootPath = rootPath;
		// destRootPath = "D:\\mydev\\Bamboo";
		// destFolderName = "src";
		destFolderName = "javasource";
		packagePrefix = "\\com\\";

		// String dest = root + File.separator + "src";
		this.scan(rootPath);
		System.out.println(noDuplicateFiles.size());
		this.move(noDuplicateFiles);
		this.check(fileList);

		System.out.println("total added:[" + fileList.size() + "]");
		// System.out.println(fileList.size() == noDuplicateFiles.size());
	}

	public void scan(String path) {
		File dir = new File(path);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					scan(path + File.separator + f.getName());
				} else {
					String p = f.getAbsolutePath();
					if (p.indexOf(packagePrefix) >= 0) {
						// p = p.substring(p.indexOf(packagePrefix));
						// fileList.add(p);
						noDuplicateFiles.add(p);
					}
					// System.out.println(f.getAbsolutePath());
					// System.out.println(p);
				}
			}
		}
	}

	public void move(Set<String> files) {
		if (files != null) {
			Iterator<String> iter = files.iterator();
			String key = "";
			String destPath = "";
			while (iter.hasNext()) {
				key = iter.next();
				destPath = this.getDestPath(key);
				if (!this.exists(destPath)) {
					this.copy(key, destPath);
					fileList.add(destPath);
				} else {
					System.out.println("already exists file :[" + destPath + "]");
				}
			}
		}
	}

	public void check(List<String> files) {
		if (files != null) {
			File file = null;
			int existsNum = 0;
			for (int i = 0; i < files.size(); i++) {
				if (this.exists(files.get(i))) {
					existsNum++;
				}
			}
			System.out.println("total dest files:[" + existsNum + "].");
		}
	}

	public boolean exists(String file) {
		File f = new File(file);
		return f.exists();
	}

	public String getDestPath(String path) {
		// C:\attachments\jar and
		// class\~atlassian-bamboo-web-5.6.0.jar\com\atlassian\breadcrumbs\ProjectCrumb.java
		StringBuffer sb = new StringBuffer();

		sb.append(this.destRootPath).append(File.separator).append(destFolderName);
		sb.append(path.substring(path.indexOf(packagePrefix)));

		return sb.toString();
	}

	public void copy(String source, String dest) {
		String path = dest.substring(0, dest.lastIndexOf(File.separator));
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File sFile = new File(source);
		File dFile = new File(dest);
		this.copy(sFile, dFile);

	}

	public void copy(File source, File dest) {
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
			this.copy(bis, bos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copy(InputStream is, OutputStream os) {
		try {
			byte[] buf = new byte[4096];
			int n = 0;
			while ((n = is.read(buf)) != -1) {
				os.write(buf, 0, n);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(is, os);
		}
	}

	public void closeResource(InputStream is, OutputStream os) {
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
