package com.codefans.util.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-4-22 Time: 下午3:31 To
 * change this template use File | Settings | File Templates.
 */
public class LogUtil {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		count();
	}

	public static void count() {
		String str = "the value of property";
		try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\thread index\\eas.log.2");
			BufferedReader br = new BufferedReader(new FileReader(file));

			String temp = "";
			int index = 0;
			Map<String, String> vals = new HashMap<String, String>();

			while ((temp = br.readLine()) != null) {
				if (temp.contains(str)) {
					temp = temp.substring(temp.indexOf("the value is [") + 14);
					temp = temp.substring(0, temp.indexOf("]"));
					vals.put(temp, temp);
					index++;
				}
			}
			System.out.println(index);

			Iterator iter = vals.keySet().iterator();
			while (iter.hasNext()) {
				System.out.println(vals.get(iter.next()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
