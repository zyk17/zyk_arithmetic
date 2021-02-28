package com.zhangsan.sleetcode;

/**
 * https://leetcode-cn.com/problems/powx-n/
 * @author zhangsan
 * @date 2021/2/28 20:11
 */
public class LeetCode50 {

    /** 递归, 时间复杂度 O(N) 空间复杂度 O(1) */
    public static double myPow(double x, int n) {
        long n1 = n;
        if(n < 0) { n1 = ~n1 + 1; x = 1/ x; }
        System.out.println(n1);
        double ans = 1;
        for (int i = 0; i < n1; i++) {
            ans *= x;
        }
        return ans;
    }

    /** 比对方法 */
    public static double myPow2(double x, int n) {
        return Math.pow(x, n);
    }

    /** 二分优化 x的4次方 == x的2次方 * x的二次方 */
    public static double myPow3(double x, int n) {
        if(n == 0 || x == 1) { return 1; }
        long n1 = n;
        if(n < 0) { n1 = ~n1 + 1; x = 1/ x; }
        return powProcess(x, n1);
    }

    public static double powProcess(double x, long n) {
        if( n == 1 ) {
            return x;
        }
        double ans = powProcess(x, n>>1);
        ans *= ans;
        if( (n & 1) == 1 ) {
            ans *= x;
        }
        return ans;
    }



    public static void main(String[] args) {
        double x = 2.0;
        int n = Integer.MIN_VALUE;

        System.out.println(myPow(x, n));
        System.out.println(myPow2(x, n));
        System.out.println(myPow3(x, n));
    }

}
