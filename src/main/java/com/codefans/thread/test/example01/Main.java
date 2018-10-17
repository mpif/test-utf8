package com.codefans.thread.test.example01;

public class Main {
	public static void main(String[] args) {
		ReturnThreadInfo info = new ReturnThreadInfo();
		info.run();
		System.out.println(info.getThreadInfo());
	}
}
