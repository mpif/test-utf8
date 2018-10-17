package com.codefans.java.modifiers.sub;

public class SubClass {

	private String username;

	int age;
	static int age2;
	protected String password;
	protected static String password2;

	public boolean flag;

	public void print() {
		System.out.println("username: " + username);
		System.out.println("age: " + age);
		System.out.println("age2: " + age2);
		System.out.println("password: " + password);
		System.out.println("password2: " + password2);

		System.out.println("flag: " + flag);
	}
}
