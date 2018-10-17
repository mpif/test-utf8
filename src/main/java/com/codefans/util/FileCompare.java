package com.codefans.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import org.junit.Test;

import com.codefans.java.util.HashCodeUtil;
import com.codefans.java.util.StringUtils;

public class FileCompare {

	private String one;
	private String another;
	private String[] arr;
	private String[] arr2;

	@Test
	public void test() {
//		findDiff();
		// move();
		// count();
		
//		String filePath = "D:/tmp/592incomeConfirmId.txt";
//		String newFilePath = "D:/tmp/595incomeConfirmId.txt";
		String filePath = "D:/tmp/86incomeConfirmId.txt";
		String newFilePath = "D:/tmp/83incomeConfirmId.txt";
		compareFile(filePath, newFilePath);
		
	}

	/**
	 * 求两个文件的差集、交集、并集,文件每行一条记录
	 * @param sourceFilePath
	 * @param destFilePath
	 */
	public void compareFile(String sourceFilePath, String destFilePath) {
		
		List<String> sourceList = this.fileToList(sourceFilePath);
		List<String> destList = this.fileToList(destFilePath);
		
		System.out.println(sourceFilePath + ":共" + sourceList.size() + "行.");
		System.out.println(destFilePath + ":共" + destList.size() + "行.");
		
		List<String> result = new ArrayList<String>();
		result.addAll(sourceList);
		result.removeAll(destList);
		System.out.println("只存在于[" + sourceFilePath + "]文件中,共:" + result.size() + "个:");
		print(result);
		
		result.clear();
		result.addAll(destList);
		result.removeAll(sourceList);
		System.out.println("只存在于[" + destFilePath + "]文件中,共:" + result.size() + "个:");
		print(result);
		
		result.clear();
		result.addAll(destList);
		result.retainAll(sourceList);
		System.out.println("两个文件的交集,共:" + result.size() + "个:");
//		print(result);
		
		result.clear();
		result.addAll(destList);
		result.addAll(sourceList);
		System.out.println("两个文件的并集,共:" + result.size() + "个:");
//		print(result);
		
	}
	
	public List<String> fileToList(String filePath) {
		List<String> list = new ArrayList<String>();
		Scanner sc = null;
		try {
			File file = new File(filePath);
			sc = new Scanner(new FileInputStream(file));
			String line = "";
			while(sc.hasNextLine()) {
				line = sc.nextLine().trim();
				list.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(sc);
		}
		return list;
	}
	
	public static void print(List<String> list) {
		for(String str : list) {
			System.out.println(str);
		}
	}
	
	public void count() {
		String path = "D:\\dev\\EAS_SVN\\build\\lib";
		String suffix = ".jar";
		System.out.println(FileUtil.fileCount(path, suffix));
	}

	public void findDiff() {
//		findDiffNames();
		findDiffContentFiles();
	}
	
	public void findDiffNames() {
		// one = "D:\\dev\\EAS_SVN_Monitor\\build\\lib";
		// another = "D:\\dev\\EAS_SVN_Monitor\\build\\lib_all";
		// one = "D:\\167 lib";
		// another = "D:\\dev\\eas_monitor\\build\\lib_snmp";
		// one = "D:\\dev\\EAS_TRUNK\\build\\lib";
		// one = "D:\\csz\\项目\\SNMP MONITOR\\7.0分离Monitor功能\\EAS7.0
		// Monitor分离\\beta\\build\\lib";
		// one = "D:\\dev\\EeaMonitor-Saas\\monitor\\lib";
		// another = "D:\\dev\\Hosting-Monitor\\build\\lib";
		one = "C:\\hostinglib\\lib";
		another = "D:\\dev\\Hosting-RB-1.2.x\\build\\lib";
		// another = "D:\\dev\\EAS_SVN\\build\\lib";
		// another = "D:\\dev\\Hosting-RB-1.2.x\\build\\lib";

		arr = this.getNames(one);
		// this.copy(arr, another);

		arr2 = this.getNames(another);

		// in arr2, but not in arr
		List eas_another = this.findDiffNames(arr, arr2);
		List eas_one = this.findDiffNames(arr2, arr);

		// String anotherName = another.substring(another.lastIndexOf("\\") +
		// 1);
		// String oneName = one.substring(one.lastIndexOf("\\") + 1);

		// String oneName = "EeaMonitor-Saas";
		// String anotherName = "Hosting-Monitor";
		String oneName = "hostinglib";
		String anotherName = "Hosting-RB-1.2.x";
		this.printList("-----------total: " + eas_another.size() + ", only in [" + anotherName + "]: ", eas_another);
		this.printList("-----------total: " + eas_one.size() + ", only in [" + oneName + "]: ", eas_one);

	}
	
	public void findDiffContentFiles() {
		String srcDir = "D:\\GitHub\\test-utf8\\src\\com\\messagesolution2";
//		String destDir = "D:\\GitHub\\test-utf8\\src\\com\\codefans";
		String shouldBeReplacement = "\\\\com\\\\messagesolution2";
		String replacementStr = "\\\\com\\\\codefans";
		
		findDiffContent(srcDir, shouldBeReplacement, replacementStr);
		
	}
	
	public void findDiffContent(String srcDir, String regex, String replacement) {
		File f = new File(srcDir);
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file.isDirectory()) {
					findDiffContent(srcDir + File.separator + file.getName() + File.separator, regex, replacement);
				} else {
						boolean flag = false;
						String srcFilePath = file.getAbsolutePath();
						String destFilePath = srcFilePath.replaceAll(regex, replacement);
						
						long srcLen = file.length();
						File destFile = new File(destFilePath);
						long destLen = destFile.length();
						if((srcLen - destLen) % 8 != 0) {
							System.out.println("文件[" + srcFilePath + "]与[" + destFilePath + "]文件存在区别!,字节数差异:" + ((srcLen - destLen) % 8));
						}
						
//						String srcFileMD5Str = new HashCodeUtil().computeFileMD5(srcFilePath);
//						String destFileMD5Str = new HashCodeUtil().computeFileMD5(destFilePath);
//						if(!srcFileMD5Str.equals(destFileMD5Str)) {
//							System.out.println("文件[" + srcFilePath + "]与[" + destFilePath + "]文件存在区别!");
//						}
						
				}
			}
		}
	}
	
	public void getFileHashCode(String filePath) {
		
	}

	public void move() {
		// List list = addPath(eas_another, another);
		// String dest = "C:\\Documents and Settings\\workbench\\����\\lib";
		// //copy files to dest path
		// FileUtil.copy(list, dest);

		// one = "D:\\dev\\eas_monitor\\build\\lib_snmp";
		// String source = "D:\\dev\\eas_monitor\\build\\lib";
		// String dest = "D:\\dev\\eas_monitor\\build\\lib\\snmp_lib";

		String source = "D:\\dev\\eas_monitor\\build\\lib";
		String dest = "D:\\dev\\eas_monitor\\build\\lib\\lib_monitor";
		// one = "C:\\EAS7Monitor\\build\\lib_backup";
		// arr = this.getNames(one);
		arr = this.getNames(FileCompare.class.getResourceAsStream("getjarstr.txt"));
		FileUtil.move(arr, source, dest);
	}

	public void copy(String[] arr, String path) {

	}

	public List addPath(List list, String path) {
		List llist = new ArrayList();
		if (list != null) {
			String temp = "";
			for (int i = 0; i < list.size(); i++) {
				llist.add(path + File.separator + list.get(i));
			}
		}
		return llist;
	}

	public String[] getNames(String path) {
		File file = new File(path);
		String[] arrTemp = null;
		String name = null;
		int n = 0;
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files.length > 0) {
				arrTemp = new String[files.length];
				int index = 0;
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					name = f.getName();
					if (name.endsWith(".jar")) {
						arrTemp[index++] = name;
						n++;
					}
					// System.out.println(f.getName());
					// try {
					// System.out.println(f.toURI().toURL().toString());//��ʽΪ"file:/D:/dev/EAS_SVN/build/lib/activation.jar"
					// } catch (MalformedURLException e) {
					// e.printStackTrace();
					// }
				}
			}
		}
		String[] dest = new String[n];
		if (n > 0) {
			System.arraycopy(arrTemp, 0, dest, 0, n);
		}
		return dest;
	}

	public String[] getNames(InputStream is) {
		Scanner sc = new Scanner(is);
		List list = new ArrayList();
		String temp = "";

		while (sc.hasNextLine()) {
			temp = sc.nextLine();
			if (StringUtils.isNotEmpty(temp)) {
				list.add(temp);
				p(temp);
			}
		}

		p("��: " + list.size() + "��");
		String[] arr = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = (String) list.get(i);
		}
		return arr;
	}

	/**
	 * �ü��ϵ�retainAll����, set��retainAll���ܸ���list��retainAll������
	 */
	public static Set getAllSameElement1(String[] strArr1, String[] strArr2) {
		if (strArr1 == null || strArr2 == null) {
			return null;
		}
		List list1 = new ArrayList(Arrays.asList(strArr1));
		Set set1 = new HashSet(list1);
		set1.retainAll(Arrays.asList(strArr2));
		return set1;
	}

	// in arr2, but not in arr
	public List findDiffNames(String[] arr, String[] arr2) {
		Map map = new HashMap();
		List list = new ArrayList();

		String temp = "";
		for (int i = 0; i < arr.length; i++) {
			temp = arr[i];
			if (map.get(temp) == null) {
				map.put(temp, temp);
			}
		}

		for (int k = 0; k < arr2.length; k++) {
			temp = arr2[k];
			if (map.get(temp) == null) {
				list.add(temp);
			}
		}

		return list;
	}

	public void printList(String introduction, List list) {
		if (list != null && list.size() > 0) {
			System.out.println(introduction);
			String temp = "";
			for (int i = 0; i < list.size(); i++) {
				temp = (String) list.get(i);
				System.out.println(temp);
			}
		}
	}

	public void p(Object o) {
		System.out.println(o);
	}
	
	public void close(Scanner sc) {
		if(sc != null) {
			sc.close();
			sc = null;
		}
	}
	
}
