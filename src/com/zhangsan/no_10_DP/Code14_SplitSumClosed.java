package com.zhangsan.no_10_DP;

import com.zhangsan.util.ArrayUtil;

/**
 * 将一个数组分成两个(累加和)尽量相等的数组.
 * 返回小的累加和数组的累加和
 * @author zhangsan
 * @date 2021/3/1 17:30
 */
public class Code14_SplitSumClosed {

    public static int right(int[] arr) {
        if ( arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return process(arr, 0, sum / 2);
    }

    public static int process(int[] arr, int i, int rest) {
        if( i == arr.length ) {
            return 0;
        }
        int p1 = process(arr, i+1, rest);
        int p2 = arr[i] > rest ? 0:  arr[i] + process(arr, i+1, rest - arr[i]);
        return Math.max(p1, p2);
    }

    public static int dp(int[] arr) {
        if ( arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int N = arr.length;
        int col = sum/2;
        int[][] dp = new int[N + 1][col+1];

        for (int i = N-1; i >= 0; i--) {
            for (int rest = 0; rest <= col; rest++) {
                int p1 = dp[i+1][rest];
                int p2 = arr[i] > rest ? 0:  arr[i] + dp[i+1][rest - arr[i]];
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][col];
    }


    // for test
    public static void main(String[] args) {
        /*int[] arr = { 3,4,5,1};
        System.out.println(right(arr));
        System.out.println(dp(arr));*/

        int times = 1000;
        int maxLength = 20;
        int maxValue = 20;

        System.out.println("测试开始: ");
        for (int i = 0; i < times; i++) {
            int[] arr = ArrayUtil.generateRandomArray(maxLength, maxValue, false, false);
            int aim = (int) (Math.random()*400+1);
            int r1 = right(arr);
            int r2 = dp(arr);
            if( r1 != r2 ) {
                System.out.println("OOPS");
                ArrayUtil.printArr(arr);
                System.out.println("aim: " + aim + "\n 暴力解: " + r1 + "\ndp解: " + r2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
