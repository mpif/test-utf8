package com.codefans.search;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;

public class RegularTextSearch {

	public static void main(String[] args) {
		RegularTextSearch regularTextSearch = new RegularTextSearch();
		regularTextSearch.search();
	}
	
	public void search() {
//		String toSearchText = "奢侈品";
		String toSearchText = "363874279";
		String dirPath = "F:/tmp/data";
		long begin = System.currentTimeMillis();
		List<File> fileList = this.gatherFiles(dirPath);
		long end = System.currentTimeMillis();
		System.out.println("gather total:[" + fileList.size() + "]files, cost:[" + (end - begin) / 1000 + "s]");
		search(fileList, toSearchText);
		long searchEnd = System.currentTimeMillis();
		System.out.println("search files cost:[" + (searchEnd - end) / 1000 + "s]");
		
		
	}
	
	/**
	 * single thread search files cost:[7390s]
	 * @param fileList
	 * @param toSearchText
	 */
	public void search(List<File> fileList, String toSearchText) {
		if(CollectionUtils.isNotEmpty(fileList)) {
			int availableProcessors = Runtime.getRuntime().availableProcessors();
			availableProcessors = availableProcessors / 2;
			System.out.println("availableProcessors:" + availableProcessors);
			ExecutorService fixedThreadPool = Executors.newFixedThreadPool(availableProcessors);  
			
			for(int i = 0; i < fileList.size(); i ++) {
				final File file = fileList.get(i);
				final String text = toSearchText;
				final int index = i;
				fixedThreadPool.submit(new Runnable() {
					@Override
					public void run() {
						search(file, text);
//						System.out.println(file.getAbsolutePath() + " search complete.");
						System.out.println("No." + (index + 1) + " file end!");
					}
					
				});
			}
			
			fixedThreadPool.shutdown();
			
		}
	}
	
	public boolean search(File file, String toSearchText) {
		boolean found = false;
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
//			int textCharLen = toSearchText.length();
			int textByteLen = toSearchText.getBytes().length;
			byte[] bytes = new byte[textByteLen];
			long length = raf.length();
			long filePointer = 0L;
			String readText = "";
			boolean getFirstByte = false;
			String charset = "";
			
			while(raf.getFilePointer() - textByteLen < length) {
				raf.seek(filePointer);
				raf.read(bytes, 0, textByteLen);
				if(!getFirstByte) {
//					if(b == -17) {
					if(bytes[0] == -17 && bytes[1] == -69 && bytes[2] == -65) {
						charset = "UTF-8";
//						textByteLen = textCharLen * 3;
//					} else if(b == 104) {
					} else {
						charset = "GB2312";
//						textByteLen = textCharLen * 2;
					}
					getFirstByte = true;
				} else {
				}
				readText = new String(bytes, 0, textByteLen, charset);
				if(readText.contains(toSearchText)) {
					System.out.println("text found in file:[" + file.getAbsolutePath() + "], readText:[" + readText + "]");
					found = true;
					break;
				}
				filePointer += 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(raf);
		}
		return found;
		
	}
	
	public List<File> gatherFiles(String dir) {
		List<File> fileList = new ArrayList<File>();
		gatherFiles(dir, fileList);
		System.out.println("size of file list:" + fileList.size());
		return fileList;
	}
	
	public void gatherFiles(String dir, List<File> fileList) {
		File dirFile = new File(dir);
		if(dirFile.isDirectory()) {
			File[] files = dirFile.listFiles();
			if(files != null && files.length > 0) {
				File file = null;
				for(int i = 0; i < files.length; i ++) {
					file = files[i];
					if(file.isDirectory()) {
						gatherFiles(dir + File.separator + file.getName(), fileList);
					} else {
						fileList.add(file);
					}
				}
			}
		} else {
			fileList.add(dirFile);
		}
	}
	
	public void close(RandomAccessFile raf) {
		try {
			if(raf != null) {
				raf.close();
				raf = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(InputStream is) {
		try {
			if(is != null) {
				is.close();
				is = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
