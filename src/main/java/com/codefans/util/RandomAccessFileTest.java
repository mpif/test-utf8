package com.codefans.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codefans.java.util.HashCodeUtil;

/**
 * @author: csz
 * @date  : 2016年11月19日下午12:54:11
 */
public class RandomAccessFileTest {

	private RandomAccessFile raf;

	@Before
	public void before() {
		String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "randomAccess.txt";
		System.out.println("path: " + path);
		File f = new File(path);
		try {
			raf = new RandomAccessFile(path, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
//		readLineByByte();
		fileCopyByLine();
//		fileCopyByByte();
//		test03();
//		test002();
		// test001();

	}

	public void readLineByByte() {
		String sFile = "g:/ExcelReaderAndWriter.java";
		String dFile = "g:/ExcelReaderAndWriter_Out.java";
		File srcFile = new File(sFile);
		File destFile = new File(dFile);
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(srcFile);
			os = new FileOutputStream(destFile);
			int readCount = 0;
			byte[] bytes = new byte[10240];
			int returnCount = 0;
			int lineByteCount = 0;
			int byteBegin = 0;
			int byteEnd = 0;
			int index = 0;
			while((readCount = is.read()) != -1) {
				bytes[index++] = (byte)readCount;
				lineByteCount++;
				if(readCount == '\r') {
					System.out.println(new String(bytes, byteBegin, byteBegin + lineByteCount));
					returnCount++;
				}
				if(readCount == '\n') {
					System.out.println(new String(bytes, byteBegin, byteBegin + lineByteCount));
					returnCount++;
				}
				if(returnCount == 2) {
//					System.out.println(new String(bytes, byteBegin, byteBegin + lineByteCount));
					System.out.println(lineByteCount);
					returnCount = 0;
					byteBegin += lineByteCount;
					lineByteCount = 0;
					
				}
//				os.write(bytes, 0, readCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				is = null;
				
				os.flush();
				os.close();
				os = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fileCopyByLine() {
//		String sFile = "g:/aa.txt";
//		String dFile = "g:/aa_out.txt";
		String sFile = "g:/ExcelReaderAndWriter.java";
		String dFile = "g:/ExcelReaderAndWriter_Out.java";
		File srcFile = new File(sFile);
		File destFile = new File(dFile);
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			
			System.out.println("line.separator:" + System.getProperty("line.separator"));
			
			br = new BufferedReader(new FileReader(srcFile));
//			br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "iso-8859-1"));
			bw = new BufferedWriter(new FileWriter(destFile));
			String line = "";
			long len = srcFile.length();
			long readLen = 0;
			int lineIndex = 0;
			int charLen = 0;
			int byteLen = 0;
			String carriageReturnLineFeedByteLen = "";
			while((line = br.readLine()) != null) {
//				int len01 = new String(line.getBytes("iso-8859-1"), "utf-8");
				readLen += line.length();
//				readLen += line.getBytes().length;
				charLen = line.length();
				byteLen = line.getBytes("utf-8").length;
				int len2 = line.getBytes("iso-8859-1").length;
				if(charLen != byteLen) {
					System.out.println(charLen);
					System.out.println(byteLen);
				}
				lineIndex++;
				if(lineIndex == 21) {
					System.out.println(line);
				}
//				if(readLen != len) {
//					readLen += 2;
//				}
				bw.write(line);
				if(len - readLen >= 1) {
//					bw.write("\r\n");
//					readLen += 2;
//					bw.newLine();
					bw.write("\n");
					readLen += 1;
				}
			}
			
			System.out.println("len:" + len);
			System.out.println("readLen:" + readLen);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				br = null;
				
				bw.flush();
				bw.close();
				bw = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		new HashCodeUtil().compareHashCode(srcFile, destFile);
		System.out.println("end!");
	}
	
	public void fileCopyByByte() {
//		String sFile = "g:/aa.txt";
//		String dFile = "g:/aa_out.txt";
		String sFile = "g:/ExcelReaderAndWriter.java";
		String dFile = "g:/ExcelReaderAndWriter_Out.java";
		File srcFile = new File(sFile);
		File destFile = new File(dFile);
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(srcFile);
			os = new FileOutputStream(destFile);
			int readCount = 0;
			byte[] bytes = new byte[1024];
			while((readCount = is.read(bytes)) != -1) {
				os.write(bytes, 0, readCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				is = null;
				
				os.flush();
				os.close();
				os = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		new HashCodeUtil().compareHashCode(srcFile, destFile);
		System.out.println("end!");
		
	}
	
	public void test03() {
		
		BufferedWriter bw = null;
		RandomAccessFile raf = null;
		File srcFile = null;
		File dstFile = null;
		try {
//			srcFile = new File("g:/aa.txt");
//			dstFile = new File("g:/aa_out.txt");
			srcFile = new File("g:/ExcelReaderAndWriter.java");
			dstFile = new File("g:/ExcelReaderAndWriter_Out.java");
			bw = new BufferedWriter(new FileWriter(dstFile));
			raf = new RandomAccessFile(srcFile,"r");
//			String line = raf.readLine();
			String line = "";
			long length = raf.length();
			byte[] bytes = new byte[1024];
			int byteLen = 0;
			long readLen = 0;
			boolean flag = false;
			String carriageReturnLineFeedChar = "";
			while(raf.getFilePointer() < length) {
				line = raf.readLine();
				line = new String(line.getBytes("ISO-8859-1"),"UTF-8");
				byte[] by = line.getBytes();
				byteLen = by.length;
				if(!flag) {
					if(raf.getFilePointer() - byteLen == 1) {
						System.out.println("\\n");
						carriageReturnLineFeedChar = "\n";
					} else if(raf.getFilePointer() - byteLen == 2) {
						System.out.println("'\r\n");
						carriageReturnLineFeedChar = "\r\n";
					}
					flag = true;
				}
				readLen += byteLen;// + "\r\n".length();
//				System.out.println(readLen);
				bw.write(line);
				if(readLen != length) {
//					bw.newLine();
					bw.write(carriageReturnLineFeedChar);
					readLen += carriageReturnLineFeedChar.length();
				} else {
					System.out.println("end of file.");
				}
				
			}
			
			long end = readLen;
			if(end < length) {
				raf.seek(end);
				raf.read(bytes, (int)end, (int)length);
				line = new String(bytes,"UTF-8");
				bw.write(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
				raf = null;
				
				bw.flush();
				bw.close();
				bw = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		new HashCodeUtil().compareHashCode(srcFile, dstFile);
		System.out.println("end!");
		
		
	}
	
	public void test002() {
		try {
			RandomAccessFile raf = new RandomAccessFile("c:/f.txt", "rw");
			raf.setLength(5 * 1024 * 1024);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void test001() {
		try {
			long num = raf.length();
			// long num =
			// raf.getFilePointer());//这样实现不了追加效果，因为每次新生成一个RandomAccessFile对象，它的getFilePointer()方法的值都为0，这样就会从文件的最开始覆盖文件内容
			p("num: " + num);
			raf.seek(num);
			p("raf.pointer: " + raf.getFilePointer());
			raf.writeBytes("helloWorld1\n");// 推荐使用
			raf.writeChars("helloWorld2\n");// 可能乱码，你也不知道你要写入的文件是什么编码格式的
			raf.writeUTF("helloWorld3\n"); // 可能乱码，你也不知道你要写入的文件是什么编码格式的
			p("raf.pointer: " + raf.getFilePointer());
		} catch (IOException e) {
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

	@After
	public void after() {

	}

	public void p(Object o) {
		System.out.println(o);
	}
}
