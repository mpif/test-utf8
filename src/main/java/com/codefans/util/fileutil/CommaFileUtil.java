package com.codefans.util.fileutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.collections.CollectionUtils;

public class CommaFileUtil {

	public static void main(String[] args) {
		CommaFileUtil cfUtil = new CommaFileUtil();
		
		try {
			String commaFilePath = "D:/tmp/adjustPrice.txt";
			cfUtil.findDuplicateLine(commaFilePath);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void findDuplicateLine(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		Scanner sc = new Scanner(fis);
		String line = "";
		String[] arr = null;
		String tmp = "";
		Integer val = null;
		Map<String, Integer> strMap = new HashMap<String, Integer>();
		Map<String, List<String>> lineMap = new HashMap<String, List<String>>();
		List<String> tmpList = null;
		
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			arr = line.split(",");
			tmp = arr[2] + "," + arr[3]; //由第3第4个字符串组成的唯一key
			if(strMap.containsKey(tmp)) {
				val = strMap.get(tmp);
				strMap.put(tmp, val + 1);
				List<String> list = lineMap.get(tmp);
				list.add(line);
				lineMap.put(tmp, list);
			} else {
				strMap.put(tmp, 1);
				tmpList = new ArrayList<String>();
				tmpList.add(line);
				lineMap.put(tmp, tmpList);
			}
			
		}
		
		Iterator<String> iter = strMap.keySet().iterator();
		String key = "";
		List<String> tlist = null;
		List<String> oneLine = new ArrayList<String>();
		
		while(iter.hasNext()) {
			key = iter.next();
			val = strMap.get(key);
			tlist = lineMap.get(key);
			if(val == 1) {
				oneLine.add(tlist.get(0));
			} else {
				System.out.println("[" + key + "]包含多条记录:");
				print(tlist);
			}
		}
		
		System.out.println("以下数据只包含一条记录:");
		print(oneLine);
		
		
		
		
	}
	
	public void print(List<String> list) {
		if(CollectionUtils.isNotEmpty(list)) {
			for(int i = 0; i < list.size(); i ++) {
				System.out.println(list.get(i));
			}
		}
	}
	
}
