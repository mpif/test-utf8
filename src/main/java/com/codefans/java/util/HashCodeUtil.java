package com.codefans.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.List;

public class HashCodeUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			HashCodeUtil hashCodeUtil = new HashCodeUtil();
			String str = "str";
			// String file = "C:/test/lucene-3.3.0.tgz";
//			String file = "g:/aa_out.txt";
//			InputStream is = new FileInputStream(new File(file));
//			long start = System.currentTimeMillis();
//			// hashCodeUtil.computeStringHashCode(str);
//			hashCodeUtil.computeStreamHashCode(is);
//			long end = System.currentTimeMillis();
//			System.out.println("total cost:[" + (end - start) / 1000 + "s]");
			
			String s = "helloWorld!!!";
			String md5Str = hashCodeUtil.computeStringMD5HashCode(s);
			System.out.println(md5Str);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void compareHashCode(String srcStr, String destStr) {
		String srcStrHash = this.computeStringMD5HashCode(srcStr);
		String destStrHash = this.computeStringMD5HashCode(destStr);
		System.out.println("srcHash:" + srcStrHash);
		System.out.println("dstHash:" + srcStrHash);
		System.out.println("srcHash.equals('dstHash'):" + srcStrHash.equals(destStrHash));
	}
	
	public void compareHashCode(File srcFile, File destFile) {
		String srcFileHash = this.computeFileMD5(srcFile);
		String destFileHash = this.computeFileMD5(destFile);
		System.out.println("srcHash:" + srcFileHash);
		System.out.println("dstHash:" + destFileHash);
		System.out.println("srcHash.equals('dstHash'):" + srcFileHash.equals(destFileHash));
	}
	
	public void computeStringHashCode(String str) {
		String md5Hash = this.computeStringMD5HashCode(str);
		String shaHash = this.computeStringSHAHashCode(str);
		System.out.println(md5Hash);
		System.out.println(shaHash);
	}

	public void computeStreamHashCode(InputStream is) {
		String md5Hash = this.computeStreamMD5(is);
		String shaHash = this.computeStreamSHA(is);
		System.out.println(md5Hash);
		// System.out.println(md5Hash.equals("c5fd907d309c79927460d4f4022e934f"));
		System.out.println(shaHash);
	}

	public String computeFileMD5(String file) {
		String md5HashString = "";
		try {
			File f = new File(file);
			md5HashString = this.computeFileMD5(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5HashString;
	}

	public String computeFileSHA(String file) {
		String shaHashString = "";
		try {
			File f = new File(file);
			shaHashString = this.computeFileSHA(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shaHashString;
	}

	public String computeFileMD5(File file) {
		String md5HashString = "";
		try {
			InputStream is = new FileInputStream(file);
			md5HashString = this.computeStreamMD5(is);
			is.close();
			is = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5HashString;
	}

	public String computeFileSHA(File file) {
		String shaHashString = "";
		try {
			InputStream is = new FileInputStream(file);
			shaHashString = this.computeStreamSHA(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shaHashString;
	}

	public String computeStreamMD5(InputStream is) {
		String md5HashString = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// InputStream is = new FileInputStream(new
			// File("C:/test/lucene-3.3.0.tgz"));
			this.compute(is, md5);
			md5HashString = this.toString(md5);
			// System.out.println(md5HashString.equals("b0239b31d13a2ca393321e7a05191aab"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5HashString;
	}

	public String computeStreamSHA(InputStream is) {
		String shaHashString = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("SHA");
			// InputStream is = new FileInputStream(new
			// File("C:/test/lucene-3.3.0.tgz"));
			this.compute(is, md5);
			shaHashString = this.toString(md5);
			// System.out.println(shaHashString.equals("0350989e207da35e45b7dd98deb35a86540be70b"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shaHashString;
	}

	/**
	 * call digest to finalize the
	 * 
	 * @return String
	 */
	public String toString(MessageDigest mDigest) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] md = mDigest.digest(); // call digest here to finalize
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public void compute(InputStream in, MessageDigest mDigest) {
		byte[] bytes = new byte[8192];
		int read = 0;
		try {
			while ((read = in.read(bytes, 0, 8192)) > 0) {
				mDigest.update(bytes, 0, read);
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String computeStringMD5HashCode(String str) {
		String md5HashCode = "";
		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			md5HashCode = this.computeStringHashCode(str, mDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5HashCode;

	}

	public String computeStringSHAHashCode(String str) {
		String shaHashCode = "";
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA");
			shaHashCode = this.computeStringHashCode(str, mDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shaHashCode;
	}

	public String computeStringHashCode(String str, MessageDigest mDigest) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = str.getBytes();
			mDigest.update(strTemp);
			byte[] md = mDigest.digest();
			int j = md.length;
			char strs[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				strs[k++] = hexDigits[byte0 >>> 4 & 0xf];
				strs[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(strs);
		} catch (Exception e) {
			return null;
		}
	}

}
