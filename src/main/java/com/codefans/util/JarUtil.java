package com.codefans.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * 
 * 1.找方法的功能未实现
 * 
 * */
public class JarUtil {

	private File file;
	private String path;
	private String path2;
	private JarFile jarFile;
	private String jarName;
	private JarFile jarFile2;
	private String jarName2;

	private String logType; // log, console, both
	private String logPath;

	private String[] clses;// 需要查找的类
	private String[] jars;// 查找类的范围

	private Set set;
	private Map map;

	@Before
	public void before() {
		logType = "both";
		// path = "C:\\Documents and Settings\\workbench\\桌面\\230_lib";
		// path = "D:\\dev\\FB-Huaxin-2.1.11-fx-33577\\build\\lib";
		// path = "D:\\dev\\ewsjavaapi\\lib";
		// path = "D:\\dev\\eas_monitor\\build\\lib";
		// path = "D:\\dev\\eas_monitor\\build\\lib_snmp";
		// path = "D:\\dev\\eas_monitor\\build\\lib_snmp\\8";
		// path = "D:\\dev\\EAS_SVN\\build\\lib";
		// path = "D:\\dev\\RB-2.1.x-gx\\build\\lib";
		// path = "D:\\dev\\EeaMonitor-Saas\\monitor\\lib";
		path = "C:\\hostinglib\\lib";
		// path = "D:\\dev\\EAS_MONITOR\\build\\lib";
		// path = "C:\\EAS7Monitor\\build\\lib";
		// path = "D:\\dev\\FB-Huaxin-2.1.11-fx-33577\\build\\lib";

		// path = "D:\\dev\\eas_monitor\\build\\lib_snmp";
		// path = "C:\\Documents and Settings\\workbench\\桌面\\EAS7.0
		// Monitor分离\\单机版\\build\\lib";
		// path2 =
		// "D:\\csz\\项目\\更新记录\\添加功能——将SNMP移植到Hosting\\Hosting移植_安装部署\\lib\\新建文件夹";
		// path2 = "D:\\dev\\Hosting-Monitor\\build\\lib";
		// path2 = "D:\\dev\\Hosting-RB-1.2.x\\build\\lib";
		path2 = "D:\\git\\test-utf8\\lib";
		// path2 = "C:\\Java\\jdk1.6.0_45_32bit\\jre\\lib";
		// path2 = "D:\\dev\\MT-2.1.17-fx\\build\\lib";
		// path2 = "C:\\Program Files\\Bamboo\\atlassian-bamboo\\WEB-INF\\lib";
		// path2 = "D:\\Development\\Bamboo\\atlassian-bamboo\\WEB-INF\\lib";
		// path2 =
		// "C:\\develop\\eclipse-SDK-3.8RC2-win32-x86_64\\eclipse\\plugins";
		// path2 = "D:\\csz\\jars&docs\\apache\\Apache
		// ActiveMQ\\apache-activemq-5.1.0-bin\\lib";
		// path2 =
		// "C:\\apache-tomcat-6.0.35-windows-x64\\apache-tomcat-6.0.35\\webapps\\webservicetest\\WEB-INF\\lib";

		// IntelliJ
		// logPath = System.getProperty("user.dir") + File.separator +
		// "Test-utf8" + File.separator + "src" + File.separator +
		// "jarUtil.log";
		// Eclipse
		logPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "jarUtil.log";
		// System.out.println(logPath);
	}

	@Test
	public void test() {// com.uwyn.rife.continuations.ContinuableObject
		// findClassInPath("javax.activation.DataHandler", path);
		// findClassInPath("DataHandler", path);
		// javax.mail.internet.MimeMultipart
		// findClassInPath("FileUtils", path2); //
		// findClassInPath("BeanNameAware", path2); //spring-beans-2.5.1.jar
		// findClassInPath("MalformedPatternException", path2);
		// //jakarta-oro-2.0.8.jar
		// findClassInPath("AxisServlet", path2);
		// //axis2-transport-http-1.6.2.jar
		// findClassInPath("SOAPMonitorService", path2);
		// //axis2-soapmonitor-servlet-1.6.2.jar
		// findClassInPath("MimeMultipart", path2); //mail.jar
		// findClassInPath("DataHandler", path2); //activation.jar
		// findClassInPath("BambooLicenseManagerImpl", path2); //activation.jar
		findClassInPath("NestableException", path2); // activation.jar
		// this.copyJarToPath("atlassian", path2, "C:\\Temp\\allJars");
		// findMethodInPath("getFileUtils"); //has problem
		// findDultiClass();

		// findConflict("Type3Message"); //具体的类，查找该类冲突的包；不填或填all则是查找所有有冲突的类
		// findConflict("all");

		// testMyList();
		// p(getJarStringFromPaths(new String[]{path}));
	}

	@After
	public void after() {
		// if(log) {
		// log();
		// }
	}

	public void findClassInJar(String clsName, String jarPath) {// name=CodeFormatter
		file = new File(jarPath);
		try {
			jarFile = new JarFile(file);
			Enumeration en = jarFile.entries();
			while (en.hasMoreElements()) {
				JarEntry entry = (JarEntry) en.nextElement();
				if (!entry.isDirectory()) {
					String cls = entry.getName();
					// p(cls);
					if (cls.endsWith(".class") || cls.endsWith(".java")) {
						int index = cls.lastIndexOf("/");
						if (index > 0) {
							// p(index);
							String cn = cls.substring(index + 1);
							// p(cn);
							cn = cn.substring(0, cn.indexOf("."));
							if (cn.equals(clsName)) {
								p(jarName);
								p(cls);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void findClassInJars(String clsName, String[] jars) {// name=CodeFormatter
		if (jars != null) {
			try {
				String jp = "";
				for (int i = 0; i < jars.length; i++) {
					jp = jars[i];
					file = new File(jp);
					jarFile = new JarFile(file);
					Enumeration en = jarFile.entries();
					while (en.hasMoreElements()) {
						JarEntry entry = (JarEntry) en.nextElement();
						if (!entry.isDirectory()) {
							String cls = entry.getName();
							// p(cls);
							if (cls.endsWith(".class") || cls.endsWith(".java")) {
								int index = cls.lastIndexOf("/");
								if (index > 0) {
									// p(index);
									String cn = cls.substring(index + 1);
									// p(cn);
									cn = cn.substring(0, cn.indexOf("."));
									if (cn.equals(clsName)) {
										p(jarName);
										p(cls);
									}
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void findClassInPath(String clsName, String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			int sum = 0;
			for (int i = 0; i < files.length; i++) {//
				File f = files[i];
				// p(f.getName()); //CodeFormatter.class
				// p(f.getAbsolutePath());//eg.org/eclipse/jdt/internal/formatter/old/CodeFormatter.class
				String jarAbsolutePath = f.getAbsolutePath();
				if (f.isFile() && f.getName().endsWith(".jar")) {
					sum++;
					jarName = f.getName();
					// p(jarAbsolutePath);
					findClassInJar(clsName, jarAbsolutePath);
				}
			}
			p("共有： " + sum + "个jar文件");
		}
	}

	// has problem
	public void findMethodInClass(JarFile jarFile, JarEntry jarEntry, String method) {
		String clsName = jarEntry.getName();
		try {

			// String jarURL = "file:" + jarFile.getName();
			String jarURL = "file://C:/hostinglib/lib/activation.jar";

			Class jarUtil = JarUtil.class;
			// URL url =
			// jarUtil.getResource("C:/hostinglib/lib/activation.jar");
			URL url = jarUtil.getResource("file://C:/hostinglib/lib/activation.jar");

			// URL[] us = {new URL(jarURL)}; //jarPath为jar包名和后缀
			URL[] us = { url };
			ClassLoader loader = new URLClassLoader(us);
			Class clss = loader.loadClass(clsName);

			// Class clss = Class.forName(clsName);

			Method[] meths = clss.getMethods();
			if (meths != null) {
				for (int i = 0; i < meths.length; i++) {
					Method meth = meths[i];
					if (meth.getName().equals(method)) {
						System.out.println(clsName);
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// int num = cls.getMethod();
		// Attributes attr = null;
		// try {
		// attr = cls.getAttributes();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// if(attr != null) {
		// for(int i = 0; i < attr.size(); i ++) {
		// Set set = attr.entrySet();
		// Iterator iter = set.iterator();
		// while(iter.hasNext()) {
		// Map.Entry entry = (Map.Entry)iter.next();
		// p(entry.getKey() + ": " + entry.getValue());
		// p("path: " + cls.getName());
		// }
		// }
		// }
	}

	public void findMethodInJar(String method, String jarPath) {
		file = new File(jarPath);
		try {
			jarFile = new JarFile(file);
			Enumeration en = jarFile.entries();
			while (en.hasMoreElements()) {
				JarEntry entry = (JarEntry) en.nextElement();
				if (!entry.isDirectory()) {
					String cls = entry.getName();
					// p(cls);
					if (cls.endsWith(".class") || cls.endsWith(".java")) {
						findMethodInClass(jarFile, entry, method);
						// int index = cls.lastIndexOf("/");
						// if(index > 0) {
						// // p(index);
						// String cn = cls.substring(index + 1);
						//// p(cn);
						// cn = cn.substring(0, cn.indexOf("."));
						// if(cn.equals(method)) {
						// p(cls);
						// }
						// }
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void findMethodInPath(String method) {
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			int sum = 0;
			for (int i = 0; i < files.length; i++) {//
				File f = files[i];
				// p(f.getName()); //CodeFormatter.class
				// p(f.getAbsolutePath());//eg.org/eclipse/jdt/internal/formatter/old/CodeFormatter.class
				String path = f.getAbsolutePath();
				if (f.isFile() && f.getName().endsWith(".jar")) {
					sum++;
					// p(path);
					findMethodInJar(method, path);
				}
			}
			p("共有： " + sum + "个jar文件");
		}
	}

	public void findClassInPaths(String cls, String[] paths) {
		for (int i = 0; i < paths.length; i++) {
			findClassInPath(cls, paths[i]);
		}
	}

	// 目前只支持找出出现两次的类
	public void findDultiClass() {
		map = new HashMap();
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			int sum = 0;
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				String path = f.getAbsolutePath();
				if (f.isFile() && f.getName().endsWith(".jar")) {
					sum++;
					// System.out.println(path);
					try {
						jarFile = new JarFile(f);
						Enumeration en = jarFile.entries();
						while (en.hasMoreElements()) {
							JarEntry entry = (JarEntry) en.nextElement();
							if (!entry.isDirectory()) {
								String cls = entry.getName();
								if (cls.endsWith(".class") || cls.endsWith(".java")) {
									int index = cls.lastIndexOf("/");
									if (index > 0) {
										String cn = cls.substring(index + 1);
										cn = cn.substring(0, cn.indexOf("."));
										if (!map.containsKey(cn)) {
											Map m = new HashMap();
											m.put("class", cls);
											m.put("jar", f.getName());
											map.put(cn, m);
										} else {
											p(cn);
											Map m = (Map) map.get(cn);
											p(m.get("class") + " 包:" + m.get("jar"));
											p(cls + " 包： " + f.getName());
										}
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			// System.out.println("共有： " + sum + "个jar文件");
		}
	}

	public void p(Object o) {
		String str = (String) o;
		String s = "";
		try {
			s = new String(str.getBytes(), "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (logType != null && logType.equals("log")) {
			log(s + "\n");
		} else if (logType != null && logType.equals("both")) {
			System.out.println(o);
			log(s + "\n");
		} else {
			System.out.println(o);
		}
	}

	// 将日志追加到日志文件
	public void log(String str) {
		File file = new File(logPath);
		boolean f = false;
		RandomAccessFile raf = null;
		try {
			if (!file.exists()) {
				f = file.createNewFile();
			} else {
				f = true;
			}
			// System.out.println("f: " + f);
			if (f) {
				raf = new RandomAccessFile(file, "rw");
				long num = raf.length();
				raf.seek(num);
				raf.writeBytes(str);
			}
		} catch (IOException e) {
			System.out.println("logPath:" + logPath);
			e.printStackTrace();
		} finally {
			try {
				if (raf != null) {
					raf.close();
					raf = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void findConflict(String scope) {
		map = new HashMap();
		File file = new File(path);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			int sum = 0;
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				String path = f.getAbsolutePath();
				if (f.isFile() && f.getName().endsWith(".jar")) {
					sum++;
					// System.out.println(path);
					try {
						jarFile = new JarFile(f);
						Enumeration en = jarFile.entries();
						while (en.hasMoreElements()) {
							JarEntry entry = (JarEntry) en.nextElement();
							if (!entry.isDirectory()) {
								String cls = entry.getName(); // .class or .java
																// or other
								if (cls.endsWith(".class") || cls.endsWith(".java")) {
									if (!map.containsKey(cls)) {
										// List list = new ArrayList();
										MyList list = new MyList();
										list.add(f.getName());
										map.put(cls, list);
									} else {
										// p(cls);
										MyList list = (MyList) map.get(cls);
										list.add(f.getName());

										map.put(cls, list);
										// p(m.get("class") + " 包:" +
										// m.get("jar"));
										// p(cls + " 包： " + f.getName());
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			// System.out.println("共有： " + sum + "个jar文件");
		}

		dataUtil(map);
		// printMap(map, scope);
	}

	public void printMap(Map map, String scope) {
		Iterator iter = map.entrySet().iterator();
		String cls = "";
		String temp = "";
		List list = null;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			cls = (String) entry.getKey();
			int index = cls.lastIndexOf("/");
			if (index > 0) {
				temp = cls.substring(index + 1);
				temp = temp.substring(0, temp.indexOf(".")); // 从org/eclipse/jdt/internal/compiler/CompilationResult.class
																// 截取出CompilationResult
			}
			list = (List) entry.getValue();
			if (list.size() >= 2) {
				if ((scope == null || scope.equals("") || scope.equalsIgnoreCase("all")) || scope.equals(temp)) {
					p(cls);
					for (int i = 0; i < list.size(); i++) {
						p(list.get(i));
					}
				}
			}
		}
	}

	public void dataUtil(Map map) {// Map<String, List> , String cls, List<Jar>
									// 9:44
		List<String> clsList = null;
		MyList result = new MyList();
		Map m = new HashMap();

		Iterator iter = map.entrySet().iterator();
		String cls = "";
		MyList list = null;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			cls = (String) entry.getKey();
			list = (MyList) entry.getValue();

			if (list.size() >= 2) {
				if (!m.containsKey(list)) {
					clsList = new ArrayList<String>();
					clsList.add(cls);
					m.put(list, clsList);
				} else {
					List<String> lst = (List<String>) m.get(list);
					lst.add(cls);
					m.put(list, lst);
				}
			}
		}

		Iterator it = m.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			MyList l = (MyList) entry.getKey();
			List<String> sl = (List<String>) entry.getValue();
			// System.out.println(l.toString() + "冲突的类共: " + sl.size() + "个,
			// 如下所示:");
			p(l.toString() + "冲突的类共: " + sl.size() + "个,  如下所示:");
			for (int i = 0; i < sl.size(); i++) {
				String str = sl.get(i);
				// System.out.println(str);
				p(str);
			}
		}
	}

	class MyList extends ArrayList {

		@Override
		public boolean equals(Object o) {
			List list = (List) o;
			boolean flag = false;
			for (int i = 0; i < list.size(); i++) {
				String str = (String) list.get(i);
				flag = false;
				for (int j = 0; j < this.size(); j++) {
					String str2 = (String) this.get(j);
					if (str.equals(str2)) {
						flag = true;
					}
				}
				if (!flag) {
					break;
				}
			}
			return flag;
		}

		@Override
		public int hashCode() {
			int n = 0;
			for (int i = 0; i < this.size(); i++) {
				String str = (String) this.get(i);
				char val[] = str.toCharArray();// 字符串是用字符数组表示的.
				int len = str.length();// 字符串长度
				for (int j = 0; j < len; j++) {
					n = 31 * n + val[j++];
				}
				n = n + len;
			}
			return n;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("[");
			for (int i = 0; i < this.size(); i++) {
				String str = (String) this.get(i);
				sb.append(str);
				if (i != this.size() - 1) {
					sb.append(", ");
				} else {
					sb.append("]");
				}
			}
			return sb.toString();
		}
	}

	// 在多个Jar中分别查找给定的多个类
	public void findClassesInJars() {

	}

	public void testMyList() {

		List l = new ArrayList();
		l.add("jar001");
		l.add("jar002");
		l.add("jar003");

		List l2 = new ArrayList();
		l2.add("jar003");
		l2.add("jar001");
		l2.add("jar002");

		System.out.println("l.equals(l2): " + l.equals(l2));
		System.out.println("l==l2: " + (l == l2));

		MyList list = new MyList();
		list.add("jar001");
		list.add("jar002");
		list.add("jar003");

		MyList list2 = new MyList();
		list2.add("jar003");
		list2.add("jar001");
		list2.add("jar002");

		System.out.println("list.equals(list2): " + list.equals(list2));
		System.out.println("list == list2: " + (list == list2));

		Map map = new HashMap();
		map.put(list, "aaaaaaa");
		map.put(list2, "bbbbbbb"); // list2与list的equals方法和hashCode方法相同，Map就认为他两是同一个key，所以添加list2时，"bbbbbbb"就将"aaaaaaa"覆盖了
		System.out.println(map.get(list));// bbbbbbb
		System.out.println(map.get(list2));// bbbbbbb

	}

	class JarObject {
		String cls;
		List list;

	}

	// public class TestMyList {
	// public static void main(String[] args) {
	// List l = new ArrayList();
	// l.add("jar001");
	// l.add("jar002");
	// l.add("jar003");
	//
	// List l2 = new ArrayList();
	// l2.add("jar003");
	// l2.add("jar001");
	// l2.add("jar002");
	//
	// System.out.println("l.equals(l2): " + l.equals(l2)); //输出false
	// System.out.println("l==l2: " + (l==l2)); //输出false
	//
	//
	// MyList list = new MyList();
	// list.add("jar001");
	// list.add("jar002");
	// list.add("jar003");
	//
	// MyList list2 = new MyList();
	// list2.add("jar003");
	// list2.add("jar001");
	// list2.add("jar002");
	//
	// System.out.println("list.equals(list2): " + list.equals(list2)); //输出true
	// System.out.println("list == list2: " + (list == list2)); //输出false
	//
	// Map map = new HashMap();
	// map.put(list, "aaaaaaa");
	// map.put(list2, "bbbbbbb");
	//
	// /*
	// map.get(list), 输出bbbbbbb， 而不是aaaaaaa,
	// 原因是：list2与list的equals方法和hashCode方法相同，
	// Map就认为他两是同一个key，所以添加list2时，"bbbbbbb"就将"aaaaaaa"覆盖了
	// */
	// System.out.println(map.get(list)); //输出bbbbbbb
	// System.out.println(map.get(list2)); //输出bbbbbbb
	// }
	// }

	public String getJarStringFromPaths(String[] paths) {
		StringBuilder sb = new StringBuilder();
		// String prefix = "set CP=%CP%;%LIB_PATH%/";
		// String suffix = ";";
		String prefix = "";
		String suffix = "";
		for (String path : paths) {
			sb.append(this.getJarStringFromPath(prefix, suffix, path));
		}
		return sb.toString();
	}

	public String getJarStringFromPath(String prefix, String suffix, String path) {
		StringBuilder sb = new StringBuilder();

		File direct = new File(path);
		File[] files = null;
		File f = null;
		String fileName = "";
		// String prefix = "set CP=%CP%;%LIB_PATH%/";
		// String suffix = ";";
		int count = 0;
		if (direct.isDirectory()) {
			files = direct.listFiles();
			for (int i = 0; i < files.length; i++) {
				f = files[i];
				fileName = f.getName();
				if (fileName.endsWith(".jar")) {
					count++;
					sb.append(prefix + fileName + suffix + "\n");
				}
			}
			p("共有Jar包：" + count);
		}
		return sb.toString();
	}

	public void copyJarToPath(String containStr, String jarPath, String destPath) {
		Set<String> jars = this.getJarsByStr(containStr, jarPath);
		FileUtil.copy(jars, destPath);
	}

	public Set<String> getJarsByStr(String containStr, String destPath) {
		Set<String> jars = new HashSet<String>();

		File file = new File(destPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			int sum = 0;
			for (int i = 0; i < files.length; i++) {//
				File f = files[i];
				String jarAbsolutePath = f.getAbsolutePath();
				if (f.isFile() && f.getName().endsWith(".jar")) {
					jarName = f.getName();
					if (isContainStr(f, containStr)) {
						sum++;
						jars.add(jarAbsolutePath);
					}
				}
			}
			p("共有： " + sum + "个jar文件");
		}

		return jars;
	}

	public boolean isContainStr(File jar, String str) {
		boolean isContain = false;
		JarFile jarFile = null;

		try {
			jarFile = new JarFile(jar);
			Enumeration en = jarFile.entries();
			while (en.hasMoreElements()) {
				JarEntry entry = (JarEntry) en.nextElement();
				if (!entry.isDirectory()) {
					String cls = entry.getName();
					if (cls.endsWith(".class") || cls.endsWith(".java")) {
						int index = cls.indexOf(str);
						if (index > 0) {
							p(jarFile.getName());
							// p(cls);
							isContain = true;
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return isContain;
	}
}
