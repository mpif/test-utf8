package com.test;

public class TestStaticBlock {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 3;
		if (n > 3) {
			System.out.println("aaa");
		}

		{
			System.out.println("bbb");
		}
		System.out.println("ccc");

	}

	static { // 比main方法先执行
		System.out.println("dddd");
	}

	{// 不执行
		System.out.println("eee");
	}

}
