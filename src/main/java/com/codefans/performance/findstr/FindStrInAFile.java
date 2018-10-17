package com.codefans.performance.findstr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindStrInAFile {

	public static void main(String[] args) {
		FindStrInAFile finder = new FindStrInAFile();
		finder.find();
	}

	public void find() {
		String tmp = System.getProperty("user.dir");
		String tmpDir = tmp + File.separator + "temp" + File.separator + "performanceinfindstr";

		// String file = tmpDir + File.separator + "hashcode_AMillion.txt";
		// //total time cost:[1188s], keyword:[0bddb6a33cbb3d87c1803f711415516a]
		// String file = tmpDir + File.separator + "hashcode_TenMillion.txt";
		// //total time cost:[9125s], keyword:[f8e8611a4b4f55d4e1e6f73f86719f26]
		String file = tmpDir + File.separator + "hashcode_AThousandMillion.txt"; // total
																					// time
																					// cost:[93367s],
																					// keyword:[f0057a0f72b26d38bee67bdbd65eb0d4]

		String keyword = "f0057a0f72b26d38bee67bdbd65eb0d4";
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
				// if(index == 100000000) {
				if (index == 1617) {
					// if(line.equals(keyword)) {
					System.out.println("keyword:[" + line + "] found in file:[" + file + "]");
					break;
					// }
				}
			}

		}
	}

}
