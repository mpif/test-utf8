package com.codefans.task;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class VideoEncrypt {

	private String path;

	private String sourceFiles = "sourceFiles.txt";
	private String destFiles = "destFiles.txt";
	private RandomAccessFile sraf;
	private RandomAccessFile draf;
	private int spoint;
	private int dpoint;

	private long commonLength = 200 * 1024 * 1024;

	public VideoEncrypt() {
		// init();
	}

	public void init() {
		String s = path + File.separator + sourceFiles;
		String d = path + File.separator + destFiles;
		try {
			File sf = new File(s);
			File df = new File(d);
			if (!sf.exists()) {
				sf.createNewFile();
			}
			sraf = new RandomAccessFile(sf, "rw");
			if (!df.exists()) {
				df.createNewFile();
			}
			draf = new RandomAccessFile(df, "rw");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VideoEncrypt video = new VideoEncrypt();
		// video.setSourceFile("F://2012伦敦奥运会开幕式 高清 主题曲.flv");
		// video.setDestFile("F://2012伦敦奥运会开幕式 高清 主题曲_encode.flv");
		// video.encode();

		// video.setSourceFile("F://2012伦敦奥运会开幕式 高清 主题曲_encode.flv");
		// video.setDestFile("F://2012伦敦奥运会开幕式 高清 主题曲_decode.flv");
		// video.decode();

		// byte[] b = "F://2012伦敦奥运会开幕式 高清 主题曲_decode.flv".getBytes();
		// for(int i = 0; i < b.length; i ++) {
		// System.out.println(b[i]);
		// }

		String action = "";
		if (args.length >= 2) {
			video.path = args[0];
			action = args[1];
			if (action != null && action.equalsIgnoreCase("encode")) {
				video.encode();
			} else if (action != null && action.equalsIgnoreCase("decode")) {
				video.decode();
			}
			video.close();
		}
	}

	public void encode() {
		init();

		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles(new SourceFileNameFilter());
			for (File f : files) {
				encodeFile(f.getAbsolutePath());
			}
		}
	}

	public void encodeFile(String file) {
		swrite(file);
		String name = file.substring(file.lastIndexOf(File.separator));
		String destFileName = this.encodeFileName(name);
		InputStream is = getInputStream(file);
		String destF = path + File.separator + destFileName + ".v";
		dwrite(destF);

		BufferedOutputStream bos = getBufferedOutputStream(destF);
		try {
			int n = 0;
			byte[] b = new byte[1024];
			byte[] str = null;
			while ((n = is.read(b)) != -1) {
				bos.write(b, 0, b.length);
				str = "_-_".getBytes();
				bos.write(str, 0, str.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, bos);
		}
	}

	public void swrite(String str) {
		try {
			if (spoint != 0) {
				sraf.skipBytes(spoint);
			}
			spoint += str.length();
			byte[] b = str.getBytes();
			sraf.write(b, 0, b.length);
			sraf.writeBytes("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dwrite(String str) {
		try {
			if (dpoint != 0) {
				draf.skipBytes(dpoint);
			}
			dpoint += str.length();
			byte[] b = str.getBytes();
			draf.write(b, 0, b.length);
			draf.writeBytes("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void decode() {
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles(new DestFileNameFilter());
			for (File f : files) {
				decodeFile(f.getAbsolutePath());
			}
		}
	}

	public void decodeFile(String file) {
		String sourceFileName = file.substring(file.lastIndexOf(File.separator) + 1, file.indexOf("."));
		String destFileName = this.decodeFileName(sourceFileName);
		String destFile = path + File.separator + destFileName;
		RandomAccessFile raf = getRandomAccessFile(file);

		BufferedOutputStream bos = getBufferedOutputStream(destFile);
		try {
			int n = 0;
			byte[] b = new byte[1024];
			byte[] str = null;
			int skip = "_-_".getBytes().length;
			while ((n = raf.read(b)) != -1) {
				bos.write(b, 0, b.length);
				raf.skipBytes(skip);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(raf, bos);
		}
	}

	public static InputStream getInputStream(String file) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return is;
	}

	public static RandomAccessFile getRandomAccessFile(String file) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return raf;
	}

	public static BufferedOutputStream getBufferedOutputStream(String file) {
		BufferedOutputStream bos = null;
		try {
			File f = new File(file);
			if (!f.exists()) {
				f.createNewFile();
			}
			bos = new BufferedOutputStream(new FileOutputStream(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos;
	}

	public static void close(InputStream is, OutputStream os) {
		try {
			if (is != null) {
				is.close();
				is = null;
			}
			if (os != null) {
				os.flush();
				os.close();
				os = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void close(RandomAccessFile raf, OutputStream os) {
		try {
			if (raf != null) {
				raf.close();
				raf = null;
			}
			if (os != null) {
				os.flush();
				os.close();
				os = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (sraf != null) {
				sraf.close();
				sraf = null;
			}
			if (draf != null) {
				draf.close();
				draf = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 *
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 *
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public String encodeFileName(String file) {
		String encodeSource = "";
		try {
			encodeSource = this.byteArr2HexStr(file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodeSource;
	}

	public String decodeFileName(String file) {
		String encodeSource = "";
		byte[] b = null;
		try {
			b = this.hexStr2ByteArr(file);
			encodeSource = new String(b, 0, b.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodeSource;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	class SourceFileNameFilter implements FilenameFilter {
		// String[] filterName = new String[] {".v", ".txt"};
		String[] filterName = null;

		public SourceFileNameFilter() {
			InputStream is = VideoEncrypt.class.getResourceAsStream("videoType.txt");
			Scanner sc = new Scanner(is);
			Set<String> set = new HashSet<String>();
			String temp = "";
			while (sc.hasNext()) {
				temp = sc.nextLine();
				if (temp != null && temp.trim().length() > 0) {
					if (temp.startsWith(".")) {
						set.add(temp.trim());
					} else {
						System.out.println("[" + temp + "] is not start with '.' ");
					}
				}
			}
			filterName = set.toArray(new String[0]);
		}

		@Override
		public boolean accept(File dir, String name) {
			String path = dir.getAbsolutePath() + File.separator + name;
			File f = new File(path);
			if (!f.isDirectory()) {
				name = name.substring(name.lastIndexOf("."));
				for (String str : filterName) {
					if (name.equalsIgnoreCase(str)) { // not .v
						return true;
					}
				}
			} else {
				return false;
			}
			return false;
		}
	}

	class DestFileNameFilter implements FilenameFilter {
		String[] filterName = new String[] { ".v" };

		@Override
		public boolean accept(File dir, String name) {
			for (String str : filterName) {
				if (name.endsWith(str)) {
					return true;
				}
			}
			return false;
		}
	}

}
