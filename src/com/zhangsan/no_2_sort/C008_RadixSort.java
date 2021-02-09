package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

import java.util.PriorityQueue;

/**
 * 基数排序
 *
 * @author zhangsan
 * @date 2021/2/8 19:09
 */
public class C008_RadixSort {

    public static void main(String[] args) {
        /*int[] arr = {11, 10, 13, 132, 13, 666, 111, 10, 100, 222};
        ArrayUtil.printArr(arr);
        radixSort2(arr);
        ArrayUtil.printArr(arr);*/



        int times = 100000;
        int maxSize = 1000;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {

            int[] arr = generateArray(maxSize, maxValue);
            int[] arr1 = ArrayUtil.copyArr(arr);

            ArrayUtil.sort(arr);
            radixSort2(arr1);
            if (!ArrayUtil.isEquals(arr, arr1)) {
                succeed = false;
                break;
            }
        }
        System.out.println("测试结束：" + (succeed ? "成功" : "失败"));
    }


    public static void radixSort(int[] arr) {
        // 10个桶
        PriorityQueue<Integer>[] queues = new PriorityQueue[10];
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new PriorityQueue<>();
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int digit = getDigit(max);
        for (int i = 0; i < digit; i++) {
            for (int j = 0; j < arr.length; j++) {
                int yv = getBitValue(arr[j], i);
                queues[yv].add(arr[j]);
            }
            int index = 0;
            // 挨个取
            for (PriorityQueue<Integer> queue : queues) {
                while (!queue.isEmpty()) {
                    arr[index++] = queue.poll();
                }
            }
        }

    }

    /**
     * 升华版，去掉桶
     */
    public static void radixSort2(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int digit = getDigit(max);
        int[] help = new int[arr.length];

        for (int i = 0; i < digit; i++) {
            int[] counts = new int[10];
            for (int j = 0; j < arr.length; j++) {
                int yv = getBitValue(arr[j], i);
                counts[yv]++;
            }
            for (int j = 1; j < counts.length; j++) {
                counts[j] += counts[j - 1];
            }
            for (int j = arr.length - 1; j >= 0; j--) {
                int yv = getBitValue(arr[j], i);
                help[--counts[yv]] = arr[j];
            }
            for (int j = 0; j < help.length; j++) {
                arr[j] = help[j];
            }
        }

    }

    /**
     * 获取个十百位的值
     *
     * @param number 数
     * @param i      个位0， 十位1， 百位2 .。。
     */
    private static int getBitValue(int number, int i) {
        for (int k = 0; k < i; k++) {
            number = number / 10;
        }
        number = number % 10;
        return number;
    }

    public static int getDigit(int number) {
        int ans = 1;
        while (number / 10 != 0) {
            ans++;
            number = number / 10;
        }
        return ans;
    }

    public static int[] generateArray(int maxSize, int maxValue) {
        maxSize = (int) (Math.random() * maxSize + 1);
        int[] rs = new int[maxSize];
        for (int i = 0; i < rs.length; i++) {
            int v = (int) (Math.random() * maxValue + 1);
            rs[i] = v;
        }
        return rs;
    }

}
