package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * 一个数组中，获取右边有多少个数*2还比这个数小
 *
 * @author zhangsan
 * @date 2021/2/4 18:43
 */
public class C004_02_ZZ01 {

    public static void main(String[] args) {
        int[] arr = {6, 1, 3, 4, 8, 1, 2, 6, 3};
        int[] arr1 = ArrayUtil.copyArr(arr);
        System.out.println("数组中有" + compareM(arr) + "个右边数，*2之后还比他小");
        System.out.println("数组中有" + zz01(arr1) + "个右边数，*2之后还比他小");
    }

    public static int zz01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length-1);
    }

    private static int process(int[] arr, int l, int r) {
        // 6, 1, 3, 4, 8, 1, 2, 6, 3
        // base case
        if(l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        int lSum = process(arr, l, mid);
        int rSum = process(arr, mid+1, r);
        int result = mergeCompute(arr, l, mid, r);
        return result+ lSum+ rSum;
    }

    private static int mergeCompute(int[] arr, int l, int mid, int r) {
        int ans = 0;    // 此次合并的结果
        int windowR = mid + 1;
        for (int i = l; i <= mid; i++) {
            while ( windowR <= r && arr[i] > (arr[windowR] << 1) ) {
                windowR++;
            }
            ans += windowR - mid -1;
        }

        int[] help = new int[r - l + 1];
        int index = 0, lIndex = l, rIndex = mid + 1;  // index 辅助数组的赋值下标， lIndex：左边下标，rIndex：右边下标
        while (lIndex <= mid && rIndex <= r) {
            if( arr[lIndex] < arr[rIndex]) {
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
        return ans;
    }

    public static int compareM(int[] arr) {
        // 6, 1, 3, 4, 8, 1, 2, 6, 3
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if ( (arr[j] << 1) < arr[i]) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

}
