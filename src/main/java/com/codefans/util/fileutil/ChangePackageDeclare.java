package com.codefans.util.fileutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.snmp4j.agent.mo.snmp.SNMPv2MIB.SysOREntry;

/**
 * @author Sean
 * @date 2016年5月7日上午9:16:04
 * @desc 修改包名工具类,非线程安全的
 */
public class ChangePackageDeclare {
//	/**
//	 * java源文件所在绝对路径根目录
//	 */
//	private String sourceAbsolutePathRoot;
//	private boolean winSeparator;
//	/**
//	 * 临时文件后缀名
//	 */
//	private String tempFileSuffix = ".temp";
//	/**
//	 * java文件原包名前缀
//	 */
//	private String originPackagePrefix;
//	/**
//	 * java文件修改后的包名前缀
//	 */
//	private String destPackagePrefix;
//	/**
//	 * 需要修改包名的根目录
//	 */
//	private String originFilePathRoot;
//	/**
//	 * 修改包名后的根目录
//	 */
//	private String destFilePathRoot;
//
//	public ChangePackageDeclare(String sourceAbsolutePathRoot, String originPackagePrefix, String destPackagePrefix) {
//		this.sourceAbsolutePathRoot = sourceAbsolutePathRoot;
//		this.originPackagePrefix = originPackagePrefix;
//		this.destPackagePrefix = destPackagePrefix;
//		
//		if(sourceAbsolutePathRoot.lastIndexOf(File.separator) > 0) {
//			originFilePathRoot = sourceAbsolutePathRoot + originPackagePrefix.replaceAll("\\.", "/");
//			destFilePathRoot = sourceAbsolutePathRoot + destPackagePrefix.replaceAll("\\.", "/");
//		} else {
//			originFilePathRoot = sourceAbsolutePathRoot + File.separator + originPackagePrefix.replaceAll(".", "/");
//			destFilePathRoot = sourceAbsolutePathRoot + File.separator + destPackagePrefix.replaceAll(".", "/");
//		}
//		
//		File destDir = new File(destFilePathRoot);
//		if(!destDir.exists()) {
//			boolean flag = destDir.mkdirs();
//			if(flag) {
//				System.out.println("目录[" + destFilePathRoot + "]创建成功!");
//			}
//		}
//		
//		System.out.println("sourceAbsolutePathRoot:" + sourceAbsolutePathRoot);
//		System.out.println("originPackagePrefix:" + originPackagePrefix);
//		System.out.println("destPackagePrefix:" + destPackagePrefix);
//		System.out.println("originFilePathRoot:" + originFilePathRoot);
//		System.out.println("destFilePathRoot:" + destFilePathRoot);
//		
//	}
//
//	public ChangePackageDeclare(String sourceAbsolutePathRoot) {
//		this.sourceAbsolutePathRoot = sourceAbsolutePathRoot;
//		Properties props = System.getProperties();
//		String fileSeparator = props.getProperty("file.separator");
//		winSeparator = fileSeparator.equals("\\");
//	}

	public ChangePackageDeclare() {

	}

	/**
	 * 只能更改单个包,参数由构造方法传入
	 */
	public void change() {
//		change(originFilePathRoot, destFilePathRoot, originPackagePrefix, destPackagePrefix);
	}
	
	public void change(String sourceAbsolutePathRoot, String originPackagePrefix, String destPackagePrefix) {
		
		String originFilePathRoot = "";
		String destFilePathRoot = "";
		
		if(sourceAbsolutePathRoot.lastIndexOf(File.separator) > 0) {
			originFilePathRoot = sourceAbsolutePathRoot + originPackagePrefix.replaceAll("\\.", "/");
			destFilePathRoot = sourceAbsolutePathRoot + destPackagePrefix.replaceAll("\\.", "/");
		} else {
			originFilePathRoot = sourceAbsolutePathRoot + File.separator + originPackagePrefix.replaceAll(".", "/");
			destFilePathRoot = sourceAbsolutePathRoot + File.separator + destPackagePrefix.replaceAll(".", "/");
		}
		
		File destDir = new File(destFilePathRoot);
		if(!destDir.exists()) {
			boolean flag = destDir.mkdirs();
			if(flag) {
				System.out.println("目录[" + destFilePathRoot + "]创建成功!");
			}
		}
		
		System.out.println("sourceAbsolutePathRoot:" + sourceAbsolutePathRoot);
		System.out.println("originPackagePrefix:" + originPackagePrefix);
		System.out.println("destPackagePrefix:" + destPackagePrefix);
		System.out.println("originFilePathRoot:" + originFilePathRoot);
		System.out.println("destFilePathRoot:" + destFilePathRoot);
		
		change(originFilePathRoot, originFilePathRoot, destFilePathRoot, originPackagePrefix, destPackagePrefix);
	}
	
	public void change(String currentFilePathRoot, String originFilePathRoot, String destFilePathRoot, String originPackagePrefix, String destPackagePrefix) {
		File f = new File(currentFilePathRoot);
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file.isDirectory()) {
					change(currentFilePathRoot + File.separator + file.getName() + File.separator, originFilePathRoot, destFilePathRoot, originPackagePrefix, destPackagePrefix);
				} else {
					try {
						boolean flag = false;
						String srcFilePath = file.getAbsolutePath();
						String destFilePath = this.getDestFilePath(originFilePathRoot, destFilePathRoot, srcFilePath);
						if (file.getName().endsWith(".java")) {
//							if(file.getName().equals("Test.java")) {
								flag = createNewFile(srcFilePath, destFilePath, originPackagePrefix, destPackagePrefix, true);
//							}
						} else {
							flag = createNewFile(srcFilePath, destFilePath, originPackagePrefix, destPackagePrefix, false);
						}
//						if (flag) {
//							boolean deleteSuccess = file.delete();
//							if(!deleteSuccess) {
//								System.out.println("文件[" + file.getAbsolutePath() + "]删除失败!");
//							}
////							rename(file.getAbsolutePath());
//						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public String getDestFilePath(String originFilePathRoot, String destFilePathRoot, String srcFilePath) {
		StringBuffer destFilePath = new StringBuffer();
		String srcFilePathSuffix = srcFilePath.substring(srcFilePath.indexOf(originFilePathRoot) + originFilePathRoot.length() + 1); 
		destFilePath.append(destFilePathRoot).append(srcFilePathSuffix);
		return destFilePath.toString();
	}
	
	public boolean createNewFile(File srcFile, File destFile, String originPackagePrefix, String destPackagePrefix, boolean isJavaFile) {
		boolean flag = false;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(srcFile));
			
			if(srcFile.getName().equals("ExcelReaderAndWriter.java")) {
				System.out.println(srcFile.getAbsolutePath());
			}
			
			bw = new BufferedWriter(new FileWriter(destFile));
			String tmp = "";
			String temp = "";
			long srcLen = srcFile.length();
			long readLen = 0;
			int lineIndex = 0;
			
			while ((tmp = br.readLine()) != null) {
				readLen += tmp.length();
				lineIndex++;
				
				if(lineIndex == 123) {
					System.out.println(tmp);
				}
				if(isJavaFile) {
					if ((tmp.startsWith("package") || tmp.startsWith("import")) && tmp.indexOf(originPackagePrefix) >= 0) {
						temp = tmp.replaceAll(originPackagePrefix, destPackagePrefix);
						bw.write(temp);
						
					} else {
						bw.write(tmp);
					}
				} else {
					bw.write(tmp);
				}
				
				if(srcLen - readLen >= 2) {
					bw.newLine();
//					bw.write("\r\n");
					readLen += 2;
				}
				
			}
			
			br.close();
			br = null;
			
			bw.close();

			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
				if (bw != null) {
					bw.close();
					bw = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean createNewFileRandomAccessFile(File srcFile, File destFile, String originPackagePrefix, String destPackagePrefix, boolean isJavaFile) {
		boolean flag = false;
		BufferedReader br = null;
		BufferedWriter bw = null;
		RandomAccessFile raf = null;
		
		try {
//			br = new BufferedReader(new FileReader(srcFile));
			
			if(srcFile.getName().equals("ExcelReaderAndWriter.java")) {
				System.out.println(srcFile.getAbsolutePath());
			}
			
//			bw = new BufferedWriter(new FileWriter(destFile));
			
			String temp = "";
			long readLen = 0;
			
			bw = new BufferedWriter(new FileWriter(destFile));
			raf = new RandomAccessFile(srcFile,"r");
//			String line = raf.readLine();
			String line = "";
			long length = raf.length();
			byte[] bytes = new byte[1024];
			int byteLen = 0;
			String carriageReturnLineFeedChar = "";
			while(raf.getFilePointer() < length) {
				line = raf.readLine();
				
				line = new String(line.getBytes("ISO-8859-1"),"UTF-8");
				byte[] by = line.getBytes();
				byteLen = by.length;
//				if(!flag) {
					if(raf.getFilePointer() - byteLen == 1) {
//						System.out.println("\\n");
						carriageReturnLineFeedChar = "\n";
					} else if(raf.getFilePointer() - byteLen == 2) {
//						System.out.println("'\r\n");
						carriageReturnLineFeedChar = "\r\n";
					}
//					flag = true;
//				}
				
				if(isJavaFile) {
					if ((line.startsWith("package") || line.startsWith("import")) && line.indexOf(originPackagePrefix) >= 0) {
						temp = line.replaceAll(originPackagePrefix, destPackagePrefix);
						bw.write(temp);
					} else {
						bw.write(line);
					}
				} else {
					bw.write(line);
				}
				
				readLen += byteLen;// + "\r\n".length();
//				System.out.println(readLen);
//				bw.write(line);
				if(length - readLen >= carriageReturnLineFeedChar.length()) {
//					bw.newLine();
					bw.write(carriageReturnLineFeedChar);
					readLen += carriageReturnLineFeedChar.length();
				} else {
//					System.out.println("end of file.");
				}
				
			}
			
//			long end = readLen;
//			if(end <= length) {
//				raf.seek(end);
//				raf.read(bytes, (int)end, (int)length);
//				line = new String(bytes,"UTF-8");
//				bw.write(line);
//			}
			
//			String tmp = "";
//			long srcLen = srcFile.length();
//			int lineIndex = 0;
//			while ((tmp = br.readLine()) != null) {
//				readLen += tmp.length();
//				lineIndex++;
//				
//				if(lineIndex == 123) {
//					System.out.println(tmp);
//				}
//				if(isJavaFile) {
//					if ((tmp.startsWith("package") || tmp.startsWith("import")) && tmp.indexOf(originPackagePrefix) >= 0) {
//						temp = tmp.replaceAll(originPackagePrefix, destPackagePrefix);
//						bw.write(temp);
//						
//					} else {
//						bw.write(tmp);
//					}
//				} else {
//					bw.write(tmp);
//				}
//				
//				if(srcLen - readLen >= 2) {
//					bw.newLine();
////					bw.write("\r\n");
//					readLen += 2;
//				}
//				
//			}
//			br.close();
//			br = null;
			
			bw.close();

			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
					br = null;
				}
				if (bw != null) {
					bw.close();
					bw = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean createNewFile(String srcFilePath, String destFilePath, String originPackagePrefix, String destPackagePrefix, boolean isJavaFile) throws IOException {
		boolean flag = false;
		File srcFile = new File(srcFilePath);
		File destFile = new File(destFilePath);
		if(!destFile.exists()) {
			String destDir = destFilePath.substring(0, destFilePath.lastIndexOf(File.separator));
			File destDirFile = new File(destDir);
			if(!destDirFile.exists()) {
				boolean createDir = destDirFile.mkdirs();
				if(createDir) {
					System.out.println("文件夹[" + destDir + "创建成功!");
				}
			}
			boolean isCreated = destFile.createNewFile();
			if(isCreated) {
				System.out.println("新文件[" + destFilePath + "]创建成功!");
			}
		}
//		flag = this.createNewFile(srcFile, destFile, originPackagePrefix, destPackagePrefix, isJavaFile);
		flag = this.createNewFileRandomAccessFile(srcFile, destFile, originPackagePrefix, destPackagePrefix, isJavaFile);
		return flag;
	}

//	public String changePackageLine(String path) {
//		String result = "";
//		result = path.substring(path.indexOf(sourceAbsolutePathRoot) + sourceAbsolutePathRoot.length(),
//				path.lastIndexOf(File.separator));
//		if (winSeparator) {
//			result = result.replaceAll("\\\\", ".");
//		} else {
//			result = result.replaceAll("/", ".");
//		}
//		return result;
//	}

//	public void rename(String file) {
//		String nf = file + tempFileSuffix;
//		File dest = new File(file);
//		File f = new File(nf);
//		f.renameTo(dest);
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String sourceAbsolutePathRoot = "G:\\GitHub\\test-utf8\\src\\";
		String originPackagePrefix = "com.messagesolution2";
		String destPackagePrefix = "com.codefans";
		
//		ChangePackageDeclare cpd = new ChangePackageDeclare(sourceAbsolutePathRoot,originPackagePrefix,destPackagePrefix);
//		cpd.change();

		ChangePackageDeclare cpd = new ChangePackageDeclare();
		cpd.change(sourceAbsolutePathRoot,originPackagePrefix,destPackagePrefix);
		
//		String srcFilePath = "D:\\GitHub\\test-utf8\\src\\com\\messagesolution2\\algorithm\\CutChineseEnglishStringByByte.java";
//		String destFilePath = cpd.getDestFilePath(srcFilePath);
//		System.out.println("destFilePath:" + destFilePath);
		
	}
}
