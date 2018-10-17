package com.codefans.java.file;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-3-26 Time: 下午5:50 To
 * change this template use File | Settings | File Templates.
 */
public class GetFileSize {
	private int level;
	private int fileCount;
	private long minFileSize;

	private Map<String, Object> basicFileInfo = new HashMap<String, Object>();
	private List<String> resultFileInfo = new ArrayList<String>();
	private List<Integer> removeIndexs = new ArrayList<Integer>();

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public long getMinFileSize() {
		return minFileSize;
	}

	public void setMinFileSize(long minFileSize) {
		this.minFileSize = minFileSize;
	}

	public long getFileSizes(File f) throws Exception {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
		return s;
	}

	// 递归
	public long getFileSize(File f) throws Exception// 取得文件夹大小
	{
		long size = 0;
		File flist[] = f.listFiles();
		if (flist != null) {
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getFileSize(flist[i]);
				} else {
					size = size + flist[i].length();
				}
			}
		}
		return size;
	}

	public String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public long getlist(File f) {// 递归求取目录文件个数
		long size = 0;
		File flist[] = f.listFiles();
		if (flist != null) {
			size = flist.length;
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getlist(flist[i]);
					size--;
				}
			}
		}
		return size;

	}

	/**
	 * @param path
	 *            get a single file size, include file and directory
	 */
	public void getFileSize(String path) {

		long startTime = System.currentTimeMillis();
		try {
			long l = 0;
			File ff = new File(path);
			if (ff.isDirectory()) { // 如果路径是文件夹的时候
				System.out.println("文件个数          " + getlist(ff));
				System.out.println("目录");
				l = getFileSize(ff);
				System.out.println(path + "目录的大小为：" + FormetFileSize(l));
			} else {
				System.out.println("    文件个数          1");
				System.out.println("文件");
				l = getFileSizes(ff);
				System.out.println(path + "文件的大小为：" + FormetFileSize(l));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("总共花费时间为：" + (endTime - startTime) + "毫秒...");
	}

	/**
	 * @param path
	 * @param level
	 * @throws Throwable
	 *
	 *             gather files and directories size under a certain level
	 *
	 */
	public void gatherFileBySingleLevel(String path, int level) throws Throwable {
		this.level = level;
		gatherFileInfoByLevel(path, 0);
		sortFileInfo();
	}

	/**
	 * @param path
	 * @param level
	 * @param minFileSize
	 * @throws Throwable
	 *
	 *             gather the size of files and directories which are under a
	 *             certain level and bigger than a certain size
	 *
	 */
	public void gatherFileBySingleLevelAndSize(String path, int level, long minFileSize) throws Throwable {
		this.level = level;
		gatherFileInfoByLevelAndSize(path, 0, minFileSize);
		sortFileInfo();
	}

	/**
	 * @param path
	 * @param level
	 * @throws Throwable
	 *
	 *             gather the size of files(exclude directories) which level is
	 *             upper than a certain level
	 *
	 */
	public void gatherFileExcludeDirByLevel(String path, int level) throws Throwable {
		this.level = level;
		gatherFileInfoExcludeDirByLevel(path, 0);
		sortFileInfo2();
	}

	/**
	 * @param path
	 * @param level
	 * @param minFileSize
	 * @throws Throwable
	 *
	 *             gather the size of files(exclude directories) which level is
	 *             upper than a certain level and size is bigger than a certain
	 *             size.
	 *
	 */
	public void gatherFileExcludeDirByLevelAndSize(String path, int level, long minFileSize) throws Throwable {
		this.level = level;
		gatherFileInfoExcludeDirByLevelAndSize(path, 0, minFileSize);
		sortFileInfo2();
	}

	public void gatherFileInfoByLevel(String path, int level) throws Throwable {
		File dir = new File(path);
		if (dir.isDirectory()) {
			if (this.level == level) {
				fileCount++;
				long dirSize = getFileSize(dir);
				basicFileInfo.put(dir.getAbsolutePath(), dirSize);
				return;
			}
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					gatherFileInfoByLevel(path + File.separator + f.getName(), level + 1);
				} else {
					if (this.level == level + 1) {
						fileCount++;
						basicFileInfo.put(f.getAbsolutePath(), f.length());
					}

				}
			}
		}
	}

	public void gatherFileInfoByLevelAndSize(String path, int level, long minFileSize) throws Throwable {
		File dir = new File(path);
		if (dir.isDirectory()) {
			if (this.level == level) {
				long dirSize = getFileSize(dir);
				if (dirSize >= minFileSize) {
					fileCount++;
					basicFileInfo.put(dir.getAbsolutePath(), dirSize);
				}
				return;
			}
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					gatherFileInfoByLevelAndSize(path + File.separator + f.getName(), level + 1, minFileSize);
				} else {
					if (this.level == level + 1) {
						if (f.length() >= minFileSize) {
							fileCount++;
							basicFileInfo.put(f.getAbsolutePath(), f.length());
						}
					}

				}
			}
		}
	}

	public void gatherFileInfoExcludeDirByLevel(String path, int level) throws Throwable {
		File dir = new File(path);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					gatherFileInfoExcludeDirByLevel(path + File.separator + f.getName(), level + 1);
				} else {
					if (this.level < level + 1) {
						fileCount++;
						basicFileInfo.put(f.getAbsolutePath(), f.length());
					}

				}
			}
		}
	}

	public void gatherFileInfoExcludeDirByLevelAndSize(String path, int level, long minFileSize) throws Throwable {
		File dir = new File(path);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					gatherFileInfoExcludeDirByLevelAndSize(path + File.separator + f.getName(), level + 1, minFileSize);
				} else {
					if (this.level < level + 1) {
						if (f.length() >= minFileSize) {
							fileCount++;
							basicFileInfo.put(f.getAbsolutePath(), f.length());
						}
					}

				}
			}
		}
	}

	public void sortFileInfo() {
		String[][] fileInfo = map2arr();
		// print(fileInfo);

		System.out.println(
				"=======ArrSize:[" + fileInfo.length + "]============================================================");

		long max = 0;
		int maxIndex = 0;
		int lineCount = 0;
		for (int i = 0; i < fileInfo.length; i++) {
			max = 0;
			maxIndex = 0;
			for (int j = 0; j < fileInfo.length; j++) {
				if (!removeIndexs.contains(j)) {
					if (Long.parseLong(fileInfo[j][1]) >= max) {
						max = Long.parseLong(fileInfo[j][1]);
						maxIndex = j;
					}
				}
			}
			removeIndexs.add(maxIndex);
			// System.out.println("size:[" + fileInfo[maxIndex][1] + "], path:["
			// + fileInfo[maxIndex][0] + "]");
			this.resultFileInfo.add(fileInfo[maxIndex][0] + "," + Long.parseLong(fileInfo[maxIndex][1]));
			lineCount++;
		}

		System.out.println("lineCount:[" + lineCount + "]");
	}

	public void sortFileInfo2() {
		List<String> keys = new ArrayList<String>();
		List<Long> values = new ArrayList<Long>();
		map2List(keys, values);

		System.out.println("values.length:[" + values.size() + "]");

		int len = values.size();
		int maxIndex = 0;
		long max = 0;
		int index = 0;
		int count = 0;
		while (index >= 0 && index < len) {
			max = 0;
			maxIndex = 0;
			for (int i = 0; i < values.size(); i++) {
				if (!removeIndexs.contains(i)) {
					if (values.get(i) >= max) {
						max = values.get(i);
						maxIndex = i;
					}
				}
			}
			index++;
			removeIndexs.add(maxIndex);
			count++;
			this.resultFileInfo.add(keys.get(maxIndex) + "," + values.get(maxIndex));
			// System.out.println("size:[" + values.get(maxIndex) + "],path:[" +
			// keys.get(maxIndex) + "]");
		}

		System.out.println("count:[" + count + "]");
	}

	public String[][] map2arr() {
		String[][] arr = new String[basicFileInfo.size()][2];
		Iterator<String> iter = basicFileInfo.keySet().iterator();
		String key = "";
		String value = "";
		int row = 0;
		while (iter.hasNext()) {
			key = iter.next();
			value = String.valueOf(basicFileInfo.get(key));
			arr[row][0] = key;
			arr[row][1] = value;
			row++;
		}
		return arr;
	}

	public void map2List(List<String> keys, List<Long> values) {
		Iterator<String> iter = basicFileInfo.keySet().iterator();
		String key = "";
		long value = 0;
		int row = 0;
		while (iter.hasNext()) {
			key = iter.next();
			value = Long.parseLong(String.valueOf(basicFileInfo.get(key)));
			keys.add(key);
			values.add(value);
			row++;
		}
	}

	public void print(String[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println("size:[" + arr[i][1] + "], path:[" + arr[i][0] + "]");
		}
	}

	public void print() {
		String[] arr = new String[2];
		String temp = "";
		for (int i = 0; i < resultFileInfo.size(); i++) {
			temp = resultFileInfo.get(i);
			arr = temp.split(",");
			System.out.println("size:[" + arr[1] + "], path:[" + arr[0] + "]");
		}
	}

	public void formatPrint() {
		String[] arr = new String[2];
		String temp = "";
		for (int i = 0; i < resultFileInfo.size(); i++) {
			temp = resultFileInfo.get(i);
			arr = temp.split(",");
			System.out.println("size:[" + FormetFileSize(Long.parseLong(arr[1])) + "], path:[" + arr[0] + "]");
		}
	}

	public static void main(String args[]) throws Throwable {
		GetFileSize g = new GetFileSize();
		// String path = "C:\\sdcard";
		// String path = "D:\\360云盘\\MessageSolution";
		// String path = "C:\\";
		// String path = "C:\\Program Files (x86)";
		// String path = "C:\\ProgramData";
		String path = "F:\\MessageSolution\\资料";
		g.getFileSize(path);

		// 获取单级目录下的文件和文件夹的大小，例如：path=C:\\sdcard,
		// level=1,那么就是获取C:\\sdcard\\a.txt或C:\\sdcard\\baidu这些文件或文件夹的大小
		g.gatherFileBySingleLevel(path, 1);
		// 获取单级目录下的文件和文件夹，并且文件或文件夹的大小必须大于或等于141043608个字节
		// g.gatherFileBySingleLevelAndSize(path, 1, 141043608);
		// 获取所有level大于1的文件的大小，不包括文件夹
		// g.gatherFileExcludeDirByLevel(path, 1);
		// 获取所有level大于1的、文件大小大于等于47518804个字节的文件，不包括文件夹
		// g.gatherFileExcludeDirByLevelAndSize(path, 1, 47518804);

		// g.print();
		g.formatPrint();

		System.out.println("TotalFile:[" + g.getFileCount() + "]");

	}
}
