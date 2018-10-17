package com.codefans.performance.findstr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.codefans.util.FileUtil;
import com.codefans.util.UniqueStringGenerator;

public class DataGenerator {

	private String tmpDir;
	private int AMillion = 1000000;
	private int TenMillion = 10000000;
	private int AThousandMillion = 100000000;
	private int ABillion = 1000000000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataGenerator dataGenerator = new DataGenerator();
		dataGenerator.generate();
	}

	public void generate() {

		String tmp = System.getProperty("user.dir");
		tmpDir = tmp + File.separator + "temp" + File.separator + "performanceinfindstr";

		List<String> types = new ArrayList<String>();
		types.add("AMillion");
		types.add("TenMillion");
		types.add("AThousandMillion");
		types.add("ABillion");
		// this.generateAllInFile(types);
		types.clear();

		// types.add("AMillion");
		types.add("TenMillion");
		types.add("AThousandMillion");
		// types.add("ABillion");
		this.generateInDiffFile(types);

	}

	public void generateAllInFile(List<String> types) {

		// System.out.println(UniqueStringGenerator.getUniqueString());
		// System.out.println(UniqueStringGenerator.getUniqueString());

		long start = 0;
		long end = 0;
		String file = "";

		if (types.contains("AMillion")) {
			start = System.currentTimeMillis();
			file = tmpDir + File.separator + "hashcode_AMillion.txt";
			this.generateAllInFile(file, AMillion); // 大小：32.4 MB (34,000,000
													// 字节), 占用空间：32.4 MB
													// (34,000,896 字节), time
													// cost:[1s]
			end = System.currentTimeMillis();
			System.out.println("AMillion, time cost:[" + (end - start) / 1000 + "s]");
		}

		if (types.contains("TenMillion")) {
			start = System.currentTimeMillis();
			file = tmpDir + File.separator + "hashcode_TenMillion.txt";
			this.generateAllInFile(file, TenMillion); // 大小：324 MB (340,000,000
														// 字节)，占用空间：324 MB
														// (340,000,768 字节),
														// time cost:[11s]
			end = System.currentTimeMillis();
			System.out.println("TenMillion, time cost:[" + (end - start) / 1000 + "s]");
		}

		if (types.contains("AThousandMillion")) {
			start = System.currentTimeMillis();
			file = tmpDir + File.separator + "hashcode_AThousandMillion.txt";
			this.generateAllInFile(file, AThousandMillion); // 大小：3.16 GB
															// (3,400,000,000
															// 字节)，占用空间：3.16 GB
															// (3,400,003,584
															// 字节), time
															// cost:[127s]
			end = System.currentTimeMillis();
			System.out.println("AThousandMillion, time cost:[" + (end - start) / 1000 + "s]");
		}

		if (types.contains("ABillion")) {
			start = System.currentTimeMillis();
			file = tmpDir + File.separator + "hashcode_ABillion.txt";
			this.generateAllInFile(file, ABillion);
			end = System.currentTimeMillis();
			System.out.println("ABillion, time cost:[" + (end - start) / 1000 + "s]");
		}

	}

	public void generateAllInFile(String f, int totalItems) {
		try {
			File file = new File(f);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileUtil fileUtil = new FileUtil(f);
			for (int i = 0; i < totalItems; i++) {
				fileUtil.appendLine(UniqueStringGenerator.getUniqueString());
			}
			fileUtil.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generateInDiffFile(List<String> types) {

		long start = 0;
		long end = 0;
		String multiPaths = "";
		multiPaths = tmpDir + File.separator + "multiPaths";

		if (types.contains("AMillion")) {
			start = System.currentTimeMillis();
			String aMillionPath = multiPaths + File.separator + "aMillion";
			this.generateInDiffFile(aMillionPath, AMillion); // 大小：32.4 MB
																// (34,000,000
																// 字节), 占用空间：256
																// MB
																// (268,435,456
																// 字节),AMillion,
																// time
																// cost:[151s]
			end = System.currentTimeMillis();
			System.out.println("AMillion, time cost:[" + (end - start) / 1000 + "s]");
		}

		if (types.contains("TenMillion")) {
			start = System.currentTimeMillis();
			String tenMillionPath = multiPaths + File.separator + "tenMillion";
			this.generateInDiffFile(tenMillionPath, TenMillion);// 大小：324 MB
																// (340,000,000
																// 字节)，占用空间：511
																// MB
																// (535,941,120
																// 字节)，
																// TenMillion,
																// time
																// cost:[265s]
			end = System.currentTimeMillis();
			System.out.println("TenMillion, time cost:[" + (end - start) / 1000 + "s]");
		}

		if (types.contains("AThousandMillion")) {
			start = System.currentTimeMillis();
			String aThousandMillionPath = multiPaths + File.separator + "aThousandMillion";
			this.generateInDiffFile(aThousandMillionPath, AThousandMillion); // 大小：3.16
																				// GB
																				// (3,400,000,000
																				// 字节)，
																				// 3.28
																				// GB
																				// (3,524,710,400
																				// 字节)，
																				// AThousandMillion,
																				// time
																				// cost:[1369s]
			end = System.currentTimeMillis();
			System.out.println("AThousandMillion, time cost:[" + (end - start) / 1000 + "s]");
		}

		if (types.contains("ABillion")) {
			start = System.currentTimeMillis();
			String aBillionPath = multiPaths + File.separator + "aBillion";
			this.generateInDiffFile(aBillionPath, ABillion);
			end = System.currentTimeMillis();
			System.out.println("ABillion, time cost:[" + (end - start) / 1000 + "s]");
		}
	}

	public void generateInDiffFile(String path, int totalItems) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			FileUtil fileUtil = null;
			Map<String, FileUtil> maps = new HashMap<String, FileUtil>();

			String uniqueStr = "";
			String tmpDir = "";
			for (int i = 0; i < totalItems; i++) {
				uniqueStr = UniqueStringGenerator.getUniqueString();
				tmpDir = path + File.separator + uniqueStr.substring(0, 2) + File.separator + uniqueStr.substring(2, 4);
				if (!maps.containsKey(tmpDir)) {
					File dir = new File(tmpDir);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String source = tmpDir + File.separator + uniqueStr.substring(0, 2) + "_"
							+ uniqueStr.substring(2, 4) + ".hashcode";
					File sFile = new File(source);
					if (!sFile.exists()) {
						sFile.createNewFile();
					}
					fileUtil = new FileUtil(source);
					fileUtil.appendLine(uniqueStr);
					maps.put(tmpDir, fileUtil);
				} else {
					fileUtil = maps.get(tmpDir);
					fileUtil.appendLine(uniqueStr);
				}
			}

			Iterator<String> iter = maps.keySet().iterator();
			String key = "";
			while (iter.hasNext()) {
				key = iter.next();
				fileUtil = maps.get(key);
				if (fileUtil != null) {
					fileUtil.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
