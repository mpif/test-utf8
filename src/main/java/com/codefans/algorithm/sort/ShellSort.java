package com.codefans.algorithm.sort;

//希尔排序 O(n的1.x次方) 
public class ShellSort extends SortBase {

	// 按照增量进行直接插入
	public void shellInsert(int[] array, int gap) {
		for (int i = gap; i < array.length; i++) {
			int temp = array[i];
			int j = i;
			for (; j >= gap && temp > (array[j - gap]); j -= gap)
				array[j] = array[j - gap];
			array[j] = temp;
		}
	}

	public void shellSort(int[] array) {
		for (int gap = array.length / 2; gap > 0; gap /= 2)// 取增量
			shellInsert(array, gap);
	}

}
