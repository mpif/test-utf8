package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.junit.Test;

public class FileTest {

	@Test
	public void test() {

		// test4();
		// test5();
		test6();

		// testFileGetParent();
		// test2();
		// test3();
		// testFileDelete();
		// getCurrentPath();

	}
	

	
	public void test6() {
		String str = "reference:file:/D:/MarsWorkspace/pomCopy/";
		String separetor = "file:/";
		str = str.substring(str.indexOf(separetor) + separetor.length());
		System.out.println("str:" + str);

		File file = new File(str);
		System.out.println(file.exists());
		System.out.println(file.isDirectory());

		// try {
		// URL url = new URL(str);
		// String urlFile = url.getFile();
		// System.out.println("urlFile:" + urlFile);
		//
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// }

	}

	public void test5() {
		String filePath = "C:/Temp/但是你没有_out.eml";
		File f = new File(filePath);
		long lastModified = f.lastModified();
		long now = System.currentTimeMillis();
		System.out.println("lastModified:" + lastModified);
		System.out.println("now:" + now);

		// Calendar cal =
		long hour = (now - lastModified) / (60 * 60 * 1000);
		long minute = (now - lastModified) / (60 * 1000);
		System.out.println("minute:" + minute);
		System.out.println("hour:" + hour);
		System.out.println("day:" + hour / 24);

	}

	public void test4() {

		File file01 = new File("a.txt");
		File file02 = new File("/a.txt");
		System.out.println("file01.getPath(): " + file01.getPath());// a.txt
		System.out.println("file02.getPath(): " + file02.getPath());// \a.txt
		System.out.println("file01.getPath(): " + file01.getAbsolutePath());// D:\dev\test-utf8\a.txt
		System.out.println("file02.getPath(): " + file02.getAbsolutePath());// D:\a.txt

		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));// file:/D:/dev/test-utf8/bin/

		System.out.println(FileTest.class.getClassLoader().getResource(""));// file:/D:/dev/test-utf8/bin/

		System.out.println("ClassLoader.getSystemResource(''): " + ClassLoader.getSystemResource(""));// file:/D:/dev/test-utf8/bin/

		System.out.println("FileTest.class.getResource(''): " + FileTest.class.getResource(""));// file:/D:/dev/test-utf8/bin/com/test/

		System.out.println("FileTest.class.getResource('/'): " + FileTest.class.getResource("/"));// file:/D:/dev/test-utf8/bin/

		// Class文件所在路径

		System.out.println(new File("/").getAbsolutePath());// D:\
		try {
			System.out.println(new File("/").getCanonicalPath());// D:\
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("user.dir: " + System.getProperty("user.dir"));// D:\dev\test-utf8

		System.out.println("file.encoding: " + System.getProperty("file.encoding"));// UTF-8

	}

	public void test3() {
		File file = new File("test/test.txt");
		System.out.println("fileName: " + file.getName());
		System.out.println("file.getParent(): " + file.getParent());
		System.out.println("ParentFileName: " + file.getParentFile().getName());
		System.out.println("ParentFileIsDirectory: " + file.getParentFile().isDirectory());
	}

	public void testFileGetParent() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(FileTest.class.getResourceAsStream("test.txt")));
			String str = "";
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File file = new File(".").getAbsoluteFile();
	}

	public void test2() {
		try {
			System.out.println("fileName: ");
			File file = new File(new URI("./test.txt"));
			System.out.println("fileName: " + file.getName());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void testFileDelete() {
		BufferedReader br = null;
		File file = null;
		String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "com" + File.separator
				+ "test" + File.separator;
		try {
			file = new File(path + "test.txt");
			br = new BufferedReader(new FileReader(file));
			String str = "";
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				throw new Exception("=====");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				if (br != null) {
					br.close();
					br = null;
				}
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			boolean flag = file.delete();
			if (flag) {
				System.out.println("成功删除文件!");
			} else {
				System.out.println("删除文件失败，请确认文件是否打开或正在被使用!");
			}
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File file2 = new File("FileTest.java").getAbsoluteFile();
		System.out.println(file2.getAbsolutePath());
	}

	public String getCurrentPath() {
		String path = "";
		File file = new File("src/com/test/conf.agent");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		path = file.getAbsolutePath();
		System.out.println("currentPath: " + path);

		// new File("conf.agent");
		// new File("bootCounter.agent");

		String p = getClassFilePath(FileTest.class);
		System.out.println(p.substring(0, p.lastIndexOf(File.separator) + 1));
		return path;
	}

	public static File getClassFile(Class clazz) {
		URL path = clazz.getResource(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1) + ".classs");
		if (path == null) {
			String name = clazz.getName().replaceAll("[.]", "/");
			path = clazz.getResource("/" + name + ".class");
		}
		return new File(path.getFile());
	}

	/**
	 * 得到当前类的路径
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassFilePath(Class clazz) {
		try {
			return java.net.URLDecoder.decode(getClassFile(clazz).getAbsolutePath(), "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
	}

}
