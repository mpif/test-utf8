package com.codefans.java.modifiers;

import com.codefans.java.modifiers.sub.SubClass;

public class ModifiersTest extends SubClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ModifiersTest mt = new ModifiersTest();
		// mt.age = 2;
		mt.password = "3";

		mt.print();
	}

}
