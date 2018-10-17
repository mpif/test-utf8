package com.codefans.java.modifiers.sub;

public class SubClass2 extends SubClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SubClass sub = new SubClass();
		sub.age = 2;
		sub.password = "3";

		sub.print();
		age2 = 3;
		password2 = "bb";

		SubClass2 sub2 = new SubClass2();
		sub2.age = 3;
		sub2.password = "333";
	}

}
