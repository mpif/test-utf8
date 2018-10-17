package java.com.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-24 14:00
 */

public class SortTest {

    private int[] arr = new int[]{2, 5, 3, 1, 4, 9, 6, 8, 7, 0};

    @Before
    public void before() {
        System.out.println("sort before:");
        print(arr);
    }

    @Test
    public void test() {
        this.bubbleSort();
        this.selectSort();
        this.insertSort();
        this.shellSort();
        this.quickSort();
        this.mergeSort();
        this.heapSort();
    }

    /**
     * 14:21
     */
    public void bubbleSort() {
        for(int i = 0; i < arr.length - 1; i ++) {
            for(int j = arr.length - 1; j > i; j --) {
                if(arr[j] < arr[j - 1]) {
                    swap(arr, j, j -1);
                }
            }
        }
    }

    public void selectSort() {

    }

    public void insertSort() {

    }

    public void shellSort() {

    }

    public void quickSort() {

    }

    public void mergeSort() {

    }

    public void heapSort() {

    }

    public void swap(int[] array, int i, int j) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @After
    public void after() {
        System.out.println("sort after:");
        print(arr);
    }

    public void print(int[] arr) {
        if(arr != null) {
            for(int i = 0; i < arr.length; i ++) {
                if(i != 0) {
                    System.out.print(", ");
                }
                System.out.print(arr[i]);
            }
            System.out.println();
        }
    }



}
