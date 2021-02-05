package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * 一个数组中有多少个子数组的和在 lower-upper 中
 *
 * @author zhangsan
 * @date 2021/2/5 13:19
 */
public class C004_03_InArraySonSumInLowerToUpper {

    public static void main(String[] args) {
        // 1, 2, 5, 4, 8, 4, 7, 6
        // 0  1  2  3  4  5  6  7
        int[] arr = {1, -3, 5, -6, 8, -5, 7, 6};
        int[] arr1 = ArrayUtil.copyArr(arr);
        int lower = 10;
        int upper = 20;

        System.out.println("共有"+ getSumSonArray(arr, lower, upper) + "个子数组达标。");
        System.out.println("共有"+ compareM(arr1, lower, upper) + "个子数组达标。");
    }

    public static int getSumSonArray(int[] arr, int lower, int upper) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        int[] sonSumArray = new int[arr.length];
        sonSumArray[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sonSumArray[i] = sonSumArray[i-1] + arr[i];
        }
        ArrayUtil.printArr(sonSumArray);
        // 这个数组是前数组的前边递加和数组，如果有负数的话就不一定有序。

        // 1, 2, 5, 4, 8, 4, 7, 6
        // 0  1  2  3  4  5  6  7
        // 1  3  8  12 20 24 31 37          不一定有序，但是管他有序不有序
        // l-n 的sum值 = sum[n] - sum[l-1]
        return process(sonSumArray, 0, sonSumArray.length-1, lower, upper);
    }

    /**
     * 根据前缀和数组，求出数组中子数组和在[lower-upper]中的个数
     */
    private static int process(int[] sonSumArray, int l, int r, int lower, int upper) {
        if(l == r) {
            return sonSumArray[l] >= lower && sonSumArray[l]<= upper? 1: 0;
        }
        int mid = l + ((r-l) >> 1);
        int lSum = process(sonSumArray, l, mid, lower, upper);
        int rSum = process(sonSumArray, mid+1, r, lower, upper);
        int result = merge(sonSumArray, l, mid, r, lower, upper);
        return lSum + rSum + result;
    }

    private static int merge(int[] sonSumArray, int l, int mid, int r, int lower, int upper) {
        int ans = 0;
        int windowL = l;
        int windowR = l;
        for (int i = mid+1; i <= r; i++) {
            int min = sonSumArray[i] - upper;
            int max = sonSumArray[i] - lower;
            while (windowR <= mid && sonSumArray[windowR] <= max) {
                windowR++;
            }
            while (windowL <= mid && sonSumArray[windowL] <= min) {
                windowL++;
            }
            ans += windowR - windowL;
        }
        // 上半部分是计算，然后因为递归条件所以第一次进来时只有两个数所以无需排序即可计算
        {
            int[] help = new int[r - l + 1];
            int index = 0, lIndex = l, rIndex = mid + 1;  // index 辅助数组的赋值下标， lIndex：左边下标，rIndex：右边下标
            while (lIndex <= mid && rIndex <= r) {
                help[index++] = sonSumArray[lIndex] < sonSumArray[rIndex] ? sonSumArray[lIndex++] : sonSumArray[rIndex++];
            }
            // 此时上边循环结束，有两种情况。
            // 1：lIndex 越界
            // 2：rIndex 越界
            while (lIndex <= mid) {
                help[index++] = sonSumArray[lIndex++];
            }
            while (rIndex <= r) {
                help[index++] = sonSumArray[rIndex++];
            }
            // 最后把辅助数组给 拷贝到 数组里
            do {
                sonSumArray[r] = help[--index];
                r--;
            } while (r >= l);
        }

        return ans;
    }


    public static int compareM(int[] arr, int lower, int upper) {
        // 时间复杂度 外层遍历，内层遍历，每次遍历都要看+比
        // N^2 * N = O(N^3)
        if(arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if( sum >= lower && sum <= upper ) {
                    ans++;
                } else if(sum > upper) {
                    break;
                }
            }
        }
        return ans;
    }

}
