package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * 选择排序
 * 排序方法：（从i开始到数组长度-1）结束，没回比出最小的
 */
public class C002_SelectSort {

    public static void main(String[] args) {
        boolean succeed = true;
        for (int i = 0; i < 100; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(10, 10);
            int[] arr2 = ArrayUtil.copyArr(arr1);

            ArrayUtil.sort(arr1);
            selectSort(arr2);

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
        selectSort(arr1);
        ArrayUtil.printArr(arr1);
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        // 代表每轮比较最小的数的下标
        int minIndex;
        // 控制比较的轮数
        for (int i = 0; i < arr.length-1; i++) {
            minIndex = i;
            // 比较的过程，找出最小的下标
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            if (minIndex != i)
                swap(arr, i, minIndex);
        }
    }

    /**
     * 数组两个数交换位置，（a=b）会出错。
     * @param a,b 下标
     */
    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];

        /*int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;*/
    }


}
