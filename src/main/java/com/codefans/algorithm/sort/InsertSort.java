package com.codefans.algorithm.sort;

public class InsertSort extends SortBase {

	// 直接插入排序,默认第一个有序，然后像打扑克那样插入[O(n*n)]
	public void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i])
					swap(array, i, j);// 使用交换技术，也可依次后移
			}
		}
	}

	// 另外一种实现,见shell插入部分
	public void insertionSort(int[] a) {
		for (int p = 1; p < a.length; p++) {
			int tmp = a[p];
			int j = p;

			for (; j > 0 && tmp < a[j - 1]; j--)
				a[j] = a[j - 1];// 如果小就往后移动
			a[j] = tmp;// 将待插入元素插到移动完的空位处
		}
	}

}
