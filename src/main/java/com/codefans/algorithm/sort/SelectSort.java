package com.codefans.algorithm.sort;

//复杂度，平方阶 
public class SelectSort extends SortBase {

	// 直接选择排序，先默认第一个最大，然后在后面的序列中找出比他大的来交换，这样不停的重复
	public void selectSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int max_potion = i;
			for (int j = i + 1; j < array.length; j++)
				if (array[max_potion] < array[j])
					max_potion = j;
			if (i != max_potion)// 如果默认失效
				swap(array, i, max_potion);

		}

	}

}
