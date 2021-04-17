package com.zhangsan.no_quadrilateral_inequality;

import com.zhangsan.util.ArrayUtil;

/**
 * 切分数组, 算出左右两部分累加和最小的.
 * 计算怎么切两边累加和中 最大的值
 *
 * @author zhangsan
 * @date 2021/4/17 13:20
 */
public class Code_BestSplitForAll {

    // 通过前缀和数组, 时间复杂度:O(n) , 空间复杂度O(n)
    public static int bestSplit(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + arr[i];

        int ans = -1;
        for (int i = 0; i < sum.length - 1; i++) {
            int p1 = sum[i];
            int p2 = sum[sum.length - 1] - sum[i];
            ans = Math.max(ans, Math.min(p1, p2));
        }
        return ans;
    }

    public static int bestSplit2(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int sumAll = arr[0];

        for (int i = 1; i < arr.length; i++)
            sumAll += arr[i];

        int ans = -1, sl = 0, sr;
        for (int i = 0; i < arr.length - 1; i++) {
            sl += arr[i];
            sr = sumAll - sl;
            ans = Math.max(ans, Math.min(sl, sr));
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 5, 4, 2, 3, 6};
        System.out.println(bestSplit(nums));
        System.out.println(bestSplit2(nums));

        int times = 10000;
        int maxLen = 100;
        int maxVal = 1000;
        for (int i = 0; i < times; i++) {
            int[] arr = ArrayUtil.generateRandomArray(maxLen, maxVal);

            int ans1 = bestSplit(arr);
            int ans2 = bestSplit2(arr);
            if (ans1 != ans2) {
                System.out.println("OOPS, " + i);
                ArrayUtil.printArr(arr);
                System.out.println(ans1 + "\t" + ans2);
                break;
            }
        }
    }

}
