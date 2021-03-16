package com.zhangsan.no_other;

import com.zhangsan.util.ArrayUtil;

import java.util.Arrays;

/**
 * 无序数组中寻找到第k小的数组
 * @author zhangsan
 * @date 2021/3/16 15:02
 */
public class Code01_FindMinKth {

    public static int minKth(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k-1];
    }

    public static int minKth2(int[] arr, int k) {
        return process(arr, 0, arr.length-1, k-1);
    }

    private static int process(int[] arr, int L, int R, int k) {
        if(L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random()*(R-L+1))]; // 随机选一个中心点
        int[] range = partition(arr, L, R, pivot);          // 荷兰国旗排序数组并获得中心点这个数左右区间
        if(k >= range[0] && k <= range[1]) {                // 命中返回这个区间
            return arr[k];
        }else if(k < range[0]) {
            return process(arr, L, range[0]-1, k);
        }else {
            return process(arr,range[0]+1, R, k);
        }
    }

    // 递归改迭代
    public static int minKth3(int[] arr, int k) {
        int L = 0;
        int R = arr.length-1;
        k--;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random()*(R-L+1))];
            range = partition(arr, L, R, pivot);
            if(k >= range[0] && k <= range[1]) {
                return pivot;
            }else if(k < range[0]) {
                R = range[0]-1;
            }else {
                L = range[1]+1;
            }
        }
        return arr[L];
    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int curIndex = l, leftBorder = l-1, rightBorder = r+1;

        // 1 3 2 4 4 8 5 6
        while (curIndex < rightBorder) {
            if (arr[curIndex] < pivot) {
                if (curIndex != ++leftBorder) {
                    swap(arr, curIndex, leftBorder);
                }
                curIndex++;
            } else if (arr[curIndex] == pivot) {
                curIndex++;
            } else {
                if (curIndex != --rightBorder) {
                    swap(arr, curIndex, rightBorder);
                }
            }
        }
        return new int[]{leftBorder+1, rightBorder-1};
    }

    private static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }


    // for test
    public static void main(String[] args) {

        int[] arr = ArrayUtil.generateRandomArray(1000, 1000);
//        int[] arr = {1,23,13,165,4321,3,1231,31,53};
        int[] arr1 = ArrayUtil.copyArr(arr);
        int[] arr2 = ArrayUtil.copyArr(arr);
        int k = (int) (Math.random()*100+1);

        int r1 = minKth(arr, k);
        int r2 = minKth2(arr1, k);
        int r3 = minKth3(arr2, k);
        System.out.println(r1 + "\t" + r2 + "\t" + r3);


        int times = 1000;
        int maxValue = 1000;
        int maxSize = 1000;


    }

}
