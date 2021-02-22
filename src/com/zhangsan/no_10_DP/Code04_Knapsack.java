package com.zhangsan.no_10_DP;

import com.zhangsan.util.ArrayUtil;

/**
 * 背包问题
 * @author zhangsan
 * @date 2021/2/22 16:04
 */
public class Code04_Knapsack {

    /** 暴力解 */
    public static int getMaxValue1(int[] w, int[] v, int bag) {
        if( w == null || v == null || w.length != v.length || w.length == 0 ) {
            return 0;
        }
        return process1(w, v, 0, bag);
    }

    public static int process1(int[] w, int[] v, int index, int rest) {
        // 重量超了
        if(rest < 0) { return -1; }
        // 索引超了`
        if(index == w.length) { return 0; }
        int p1 = process1(w, v, index+1, rest);    // 不要当前
        int p2Next = process1(w, v, index+1, rest - w[index]); // 要当前
        int p2 = 0;
        if( p2Next != -1 ) {
            p2 = v[index] + p2Next; // 如果要当前重量没超的话的结果，赋值p2
        }
        return Math.max(p1, p2);    // 返回结果最大的
    }

    public static int getMaxValue2(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N+1][bag+1];
        // 第n行全是0，不用填
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index+1][rest];
                int p2Next = rest - w[index] < 0 ? -1 : dp[index+1][rest - w[index] ];
                int p2 = 0;
                if( p2Next != -1 ) {
                    p2 = v[index] + p2Next;
                }
                dp[index][rest] = Math.max(p1, p2);;
            }
        }

        /*for (int[] ints : dp) {
            ArrayUtil.printArr(ints);
        }*/

        return dp[0][bag];
    }

    public static void main(String[] args) {
        int N = 30;
        int[] w = { 3, 2, 4, 7, 3, 1, 7 };
        int[] v = { 5, 6, 3, 19, 12, 4, 2 };

//        int[] w = ArrayUtil.generateRandomArray( N, 10, false, true );
//        int[] v = ArrayUtil.generateRandomArray( N, 20, false, true );
        int bag = 15;

        System.out.println("重量：");
        ArrayUtil.printArr(w);
        System.out.println("价值:");
        ArrayUtil.printArr(v);

        long s1 = System.nanoTime();
        int r1 = getMaxValue1(w, v, bag);
        long s2 = System.nanoTime();
        int r2 = getMaxValue2(w, v, bag);
        long s3 = System.nanoTime();


        System.out.println("暴力解答案： " + r1 + "耗时：" + (s2 - s1));
        System.out.println("加入缓存解答案： " + r2 + "耗时：" + (s3 - s2));

    }

}
