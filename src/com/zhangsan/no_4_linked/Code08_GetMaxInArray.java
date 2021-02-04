package com.zhangsan.no_4_linked;

import com.zhangsan.util.ArrayUtil;

/**
 * 数组求最大值
 * @author zhangsan
 * @date 2021/2/3 16:46
 */
public class Code08_GetMaxInArray {

    public static int getMax(int[] arr) {
        if( arr == null || arr.length == 0){
            return -1;
        }
        return process(arr, 0, arr.length-1);
    }

    public static int process(int[] arr, int l, int r) {
        if(l == r) {
            return arr[l];
        }
        int mid = l + ((r-l)>>1);
        /*
        int lMax = process(arr, l, mid);
        int rMax = process(arr, mid + 1, r);
        return Math.max(lMax, rMax);
        */
        return Math.max(process(arr, l, mid), process(arr, mid + 1, r));
    }

    public static int compareM(int[] arr) {
        if( arr == null || arr.length == 0){
            return -1;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        /*int[] arr = { 0,12,54,6534,57,4653,123,165,321,5324,123,489653};
        long start = System.nanoTime();
        System.out.println(getMax(arr));
        long end = System.nanoTime();
        System.out.println(compareM(arr));
        long end1 = System.nanoTime();

        System.out.println((end - start));
        System.out.println((end1 - end));*/
        int maxSize = 100;
        int maxValue = 199999;
        boolean succeed = true;
        int times = 100000;
        System.out.println("测试开始。。");
        for (int i = 0; i < times; i++) {
            int[] arr = ArrayUtil.generateRandomArray(100, 199999);
            if(getMax(arr) != compareM(arr)) {
                succeed = false;
                ArrayUtil.printArr(arr);
                System.out.println("递归方法： " + getMax(arr) + ",for循环方法：" + compareM(arr));
            }
        }
        System.out.println("测试结束： " + (succeed? "pass": "fail"));
    }



}
