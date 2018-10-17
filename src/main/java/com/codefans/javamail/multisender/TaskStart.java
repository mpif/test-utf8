package com.codefans.javamail.multisender;

import java.util.List;
import java.util.Map;

public class TaskStart {

	public static String[] addresses = new String[] {
			// "a@new2010.local",
			// "b@new2010.local",
			// "d@new2010.local",
			// "e@new2010.local",
			// "f@new2010.local",
			// "ff@new2010.local"
	};

	// public static String[] addresses = new String[]{
	// "wangb@new2010.local",
	// "f@new2010.local",
	// "eeaadmin@new2010.local",
	// "report@new2010.local",
	// "1@new2010.local"
	// };

	// public static String[] addresses = new String[]{
	// "hlp@new2010.local",
	// "wangb@new2010.local",
	// "f@new2010.local",
	// "eeaadmin@new2010.local",
	// "wang@new2010.local",
	// "user1015@new2010.local",
	// "report@new2010.local",
	// "1@new2010.local",
	// "user1@new2010.local",
	// "user2@new2010.local",
	// };

	public static String[] addresses2 = new String[] { "bj3@new2010.local", "bj1@new2010.local",
			"mailjourna@new2010.local", "south@new2010.local", "kyo king@new2010.local", "gz1@new2010.local",
			"hlp@new2010.local", "sh1@new2010.local", "smtp journal@new2010.local", "eeajournal@new2010.local",
			"north@new2010.local", "JackWang@new2010.local", "wj@new2010.local", "taojournal2@new2010.local",
			"mo.yun@new2010.local", "wujuan@new2010.local", "209journal@new2010.local", "nana wu@new2010.local",
			"nana@new2010.local", "小，三@new2010.local", "wangb@new2010.local", "kevin2@new2010.local",
			"tem.plate@new2010.local", "f@new2010.local", "test1@new2010.local", "taojournal@new2010.local",
			"jing.ling@new2010.local", "JIN@new2010.local", "onlinetest@new2010.local", "kevin1@new2010.local",
			"eeaadmin@new2010.local", "nanajournal@new2010.local", "wang@new2010.local", "金贝勒@new2010.local",
			"Administrator@new2010.local", "hello@new2010.local", "u@new2010.local", "h123@new2010.local",
			"shi.zhu@new2010.local", "c@new2010.local", "myjournal@new2010.local", "金；格；格@new2010.local",
			"linjournal@new2010.local", "h789@new2010.local", "user1015@new2010.local", "report@new2010.local",
			"bj2@new2010.local", "1@new2010.local", "h456@new2010.local", "hi,world@new2010.local",
			"user1@new2010.local", "thj@new2010.local", "user1010@new2010.local", "user2@new2010.local",
			"sunyu@new2010.local"
			// ,
			// "FederatedEmail.4c1f4d8b-8179-4148-93bf-00a95fa1e042@new2010.local",
			// "DiscoverySearchMailbox
			// {D919BA05-46A6-415f-80AD-7E09334BB852}@new2010.local",
			// "SystemMailbox{1f05a927-943b-4961-817d-67d5606a7312}@new2010.local",
			// "SystemMailbox{e0dc1c29-89c3-4034-b678-e6c29d823ed9}@new2010.local",
			// "michael@new2010.local",
	};

	public static void sendMail(List<Map<String, Object>> list) {
		Map<String, Object> map = null;
		String address = "";
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			address = map.get("username") + "@" + map.get("domain");
			if ("f@new2010.local".equals(address)) {
				Thread t = new Thread(new TaskThread(address));
				t.start();
				break;
			}
		}
	}

	public static void sendMail() {
		getEmailAddresses();
		send();
	}

	public static void getEmailAddresses() {
		Configuration config = new Configuration();
		List<String> emailAddresses = config.getEmailAddresses();
		addresses = emailAddresses.toArray(new String[] { null });
	}

	public static void send() {
		String address = "";
		for (int i = 0; i < addresses.length; i++) {
			address = addresses[i];
			Thread t = new Thread(new TaskThread(address));
			t.start();
			System.out.println("add task [" + address + "]");
		}
	}

	public static void printMailUser(List<Map<String, Object>> list) {
		Map<String, Object> map = null;
		StringBuilder sb = new StringBuilder();
		String address = "";
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			address = map.get("username") + "@" + map.get("domain");
			if (address.endsWith("@new2010.local")) {
				sb.append("\"").append(address).append("\", ");
			}
		}
		System.out.println(sb.toString());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TaskTool tool = new TaskTool();
		// List<Map<String, Object>> list = tool.getAllUsers();
		// printMailUser(list);
		// sendMail(list);

		sendMail();

	}

}
