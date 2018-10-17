package com.codefans.java.file;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import java.io.*;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-5-19 Time: 下午3:40 To
 * change this template use File | Settings | File Templates.
 */
public class NetFileUtil {

	/**
	 * Description: 从共享目录拷贝文件到本地 @Version1.0 Sep 25, 2009 3:48:38 PM @param
	 * remoteUrl 共享目录上的文件路径 @param localDir 本地目录
	 */
	public void smbGet(String remoteUrl, String localDir) {
		InputStream in = null;
		OutputStream out = null;
		try {
			SmbFile remoteFile = new SmbFile(remoteUrl);
			if (remoteFile == null) {
				System.out.println("共享文件不存在");
				return;
			}
			String fileName = remoteFile.getName();
			File localFile = new File(localDir + File.separator + fileName);
			in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
			out = new BufferedOutputStream(new FileOutputStream(localFile));
			byte[] buffer = new byte[1024];
			while (in.read(buffer) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Description: 从本地上传文件到共享目录 @Version1.0 Sep 25, 2009 3:49:00 PM @param
	 * remoteUrl 共享文件目录 @param localFilePath 本地文件绝对路径
	 */
	public void smbPut(String remoteUrl, String localFilePath) {
		InputStream in = null;
		OutputStream out = null;
		try {
			File localFile = new File(localFilePath);
			String fileName = localFile.getName();
			SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);
			in = new BufferedInputStream(new FileInputStream(localFile));
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			byte[] buffer = new byte[1024];
			while (in.read(buffer) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// NetFileUtil test = new NetFileUtil() ;
		// // smb:域名;用户名:密码@目的IP/文件夹/文件名.xxx
		// //test.smbGet("smb://szpcg;jiang.t:xxx@192.168.193.13/Jake/test.txt",
		// "c://") ;
		// test.smbPut("smb://szpcg;jiang.t:xxx@192.168.193.13/Jake",
		// "c://test.txt") ;

		try {
			File file = new File("\\\\10.0.0.121\\archive\\eea.storage.20140519164255\\new2010.local\\email\\index");
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}

	}

}
