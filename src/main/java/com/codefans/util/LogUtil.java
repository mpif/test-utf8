package com.codefans.util;

import java.io.*;
import java.util.*;

public class LogUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogUtil util = new LogUtil();

		String[] paths = new String[] { "D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.1",
				"D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.2", "D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.3",
				"D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.4", "D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.5",
				"D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.6", "D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.7",
				"D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.8" };
		// util.collectMessageClassFromFiles(paths);

		String path = "D:\\客户问题及日志logs\\Cohen Gresser\\eas.log.6";
		// util.collectMessageClassFromFile(path);

		// String content =
		// "dfadfdfdfd<t:ItemClass>aaa</t:ItemClass>dfdfdfdfdfdfd<t:ItemClass>bbb</t:ItemClass>ffdfsfdsfdsfd";
		// util.collectMessageClassFromString(content);

		// String str = "because message class is not IPM.Note";
		String str = "-- Stub status is : 41001, subject: ";
		// util.findStrFromFiles(paths, str);
		// util.countSubjectFromFiles(paths, str);

		// String xml = "<m:FindFolderResponse
		// xmlns:m=\"http://schemas.microsoft.com/exchange/services/2006/messages\"
		// xmlns:t=\"http://schemas.microsoft.com/exchange/services/2006/types\"><m:ResponseMessages><m:FindFolderResponseMessage
		// ResponseClass=\"Error\"><m:MessageText>SMTP
		// 地址没有与其关联的邮箱。</m:MessageText><m:ResponseCode>ErrorNonExistentMailbox</m:ResponseCode><m:DescriptiveLinkKey>0</m:DescriptiveLinkKey><m:MessageXml><t:Value
		// Name=\"SmtpAddress\">rongwang.sun@cimc.com</t:Value></m:MessageXml></m:FindFolderResponseMessage></m:ResponseMessages></m:FindFolderResponse>";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "\n"
				+ "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + "  <s:Body>\n"
				+ "    <s:Fault>\n"
				+ "      <faultcode xmlns:a=\"http://schemas.microsoft.com/exchange/services/2006/types\">a:ErrorNonExistentMailbox</faultcode>\n"
				+ "      <faultstring xml:lang=\"zh-CN\">SMTP 地址没有与其关联的邮箱。</faultstring>\n" + "      <detail>\n"
				+ "        <e:ResponseCode xmlns:e=\"http://schemas.microsoft.com/exchange/services/2006/errors\">ErrorNonExistentMailbox</e:ResponseCode>\n"
				+ "        <e:Message xmlns:e=\"http://schemas.microsoft.com/exchange/services/2006/errors\">SMTP 地址没有与其关联的邮箱。</e:Message>\n"
				+ "        <t:MessageXml xmlns:t=\"http://schemas.microsoft.com/exchange/services/2006/types\">\n"
				+ "          <t:Value Name=\"SmtpAddress\">wei.zuo@cimc-raffles.com</t:Value>\n"
				+ "        </t:MessageXml>\n" + "      </detail>\n" + "    </s:Fault>\n" + "  </s:Body>\n"
				+ "</s:Envelope>";

		util.logAnalysis();

		// util.count();

		String logFile = "D:\\csz\\客户问题及日志logs\\中集\\2014-05-15.16\\2014-05-15\\eas - 副本.log";
		// String keyStr01 = "无具有此 GUID 的邮箱。";
		String keyStr01 = "SMTP 地址没有与其关联的邮箱。";
		String keyStr02 = "ErrorNonExistentMailbox";
		String opt = "&&";
		// util.count(logFile, keyStr01, keyStr02, opt);

		// String line =
		// "aaaaaaaaaaaaaa111bbbbbbbbbbbbb222ccccccccccccc333dddddddddddddddd";
		// String[] keys = new String[]{"111", "222"};
		// String opt = "||";
		// boolean result = util.contains(line, keys, opt);
		// System.out.println(result);

	}

	public void count() {
		String prefix = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Header><ExchangeImpersonation xmlns=\"http://schemas.microsoft.com/exchange/services/2006/types\"><ConnectingSID><PrimarySmtpAddress>";
		String suffix = "</PrimarySmtpAddress></ConnectingSID></ExchangeImpersonation><RequestServerVersion Version=\"Exchange2010_SP1\" xmlns=\"http://schemas.microsoft.com/exchange/services/2006/types\"/></soap:Header><soap:Body><UpdateItem";
		try {
			InputStream inputStream = new FileInputStream(
					new File("D:\\csz\\客户问题及日志logs\\中集\\2014-05-09\\eas - 副本 (2).log"));
			Scanner sc = new Scanner(inputStream);

			String line = "";
			Set<String> addrs = new HashSet<String>();
			int lineIndex = 0;
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if (line.startsWith(prefix) && line.indexOf(suffix) >= 0) {
					addrs.add(line.substring(prefix.length(), line.indexOf(suffix)));
				}
				lineIndex++;
			}

			Iterator<String> iterator = addrs.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			System.out.println("total:[" + addrs.size() + "]");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void count(String... keysAndOpt) {
		if (keysAndOpt != null && keysAndOpt.length >= 3) {
			String logFile = keysAndOpt[0];
			String opt = keysAndOpt[keysAndOpt.length - 1];
			String[] keys = Arrays.copyOfRange(keysAndOpt, 1, keysAndOpt.length - 1);
			try {
				InputStream inputStream = new FileInputStream(new File(logFile));
				Scanner sc = new Scanner(inputStream);

				String line = "";
				Set<String> addrs = new HashSet<String>();
				int lineIndex = 0;
				while (sc.hasNextLine()) {
					line = sc.nextLine();
					if (this.contains(line, keys, opt)) {
						addrs.add(line);
						lineIndex++;
					}
				}

				Iterator<String> iterator = addrs.iterator();
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}
				System.out.println("total found:[" + lineIndex + "]");
				System.out.println("No Duplicate Count:[" + addrs.size() + "]");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean contains(String line, String[] keys, String opt) {
		boolean result = true;
		if (keys != null && keys.length > 0) {
			for (int i = 0; i < keys.length; i++) {
				if ("&&".equals(opt)) {
					result = result && (line.indexOf(keys[i]) >= 0);
				} else if ("||".equals(opt)) {
					result = result || (line.indexOf(keys[i]) >= 0);
				}
			}
		} else {
			System.out.println("keys is empty.");
		}
		return result;
	}

	public void logAnalysis() {
		// String logFile =
		// "D:\\csz\\客户问题及日志logs\\中集\\2014-05-15.16\\2014-05-15\\eas - 副本.log";
		String logFile = "D:\\csz\\客户问题及日志logs\\中集\\日志错误收集\\SMTP 地址没有与其关联的邮箱, ErrorNonExistentMailbox.txt";
		XmlUtil xmlUtil = new XmlUtil();
		Scanner sc = new Scanner(xmlUtil.getResource(logFile));
		String line = "";
		Set set = new HashSet();
		List<String> total = new ArrayList<String>();

		String text = "";
		while (sc.hasNextLine()) {
			line = sc.nextLine().trim();
			if (line.startsWith("<m:FindFolderResponse")) {
				String responseCode = "ErrorNonExistentMailbox";
				String responseCodeXPath = "//m:FindFolderResponse/m:ResponseMessages/m:FindFolderResponseMessage/m:ResponseCode";
				String messageTextXPath = "//m:FindFolderResponse/m:ResponseMessages/m:FindFolderResponseMessage/m:MessageXml/t:Value[@Name='SmtpAddress']";

				text = xmlUtil.find(line, responseCode, responseCodeXPath, messageTextXPath);
				if (text != null && text.trim().length() > 0) {
					set.add(text);
					total.add(text);
				}
			} else if (line.indexOf("<?xml version=\"1.0\" encoding=\"utf-8\"?>") >= 0
					&& line.indexOf("<s:Fault><faultcode") >= 0) {
				line = line.substring(line.indexOf("<?xml version=\"1.0\" encoding=\"utf-8\"?>"));
				String responseCode = "ErrorNonExistentMailbox";
				String nameNamespaceKey = "e";
				String nameNamespaceValue = "http://schemas.microsoft.com/exchange/services/2006/errors";
				String nameXPath = "//e:ResponseCode";

				String textNamespaceKey = "t";
				String textNamespaceValue = "http://schemas.microsoft.com/exchange/services/2006/types";
				String textXPath = "//t:Value";

				text = xmlUtil.findNamespaceNodeText(line, responseCode, nameNamespaceKey, nameNamespaceValue,
						nameXPath, textNamespaceKey, textNamespaceValue, textXPath);
				if (text != null && text.trim().length() > 0) {
					set.add(text);
					total.add(text);
				}
			}
		}
		xmlUtil.print(set);
		xmlUtil.print("find total:[" + total.size() + "] records!");
		xmlUtil.print("NoDuplicateCount:[" + set.size() + "] records!");
	}

	public void countSubjectFromFiles(String[] paths, String str) {
		if (paths != null && paths.length > 0) {
			for (int i = 0; i < paths.length; i++) {
				System.out.println("===============" + paths[i] + "  begining==============");
				countSubjectFromFile(paths[i], str);
				System.out.println("===============" + paths[i] + "  end     ==============");
			}
		}
	}

	public void countSubjectFromFile(String path, String str) {
		try {
			File f = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(f));

			Map<String, String> subjects = new HashMap<String, String>();

			String tmp = "";
			String sub = "";
			while ((tmp = br.readLine()) != null) {
				if (tmp.contains(str)) {
					if (tmp.indexOf(", date: ") >= 0) {
						sub = tmp.substring(tmp.indexOf(str) + str.length(), tmp.indexOf(", date: "));
					} else {
						sub = tmp.substring(tmp.indexOf(str) + str.length());
					}
					subjects.put(sub, sub);
				}
			}
			// printMap(subjects);
			System.out.println("total:[" + subjects.size() + "]");
			br.close();
			br = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findStrFromFiles(String[] paths, String str) {
		if (paths != null && paths.length > 0) {
			for (int i = 0; i < paths.length; i++) {
				System.out.println("===============" + paths[i] + "  begining==============");
				findStrFromFile(paths[i], str);
				System.out.println("===============" + paths[i] + "  end     ==============");
			}
		}
	}

	public void findStrFromFile(String path, String str) {
		try {
			File f = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(f));

			String tmp = "";
			int count = 0;
			while ((tmp = br.readLine()) != null) {
				if (tmp.contains(str)) {
					// System.out.println(tmp);
					count++;
				}
			}
			System.out.println("total:[" + count + "]");
			br.close();
			br = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void collectMessageClassFromFiles(String[] paths) {
		if (paths != null && paths.length > 0) {
			for (int i = 0; i < paths.length; i++) {
				System.out.println("===============" + paths[i] + "  begining==============");
				collectMessageClassFromFile(paths[i]);
				System.out.println("===============" + paths[i] + "  end     ==============");
			}
		}
	}

	public void collectMessageClassFromFile(String path) {
		try {
			File f = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(f));

			Map<String, String> messageClasses = new HashMap<String, String>();

			String start = "<t:ItemClass>";
			String end = "</t:ItemClass>";
			// String start = "<t:ResponseType>";
			// String end = "</t:ResponseType>";
			// String start = "<t:RoutingType>";
			// String end = "</t:RoutingType>";
			// String start = "<m:ResponseCode>";
			// String end = "</m:ResponseCode>";

			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				if (tmp.trim().length() > 0) {
					collect(tmp, start, end, messageClasses);
				}
			}
			br.close();
			br = null;

			printMap(messageClasses);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void collectMessageClassFromString(String content) {
		try {
			Map<String, String> messageClasses = new HashMap<String, String>();

			String start = "<t:ItemClass>";
			String end = "</t:ItemClass>";
			// String start = "<t:ResponseType>";
			// String end = "</t:ResponseType>";
			// String start = "<t:RoutingType>";
			// String end = "</t:RoutingType>";

			if (content.trim().length() > 0) {
				collect(content, start, end, messageClasses);
			}
			printMap(messageClasses);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void collect(String line, String start, String end, Map<String, String> messageClasses) {
		if (line.length() < start.length() + end.length()) {
			return;
		}
		String cls = "";
		if (line.indexOf(start) >= 0) {
			cls = line.substring(line.indexOf(start) + start.length(), line.indexOf(end));
			messageClasses.put(cls, cls);
			line = line.substring(line.indexOf(end) + end.length());
			collect(line, start, end, messageClasses);
		}
	}

	public void printMap(Map<String, String> map) {
		Iterator<String> iter = map.keySet().iterator();
		String key = "";
		while (iter.hasNext()) {
			key = iter.next();
			System.out.println(map.get(key));
		}
	}

	public void println(Object obj) {
		System.out.println(obj);
	}

}
