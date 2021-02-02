package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

import java.util.Arrays;

/**
 * 插入排序
 * 左边都是有序数组，然后拿一个和这个有序数组比插入成新的有序数组
 */
public class C003_InsertionSort {

    public static void main(String[] args) {
        boolean succeed = true;
        for (int i = 0; i < 100; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(10, 10);
            int[] arr2 = ArrayUtil.copyArr(arr1);

            ArrayUtil.sort(arr1);
            insertionSort(arr2);

            // 排序不成功。
            if(! ArrayUtil.isEquals(arr1, arr2)) {
                ArrayUtil.printArr(arr1);
                System.out.println("========================================");
                ArrayUtil.printArr(arr2);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed? "排序成功!": "排序有误!");
        int[] arr1 = ArrayUtil.generateRandomArray(10, 10);
        ArrayUtil.printArr(arr1);
        insertionSort(arr1);
        ArrayUtil.printArr(arr1);
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
