package com.codefans.java.io.write;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.codefans.java.io.util.IOCommonUtil;

/**
 * Created By MyEclipse 8.6 M1 User: caisz Date: 2012-9-3 Time: 上午11:51:05 To
 * change this template use Window | Preferences | Java | Code Style | Code
 * Templates
 */
public class OutputAndBufferdOutput extends IOCommonUtil {

	/**
	 * 一个方法一个方法分开单独执行： inputOutput:0.766s ~~ 0.859s inputBufferOutput:0.265s
	 * 0.375s bufferInputOutput:0.813s 1.172s 1.469s 2s
	 * BufferInputBufferOutput:0.203s 0.218s 0.5s
	 *
	 * 一起执行： inputOutput:0.969s 0.765s inputBufferOutput:1.234s 1.157s
	 * bufferInputOutput:0.844s 0.718s BufferInputBufferOutput:1.187s 1.219s
	 *
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		OutputAndBufferdOutput test = new OutputAndBufferdOutput();

		String path = "C:\\Documents and Settings\\workbench\\桌面\\快捷方式\\测试附件\\";
		String source = "";
		String dest = "";
		long start = 0;
		long end = 0;

		start = System.currentTimeMillis();
		source = path + "9M_rar_1.eml";
		dest = path + "9M_rar_1_out.eml";
		test.inputOutput(source, dest);
		end = System.currentTimeMillis();
		System.out.println("inputOutput time: " + (end - start) / 1000f + "s");

		start = System.currentTimeMillis();
		source = path + "9M_rar_2.eml";
		dest = path + "9M_rar_2_out.eml";
		test.inputBufferOutput(source, dest);
		end = System.currentTimeMillis();
		System.out.println("inputBufferOutput time: " + (end - start) / 1000f + "s");

		start = System.currentTimeMillis();
		source = path + "9M_rar_3.eml";
		dest = path + "9M_rar_3_out.eml";
		test.bufferInputOutput(source, dest);
		end = System.currentTimeMillis();
		System.out.println("bufferInputOutput time: " + (end - start) / 1000f + "s");
		//
		start = System.currentTimeMillis();
		source = path + "9M_rar_4.eml";
		dest = path + "9M_rar_4_out.eml";
		test.BufferInputBufferOutput(source, dest);
		end = System.currentTimeMillis();
		System.out.println("BufferInputBufferOutput time: " + (end - start) / 1000f + "s");
	}

	public void inputOutput(String inputFile, String outputFile) {
		InputStream is = getInputStream(inputFile);
		outputStream(is, outputFile);
	}

	public void inputBufferOutput(String inputFile, String outputFile) {
		InputStream is = getInputStream(inputFile);
		bufferedOutputStream(is, outputFile);
	}

	public void bufferInputOutput(String inputFile, String outputFile) {
		InputStream is = getBufferInputStream(inputFile);
		outputStream(is, outputFile);
	}

	public void BufferInputBufferOutput(String inputFile, String outputFile) {
		InputStream is = getBufferInputStream(inputFile);
		bufferedOutputStream(is, outputFile);
	}

	public InputStream getInputStream(String inputFile) {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(inputFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	public InputStream getBufferInputStream(String inputFile) {
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(new File(inputFile)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bis;
	}

	public void outputStream(InputStream is, String outputFile) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(outputFile));
			byte[] bytes = new byte[1024];
			int n = 0;
			while ((n = is.read(bytes)) != -1) {
				fos.write(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeResource(is, fos);
		}
	}

	public void bufferedOutputStream(InputStream is, String outputFile) {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(new File(outputFile)));
			byte[] bytes = new byte[1024];
			int n = 0;
			while ((n = is.read(bytes)) != -1) {
				bos.write(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeResource(is, bos);
		}
	}

}
