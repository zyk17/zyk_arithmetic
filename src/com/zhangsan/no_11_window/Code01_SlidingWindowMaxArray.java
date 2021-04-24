package com.zhangsan.no_11_window;

import com.zhangsan.util.ArrayUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个窗口大小
 * 返回窗口滑动每一步的最大值
 *
 * @author zhangsan
 * @date 2021/3/2 11:33
 */
public class Code01_SlidingWindowMaxArray {

    // 暴力解, 每一步都比w个数
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int[] result = new int[arr.length - w + 1];
        int index = 0;
        int l = 0;
        int r = w - 1;
        while (r < arr.length) {
            int max = arr[l];
            for (int i = l; i <= r; i++) {
                max = Math.max(max, arr[i]);
            }
            result[index++] = max;
            l++;
            r++;
        }
        return result;
    }

    public static int[] right2(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int[] result = new int[arr.length - w + 1];
        int index = 0;
        Deque<Integer> deque = new LinkedList<Integer>();

        for (int r = 0; r < arr.length; r++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[r]) {
                deque.pollLast();
            }
            deque.addLast(r);
            if (r - w == deque.peekFirst()) {
                deque.pollFirst();
            }
            if (r >= w - 1) {
                result[index++] = arr[deque.peekFirst()];
            }
        }
        return result;
    }

    /**
     * 返回滑动窗口每一步的最大值, 窗口大小w.
     */
    public static int[] right3(int[] arr, int w) {
        int N = arr.length;
        int[] ans = new int[N - w + 1];
        int helpIndex = 0;

        Deque<Integer> deque = new LinkedList<>();
        for (int r = 0; r < N; r++) {
            while (!deque.isEmpty() && arr[r] > deque.peekLast()) {
                deque.pollLast();
            }
            deque.addLast(r);
            if (r >= w - 1) {  // 从这里开始每一步都要统计答案
                ans[helpIndex++] = deque.peekFirst();
            }
            if (deque.peekFirst() == r - w + 1) {
                deque.pollFirst();
            }
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        /*int[] arr = {3, 6, 4, 5, 1, 3, 9};
        int w = 3;
        int[] r1 = right(arr, w);
        int[] r2 = right2(arr, w);
        int[] r3 = right2(arr, w);

        ArrayUtil.printArr(r1);
        ArrayUtil.printArr(r2);
        ArrayUtil.printArr(r3);*/

        int times = 100;
        int maxSize = 10000;
        int maxValue = 10;

        for (int i = 0; i < times; i++) {
            int w = (int) (Math.random()*10+1);
            int[] arr = ArrayUtil.generateRandomArray(maxSize, maxValue);

            long s1 = System.nanoTime();
            int[] r1 = right(arr, w);
            long s2 = System.nanoTime();
            int[] r2 = right2(arr, w);
            long s3 = System.nanoTime();
            int[] r3 = right3(arr, w);
            long s4 = System.nanoTime();
            if( !ArrayUtil.isEquals(r1, r2) ) {
                System.out.println("OOPS");
                System.out.println("窗口大小: " + w + "原始数组: ");
                ArrayUtil.printArr(arr);
                System.out.println("暴力解:");
                ArrayUtil.printArr(r1);
                System.out.println("窗口滚动解");
                ArrayUtil.printArr(r2);
                System.out.println("窗口滚动解");
                ArrayUtil.printArr(r3);
                break;
            }
            System.out.println("数组大小:" + arr.length + ", 窗口大小: " + w + "暴力解时间: " + (s2-s1) + ", 窗口滚动解时间:" + (s3-s2) + ",zzz" + (s4 - s3));
        }

    }

}
