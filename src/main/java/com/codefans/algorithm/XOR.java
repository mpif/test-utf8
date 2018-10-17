package com.codefans.algorithm;

public class XOR {

	/**
	 * 给出2n+1个数，其中有2n个数出现过两次，如何用最简便的方法找出里面只出现了一次的那个数。
	 * 
	 * 例如这样一组数3,3,1,2,4,2,5,5,4，其中只有1出现了1次，其他都是出现了2次，如何找出其中的1？
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = { 3, 3, 1, 2, 4, 2, 5, 5, 4 };
		int res = 0;// 初始值
		for (int i = 0; i < arr.length; i++) {
			res ^= arr[i];
		}
		System.out.println(res);

		System.out.println(4 ^ 0); // 输出 4 ,原理: 任何数异或0值不变
		System.out.println(4 ^ 4); // 输出 0 ,原理: 任何数与自己异或值为0
		System.out.println(6 ^ 5 ^ 5); // 输出 6 ,原理: 一个数两次异或同一个数，值不变

	}

	/*
	 * 运行结果：
	 * 
	 * 1
	 * 
	 * 算法的原理就是：任何数异或0值不变，任何数与自己异或值为0。
	 * 
	 * 因此一个数两次异或同一个数，值不变。
	 * 
	 * 
	 */

}
