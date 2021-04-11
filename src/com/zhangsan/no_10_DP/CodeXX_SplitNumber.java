package com.zhangsan.no_10_DP;

/**
 * 将一个数拆分, 有几种拆法
 * 例子: 5
 * 1 1 1 1 1
 * 1 1 1 2
 * 1 1 2 1    × 不能比前边数小
 * 1 2 2
 * 1 1 3
 * 1 4
 * 2 3
 * 5
 * 7种.
 *
 * @author zhangsan
 * @date 2021/4/11 16:54
 */
public class CodeXX_SplitNumber {

    public static int ways(int num) {
        if (num < 2) {
            return num;
        }
        return process(1, num);
    }


    //返回有多少种拆法, pre前一个拆的数组, rest还剩多少数
    private static int process(int pre, int rest) {
        // 这里看是否刚好拆完, 上游调用保证了pre的正确性.
        if (rest <= 0) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // cur 从pre开始, 所以保证了一定比pre大
        for (int cur = pre; cur <= rest; cur++) {
            ways += process(cur, rest - cur);
        }
        return ways;
    }

    public static int dp(int num) {
        if (num < 2) {
            return num;
        }
        int N = num + 1;
        int[][] dp = new int[N][N];
        // pre: 行, rest: 列
        // rest == 0, all = 1, 第1列
        for (int r = 0; r < N; r++) {
            dp[r][0] = 1;
        }
        for (int pre = N-1; pre >= 0; pre--) {
            for (int rest = 1; rest < N; rest++) {
                int ways = 0;
                for (int cur = pre; cur <= rest; cur++) {
                    ways += dp[cur][rest - cur];
                }
                dp[pre][rest] = ways;
            }
        }

        return dp[1][num];
    }


    // for test
    public static void main(String[] args) {
        int num = 5;
        System.out.println(ways(num));
        System.out.println(dp(num));
    }

}
