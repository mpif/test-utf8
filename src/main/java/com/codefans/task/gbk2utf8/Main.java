package com.codefans.task.gbk2utf8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main extends Thread {

	private String sourceRootPath;
	private String destRootPath;

	private String sourceCharsetName;
	private String destCharsetName;

	private int directoryCount;
	private int fileCount;
	private int total;

	public Main(String sPath, String dPath, String sCharsetName, String dCharsetName) {
		this.sourceRootPath = sPath;
		this.destRootPath = dPath;
		this.sourceCharsetName = sCharsetName;
		this.destCharsetName = dCharsetName;

		mkDir(dPath);
	}

	public void run() {
		convert(sourceRootPath, destRootPath);
		p("directoryCount:" + directoryCount);
		p("fileCount:" + fileCount);
		total = directoryCount + fileCount;
		p("total:" + total);
	}

	public void convert(String srp, String drp) {
		File path = new File(srp);
		if (path.exists()) {
			if (path.isDirectory()) {
				File[] files = path.listFiles();
				File file = null;
				String sfName = "";
				String dfName = "";
				for (int i = 0; i < files.length; i++) {
					file = files[i];
					sfName = srp + File.separator + file.getName();
					dfName = drp + File.separator + file.getName();
					if (file.isDirectory()) {
						mkDir(dfName);
						convert(sfName, dfName);
					} else {
						convertFile(sfName, dfName);
						p(dfName + " be created!");
					}
				}
			}
		} else {
			p(path + " do not exists!");
		}
	}

	public void convertFile(String sourceFile, String destFile) {
		fileCount++;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), sourceCharsetName));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile), destCharsetName));

			String temp = "";
			// int line = 0;
			while ((temp = br.readLine()) != null) {
				// line ++;
				// if(line == 56) {
				// p("this line has chinese");
				// }
				bw.write(temp);
				bw.newLine();
			}
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
	}

	public void mkDir(String dir) {
		directoryCount++;
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
	}

	public void p(Object o) {
		System.out.println(o);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sp = "D:\\dev\\eea_help";
		String dp = "C:\\eea_help_UTF-8";
		String sourceCharsetName = "GBK";
		String destCharsetName = "UTF-8";

		if (args.length == 4) {
			sp = args[0];
			dp = args[1];
			sourceCharsetName = args[2];
			destCharsetName = args[3];
		} else {
			System.out.println("Usge: java Main sourceFolderPath destFolderPath sourceCharsetName destCharsetName");
			return;
		}

		Main m = new Main(sp, dp, sourceCharsetName, destCharsetName);
		m.start();

		// String sf =
		// "D:\\dev\\eea_help\\src\\com\\messagesolution\\exchangeews\\RetrieveFolder.java";
		// String df =
		// "C:\\eea_help_UTF-8_ConvertByCode\\src\\com\\messagesolution\\exchangeews\\RetrieveFolder.java";
		// m.convertFile(sf, df);
	}

}
