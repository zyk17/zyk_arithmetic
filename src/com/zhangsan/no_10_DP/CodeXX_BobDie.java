package com.zhangsan.no_10_DP;

/**
 * 醉汉Bob 在R*C的矩阵中活动
 * 每一步都可能向上下左右等概率移动
 * 返回走k布之后, 还存活在棋盘上的概率
 *
 * @author zhangsan
 * @date 2021/4/11 14:19
 */
public class CodeXX_BobDie {

    public static double livePosibility1(int R, int C, int r, int c, int k) {
        double n = move(R, C, r, c, k);
//        System.out.println(n);
//        System.out.println(Math.pow(4, k));
        return n / Math.pow(4, k);
    }

    // 1 ~ R, 1 ~ C
    public static int move(int R, int C, int r, int c, int rest) {
        if (r == R + 1 || r == 0 || c == 0 || c == C + 1) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int p1 = move(R, C, r + 1, c, rest - 1);
        int p2 = move(R, C, r - 1, c, rest - 1);
        int p3 = move(R, C, r, c + 1, rest - 1);
        int p4 = move(R, C, r, c - 1, rest - 1);
        return p1 + p2 + p3 + p4;
    }

    public static double dp(int R, int C, int r, int c, int k) {
        // r  c  rest
        int[][][] dp = new int[R][C][k + 1];
        for (int r1 = 0; r1 < R; r1++) {
            for (int c1 = 0; c1 < C; c1++) {
                dp[r1][c1][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r1 = 0; r1 < R; r1++) {
                for (int c1 = 0; c1 < C; c1++) {
                    int p1 = r1 + 1 == R ? 0 : dp[r1 + 1][c1][rest - 1];
                    int p2 = r1 - 1 < 0 ? 0 : dp[r1 - 1][c1][rest - 1];
                    int p3 = c1 + 1 == C ? 0 : dp[r1][c1 + 1][rest - 1];
                    int p4 = c1 - 1 < 0 ? 0 : dp[r1][c1 - 1][rest - 1];
                    dp[r1][c1][rest] = p1 + p2 + p3 + p4;
                }
            }
        }
        return dp[r - 1][c - 1][k] / Math.pow(4, k);
    }

    public static void pick(int[][][] dp, int N, int M, int r, int c, int rest) {
    }


    // for test
    public static void main(String[] args) {
        int R = 5;
        int C = 5;
        int r = 2;
        int c = 2;
        int k = 5;
        System.out.println(livePosibility1(R, C, r, c, k));
        System.out.println(dp(R, C, r, c, k));
    }

}
