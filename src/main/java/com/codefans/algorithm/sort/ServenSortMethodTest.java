package com.codefans.algorithm.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2017-09-15 18:15
 **/
public class ServenSortMethodTest {

    private int[] arr = new int[]{2, 5, 3, 1, 4, 9, 6, 8, 7, 0};

    @Before
    public void before() {
        System.out.println("sort before:");
        print(arr);
    }

    @After
    public void after() {
        System.out.println("sort after:");
        print(arr);
    }

    @Test
    public void sortTest() {
        bubbleSortTest();
    }

    public void bubbleSortTest() {


//        this.bubbleSort(arr);
//        new BubbleSort().bubbleSort(arr);

//        this.selectSort(arr);

        this.insertSort(arr);


    }

    public void bubbleSort(int[] arr) {
        int temp = 0;
        for(int i = 0; i < arr.length - 1; i ++) {
            for(int j = 0; j < arr.length - 1; j ++) {
                if(arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void selectSort(int[] arr) {
        int temp = 0;
        for(int i = 0; i < arr.length - 1; i ++) {
            for(int j = i + 1; j < arr.length; j ++) {
                if(arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public void insertSort(int[] arr) {
        int temp = 0;
        for(int i = 1; i < arr.length; i ++) {
            temp = arr[i];
            int j = i;

            for(; j > 0 && temp < arr[j - 1]; j --) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;

            print(arr);

        }
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
