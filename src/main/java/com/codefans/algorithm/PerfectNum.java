package com.codefans.algorithm;

public class PerfectNum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 1000;
		int perfectNum[] = new int[100];
		for (int i = 2; i < n; i++) {
			int sum = 0;
			int index = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
					perfectNum[index++] = j;
				}
			}
			if (sum == i) {
				for (int j = 0; j < index; j++) {
					System.out.print(perfectNum[j]);
					if (j != index - 1) {
						System.out.print(" + ");
					}
				}
				System.out.println(" = " + i);
			}
		}
	}

}
