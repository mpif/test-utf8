package com.codefans.algorithm.sort;

public class SortBase {

	protected void swap(int[] array, int i, int j) {
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	protected void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
	}

}
