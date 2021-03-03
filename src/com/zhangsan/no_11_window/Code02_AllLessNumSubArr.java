package com.zhangsan.no_11_window;

import com.zhangsan.util.ArrayUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个数组arr, 和一个整数num
 * 某个arr中的子数组sub, 如果想要达标,必须满足
 * sub中的最大值-最小值 <= num
 * 返回达标的子数组数量
 * @author zhangsan
 * @date 2021/3/2 14:20
 */
public class Code02_AllLessNumSubArr {

    public static int allLessNumSubArray(int[] arr, int num) {
        if( arr == null || arr.length == 0 || num < 0 ) { return 0; }
        int count = 0;
        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int max = arr[L], min = arr[L];
                for (int i = L+1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if( max-min <= num ) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int allLessNumSubArray2(int[] arr, int num) {
        if( arr == null || arr.length == 0 || num < 0 ) { return 0; }

        int count = 0, N = arr.length;
        Deque<Integer> maxDeque = new LinkedList<Integer>();
        Deque<Integer> minDeque = new LinkedList<Integer>();

        int R = 0;
        for (int L = 0; L < N; L++) {
            while ( R < N ) {
                while (!maxDeque.isEmpty() && arr[maxDeque.peekLast()] <= arr[R]) {
                    maxDeque.pollLast();
                }
                maxDeque.addLast(R);

                while (!minDeque.isEmpty() && arr[minDeque.peekLast()] >= arr[R]) {
                    minDeque.pollLast();
                }
                minDeque.addLast(R);
                if( arr[maxDeque.peekFirst()] - arr[minDeque.peekFirst()] > num ) {
                    break;
                }
                R++;
            }
            count += (R-L);
            if( L == maxDeque.peekFirst() ) {
                maxDeque.pollFirst();
            }
            if( L == minDeque.peekFirst() ) {
                minDeque.pollFirst();
            }
        }

        return count;
    }


    // for test
    public static void main(String[] args) {
//        int[] arr = {0, 0, -5};
//        int num = 3;
//
//        System.out.println(allLessNumSubArray(arr, num));
//        System.out.println(allLessNumSubArray2(arr, num));


        int times = 100;
        int maxSize = 1000;
        int maxValue = 10;

        for (int i = 0; i < times; i++) {
            int num = (int) (Math.random() * 10 + 1);
            int[] arr = ArrayUtil.generateRandomArray(maxSize, maxValue);

            long s1 = System.nanoTime();
            int r1 = allLessNumSubArray(arr, num);
            long s2 = System.nanoTime();
            int r2 = allLessNumSubArray2(arr, num);
            long s3 = System.nanoTime();
            if (r1 != r2) {
                System.out.println("OOPS");
                System.out.println("窗口大小: " + num + "原始数组: ");
                ArrayUtil.printArr(arr);
                System.out.println("暴力解:");
                System.out.println(r1);
                System.out.println("窗口滚动解");
                System.out.println(r2);
                break;
            }
            System.out.println("数组大小:" + arr.length + ", 窗口大小: " + num + "暴力解时间: " + (s2 - s1) + ", 窗口滚动解时间:" + (s3 - s2));

        }
    }

}
