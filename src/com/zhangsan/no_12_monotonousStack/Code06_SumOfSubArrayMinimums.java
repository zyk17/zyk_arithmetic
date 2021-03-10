package com.zhangsan.no_12_monotonousStack;

import java.util.*;

/**
 * 求一个数组所有子数组最小值的累加和
 * @author zhangsan
 * @date 2021/3/10 19:10
 */
public class Code06_SumOfSubArrayMinimums {

    // 不包含重复元素
    public static int f(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int N = arr.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                // 弹出计算
                Integer cur = stack.pop();
                sum += (cur - (stack.isEmpty()? -1: stack.peek())) * (i - cur) * arr[cur];
            }
            stack.push(i);
        }
        // 最终计算
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            sum += (cur - (stack.isEmpty()? -1: stack.peek())) * (N - cur) * arr[cur];
        }
        return sum;
    }

    public static int compareF(int[] arr) {
        int sum = 0;
        int N = arr.length;
        // 控制轮数
        for (int i = 0; i < N; i++) {
            // 控制子数组大小
            for (int j = i; j < N; j++) {
                int min = arr[i];
                // 找最小值
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                sum += min;
            }
        }
        return sum;
    }

    // for test
    public static void main(String[] args) {
        /*int[] arr = ArrayUtil.generateRandomArray(50000, 1000, false, false);
        long s1 = System.nanoTime();
        int r1 = f(arr);
        long s2 = System.nanoTime();
        int r2 = compareF(arr);
        long s3 = System.nanoTime();
        System.out.println(r1 + ", 耗时: " + (s2-s1));
        System.out.println(r2 + ", 耗时: " + (s3-s2));*/

        int n = 10000000;

        long s1 = System.nanoTime();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            stack.pollFirst();
        }
        long s2 = System.nanoTime();

        long s3 = System.nanoTime();
        Stack<Integer> stack2 = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack2.push(i);
        }
        for (int i = 0; i < n; i++) {
            stack2.pop();
        }
        long s4 = System.nanoTime();
        System.out.println("ArrayDeque实现栈:" + (s2-s1));
        System.out.println("Stack实现栈:" + (s4-s3));



        long s5 = System.nanoTime();
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            queue.push(i);
        }
        for (int i = 0; i < n; i++) {
            queue.pop();
        }
        long s6 = System.nanoTime();

        long s7 = System.nanoTime();
        Deque<Integer> queue2 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue2.push(i);
        }
        for (int i = 0; i < n; i++) {
            queue2.pop();
        }
        long s8 = System.nanoTime();
        System.out.println("ArrayDeque实现队列:" + (s6-s5));
        System.out.println("LinkedList实现栈:" + (s8-s7));

    }

}
