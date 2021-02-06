package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

import java.util.PriorityQueue;

/**
 * 一个无序数组，有一定规律，如果排序每个位置移动位置不超过k。排序
 *
 * @author zhangsan
 * @date 2021/2/6 0:13
 */
public class C006_03_HeapSortA {

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 2, 7, 8, 5, 6, 11, 12, 9, 10, 15, 16, 13, 14, 19, 20, 17, 18};
        ArrayUtil.printArr(arr);
        arr = sort(arr, 2);
        ArrayUtil.printArr(arr);
    }

    public static int[] sort(int[] arr, int k) {
        // 优先级队列，是个小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int[] help = new int[arr.length];
        int lIndex = 0;
        for (int i = 0; i < arr.length; ) {
            if (queue.size() <= k) {
                queue.add(arr[i++]);
            } else {
                help[lIndex++] = queue.poll();
            }
        }
        while (!queue.isEmpty()) {
            help[lIndex++] = queue.poll();
        }
        return help;
    }


    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

}
