package com.codefans.task;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.codefans.java.util.HashCodeUtil;

public class HashCodeTool {

	private HashCodeUtil hashCodeUtil;
	private FileWriter writer;
	private String outFileName;
	private String inFileName;
	private Map<String, FileHashObj> hashObjs;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashCodeTool tool = new HashCodeTool();
		tool.computeFilesHashCode();
		// tool.validFilesHashCode();
	}

	public void computeFilesHashCode() {
		List<String> paths = new ArrayList<String>();
		// paths.add("H:\\My Virtual Machines\\windows7cn_32bit");
		// paths.add("H:\\My Virtual Machines\\windows7cn_64bit");
		// paths.add("H:\\My Virtual Machines\\Exchange2010En");
		// paths.add("H:\\My Virtual Machines\\xpcnsp3");
		// paths.add("H:\\My Virtual Machines\\win2008r2sp1");
		// paths.add("H:\\My Virtual Machines\\win2003CNsp2");
		// paths.add("H:\\My Virtual Machines\\win2003x64CN");
		paths.add("D:\\software\\Android\\网络社区\\tmp");
		outFileName = "hashcodes_before_upload.txt";
		this.computeFilesHashCode(paths);
	}

	public void validFilesHashCode() {
		List<String> paths = new ArrayList<String>();
		paths.add("H:\\My Virtual Machines\\windows7cn_32bit");
		paths.add("H:\\My Virtual Machines\\windows7cn_64bit");
		inFileName = "hashcodes_before_upload.txt";
		this.validFilesHashCode(paths);
	}

	public void computeFilesHashCode(List<String> paths) {
		String path = "";
		String out = "";
		if (paths != null && paths.size() > 0) {
			hashCodeUtil = new HashCodeUtil();
			for (int i = 0; i < paths.size(); i++) {
				path = paths.get(i);
				out = path + File.separator + outFileName;
				writer = this.getFileWriter(out, true);

				File dir = new File(path);
				if (dir.isDirectory()) {
					File[] files = dir.listFiles(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {
							boolean flag = false;
							if (!name.equalsIgnoreCase(outFileName)) {
								flag = true;
							}
							return flag;
						}
					});
					if (files != null && files.length > 0) {
						File file = null;
						for (int j = 0; j < files.length; j++) {
							file = files[j];
							this.append(file.getName());
							this.append("	MD5: " + hashCodeUtil.computeFileMD5(file));
							this.append("	SHA: " + hashCodeUtil.computeFileSHA(file));
						}
					}
				}

				this.close();

			}
		} else {
			System.out.println("paths can not be empty!");
		}
	}

	public Map<String, FileHashObj> getFileHashObj(String file) {
		hashObjs = new HashMap<String, FileHashObj>();

		try {
			File f = new File(file);
			if (!f.exists()) {
				System.out.println("can not find file:[" + file + "]");
			}

			Scanner sc = new Scanner(f);
			String line = "";
			String fileName = "";
			String md5 = "";
			String sha = "";
			boolean findMd5 = false;
			boolean findSHA = false;
			FileHashObj hashObj = null;
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if (line != null && line.trim().length() > 0) {
					if (line.contains("MD5:")) {
						md5 = line.substring(line.indexOf("MD5:") + 4).trim();
						findMd5 = true;
					} else if (line.contains("SHA:")) {
						sha = line.substring(line.indexOf("SHA:") + 4).trim();
						findSHA = true;
					} else {
						fileName = line.trim();
					}
				}
				if (findMd5 && findSHA) {
					hashObj = new FileHashObj();
					hashObj.setFileName(fileName);
					hashObj.setMd5(md5);
					hashObj.setSha(sha);
					hashObjs.put(fileName, hashObj);
					findMd5 = false;
					findSHA = false;
				}
			}

			// this.print(hashObjs);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashObjs;
	}

	public void validFilesHashCode(List<String> paths) {
		String path = "";
		String in = "";
		if (paths != null && paths.size() > 0) {
			hashCodeUtil = new HashCodeUtil();
			for (int i = 0; i < paths.size(); i++) {
				path = paths.get(i);
				in = path + File.separator + inFileName;
				this.getFileHashObj(in);

				File dir = new File(path);
				if (dir.isDirectory()) {
					File[] files = dir.listFiles(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {
							boolean flag = false;
							if (!name.equalsIgnoreCase(inFileName)) {
								flag = true;
							}
							return flag;
						}
					});
					if (files != null && files.length > 0) {
						File file = null;
						String fileName = "";
						String md5 = "";
						String sha = "";
						FileHashObj hashObj = null;

						for (int j = 0; j < files.length; j++) {
							file = files[j];
							fileName = file.getName();
							hashObj = hashObjs.get(fileName);

							md5 = hashCodeUtil.computeFileMD5(file);
							sha = hashCodeUtil.computeFileSHA(file);

							if (hashObj != null) {
								if (!md5.equals(hashObj.getMd5())) {
									System.out.println(
											"file:[" + fileName + "] is invalid, md5 is not the same as before!");
									System.out.println("newMD5:[" + md5 + "]");
									System.out.println("oldMD5:[" + hashObj.getMd5() + "]");
								}
								if (!sha.equals(hashObj.getSha())) {
									System.out.println(
											"file:[" + fileName + "] is invalid, sha is not the same as before!");
									System.out.println("newSHA:[" + sha + "]");
									System.out.println("oldSHA:[" + hashObj.getSha() + "]");
								}
								if (md5.equals(hashObj.getMd5()) && sha.equals(hashObj.getSha())) {
									System.out.println("file:[" + fileName
											+ "] is valid! Both MD5 and SHA is the same as before!");
								}
							} else {
								System.out.println("can not find FileHashObj by key:[" + fileName + "]");
							}

						}
					}
				} // end if

			} // end for

		} else {
			System.out.println("paths can not be empty!");
		}
	}

	public FileWriter getFileWriter(String outFile, boolean append) {
		try {
			File f = new File(outFile);
			if (!f.exists()) {
				f.createNewFile();
			} else {
				f.delete();
			}
			writer = new FileWriter(f, append);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer;
	}

	public void append(String content) {
		try {
			writer.append(content);
			writer.append("\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (writer != null) {
				writer.close();
				writer = null;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void print(Map<String, FileHashObj> hashObjs) {
		Iterator<String> iter = hashObjs.keySet().iterator();
		String key = "";
		FileHashObj hashObj = null;
		while (iter.hasNext()) {
			key = iter.next();
			hashObj = hashObjs.get(key);
			System.out.println(hashObj.toString());
		}
	}

	class FileHashObj {
		private String fileName;
		private String md5;
		private String sha;

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getMd5() {
			return md5;
		}

		public void setMd5(String md5) {
			this.md5 = md5;
		}

		public String getSha() {
			return sha;
		}

		public void setSha(String sha) {
			this.sha = sha;
		}

		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("fileName:[").append(fileName).append("]\r\n");
			sb.append("md5:[").append(md5).append("]\r\n");
			sb.append("sha:[").append(sha).append("]\r\n");
			return sb.toString();
		}
	}

}
