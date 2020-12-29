package com.zhangsan.no_sort;

import java.util.Arrays;

/**
 * 选择排序
 * 排序方法：（从i开始到数组长度-1）结束，没回比出最小的
 *
 * @author zhangyuekun
 * @date 2020/12/29 11:55
 */
public class SelectSort {

    public static void main(String[] args) {
        System.err.println("选择排序====");
        int[] arr = {5, 6, 1, 2, 0, 8, 4, 35, 134, 64, 246546, 11, 32};
        System.out.println("排序前：\n" + Arrays.toString(arr));
        System.out.println("================================");
        long startTime = System.currentTimeMillis();
        selectSort(arr);
        long endtTime = System.currentTimeMillis();
        System.out.println("耗时： " + (endtTime-startTime));
        System.out.println("排序后：\n" + Arrays.toString(arr));
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] arr) {
        // 代表每轮比较最小的数的下标
        int minIndex;
        // 控制比较的轮数
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            // 控制比较的过程
            for (int j = i+1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex]? j: minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 数组两个数交换位置
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


}
