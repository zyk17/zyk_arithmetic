package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * 快速排序
 *
 * @author zhangsan
 * @date 2021/2/5 16:35
 */
public class C005_QuickSort {

    public static void main(String[] args) {
        /*int[] arr = {6, 3, 5, 4, 8, 2, 4, 1};
        ArrayUtil.printArr(arr);
        quickSort1(arr);
        ArrayUtil.printArr(arr);*/

        boolean succeed = true;
        for (int i = 0; i < 100000; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(100, 1000000);
            int[] arr2 = ArrayUtil.copyArr(arr1);

            ArrayUtil.sort(arr1);
            try {
                quickSort1(arr2);
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
        int[] arr1 = ArrayUtil.generateRandomArray(20, 10000);
        ArrayUtil.printArr(arr1);
        quickSort1(arr1);
        ArrayUtil.printArr(arr1);
    }

    /**
     * 荷兰国旗   左边数<基数    中间==基数     右边>基数
     */
    public static void netherlandsFlag(int[] arr) {
        int curIndex = 0, leftBorder = -1, rightBorder = arr.length;

        int stand = 4;//arr[arr.length-1];
        // 1 3 2 4 4 8 5 6
        while (curIndex < rightBorder) {
            if (arr[curIndex] < stand) {
                if (curIndex != ++leftBorder) {
                    swap(arr, curIndex, leftBorder);
                }
                curIndex++;
            } else if (arr[curIndex] == stand) {
                curIndex++;
            } else {
                if (curIndex != --rightBorder) {
                    swap(arr, curIndex, rightBorder);
                }
            }
        }
        System.out.println("基数：" + stand + "，左边界:" + leftBorder + "，右边界: " + rightBorder);
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int curIndex = l, leftBorder = l - 1, rightBorder = r + 1;

        int stand = arr[r];
        // 1 3 2 4 4 8 5 6
        while (curIndex < rightBorder) {
            if (arr[curIndex] < stand) {
                if (curIndex != ++leftBorder) {
                    swap(arr, curIndex, leftBorder);
                }
                curIndex++;
            } else if (arr[curIndex] == stand) {
                curIndex++;
            } else {
                if (curIndex != --rightBorder) {
                    swap(arr, curIndex, rightBorder);
                }
            }
        }
        process(arr, l, leftBorder);
        process(arr, rightBorder, r);
    }

    public static void swap(int[] arr, int l, int r) {
        arr[l] = arr[l] ^ arr[r];
        arr[r] = arr[l] ^ arr[r];
        arr[l] = arr[l] ^ arr[r];

        /*int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;*/

        // l = l^r;
        // r = l^r^r = l ^ 0 = l;
        // l = l^r^l = r ^ 0 = r;
    }

}
