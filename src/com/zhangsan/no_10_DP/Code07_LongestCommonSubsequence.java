package com.zhangsan.no_10_DP;

import java.util.Arrays;

/**
 * 两个字符串的最长公共子序列长度
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * @author zhangsan
 * @date 2021/2/23 16:20
 */
public class Code07_LongestCommonSubsequence {

    /** 暴力解 */
    public static int longestCommonSubsequence1(String text1, String text2) {
//        if(StringUtil.isEmpty(text1) || StringUtil.isEmpty(text2)) { return 0; }
        if( text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0 ) {
            return 0;
        }
        return process1( text1.toCharArray(), text2.toCharArray(), text1.length()-1, text2.length()-1 );
    }

    public static int process1(char[] s1, char[] s2, int i, int j) {
        boolean isEqual = s1[i] == s2[j];    // s1[i] 和 s2[j] 是否相等
        if( i == 0 && j == 0 ) {
            return isEqual ? 1 : 0;
        } else if( i == 0 ) {
            return isEqual? 1: process1(s1, s2, i, j - 1);
        } else if (j == 0) {
            return isEqual? 1: process1(s1, s2, i - 1, j);
        }else {
            // i!=0 && j!=0
            int p1 = process1(s1, s2, i, j-1);
            int p2 = process1(s1, s2, i-1, j);
            int p3 = isEqual? 1 + process1(s1, s2, i-1, j-1) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    /** 暴力解+缓存 */
    public static int longestCommonSubsequence2(String text1, String text2) {
//        if(StringUtil.isEmpty(text1) || StringUtil.isEmpty(text2)) { return 0; }
        if( text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0 ) {
            return 0;
        }
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int[][] dp = new int[s1.length][s2.length];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        int ans = process2(s1, s2, s1.length - 1, s2.length - 1, dp);
        /*for (int[] arr : dp) {
            ArrayUtil.printArr(arr);
        }*/
        return ans;
    }

    public static int process2(char[] s1, char[] s2, int i, int j, int[][] dp) {
        boolean isEqual = s1[i] == s2[j];    // s1[i] 和 s2[j] 是否相等
        int ans;
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        if( i == 0 && j == 0 ) {
            ans = isEqual ? 1 : 0;
        } else if( i == 0 ) {
            ans = isEqual? 1: process2(s1, s2, i, j - 1, dp);
        } else if (j == 0) {
            ans = isEqual? 1: process2(s1, s2, i - 1, j, dp);
        }else {
            // i!=0 && j!=0
            int p1 = process2(s1, s2, i, j-1, dp);
            int p2 = process2(s1, s2, i-1, j, dp);
            int p3 = isEqual? 1 + process2(s1, s2, i-1, j-1, dp) : 0;
            ans = Math.max(p1, Math.max(p2, p3));
        }
        dp[i][j] = ans;
        return ans;
    }
    /** 反推缓存赋值方法 */
    public static int longestCommonSubsequence3(String text1, String text2) {
//        if(StringUtil.isEmpty(text1) || StringUtil.isEmpty(text2)) { return 0; }
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int N = text1.length();
        int M = text2.length();
        int[][] dp = new int[N][M];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1: dp[i - 1][0];
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = s1[0] == s2[j] ? 1: dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i][j-1];
                int p2 = dp[i-1][j];
                int p3 = s1[i] == s2[j]? 1 + dp[i-1][j-1] : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        /*for (int[] arr : dp) {
            ArrayUtil.printArr(arr);
        }*/
        return dp[N-1][M-1];
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(longestCommonSubsequence1(s1, s2));
        System.out.println(longestCommonSubsequence2(s1, s2));
        System.out.println(longestCommonSubsequence3(s1, s2));
    }

}
