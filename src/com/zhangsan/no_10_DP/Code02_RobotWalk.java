package com.zhangsan.no_10_DP;

import java.util.Arrays;

/**
 * 机器人走路
 *
 * n: 一共有 1-n 个位置
 * start: 机器人一开始的位置
 * aim: 机器人行走的目标位置
 * k: 机器人能走多少布
 *
 * 机器人如果在1只能往右走，在终点只能往终点-1位置走
 *
 * 返回机器人有多少种走法可以走到
 *
 * @author zhangsan
 * @date 2021/2/19 17:46
 */
public class Code02_RobotWalk {

    /** 暴力尝试 */
    public static int ways1( int n, int start, int aim, int k ) {
        /*if( n < 2 || k < 1 || aim < 1 || aim > n || start < 1 || start > n  ) {
            return 0;
        }*/
        return walkProcess1( start, k, aim, n );
    }

    /**
     * 走路过程
     * @param cur 当前位置
     * @param rest 剩余步数
     * @param aim 目标位置
     * @param n 共有多少个位置
     * @return 走路的方法数
     */
    public static int walkProcess1( int cur, int rest, int aim, int n ) {
        if( rest == 0 ) {
            return cur == aim? 1 : 0;
        }
        if( cur == 1 ) { return walkProcess1(2, rest - 1, aim, n); }
        else if( cur == n ) { return walkProcess1(n - 1, rest - 1, aim, n); }
        else {
            int r = walkProcess1(cur + 1, rest - 1, aim, n);
            int l = walkProcess1(cur - 1, rest - 1, aim, n);
            return l + r;
        }
    }

    /** 参数优化，加入二维数组缓存 */
    public static int ways2( int n, int start, int aim, int k ) {
        int[][] dp = new int[n+1][k+1]; // 多少个路*多少个选择的大小
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return walkProcess2(start, k, aim, n, dp);
    }

    /**
     * 走路过程
     * @param cur 当前位置
     * @param rest 剩余步数
     * @param aim 目标位置
     * @param n 共有多少个位置
     * @return 走路的方法数
     */
    public static int walkProcess2(int cur, int rest, int aim, int n, int[][] dp) {
        if( dp[cur][rest] != -1 ) {
            return dp[cur][rest];
        }
        int ans = 0;
        if( rest == 0 ) {
            ans = cur == aim? 1 : 0;
            return ans;
        }
        if( cur == 1 ) { ans = walkProcess1(2, rest - 1, aim, n); }
        else if( cur == n ) { ans = walkProcess1(n - 1, rest - 1, aim, n); }
        else {
            ans = walkProcess2(cur + 1, rest - 1, aim, n, dp) + walkProcess2(cur - 1, rest - 1, aim, n, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /** 反推二位数组的赋值方法，发现其实就是杨辉三角形 */
    public static int ways3( int n, int start, int aim, int k ) {
        int[][] dp = new int[n+1][k+1]; // 多少个路*多少个选择的大小
        dp[aim][0] = 1;
        for (int col = 1; col <= k; col++) {

            dp[0][col] = dp[1 ][col - 1];
            for (int row = 1; row < n; row++) {
                dp[row][col] = dp[row - 1][col - 1] + dp[row + 1][col - 1];
            }
            dp[n][col] = dp[n-1][col - 1];
        }

        /*for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }*/

        return dp[start][k];
    }

    public static void main(String[] args) {
        int n = 5;
        int start = 4;
        int aim = 4;
        int k = 4;

        long s1 = System.nanoTime();
        int r1 = ways1(n , start, aim, k);
        long s2 = System.nanoTime();
        int r2 = ways2(n , start, aim, k);
        long s3 = System.nanoTime();
        int r3 = ways3(n , start, aim, k);
        long s4 = System.nanoTime();

        System.out.println("暴力解答案: " + r1 + ",耗时：" + (s2 - s1));
        System.out.println("动态规划: " + r2 + ",耗时：" + (s3 - s2));
        System.out.println("反推: " + r3 + ",耗时：" + (s4 - s3));

    }

}
