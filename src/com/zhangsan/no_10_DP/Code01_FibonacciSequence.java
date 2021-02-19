package com.zhangsan.no_10_DP;

/**
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * @author zhangsan
 * @date 2021/2/19 16:01
 */
public class Code01_FibonacciSequence {

    /** 暴力递归，有好多冗余的重复计算 */
    public static int fib1(int n) {
        return compute(n);
    }

    public static int compute(int n) {
        if(n == 0 || n == 1) { return n; }
        return compute(n - 1) + compute( n -2 ) % 1000000007;
    }

    /** 初始化 */
    public static int fib2(int n) {
        return ans[n];
    }
    static int[] ans = new int[101];
    static{
        ans[1] = 1;
        for (int i = 2; i <= 100; i++) {
            ans[i] = (ans[i - 1] + ans[i - 2] )  % 1000000007 ;
        }
    }

    /** 动态规划 */
    public static int fib3(int n) {
        ans2[0] = 0; ans2[1] = 1;
        return dp3(n);
    }
    static int[] ans2 = new int[101];
    public static int dp3(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        if( ans2[n] == 0 ) {
            ans2[n] += (dp3(n-1) + dp3(n-2)) % 1000000007;
        }
        return ans2[n];
    }


    public static void main(String[] args) {
        int n = 10;
        for (int i = 0; i < 10; i++) {
            long s1 = System.nanoTime();
            int r1 = fib1(i);
            long s2 = System.nanoTime();
            int r2 = fib2(i);
            long s3 = System.nanoTime();
            int r3 = fib3(i);
            long s4 = System.nanoTime();

            System.out.println("结果1：" + r1 + ",耗时" + (s2 - s1) + "ns.");
            System.out.println("结果2：" + r2 + ",耗时" + (s3 - s2) + "ns.");
            System.out.println("结果3：" + r3 + ",耗时" + (s4 - s3) + "ns.");
        }
    }

}
