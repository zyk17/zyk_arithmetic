package com.zhangsan.no_12_monotonousStack;

import java.util.Stack;

/**
 * 一个只包含正数的数组, 求它的的子数组中 (累加和 * 最小值) 最大的子数组的结果
 * @author zhangsan
 * @date 2021/3/8 15:47
 */
public class Code02_AllTimesMinToMax {

    public static int allTimesMinToMax(int[] arr) {
        int N = arr.length;
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i-1] + arr[i];
        }

        Stack<Integer> stack = new Stack<>();
        int max = -1;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                // 计算结果
                int cur = stack.pop();
                max = Math.max(max, stack.isEmpty()? sum[i-1] * arr[cur] : (sum[i-1] - sum[stack.peek()]) * arr[cur] );
                /*int l = stack.isEmpty()? 0 : stack.peek() + 1;
                int r = i-1;
                if(l > 0) {
                    max = Math.max(max, (sum[r] - sum[l - 1]) * arr[cur]);
                }else {
                    max = Math.max(max, sum[r] * arr[cur]);
                }*/
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            max = Math.max(max, stack.isEmpty()? sum[cur] * arr[cur] : (sum[N-1] - sum[stack.peek()]) * arr[cur] );
        }
        return max;
    }

    // for test
    public static void main(String[] args) {
        int[] arr = {1,2,2,5,2,2,3};
        int r1 = allTimesMinToMax(arr);
        System.out.println(r1);
    }

}
