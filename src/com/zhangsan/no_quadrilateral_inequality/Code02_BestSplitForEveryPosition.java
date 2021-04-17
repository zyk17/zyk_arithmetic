package com.zhangsan.no_quadrilateral_inequality;

import com.zhangsan.util.ArrayUtil;

/**
 * code01的要求, 现在是求每一步的结果
 *
 * @author zhangsan
 * @date 2021/4/17 15:17
 */
public class Code02_BestSplitForEveryPosition {

    // 非负数组
    // 不会回退的, 窗口解决, 没计算一个位置下个位置进来不会回退.
    public static int[] bestSplit1(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        int N = arr.length;
        int[] ans = new int[N], sum = new int[N + 1];
        ans[0] = 0;
        for (int i = 0; i < N; i++)
            sum[i + 1] = sum[i] + arr[i];

        int best = 0;   // 记录前一步的最优划分
        for (int range = 1; range < N; range++) {
            while (best + 1 < range) {
                int befor = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));       // 之前的情况, 不扩
                int after = Math.min(sum(sum, 0, best + 1), sum(sum, best + 2, range));  // 之前的情况在阔一步
                if (after >= befor)     // 如果值得往右扩, 扩
                    best++;
                else break;             // 否则直接断掉, 不用回退因为非负数组
            }
            ans[range] = Math.min(sum(sum, 0, best), sum(sum, best + 1, range));    // 收集答案
        }

        return ans;
    }

    // 原始数组  :      { 1,  3,  4,  5,   6}
    //                  0    1   2   3    4
    // 累加和数组:    {0,  1,  4,  8,  13,  19}
    //               0   1   2   3   4    5
    // arr[L]~arr[R]的累加和 == sum[R+1] - sum[L]
    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    // 暴力解: 贼垃圾
    public static int[] bestSplit2(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        int N = arr.length;
        int[] ans = new int[N];
        ans[0] = 0;
        for (int range = 0; range < N; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = 0;
                for (int L = 0; L <= s; L++) {
                    sumL += arr[L];
                }
                int sumR = 0;
                for (int R = s + 1; R <= range; R++) {
                    sumR += arr[R];
                }
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }
        return ans;
    }

    // code01 的做法, 不过是每个位置都要来个i的复杂度也就是: O(n^2)
    public static int[] bestSplit3(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        int N = arr.length;
        int[] ans = new int[N], sum = new int[N + 1];
        ans[0] = 0;
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        for (int range = 0; range < N; range++) {
            for (int s = 0; s < range; s++) {
                int sumL = sum(sum, 0, s);
                int sumR = sum(sum, s + 1, range);
                ans[range] = Math.max(ans[range], Math.min(sumL, sumR));
            }
        }
        return ans;
    }


    // for test
    public static void main(String[] args) {
        /*int[] arr = {8, 1, 22, 0, 0, 4, 8, 5, 1, 0, 1, 31};
        long s1 = System.currentTimeMillis();
        int[] ans1 = bestSplit1(arr);
        long s2 = System.currentTimeMillis();
        int[] ans2 = bestSplit2(arr);
        long s3 = System.currentTimeMillis();
        int[] ans3 = bestSplit3(arr);
        long s4 = System.currentTimeMillis();

        System.out.println("窗口不回退O(N):" + (s2 - s1));
        ArrayUtil.printArr(ans1);
        System.out.println("暴力解每一步O(N^2),总(N^3):" + (s3 - s2));
        ArrayUtil.printArr(ans2);
        System.out.println("每一步O(N), 总(N^2):" + (s4 - s3));
        ArrayUtil.printArr(ans3);*/

        int times = 100000;
        int maxSize = 10, maxValue = 10000;
        for (int i = 0; i < times; i++) {
            int[] arr = ArrayUtil.generateRandomArray(maxSize+=100, maxValue, false, true);   // 包含负数
            long s1 = System.currentTimeMillis();
            int[] ans1 = bestSplit1(arr);
            long s2 = System.currentTimeMillis();
            int[] ans2 = bestSplit2(arr);
            long s3 = System.currentTimeMillis();
            int[] ans3 = bestSplit3(arr);
            long s4 = System.currentTimeMillis();
            if(!ArrayUtil.isEquals(ans1, ans2) || !ArrayUtil.isEquals(ans2, ans3)) {
                System.out.println("OOPS");
                break;
            }
            System.out.println("================size:"+ maxSize +"==================");
            System.out.println("窗口不回退O(N):" + (s2 - s1));
            System.out.println("暴力解每一步O(N^2),总(N^3):" + (s3 - s2));
            System.out.println("每一步O(N), 总(N^2):" + (s4 - s3));
            System.out.println("==================================");
        }

    }


}
