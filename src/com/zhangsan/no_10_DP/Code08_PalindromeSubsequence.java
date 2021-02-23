package com.zhangsan.no_10_DP;

import java.util.Arrays;

/**
 * 一个字符串的最长回文子序列
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * @author zhangsan
 * @date 2021/2/23 18:47
 */
public class Code08_PalindromeSubsequence {

    /** 解1：根据上一章，两个字符串找公共子序列的方法。我们将字符串做个反转，反转后和它本身的最长公共子序列就是它的最长公共子序列。 */
    public static int longestPalindromeSubseq1(String s) {
        if( s == null || s.length() == 0) { return 0; }
        if(s.length() == 1) { return 1; }
        String reverse = new StringBuffer(s).reverse().toString();
        return longestCommonSubsequence(s, reverse);
    }

    /** 返回两个字符串寻找最长公共子序列长度 */
    public static int longestCommonSubsequence(String text1, String text2) {
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
        return dp[N-1][M-1];
    }

    /** 解2：暴力递归 */
    public static int longestPalindromeSubseq2(String s) {
        if( s == null || s.length() == 0) { return 0; }
        if(s.length() == 1) { return 1; }
        return process2(s.toCharArray(), 0, s.length()-1);
    }

    /**
     * 主函数调用控制字符串为大于1.
     * base case：l == r-1 情况 return 2或1
     * 有四种情况：
     * 以l开头   以r结尾      的回文
     * 以l开头   不以r结尾     的回文
     * 不以l开头 以r结尾       的回文
     * 不以l开头 不以r结尾      的回文
     */
    private static int process2(char[] str, int l, int r) {
        if( l == r-1 ) { return str[l] == str[r] ? 2 : 1; }
        if( l == r ) { return 1; }

        int ans = 0;
        int p1 = str[l] == str[r] ? process2(str, l + 1, r - 1)+2 : 0;
        int p2 = process2(str, l, r-1);
        int p3 = process2(str, l + 1, r);
        int p4 = process2(str, l + 1, r - 1);
        ans = Math.max(p1, Math.max(p2, Math.max(p3, p4)));
        return ans;
    }

    /** 解3：暴力递归+缓存 */
    public static int longestPalindromeSubseq3(String s) {
        if( s == null || s.length() == 0) { return 0; }
        if(s.length() == 1) { return 1; }
        int N = s.length();
        int [][] dp = new int[N][N];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        int ans = process3(s.toCharArray(), 0, s.length() - 1, dp);

        /*System.out.println("缓存：");
        for (int[] arr : dp) {
            ArrayUtil.printArr(arr);
        }*/

        return ans;
    }

    private static int process3(char[] str, int l, int r, int[][] dp) {
        if( l == r-1 ) { return str[l] == str[r] ? 2 : 1; }
        if( l == r ) { return 1; }
        if(dp[l][r] != -1) {
            return dp[l][r];
        }
        int ans = 0;
        int p1 = str[l] == str[r] ? process3(str, l + 1, r - 1, dp)+2 : 0;
        int p2 = process3(str, l, r-1, dp);
        int p3 = process3(str, l + 1, r, dp);
        int p4 = process3(str, l + 1, r - 1, dp);
        ans = Math.max(p1, Math.max(p2, Math.max(p3, p4)));
        dp[l][r] = ans;
        return ans;
    }

     /** 解4：dp */
    public static int longestPalindromeSubseq4(String s) {
        if (s == null || s.length() == 0) { return 0; }
        if (s.length() == 1) { return 1; }
        int N = s.length();
        char[] str = s.toCharArray();

        int[][] dp = new int[N][N];
        dp[N-1][N-1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i+1] = str[i] == str[i+1] ? 2 : 1;
        }
        int row = N-2;
        int col = 2;
        while (row >= 0) {
            int i = 0;
            for (int j = col; j < N; j++) {
                // 根据空间感（二维数组）发现了 左下一定不如左和下，左下+2的情况下有可能大于左下
                dp[i][j] = Math.max( dp[i][j-1], dp[i + 1][j] );
                if( str[i] == str[j] ) {
                    dp[i][j] = Math.max( dp[i][j], dp[i + 1][j - 1]+2 );
                }
//                int p1 = str[i] == str[j] ? dp[i + 1][j - 1]+2 : 0;
//                int p2 = dp[i][j-1];
//                int p3 = dp[i + 1][j];
//                int p4 = dp[i + 1][j-1];
//                dp[i][j] = Math.max(p1, Math.max(p2, Math.max(p3, p4)));
                i++;
            }
            col++;
            row--;
        }

        /*System.out.println("缓存表");
        for (int[] arr : dp) {
            ArrayUtil.printArr(arr);
        }*/

        return dp[0][N-1];
    }
    /** 测试 */
    public static void main(String[] args) {
        String s = "abcq2w1e2478654as532ba";
        System.out.println(longestPalindromeSubseq1(s));
        System.out.println(longestPalindromeSubseq2(s));
        System.out.println(longestPalindromeSubseq3(s));
        System.out.println(longestPalindromeSubseq4(s));
    }
}
