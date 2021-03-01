package com.zhangsan.no_10_DP;

import java.util.Arrays;

/**
 * 不同的货币拼成目标金额
 *
 * 给定一个数组arr, 都是正整数,然后每一张代表一张货币
 * 给定一个int aim 表示要多少钱
 *
 * 每一张都是不同的即使金额相同.问有多少种拼法
 *
 * @author zhangsan
 * @date 2021/2/27 22:00
 */
public class Code12_CoinsWayEveryPaperDifferent {

    public static int coinWays(int[] arr, int aim) {
        return proess(arr, 0, aim);
    }

    /** 暴力递归 */
    public static int proess(int[] arr, int i, int rest) {
        if( rest < 0 ) {
            return 0;
        }
        if( i == arr.length ) {
            return rest==0 ? 1: 0;
        }
        // 要当前和不要当前
        return proess(arr, i+1, rest-arr[i]) + proess(arr, i+1, rest);
    }

    /** 记忆化搜索 */
    public static int coinWays2(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        int ans = proess2(arr, 0, aim, dp);
        /*for (int[] array : dp) {
            ArrayUtil.printArr(array);
        }*/
        return ans;
    }

    public static int proess2(int[] arr, int i, int rest, int[][] dp) {
        if( rest < 0 ) {
            return 0;
        }
        if(dp[i][rest] != -1) {
            return dp[i][rest];
        }
        int ans = 0;
        if( i == arr.length ) {
            ans = rest==0 ? 1: 0;
        }else {
            ans = proess2(arr, i+1, rest-arr[i], dp) + proess2(arr, i+1, rest, dp);
        }
        dp[i][rest] = ans;
        return ans;
    }

    public static int coinWays3(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        // base case设计最后一行
        dp[N][0] = 1;

        for (int i = N-1; i >= 0; i--) {
            for (int r = 0; r <= aim; r++) {
                int p1 = dp[i+1][r];
                int p2 = 0;
                if( r - arr[i] >= 0 ) {
                    p2 = dp[i+1][ r-arr[i] ];
                }
                dp[i][r] = p1+p2;
            }
        }
        /*for (int[] array : dp) {
            ArrayUtil.printArr(array);
        }*/
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 3, 6,9,3 };
        int aim = 8;
        System.out.println("暴力递归: ");
        System.out.println(coinWays(arr, aim));
        System.out.println("记忆化搜索: ");
        System.out.println(coinWays2(arr, aim));
        System.out.println("动态规划: ");
        System.out.println(coinWays3(arr, aim));
    }

}
