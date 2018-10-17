package com.codefans.java.lang;

public class StringDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringDemo sd = new StringDemo();
		sd.test();

	}

	public void test() {
		test01();
		test02();
	}

	public void test01() {
		String s1 = "tty";
		String s2 = new String("tty");
		String s3 = "tty";
		if (s1 == s2) {
			System.out.println("s1和s2是同一个对象");
		} else {
			System.out.println("s1和s2不是同一个对象");
		}
		if (s1 == s3) {
			System.out.println("s1和s3是同一个对象");
		} else {
			System.out.println("s1和s3不是同一个对象");
		}
	}

	public void test02() {
		char c = '0';
		System.out.println((int) c); // 48
		c = '1';
		System.out.println((int) c); // 49
		c = 'a';
		System.out.println((int) c); // 97
		c = 'A';
		System.out.println((int) c); // 65
		c = 'z';
		System.out.println((int) c); // 122
		c = 'Z';
		System.out.println((int) c); // 90

		c = (char) 48;
		System.out.println(c); // '0'

		c = (char) 49;
		System.out.println(c); // '1'

		c = (char) 97;
		System.out.println(c); // 'a'

		c = (char) 65;
		System.out.println(c); // 'A'

		c = (char) 122;
		System.out.println(c); // 'z'

		c = (char) 90;
		System.out.println(c); // 'Z'

	}

}
