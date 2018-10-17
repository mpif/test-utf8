package com.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainTest {
	static String s;

	/**
	 * @return void
	 * @author caisz
	 */
	public static void main(String[] args) {

		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		StackTraceElement ste = new Throwable().getStackTrace()[0];
		System.out.println("line1: " + ste.getLineNumber());
		System.out.println("line2: " + ste.getLineNumber());
		System.out.println("line3: " + ste.getLineNumber());

		System.out.println("line4: " + new Throwable().getStackTrace()[0].getLineNumber());
		System.out.println("line5: " + new Throwable().getStackTrace()[0].getLineNumber());
		System.out.println("line6: " + new Throwable().getStackTrace()[0].getLineNumber());

		System.out.println("line7: " + getLineInfo());
		System.out.println("line8: " + getLineInfo());
		System.out.println("line9: " + getLineInfo());

		System.out.println("stacktrace.size: " + new Throwable().getStackTrace().length);
		// StackTraceElement ste2 = new Throwable().getStackTrace()[2];
		// System.out.println(ste2.getFileName() + ": Line " +
		// ste2.getLineNumber());

		String isTrue = null;
		// String isTrue = "false";
		System.out.println("==========" + Boolean.parseBoolean(null));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date date = sdf.parse("");
			System.out.println("date: " + date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("s: " + s);

		String str = "1";
		System.out.println(Integer.parseInt(str));

		MainTest mt = new MainTest();
		List list = new ArrayList();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		mt.change(list);
		list.add("world");

		mt.printList(list);

		Map map = new HashMap();
		map.put("aa", "aa");
		mt.change(map);
		map.put("bb", "bb");
		mt.printMap(map);

		String stri = "aaa";
		mt.change(stri);
		System.out.println(stri);

		int n = 10;
		mt.change(n);
		System.out.println(n);

		boolean flag = false;
		mt.change(flag);
		System.out.println(flag);

		StringBuffer sb = new StringBuffer();
		sb.append("aaa");
		mt.change(sb);
		sb.append("world");
		System.out.println(sb.toString());

		// int result = 1 + 1 + 1 + 1 + 1
		// + 1 + 1 + 1 + 1 + 1
		// + 1 + 1 * 0 + 1;
		// System.out.println("result:[" + result + "]");

	}

	public static String getLineInfo() {
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		return ste.getFileName() + ": Line " + ste.getLineNumber();
	}

	public void change(List list) {
		list.add("hello");
	}

	public void change(Map map) {
		map.remove("aa");
		map.put("aa", "hello world");
		// map.put("aaa", "aaa");
	}

	public void change(String str) {
		str = "hello";
	}

	public void change(int n) {
		n = 111;
	}

	public void change(boolean flag) {
		flag = true;
	}

	public void change(StringBuffer sb) {
		sb.append("hello");
	}

	public void printList(List list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public void printMap(Map map) {
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
