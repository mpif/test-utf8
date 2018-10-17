package com.codefans.java.math;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class FloatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(0.05 + 0.01);
		System.out.println(1.0 - 0.42);
		System.out.println(4.015 * 100);
		System.out.println(123.3 / 100);
		System.out.println(0.05);

		// 可以利用DecimalFormat类来格式化浮点数，但是得到的结果也有可能是错误的值
		System.out.println(new java.text.DecimalFormat("0.00").format(3.125));
		System.out.println(new java.text.DecimalFormat("0.00").format(3.135));

		// 以下代码是java.math.BigDecimal的构造函数
		BigDecimal bd1 = new BigDecimal(0.05);
		System.out.println(bd1.toString());
		BigDecimal bd2 = new BigDecimal("0.05");
		System.out.println(bd2.toString());

		// 以下代码使用java.math.BigDecimal来实现浮点数的精确计算
		// +
		BigDecimal bd3 = new BigDecimal(String.valueOf(0.05));
		BigDecimal bd4 = new BigDecimal(String.valueOf(0.01));
		System.out.println((bd3.add(bd4)).doubleValue());

		// -
		BigDecimal bd5 = new BigDecimal(String.valueOf(0.05));
		BigDecimal bd6 = new BigDecimal(String.valueOf(0.01));
		System.out.println((bd5.subtract(bd6)).doubleValue());

		// *
		BigDecimal bd7 = new BigDecimal(String.valueOf(0.05));
		BigDecimal bd8 = new BigDecimal(String.valueOf(0.01));
		System.out.println((bd7.multiply(bd8)).doubleValue());

		/// 这里没有考虑数据错误的可能情况
		// 定义了精确位数
		int scale = 10;
		BigDecimal bd9 = new BigDecimal(String.valueOf(0.05));
		BigDecimal bd10 = new BigDecimal(String.valueOf(0.03));
		System.out.println((bd9.divide(bd10, scale, BigDecimal.ROUND_HALF_EVEN)).doubleValue());

		// 四舍五入
		scale = 4;
		BigDecimal bd11 = new BigDecimal(String.valueOf(3.1415926));
		System.out.println(bd11.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());

		int num1 = 1;
		int num2 = 2;
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String result = numberFormat.format((float) num1 / (float) num2 * 100);
		System.out.println("num1和num2的百分比为:" + result + "%");

	}

}
