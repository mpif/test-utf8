package com.codefans.hashcode;

public class StringHashCode {

	/**
	 * @return void
	 * @author caisz
	 */
	public static void main(String[] args) {
		String str = "hello world";
		String str2 = "hello wordl";

		System.out.println("str.hashcode: " + str.hashCode());
		System.out.println("str2.hashcode: " + str2.hashCode());

		// 两个不同字符串的hashcode值可能相同，如何唯一标识一个字符串???
		String a = "/2010/6/13/19/766105207591607338053525248042284292/1.0";
		String b = "/2010/10/8/19/766105208164534487403083462088868610/1.0";

		System.out.println("a.hashcode: " + a.hashCode());
		System.out.println("b.hashcode: " + b.hashCode());
		System.out.println(a.hashCode() == b.hashCode());
		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));

	}

}
