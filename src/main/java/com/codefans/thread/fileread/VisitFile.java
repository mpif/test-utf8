package com.codefans.thread.fileread;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class VisitFile {
	// private static int readnum=0;//为了测试用的
	// private static int writenum=0;//为了测试用的
	private String filename; // 文件名
	private String webAppName = ""; // web应用名
	private boolean isReading = false;
	private boolean isWriting = false;

	public VisitFile() {
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setWebAppName(String webAppName) {
		this.webAppName = webAppName;
	}

	public String getWebAppName() {
		return this.webAppName;
	}

	public synchronized void setDateToFile(String filename, Object[] data) {
		if (!webAppName.equals(""))
			filename = generateFileName(filename);
		File f = new File(filename);
		if (f.exists()) {
			try {
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			RandomAccessFile rf = new RandomAccessFile(f, "rw");
			for (int i = 0; i < data.length; i++) {
				rf.write(data.toString().getBytes());
				rf.writeByte(13);// 写入一个回车
			}
			if (rf != null)
				rf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("writenum="+(++writenum));
	}

	public synchronized Object[] getQueryResult(String filename) {
		ArrayList list = new ArrayList();
		if (!webAppName.equals(""))
			filename = generateFileName(filename);
		// filename=System.getProperty("user.dir")+"\\webapps\\"+webAppName+"\\WEB-INF\\classes\\"+packageName+"\\"+filename;
		System.out.println("filename=" + filename);
		File f = new File(filename);
		if (!f.exists()) {
			return list.toArray();
		}
		try {
			RandomAccessFile rf = new RandomAccessFile(f, "r");
			int i = 0;
			while (i < rf.length() - 1) {
				int j = 0;
				byte[] temp = new byte[100];
				byte b = rf.readByte();
				i++;
				while (b != 13) {
					temp[j++] = b;
					b = rf.readByte();
					i++;
				}
				// System.out.print(new String(temp).trim()+",");
				list.add(new String(temp).trim());
			}
			if (rf != null)
				rf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.print("readnum="+(++readnum));
		// System.out.println();
		return list.toArray();
	}

	private String generateFileName(String filename) {
		Class c = this.getClass();
		String className = c.getName();
		String packageName = "";
		if (className.indexOf(".") != -1) {
			className = className.substring(0, className.lastIndexOf("."));
			packageName = className.replace('.', File.separatorChar);
		}
		String tempfilename = "";
		tempfilename = System.getProperty("user.dir") + File.separator;
		tempfilename += "webapps" + File.separator + webAppName + File.separator;
		tempfilename += "WEB-INF" + File.separator + "classes" + File.separator;
		tempfilename += packageName + File.separator + filename;
		return tempfilename;
	}

	/***************************************************************
	 * 1.注意：如果是web应用，请先调用setWebAppName(String webAppName);
	 ****************************************************************/
	public static void main(String args[]) {
		try {
			VisitFile vf = new VisitFile();
			String[] aa = { "aa", "张孝祥12" };
			// vf.setDateToFile("xjs",aa);
			Object results[] = vf.getQueryResult("xjs");
			// System.out.println("results[].length="+results.length);
			for (int i = 0; i < results.length; i++) {
				System.out.println("results=" + results);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
