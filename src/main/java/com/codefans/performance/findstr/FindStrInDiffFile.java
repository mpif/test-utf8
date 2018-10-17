package com.codefans.performance.findstr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindStrInDiffFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FindStrInDiffFile findDiffFile = new FindStrInDiffFile();
		findDiffFile.find();
	}

	public void find() {
		String tmp = System.getProperty("user.dir");
		String tmpDir = tmp + File.separator + "temp" + File.separator + "performanceinfindstr" + File.separator;

		// String file = tmpDir + File.separator + "multiPaths" + File.separator
		// + "aMillion"; //total time cost:[29s],
		// keyword:[ffffc6e0edd4900a7b51c30489909dd4]
		// String file = tmpDir + File.separator + "multiPaths" + File.separator
		// + "tenMillion"; //total time cost:[39s],
		// keyword:[f302acabaa60692f13556f9dee064c7e]
		String file = tmpDir + File.separator + "multiPaths" + File.separator + "aThousandMillion"; // total
																									// time
																									// cost:[67s],
																									// keyword:[f67547a785d9b14e107c0f38cdd13d5e]
		String keyword = "f67547a785d9b14e107c0f38cdd13d5e";

		String firstTwo = keyword.substring(0, 2);
		String secondTwo = keyword.substring(2, 4);

		file = file + File.separator + firstTwo + File.separator + secondTwo + File.separator + firstTwo + "_"
				+ secondTwo + ".hashcode";

		try {

			long start = System.currentTimeMillis();
			find(file, keyword);
			long end = System.currentTimeMillis();

			// System.out.println("total time cost:[" + (end - start) / 1000 +
			// "s]");
			System.out.println("total time cost:[" + (end - start) + "s]");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void find(String file, String keyword) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(file));
		String line = "";
		int index = 0;
		while (sc.hasNextLine()) {
			index++;
			line = sc.nextLine().trim();
			if (line != null && line.trim().length() > 0) {
				if (line.equals(keyword)) {
					System.out.println("keyword:[" + keyword + "] found in file:[" + file + "]");
					break;
				}
			}

		}
	}

}
