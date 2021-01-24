package com.zhangsan.no_sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        System.err.println("冒泡排序==========================");
        int[] arr = {5, 6, 1, 2, 0, 8, 4, 35, 134, 64, 246546, 11, 32};
        System.out.println("排序前：\n" + Arrays.toString(arr));
        System.out.println("================================");
        long startTime = System.currentTimeMillis();
        bubbleSort(arr);
        long endtTime = System.currentTimeMillis();
        System.out.println("耗时： " + (endtTime-startTime)+"ms");
        System.out.println("排序后：\n" + Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        // 共比数组长度次
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > 0; j--) {
                // 每轮比出最大的
                if(arr[j] < arr[j-1]){
                    swap(arr, j, j-1);
                }
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

}
