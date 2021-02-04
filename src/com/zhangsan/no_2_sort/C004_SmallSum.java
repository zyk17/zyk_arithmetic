package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * 利用归并问题解决小和问题
 *
 * @author zhangsan
 * @date 2021/2/4 16:51
 */
public class C004_SmallSum {

    public static void main(String[] args) {
        // 6, 3, 2, 1, 6, 7
        // 求出每个数左边比他小的数的和
        /*int[] arr = {6, 3, 2, 1, 6, 7};
        int[] arr1 = ArrayUtil.copyArr(arr);
        System.out.println(smallSum(arr));
        System.out.println(compareM(arr1));*/

        boolean succeed = true;
        for (int i = 0; i < 100000; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(50, 100);
            int[] arr2 = ArrayUtil.copyArr(arr1);

            int r1 = compareM(arr1);
            int r2 = smallSum(arr2);

            // 排序不成功。
            if (r1 != r2) {
                ArrayUtil.printArr(arr1);
                System.out.println("========================================");
                ArrayUtil.printArr(arr2);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "计算成功!" : "计算有误!");
        int[] arr1 = ArrayUtil.generateRandomArray(10, 10);
        ArrayUtil.printArr(arr1);
        System.out.println(smallSum(arr1));

    }

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);

        int lSum = process(arr, l, mid);
        int rSum = process(arr, mid + 1, r);
        int mergeResult = merge(arr, l, mid, r);
        return mergeResult + lSum + rSum;
    }
    public static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int index = 0, lIndex = l, rIndex = mid + 1;  // index 辅助数组的赋值下标， lIndex：左边下标，rIndex：右边下标
        int sum = 0;    // 此次合并的结果
        while (lIndex <= mid && rIndex <= r) {
            if( arr[lIndex] < arr[rIndex]) {
                sum += arr[lIndex] * (r-rIndex + 1);
                help[index++] = arr[lIndex++];
            }else {
                help[index++] = arr[rIndex++];
            }
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
        return sum;
    }

    public static int compareM(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }

}
