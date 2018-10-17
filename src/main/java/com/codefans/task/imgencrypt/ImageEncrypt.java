package com.codefans.task.imgencrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class ImageEncrypt extends FileEncrypt {

	private String srcPath;
	private String destPath;

	public ImageEncrypt() {
	}

	public ImageEncrypt(String srcPath, String destPath) {
		this.srcPath = srcPath;
		this.destPath = destPath;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String srcPath = "";
		String destPath = "";
		ImageEncrypt imageEncrypt = new ImageEncrypt();
		srcPath = "C:\\images\\";
		destPath = "C:\\images\\encrypt\\";
		imageEncrypt.encrypt(srcPath, destPath);
		srcPath = "C:\\images\\encrypt\\";
		destPath = "C:\\images\\decrypt\\";
		imageEncrypt.decrypt(srcPath, destPath);

		// File f = new File("");

	}

	public void encrypt_old() {
		// System.out.println(StoreFileHeader.headerFirstElementLength);
		// System.out.println(StoreFileHeader.headerFirstElement);

		String sourcePath = "C:\\images\\1\\";
		String sourceName = "2014-10-24_12-42-55_610.jpg";
		String source = sourcePath + sourceName;
		String dest = sourcePath + sourceName + ".encrypt";

		File sourceFile = new File(source);

		long length = sourceFile.length();
		RandomAccessFile raf = null;
		InputStream is = null;
		try {
			raf = new RandomAccessFile(dest, "rw");
			is = new FileInputStream(sourceFile);

			ImageObject imageObject = new ImageObject(raf, is, length);
			System.out.println("imageObject.getFirstMidPartOffset()[" + imageObject.getFirstMidPartOffset() + "]");
			System.out.println("imageObject.getSecondMidPartOffset()[" + imageObject.getSecondMidPartOffset() + "]");
			imageObject.writeSelf();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void encrypt(String srcPath, String destPath) {

		// String sourcePath = "C:\\images\\1\\";
		// String sourceName = "2014-10-24_12-42-55_610.jpg";
		// String source = sourcePath + sourceName;
		// String dest = sourcePath + sourceName + ".encrypt";
		// List<String> sourceFiles = new ArrayList<String>();
		// sourceFiles.add(source);
		// this.encrypt(sourceFiles, dest);

		// String root = "C:\\images\\";
		// File rootPath = new File(root);
		File rootPath = new File(srcPath);

		String sourcePath = "";
		// String destPath = "";
		if (rootPath.isDirectory()) {
			File[] files = rootPath.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg")) {
						return true;
					}
					return false;
				}

			});

			String sourceName = "";
			// String sPath = "";
			String dPath = "";
			for (int i = 0; i < files.length; i++) {
				sourcePath = files[i].getAbsolutePath();
				// sPath = sourcePath.substring(0,
				// sourcePath.lastIndexOf(File.separator));
				sourceName = sourcePath.substring(sourcePath.lastIndexOf(File.separator) + 1);
				dPath = destPath + File.separator + this.computeStringMD5HashCode(sourceName) + ".encrypt";
				this.encryptImage(sourcePath, dPath);
			}
		}

	}

	public void encryptImage(String source, String dest) {
		try {
			ImageObject imageObject = new ImageObject(source, dest);
			System.out.println("imageObject.getFirstMidPartOffset()[" + imageObject.getFirstMidPartOffset() + "]");
			System.out.println("imageObject.getSecondMidPartOffset()[" + imageObject.getSecondMidPartOffset() + "]");
			imageObject.writeSelf();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void decrypt_old() {
		String sourcePath = "C:\\images\\1\\";
		String sourceName = "2014-10-24_12-42-55_610.jpg.encrypt";
		String namePrefix = sourceName.substring(0, sourceName.indexOf("."));
		String nameSuffix = sourceName.substring(sourceName.indexOf("."), sourceName.lastIndexOf("."));

		String source = sourcePath + sourceName;
		String dest = sourcePath + namePrefix + "_decrypt" + nameSuffix;

		File sourceFile = new File(source);

		long length = sourceFile.length();
		RandomAccessFile raf = null;
		OutputStream out = null;
		try {
			raf = new RandomAccessFile(source, "rw");
			out = new FileOutputStream(dest);

			ImageObject imageObject = new ImageObject(raf, out, length);
			System.out.println("imageObject.getFirstMidPartOffset()[" + imageObject.getFirstMidPartOffset() + "]");
			System.out.println("imageObject.getSecondMidPartOffset()[" + imageObject.getSecondMidPartOffset() + "]");
			imageObject.readSelf();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void decrypt(String srcPath, String destPath) {
		// String sourcePath = "C:\\images\\1\\";
		// String sourceName = "2014-10-24_12-42-55_610.jpg.encrypt";
		// String namePrefix = sourceName.substring(0, sourceName.indexOf("."));
		// String nameSuffix = sourceName.substring(sourceName.indexOf("."),
		// sourceName.lastIndexOf("."));
		// String source = sourcePath + sourceName;
		// String dest = sourcePath + namePrefix + "_decrypt" + nameSuffix;
		// List<String> sourceFiles = new ArrayList<String>();
		// sourceFiles.add(source);
		// this.decrypt(sourceFiles, dest);

		// String root = "C:\\images\\";
		String root = srcPath;
		File rootPath = new File(root);

		String sourcePath = "";
		String dPath = "";
		if (rootPath.isDirectory()) {
			File[] files = rootPath.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.endsWith(".encrypt")) {
						return true;
					}
					return false;
				}

			});

			sourcePath = root;
			File f = null;
			for (int i = 0; i < files.length; i++) {
				f = files[i];
				sourcePath = f.getAbsolutePath();
				// String sourceName = f.getName();//
				// "2014-10-24_12-42-55_610.jpg.encrypt";
				// String namePrefix = sourceName.substring(0,
				// sourceName.indexOf("."));
				// String nameSuffix =
				// sourceName.substring(sourceName.indexOf("."),
				// sourceName.lastIndexOf("."));
				// dPath = sourcePath.substring(0,
				// sourcePath.lastIndexOf(File.separator) + 1) + namePrefix +
				// "_decrypt" + nameSuffix;
				// this.decrypt(sourcePath, dPath);
				this.decryptImage(sourcePath, destPath);
			}
		}
	}

	public void decryptImage(String encryptFile, String origiPath) {

		try {

			ImageObject imageObject = new ImageObject(encryptFile, origiPath);
			System.out.println("imageObject.getFirstMidPartOffset()[" + imageObject.getFirstMidPartOffset() + "]");
			System.out.println("imageObject.getSecondMidPartOffset()[" + imageObject.getSecondMidPartOffset() + "]");
			imageObject.readSelf();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
