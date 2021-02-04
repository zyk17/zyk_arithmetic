package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * 归并排序
 *
 * @author zhangsan
 * @date 2021/2/3 17:56
 */
public class C004_MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        // base case
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        // 2 3
        // l:0  m:0  r:1

        // 1 2 3 6 ||  2 4 5 8
        // l = 0     mid = 3   r = 7
        // 1 2 2 3 4 5 6

        int[] help = new int[r - l + 1];
        int index = 0, lIndex = l, rIndex = mid+1;  // index 辅助数组的赋值下标， lIndex：左边下标，rIndex：右边下标
        while (lIndex <= mid && rIndex <= r) {
            help[index++] = arr[lIndex] < arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
        }
        // 此时上边循环结束，有两种情况。
        // 1：lIndex 越界
        // 2：rIndex 越界
        while (lIndex <= mid) {
            help[index++] = arr[lIndex++];
        }
        while (rIndex <= r) {
            help[index++] = arr[rIndex++];
        }
        // 最后把辅助数组给 拷贝到 数组里
        while ( r >= l) {
            arr[r] = help[--index];
            r--;
        }
    }


    public static void main(String[] args) {
        /*int[] arr = {2, 3, 6, 1, 5, 4, 8, 2};
        ArrayUtil.printArr(arr);
        mergeSort(arr);
        ArrayUtil.printArr(arr);*/


        boolean succeed = true;
        for (int i = 0; i < 100; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(10, 10);
            int[] arr2 = ArrayUtil.copyArr(arr1);

            ArrayUtil.sort(arr1);
            mergeSort(arr2);

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
        mergeSort(arr1);
        ArrayUtil.printArr(arr1);
    }
}
