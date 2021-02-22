package com.zhangsan.no_10_DP;

import java.util.Arrays;

/**
 * 给定一个数字字符串，1-26对应A-Z, 请问有多少种方式 将数字字符串转为字母字符串
 * @author zhangsan
 * @date 2021/2/22 19:04
 */
public class Code05_ConvertToLetterString {

    /*static char[] charMap = new char[26];
    static {
        int v = 65;
        for (int i = 0; i < charMap.length; i++) {
            charMap[i] = (char) v++;
        }
    }*/

    public static int number1(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        return process1( str.toCharArray(), 0 );
    }

    private static int process1(char[] str, int i) {
        if( i == str.length ) { return 1; }
        if( str[i] == '0' ) { return 0; }

        int way = process1(str, i+1);
        if( i + 1 < str.length && ( str[i] - '0' )*10 + str[i+1] - '0' < 27 ) {
            // 可行
            way += process1(str, i+2);
        }
        return way;
    }

    public static int number2(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        int[] dp = new int[str.length()];
        Arrays.fill(dp, -1);
        return process2( str.toCharArray(), 0, dp );
    }

    private static int process2(char[] str, int i, int[] dp) {
        if( i == str.length ) { return 1; }
        if(dp[i] != -1){
            return dp[i];
        }
        int ans = 0;
        if( str[i] != '0' ) {

            ans = process2(str, i + 1, dp);
            if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                // 可行
                ans += process2(str, i + 2, dp);
            }
        }
        dp[i] = ans;
        return ans;
    }


    public static int number3(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int N = str.length();
        char[] chars = str.toCharArray();
        int[] dp = new int[N+1];
        dp[N] = 1;
        for (int i = N-1; i >= 0; i--) {
            if( chars[i] != '0' ) {
                int ans = dp[i+1];
                if( i + 1 < chars.length && ( chars[i] - '0' )*10 + chars[i+1] - '0' < 27 ) {
                    ans += dp[i+2];
                }
                dp[i] = ans;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str = "11043142312312321312321375321312232345";


        long s1 = System.nanoTime();
        int r1 = number1(str);
        long s2 = System.nanoTime();
        int r2 = number2(str);
        long s3 = System.nanoTime();
        int r3 = number3(str);
        long s4 = System.nanoTime();

        System.out.println("暴力递归答案:" + r1 + "，耗时：" + (s2 - s1) );
        System.out.println("加入缓存答案:" + r2 + "，耗时：" + (s3 - s2) );
        System.out.println("反推缓存直接操作缓存答案:" + r3 + "，耗时：" + (s4 - s3) );
    }

}
