package com.codefans.java._static;

/*
 * @Author: Sean
 * @Time: 2015-08-18 09:41:43
 */
public class StaticOverride {

	public static void print() {
		System.out.println("StaticOverride static print method.");
	}

	public void println() {
		System.out.println("StaticOverride static println method.");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// StaticOverride so = new SubStaticOverride();
		SubStaticOverride so = new SubStaticOverride();
		so.print();
		so.println();

		// StaticOverride.print();
		// SubStaticOverride.print();

	}

}

class SubStaticOverride extends StaticOverride {

	// public static void print() {
	// System.out.println("SubStaticOverride static print method.");
	// }

	public void println() {
		System.out.println("SubStaticOverride static println method.");
	}

}
