package com.codefans.java.io.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/*
 * @Author: Sean
 * @Time: 2015-05-27 09:49:20
 */
public class MappedByteBufferRandomAccessFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MappedByteBufferRandomAccessFile mbbraf = new MappedByteBufferRandomAccessFile();
		mbbraf.copy();
	}

	public void copy() {
		// ioCopy(); //Spend: 6.762s
		nioCopy(); // Spend: 6.465s
	}

	public void nioCopy() {
		RandomAccessFile rafi = null;
		RandomAccessFile rafo = null;

		FileChannel fcin = null;
		FileChannel fcout = null;

		try {

			String srcFile = "C:\\Temp\\performance\\1024M";
			String destFile = "C:\\Temp\\performance\\1024M_nioCopy";

			File f = new File(destFile);
			if (!f.exists()) {
				f.createNewFile();
			}

			rafi = new RandomAccessFile(srcFile, "r");
			rafo = new RandomAccessFile(destFile, "rw");
			fcin = rafi.getChannel();
			fcout = rafo.getChannel();
			long size = fcin.size();
			MappedByteBuffer mbbi = fcin.map(FileChannel.MapMode.READ_ONLY, 0, size);
			MappedByteBuffer mbbo = fcout.map(FileChannel.MapMode.READ_WRITE, 0, size);
			long start = System.currentTimeMillis();
			for (int i = 0; i < size; i++) {
				byte b = mbbi.get(i);
				mbbo.put(i, b);
			}

			System.out.println("Spend: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fcin.close();
				fcout.close();
				rafi.close();
				rafo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void ioCopy() {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			String srcFile = "C:\\Temp\\performance\\1024M";
			String destFile = "C:\\Temp\\performance\\1024M_ioCopy";

			File f = new File(destFile);
			if (!f.exists()) {
				f.createNewFile();
			}

			bis = new BufferedInputStream(new FileInputStream(new File(srcFile)));
			bos = new BufferedOutputStream(new FileOutputStream(f));
			byte[] bytes = new byte[1024];
			int len = 0;

			long start = System.currentTimeMillis();
			while ((len = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, len);
			}

			System.out.println("Spend: " + (double) (System.currentTimeMillis() - start) / 1000 + "s");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
