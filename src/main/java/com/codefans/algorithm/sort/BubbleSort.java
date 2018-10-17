package com.codefans.algorithm.sort;

public class BubbleSort extends SortBase {
	// 冒泡是一种简单的交换排序[O(n*n)]
	public void bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++)
			for (int j = 0; j < array.length - i - 1; j++)
				if (array[j] < array[j + 1])// 小的往上冒,由大到小
					swap(array, j, j + 1);
	}

}
