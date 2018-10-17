package org.apache.commons.lang;

import org.apache.commons.lang3.StringUtils;

public class StringUtilsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(StringUtils.center(" demoStopWatch ", 30, "=")); // =======
																			// demoStopWatch
																			// ========

		// isEmpty(CharSequence cs)
		System.out.println("isEmpty(''): " + StringUtils.isEmpty(""));// true
		System.out.println("isEmpty(' '): " + StringUtils.isEmpty(" "));// false
		System.out.println("isEmpty(null): " + StringUtils.isEmpty(null));// true

		// isBlank(CharSequence cs)
		System.out.println("isBlank(''): " + StringUtils.isBlank("")); // true
		System.out.println("isBlank('  '): " + StringUtils.isBlank("  "));// true
		System.out.println("isBlank(null): " + StringUtils.isBlank(null));// true

		System.out.println(StringUtils.defaultIfBlank(" ", "blank")); // blank
		System.out.println(StringUtils.defaultIfEmpty("", "empty")); // empty
		System.out.println(StringUtils.defaultString("aaa")); // aaa
		System.out.println(StringUtils.defaultString("aaa", "bbbb")); // aaa

		System.out.println(StringUtils.deleteWhitespace(" aa bb ")); // aabb

		System.out.println(StringUtils.trim(""));
		System.out.println(StringUtils.trimToEmpty(""));
		System.out.println(StringUtils.trimToNull("") == null); // true

		System.out.println(StringUtils.isNumeric("2c2")); // false
		System.out.println(StringUtils.isNumericSpace("")); // true
	}

}
