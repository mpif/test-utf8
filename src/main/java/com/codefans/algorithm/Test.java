package com.codefans.algorithm;

/**
 * @class 打印回型数组Su_Huirong版
 * @author Su_Huirong
 * @time 5小时
 * @version Su_Huirong 5小时版 历经各种磨难 各种考验 调试时间3h  
 */
import java.util.Scanner;

public class Test {
	/**
	 * @row 列
	 * @length 行
	 * @sum 数组元素数量
	 * @math 二维数组：第一个维度为列，第二个维度为行
	 */
	int row, length;
	int num;
	int[][] math;

	/**
	 * 数组初始化： 根据元素数量sum判断行和列，给二维数组分配空间
	 */
	public void initial() {
		/**
		 * @temp 保存乘积等于sum的两个因数的差，取差小的两个因数，比如sum为24，因数有1、2、3、4、6、8、12。
		 *       我们取因数4、6而不是取因数1、12
		 */
		int temp = num + 1;
		length = (num + 1) / 2;
		for (int i = temp; i > 0; i--) {
			for (int j = 1; j <= i; j++) {
				if (i * j == num && i - j < temp) {
					row = j;
					length = i;
					temp = i * j;
				}
			}
		}
		/**
		 * @followRow 此变量用于追踪一轮赋值结束后下一次赋值的位置(列)
		 * @followLength 此变量用于追踪一轮赋值结束后下一次赋值的位置(行)
		 * @flagRow 标记赋值范围(列的范围)
		 * @flagLength 标记赋值范围(行的范围)
		 */
		math = new int[row][length];
		int followRow = 0, followLength = 0;
		int flagRow = 0, flagLength = 1;
		/**
		 * @row 用来表示当前行
		 * @length 用来表示当前列
		 */
		row = 0;
		length = 0;
		/**
		 * 核心函数(3h调试的就是他) 4个if选择结构判断坐标与追踪坐标的位置判断方向上、下、左、右从而判断如何赋值
		 */
		for (int i = 1; i <= num;) {
			/** 向右 */
			if (row == flagRow && length < flagLength) {
				followLength = math[0].length - 1;
				while (math[row][followLength] != 0 && followLength > 0) {
					followLength--;
				}
				while (length < followLength) {
					math[row][length++] = i++;
				}
				flagRow = row + 1;
				flagLength = length;
			}
			/** 向下 */
			if (row < flagRow && length == flagLength) {
				followRow = math.length - 1;
				while (math[followRow][length] != 0 && followRow > 0) {
					followRow--;
				}
				while (row < followRow) {
					math[row++][length] = i++;
				}
				flagRow = row;
				flagLength = length - 1;
			}
			/** 向左 */
			if (row == flagRow && length > flagLength) {
				followLength = 0;
				while (math[row][followLength] != 0 && followLength < math[0].length - 1) {
					followLength++;
				}
				while (length > followLength) {
					math[row][length--] = i++;
				}
				flagRow = row - 1;
				flagLength = length;
			}
			/** 向上 */
			if (row > flagRow && length == flagLength) {
				followRow = 0;
				while (math[followRow][length] != 0) {
					followRow++;
				}
				do {
					math[row--][length] = i++;
				} while (row > followRow);
				flagRow = row;
				flagLength = length + 1;
			}

		}
	}

	/**
	 * @show() 循环打印二维数组
	 *
	 */
	public void show() {
		for (int i = 0; i < math.length; i++) {
			for (int j = 0; j < math[i].length; j++) {
				System.out.print(math[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * @main() 主方法
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Test t = new Test();
		System.out.print("请输入环形数组元素数量：");
		t.num = input.nextInt();
		t.initial();
		t.show();
	}
}