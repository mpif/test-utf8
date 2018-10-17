package com.codefans.util.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassFileTool {

	private String source = "default Source Path";
	private String dest = "C:/Users/Administrator/Desktop/classes";

	public static void main(String[] args) {
		ClassFileTool classFileTool = new ClassFileTool();
		classFileTool.copy();
	}

	public void copy() {
		String classSourcePath = "D:/dev/HostingTrunk/dist/classes";
		InputStream is = ClassFileTool.class.getResourceAsStream("classes.txt");
		String classSourcePrefix = "src/main/java/";
		String dest = "C:/Users/Administrator/Desktop/classes";
		copy(classSourcePath, is, classSourcePrefix, dest);
	}

	public void copy(String classSourcePath, String source, String classSourcePrefix, String dest) {
		try {
			InputStream is = new FileInputStream(new File(source));
			this.copy(classSourcePath, is, classSourcePrefix, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copy(String classSourcePath, InputStream source, String classSourcePrefix, String dest) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(source));
			String tmp = "";
			String sourceFile = "";
			String sourceName = "";
			String destFile = "";
			List<String> sources = new ArrayList<String>();
			List<String> dests = new ArrayList<String>();

			while ((tmp = br.readLine()) != null) {
				tmp = tmp.substring(tmp.indexOf(classSourcePrefix) + classSourcePrefix.length());
				sourceName = tmp.substring(tmp.lastIndexOf("/") + 1);
				tmp = tmp.substring(0, tmp.lastIndexOf("/"));

				sourceFile = classSourcePath + File.separator + tmp;

				if (sourceName.endsWith(".java")) {
					final String srcName = sourceName.replaceAll(".java", ".class");
					final String tmpName = sourceName.substring(0, sourceName.indexOf(".java"));
					File file = new File(sourceFile);
					if (file.isDirectory()) {
						File[] files = file.listFiles(new FilenameFilter() {
							@Override
							public boolean accept(File dir, String name) {
								if (name.equals(srcName) || name.startsWith(tmpName + "$")) {
									return true;
								}
								return false;
							}

						});

						destFile = dest + File.separator + tmp;
						for (int i = 0; i < files.length; i++) {
							File f = files[i];
							sources.add(sourceFile + File.separator + f.getName());
							dests.add(destFile + File.separator + f.getName());
						}
					}
				} else {
					destFile = dest + File.separator + tmp;
					sources.add(sourceFile + File.separator + sourceName);
					dests.add(destFile + File.separator + sourceName);
				}

				// System.out.println(tmp);
				// System.out.println(sourceFile);
				// System.out.println(destFile);

				for (int i = 0; i < sources.size(); i++) {
					this.copy(sources.get(i), dests.get(i));
				}
				System.out.println(sources.size());
				System.out.println(dests.size());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copy(String sourceFile, String destFile) {

		InputStream is = null;
		OutputStream os = null;
		try {

			String destPath = destFile.substring(0, destFile.lastIndexOf(File.separator));
			File destPathFile = new File(destPath);
			if (!destPathFile.exists()) {
				destPathFile.mkdirs();
			}

			File source = new File(sourceFile);
			File dest = new File(destFile);
			if (!dest.exists()) {
				dest.createNewFile();
			}

			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] bytes = new byte[1024];
			int count = 0;
			while ((count = is.read(bytes)) != -1) {
				os.write(bytes, 0, count);
			}
			os.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

}
