package com.codefans.java.classes.test01;

public class SystemInfo extends BaseInfo {

	public SystemInfo(int n) {
		System.out.println(n);
	}

	public void setEmailAddress(String address) {
		this.putPrefAttribute("bbb", address);
	}

	public String getEmailAddress() {
		return (String) this.getPrefAttribute("bbb");
	}

	public static void main(String[] args) {
		SystemInfo info = new SystemInfo(6);
		info.putPrefAttribute("aaa", "aaa");

		System.out.println(info.getPrefAttribute("aaa"));
	}
}
