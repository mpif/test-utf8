package com.codefans.algorithm.test;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 14-4-28 Time: 下午5:48 To
 * change this template use File | Settings | File Templates.
 */
public class StringSwitch {

	public static void main(String[] args) {

		StringSwitch stringSwitch = new StringSwitch();
		char[] source = new char[] { 'a', 'b', 'c', '\n', '\r' };
		char[] dest = new char[source.length * 5];
		stringSwitch.expand(source, dest);

	}

	public void expand(char[] s, char[] t) {
		int i, j;
		for (i = j = 0; s[i] != '\0'; i++)
			;
	}

}
