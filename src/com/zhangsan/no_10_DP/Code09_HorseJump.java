package com.zhangsan.no_10_DP;

import java.util.Arrays;

/**
 * 一个象棋棋盘 10*9
 * 一个🐎马走日，请问走到 a, b点，只能走k布，有多少种方法。
 * @author zhangsan
 * @date 2021/2/24 13:45
 */
public class Code09_HorseJump {

    /** 1：暴力递归 */
    public static int ways1(int a, int b, int k) {
        return process1( 0, 0, k, a, b );
    }

    private static int process1(int x, int y, int step, int a, int b) {
        if( x < 0 || y < 0 || x > 9 || y > 8 ) {
            return 0;
        }
        if( step == 0 ) {
            return x == a && y == b ? 1 : 0;
        }
        int ans = process1( x+2, y+1, step-1, a, b );
        ans += process1( x+2, y-1, step-1, a, b );
        ans += process1( x+1, y+2, step-1, a, b );
        ans += process1( x+1, y-2, step-1, a, b );
        ans += process1( x-2, y+1, step-1, a, b );
        ans += process1( x-2, y-1, step-1, a, b );
        ans += process1( x-1, y+2, step-1, a, b );
        ans += process1( x-1, y-2, step-1, a, b );
        return ans;
    }

    /** 2：暴力递归+ 缓存 */
    public static int ways2(int a, int b, int k) {
        int[][][] dp = new int[10][9][k+1];
        for (int[][] two : dp) {
            for (int[] one : two) {
                Arrays.fill(one, -1);
            }
        }

        int ans = process2(0, 0, k, a, b, dp);

        /*System.out.println("三维缓存表：");
        for (int[][] two : dp) {
            for (int[] one : two) {
                ArrayUtil.printArr(one);
            }
        }*/

        return ans;
    }

    private static int process2(int x, int y, int step, int a, int b, int[][][] dp) {
        if( x < 0 || y < 0 || x > 9 || y > 8 ) {
            return 0;
        }
        if( dp[x][y][step] != -1 ) {
            return dp[x][y][step];
        }
        if( step == 0 ) {
            return x == a && y == b ? 1 : 0;
        }
        int ans = process2( x+2, y+1, step-1, a, b, dp);
        ans += process2( x+2, y-1, step-1, a, b, dp);
        ans += process2( x+1, y+2, step-1, a, b, dp);
        ans += process2( x+1, y-2, step-1, a, b, dp);
        ans += process2( x-2, y+1, step-1, a, b, dp);
        ans += process2( x-2, y-1, step-1, a, b, dp);
        ans += process2( x-1, y+2, step-1, a, b, dp);
        ans += process2( x-1, y-2, step-1, a, b, dp);
        dp[x][y][step] = ans;
        return ans;
    }

    /** 3：转换成dp */
    public static int ways3(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        // 层按照剩余步数往上填, x==a y==b 的为1
        dp[a][b][0] = 1;
        for (int step = 1; step <= k; step++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ans = pick(dp, x+2, y+1, step-1);
                    ans += pick( dp,x+2, y-1, step-1);
                    ans += pick( dp,x+1, y+2, step-1);
                    ans += pick( dp,x+1, y-2, step-1);
                    ans += pick( dp,x-2, y+1, step-1);
                    ans += pick( dp,x-2, y-1, step-1);
                    ans += pick( dp,x-1, y+2, step-1);
                    ans += pick( dp,x-1, y-2, step-1);
                    dp[x][y][step] = ans;
                }
            }
        }
        /*System.out.println("三维缓存表：");
        for (int[][] two : dp) {
            for (int[] one : two) {
                ArrayUtil.printArr(one);
            }
        }*/
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int step) {
        if( x < 0 || y < 0 || x > 9 || y > 8 ) {
            return 0;
        }
        return dp[x][y][step];
    }


    public static void main(String[] args) {
        int a = 7;
        int b = 7;
        int k = 10;

        long s1 = System.nanoTime();
        int r1 = ways1(a, b, k);
        long s2 = System.nanoTime();
        int r2 = ways2(a, b, k);
        long s3 = System.nanoTime();
        int r3 = ways3(a, b, k);
        long s4 = System.nanoTime();


        System.out.println("暴力递归，结果：" + r1 + "耗时: " + ( s2-s1 ));
        System.out.println("暴力递归+缓存，结果：" + r2 + "耗时: " + ( s3-s2 ));
        System.out.println("动态规划 ，结果：" + r3 + "耗时: " + ( s4-s3 ));
    }

}
