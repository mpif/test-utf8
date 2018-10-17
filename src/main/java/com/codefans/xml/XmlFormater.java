package com.codefans.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class XmlFormater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XmlFormater formater = new XmlFormater();
		// formater.format();
		formater.formatFileString();
	}

	public void formatFileString() {
		String file = "FindItemRequest.txt";
		InputStream is = this.getInputStream(file);
		Scanner sc = new Scanner(is);
		String tmp = "";
		while (sc.hasNextLine()) {
			tmp = sc.nextLine();
			System.out.print("sb.append(\"");
			System.out.print(tmp.replaceAll("\"", "\\\\\""));
			System.out.print("\");");
			System.out.println();
		}
	}

	public void format() {
		String source = getStr();
		// System.out.println(source);

		// Pattern pattern = Pattern.compile("<.*?>");
		// Matcher matcher = pattern.matcher(source);
		// while(matcher.find()) {
		// System.out.println(matcher.group());
		// }

		// char[] chs = new char[]{'1', '2', 'h', 'e', 'l', 'l', 'o', 'w', 'o',
		// 'r', 'l', 'd'};
		// int begin = 2;// 'h'
		// int end = 6; // 'o'
		// System.out.println(new String(chs, 2, (end - begin + 1)));

		char[] chs = source.toCharArray();
		boolean start = false;
		int tagBegin = 0;
		int tagEnd = 0;
		int textBegin = 0;
		int textEnd = 0;
		char c = ' ';

		LinkedList<String> linkedlist = new LinkedList<String>();
		List<String> list = new ArrayList<String>();
		String tmp = "";

		for (int i = 0; i < chs.length; i++) {
			c = chs[i];
			if (c == '<') {
				tagBegin = i;
				if (start) {
					textEnd = i - 1;
					tmp = new String(chs, textBegin, (textEnd - textBegin + 1));
					// System.out.println(tmp);
					// linkedlist.push(tmp);
					list.add(tmp);
					start = false;
				}

			}
			if (c == '>') {
				tagEnd = i;
				tmp = new String(chs, tagBegin, (tagEnd - tagBegin + 1));
				// System.out.println(tmp);
				// linkedlist.push(tmp);
				list.add(tmp);
				if (i < chs.length - 1) {
					if (chs[i + 1] != '<') {
						textBegin = i + 1;
						start = true;
					}
				}
			}
		}

		// formater.printList(list);

		// format print
		boolean shortStart = false;
		boolean nextIsText = false;
		for (int i = 0; i < list.size(); i++) {
			tmp = list.get(i);
			if (i == 0) {
				System.out.println(tmp);
			} else {

				if (linkedlist.size() == 0) {
					System.out.println(printBlank(linkedlist.size()) + tmp);
					linkedlist.push(tmp);
				} else {
					if (tmp.startsWith("<") && tmp.endsWith("/>")) {
						System.out.println(printBlank(linkedlist.size()) + tmp);
					} else if (tmp.startsWith("</") && tmp.endsWith(">")) {
						linkedlist.removeFirst();
						// System.out.println(formater.printBlank(linkedlist.size())
						// + tmp);
						System.out.println(tmp);
					} else if (tmp.startsWith("<") && tmp.endsWith(">")) {
						// if(tmp.length() <= 100) {
						// shortStart = true;
						// System.out.print(formater.printBlank(linkedlist.size())
						// + tmp);
						// } else {
						System.out.print(printBlank(linkedlist.size()) + tmp);
						// }
						linkedlist.push(tmp);
					} else { // text node
						// if(shortStart) {
						// System.out.print(formater.printBlank(linkedlist.size())
						// + tmp);
						// } else {
						// System.out.print(formater.printBlank(linkedlist.size())
						// + tmp);
						System.out.print(tmp);
						// }
					}
				}

			}
		}
	}

	public String printBlank(int size) {
		StringBuffer sb = new StringBuffer();
		String TAG_BLANK = "	";
		for (int i = 0; i < size; i++) {
			sb.append(TAG_BLANK);
		}
		return sb.toString();
	}

	public void printList(List list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public String getStr() {
		return convertFileToString("GetItemResponse.txt");
	}

	public String getFileString() {
		return convertFileToString("FindItemRequest.txt");
	}

	public String convertFileToString(String file) {
		StringBuffer sb = new StringBuffer();
		try {
			InputStream is = getInputStream(file);
			byte[] bytes = new byte[1024];
			int n = 0;
			while ((n = is.read(bytes)) != -1) {
				sb.append(new String(bytes, 0, n, "utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public InputStream getInputStream(String file) {
		return XmlFormater.class.getResourceAsStream(file);
	}

}
