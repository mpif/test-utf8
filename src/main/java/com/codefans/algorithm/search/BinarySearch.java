package com.codefans.algorithm.search;

import net.sf.ehcache.config.Searchable;

/**
 * @author: caishengzhi
 * @date: 2017-09-20 10:23
 * 折半查找，也称二分查找
 * 非递归实现和递归实现
 * 时间复杂度为O(log2n), 计算方式: n/2+n/4+n/8+...n/2^k
 **/
public class BinarySearch implements SearchBase {

    private int[] arr = new int[]{1,3,5,7,9,11,16,18,20};
    private int value = 9;

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        bs.search();
    }

    @Override
    public void search() {
        this.loopSearch();
        this.recursionSearch();
    }

    /**
     * 二分查找的非递归实现
     */
    public void loopSearch() {

        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int valueIndex = -1;

        while((low<= high) && (low <= arr.length - 1) && high <= arr.length - 1) {
            mid = (low + high) / 2;
            if(value == arr[mid]) {
                valueIndex = mid;
                break;
            }
            if(value < arr[mid]) {
                high = mid - 1;
            } else if(value > arr[mid]) {
                low = mid + 1;
            }

        }

        System.out.println("loopSearch --> valueIndex=" + valueIndex);

    }

    public void recursionSearch() {
        int[] array = arr;
        int low = 0;
        int high = array.length - 1;
        int val = value;
        int valueIndex = this.recursionSearch(array, low, high, val);
        System.out.println("recursionSearch --> valueIndex=" + valueIndex);
    }

    /**
     * 二分查找的递归实现
     * @param arr
     * @param low
     * @param high
     * @param val
     * @return
     */
    public int recursionSearch(int[] arr, int low, int high, int val) {
        int mid = (low + high) / 2;
        int index = 0;
        if(val == arr[mid]) {
            index = mid;
        } else if(val > arr[mid]) {
            index = recursionSearch(arr, mid+1, high, val);
        } else if(val < arr[mid]) {
            index = recursionSearch(arr, mid+1, mid-1, val);
        } else {
            index = -1;
        }
        return index;
    }



}
