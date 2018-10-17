package com.codefans.java.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileTest {

	static String path = "D:\\dev\\test-utf8\\src\\com\\messagesolution\\java\\file\\";
	static String desktop = "C:\\Documents and Settings\\workbench\\桌面\\";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// File f = new File(path + "default.txt");
		// System.out.println(f.getAbsolutePath());
		// try {
		// System.out.println(f.getCanonicalPath());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// copy();
		test();

	}

	public static void test() {
		File rootPath = new File("C:\\images");
		File[] files = rootPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg")) {
					if ("append".equals("append")) {
						// if(exists(dir,name)) {
						// return true;
						// } else {
						// File f = new File(dir, name);
						File f = new File("C:\\images\\", name);
						if (!f.exists()) {
							System.out.println("dir:" + dir.getAbsolutePath() + ", name:" + name + ", do not exists.");
						} else {
							System.out.println("exists, file is:" + f.getAbsolutePath());
						}
					} else {
						return true;
					}
				}
				return false;
			}

		});
	}

	// public boolean exists(File dir, String name) {
	// boolean flag = false;
	// File source = new File(dir, name);
	// try {
	// InputStream is = new FileInputStream(source);
	// MD5 md5 = new MD5();
	// md5.compute(is);
	// String hashCode = md5.getString();
	//
	// File dest = new File(this.destPath, hashCode);
	// flag = dest.exists();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return flag;
	// }

	public static void copy() {
		String s = desktop + "b.doc";
		String d = desktop + "b_.doc";
		File df = new File(d);
		InputStream is = null;
		OutputStream os = null;
		try {
			if (!df.exists()) {
				df.createNewFile();
			}

			is = new FileInputStream(new File(s));
			os = new FileOutputStream(df);

			int n = 0;
			byte[] b = new byte[1024];
			int num = 0;
			while ((n = is.read(b)) != -1) {
				os.write(b, 0, n);
				num++;
				if (num == 20) {
					break;
				}
			}
			os.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(is, os);
		}

	}

	public static void close(InputStream is, OutputStream os) {
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
