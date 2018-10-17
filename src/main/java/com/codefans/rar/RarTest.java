package com.codefans.rar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;

public class RarTest {

	public static void main(String[] args) {
		unrar("C:\\Users\\Administrator\\Downloads\\20131030_12590876\\加密的压缩文件.rar",
				"C:\\Users\\Administrator\\Downloads\\decompress\\", "fff");
		// unrar("C:\\Users\\Administrator\\Downloads\\20131030_12590876\\加密的RAR文件.rar",
		// "C:\\Users\\Administrator\\Downloads\\decompress\\","fff");
		// try {
		// RarDecompressionUtil.unrar("E:\\项目参考资料\\123.rar", "123");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public static void unrar(String srcRar, String dest, String password) {
		if (!srcRar.toLowerCase().endsWith(".rar")) {
			System.out.println("非RAR文件");
			return;
		}
		File destFile = new File(dest);
		if (!destFile.exists()) {
			destFile.mkdir();
		}
		Archive archive = null;
		try {
			archive = new Archive(new File(srcRar), password, false);
			archive.getMainHeader().print();

			FileHeader fileHeader = archive.nextFileHeader();

			while (null != fileHeader) {
				if (fileHeader.isDirectory()) {
					// File unDirectory = new File(dest + File.separator +
					// fileHeader.getFileNameW());
					File unDirectory = new File(dest + File.separator + fileHeader.getFileNameString());
					unDirectory.mkdir();
				} else {
					// File unFile = new File(dest + File.separator +
					// fileHeader.getFileNameW().trim());
					File unFile = new File(dest + File.separator + fileHeader.getFileNameString().trim());
					if (!unFile.exists()) {
						if (!unFile.getParentFile().exists()) {
							unFile.getParentFile().mkdir();
						}
						unFile.createNewFile();
					}
					FileOutputStream out = new FileOutputStream(unFile);
					archive.extractFile(fileHeader, out);
					out.flush();
					out.close();
				}
				fileHeader = archive.nextFileHeader();
			}
			archive.close();
		} catch (RarException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
