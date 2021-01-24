package com.zhangsan.no_sort;

import java.util.Arrays;

/**
 * 插入排序
 * 左边都是有序数组，然后拿一个和这个有序数组比插入成新的有序数组
 */
public class InsertionSort {

    public static void main(String[] args) {
        System.err.println("插入排序==========================");
        int[] arr = {5, 6, 1, 2, 0, 8, 4, 35, 134, 64, 246546, 11, 32};
        System.out.println("排序前：\n" + Arrays.toString(arr));
        System.out.println("================================");
        long startTime = System.currentTimeMillis();
        insertionSort(arr);
        long endtTime = System.currentTimeMillis();
        System.out.println("耗时： " + (endtTime-startTime)+"ms");
        System.out.println("排序后：\n" + Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j-1] ; j--) {
                swap(arr, j, j-1);
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

}
