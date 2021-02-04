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
        int index = 0, lIndex = l, rIndex = mid + 1;  // index 辅助数组的赋值下标， lIndex：左边下标，rIndex：右边下标
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
        do {
            arr[r] = help[--index];
            r--;
        } while (r >= l);
    }

    /**
     * 递归版改迭代版。
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int stepSize = 1;
        int n = arr.length-1;

        while ( stepSize < arr.length ) {
            int l = 0;
            // l==n 只有1位也不做了
            while (l < n) {
                // mid = L + 步长 -1
                int mid = l+stepSize-1;
                if(mid > n) {
                    break;
                }
                // r = mid + 步长。 如果超过了数组长度，就采用数组最后一个地址
                int r = Math.min(mid + stepSize, n);
                merge(arr, l, mid, r);
                l = r + 1;
            }
            stepSize <<= 1;
        }
    }


    public static void main(String[] args) {
        /*int[] arr = {-2, -1, 0, 0, 0, 1, 2, 9, -1, -1};
        ArrayUtil.printArr(arr);
        mergeSort2(arr);
        ArrayUtil.printArr(arr);*/

        boolean succeed = true;
        for (int i = 0; i < 10000000; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(100, 1000000);
            int[] arr2 = ArrayUtil.copyArr(arr1);

            ArrayUtil.sort(arr1);
            try {
                mergeSort2(arr2);
            }catch (Exception e) {
                System.out.println("=====出现异常：=====");
                ArrayUtil.printArr(arr2);
            }

            // 排序不成功。
            if (!ArrayUtil.isEquals(arr1, arr2)) {
                ArrayUtil.printArr(arr1);
                System.out.println("========================================");
                ArrayUtil.printArr(arr2);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "排序成功!" : "排序有误!");
        int[] arr1 = ArrayUtil.generateRandomArray(10, 10);
        ArrayUtil.printArr(arr1);
        mergeSort2(arr1);
        ArrayUtil.printArr(arr1);
    }
}
