package com.zhangsan.no_10_DP;

import com.zhangsan.util.ArrayUtil;

/**
 * 砍怪兽问题
 * 给定三个参数: N,M,K
 * 怪兽有N滴血, 英雄每次砍 0-M中等概率滴血
 * 求K次攻击后,英雄把怪兽砍死的概率
 * @author zhangsan
 * @date 2021/3/1 12:53
 */
public class Code13_KillMonster {

    public static double right1(int n, int m, int k) {
        // 边界条件: 怪兽没血, 我攻击小于0, 我不能攻击
        if (n < 0 || m < 0 || k < 0) { return 0; }
        // 总共有多少种砍法 1 -> 1 2 3  2-> 1 2 3  3-> 1 2 3    1 1->1 1 3   1 2-> 1 2 3 ..........
        double all = Math.pow(m+1, k);  // (0~m)的k次方
        // 砍死的次数
        int kills = process1(n, m, k);
        System.out.println(kills);
        return kills/all;
    }

    private static int process1(int restN, int m, int restK) {
        if(restK == 0) {
            return restN > 0 ?  0: 1;
        }
        if( restN <= 0 ) {
            return (int) Math.pow( m+1, restK );
        }
        int ways = 0;
        for (int i = 0; i <= m; i++) {
            ways += process1(restN - i, m, restK - 1);
        }
        return ways;
    }

    /** right1的动态规划版本 */
    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) { return 0; }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];
                    } else {
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static double right2(int n, int m, int k) {
        // 边界条件: 怪兽没血, 我攻击小于0, 我不能攻击
        if (n < 0 || m < 0 || k < 0) { return 0; }
        // 总共有多少种砍法 1 -> 1 2 3  2-> 1 2 3  3-> 1 2 3    1 1->1 1 3   1 2-> 1 2 3 ..........
        double all = Math.pow(m+1, k);  // (0~m)的k次方
        // 砍死的次数
        int[][] dp = new int[k+1][n+1];
        int kills = process2(n, m, k, dp);


        System.out.println("缓存表:");
        for (int[] arr : dp) {
            ArrayUtil.printArr(arr);
        }
        return kills/all;
    }

    private static int process2(int restN, int m, int restK, int[][] dp) {
        if( restN <= 0 ) {
            return  (int) Math.pow( m+1, restK );
        }
        int ways = 0;
        if(restK == 0) {
            ways = restN > 0 ?  0: 1;
        }else  {
            for (int i = 0; i <= m; i++) {
                ways += process2(restN - i, m, restK - 1, dp);
            }
        }
        dp[restN][restK] = ways;
        return ways;
    }




    // for test
    public static void main(String[] args) {
        int n = 3;
        int m = 1;
        int k = 3;
        System.out.println(right1(n, m, k));
        System.out.println(dp1(n, m, k));
//        System.out.println(right2(n, m, k));
    }

}
