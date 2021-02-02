package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * 冒泡排序
 */
public class C001_BubbleSort {

    public static void main(String[] args) {
        boolean succeed = true;
        for (int i = 0; i < 100; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(10, 10);
            int[] arr2 = ArrayUtil.copyArr(arr1);

            ArrayUtil.sort(arr1);
            bubbleSort(arr2);

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
        bubbleSort(arr1);
        ArrayUtil.printArr(arr1);

    }

    public static void bubbleSort(int[] arr) {
        if(arr == null || arr.length < 2)
            return;
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
