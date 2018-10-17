package com.codefans.java._static;

public class StaticInit {

	public StaticInit() {
		System.out.println("construct method called");
	}

	public static void print() {
		System.out.println("print method called");
	}

	static {
		System.out.println("static block called");
	}

	public static StaticInit access = new StaticInit();
}
