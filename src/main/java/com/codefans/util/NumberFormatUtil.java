package com.codefans.util;

import java.text.NumberFormat;

/*
 * @Author: Sean
 * @Time: 2015-08-20 09:45:23
 */
public class NumberFormatUtil {

	public static String divide(int num1, int num2) {
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String result = numberFormat.format((float) num1 / (float) num2 * 100);
		System.out.println("num1和num2的百分比为:" + result + "%");
		return result;
	}
}
