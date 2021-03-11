package com.zhangsan.no_13_fastPower;

/**
 *
 * 斐波那契快速幂解
 *
 * @author zhangsan
 * @date 2021/3/10 22:19
 */
public class Fibonacci {

    public static int f(int n) {
        int[] dp = new int[n];
        dp[0] = 1; dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }

    public static int f2(int n) {



        return 0;
    }



    // a的b次方. 不考虑负数
    // 二分算.
    // a的19次方 = 0...010011 = a的1次方*a的2次方*a的16次方
    public static int pow(int a, int b) {
        int ans = 1;    // 0次方
        for (; b != 0; b >>= 1) {
            if( (b & 1) == 1 ) {
                ans *= a;
            }
            a *= a;
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int n = 6;
        int r1 = f(n);
        System.out.println(r1);


        int a = 9;
        int b = 7;
        System.out.println(pow(a, b));
        System.out.println(Math.pow(a, b));
    }

}
