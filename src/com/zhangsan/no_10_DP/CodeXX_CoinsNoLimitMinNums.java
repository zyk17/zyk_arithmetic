package com.zhangsan.no_10_DP;

/**
 * 货币数组, 组成aim. 每张都是无限的, 求组成aim的最少张数
 *
 * @author zhangsan
 * @date 2021/4/11 16:16
 */
public class CodeXX_CoinsNoLimitMinNums {

    public static int minNums(int[] arr, int aim) {
        return process(arr, aim, 0);
    }

    private static int process(int[] arr, int rest, int i) {
        // 超过要组成的金额了, 返回一个无效解
        if (rest < 0) return Integer.MAX_VALUE;
        // 货币用完了, 返回是否组成了, 组成我就是用0张, 否则就是一个无效解
        if (i == arr.length) return rest == 0 ? 0 : Integer.MAX_VALUE;
        int nums = Integer.MAX_VALUE;
        for (int num = 0; num * arr[i] <= rest; num++) {
            int next = process(arr, rest - num * arr[i], i + 1);
            // 后序的答案如果不是一个无效值, 那我的答案就等于  (后序的答案 + 我用的张数), 最后选择一个答案中的最小值
            if (next != Integer.MAX_VALUE) nums = Math.min(nums, next + num);
        }
        return nums;
    }

    public static int dp(int[] arr, int aim) {
        int R = arr.length + 1, C = aim + 1;
        int[][] dp = new int[R][C];
        dp[R - 1][0] = 0;
        // 最后一行: c=0, 其他都是无效解
        for (int c = 1; c < C; c++) {
            dp[R - 1][c] = Integer.MAX_VALUE;
        }
        // 从下往上(内层), 从左往右(外层)
        for (int c = 0; c < C; c++) {
            for (int r = R - 2; r >= 0; r--) {
                int nums = Integer.MAX_VALUE;
                for (int num = 0; num * arr[r] <= c; num++) {
                    // 后序答案
                    int next = c - num * arr[r] < 0 ? Integer.MAX_VALUE : dp[r + 1][c - num * arr[r]];
                    if (next != Integer.MAX_VALUE) nums = Math.min(nums, next + num);
                }
                dp[r][c] = nums;
            }
        }

        return dp[0][aim];
    }

    // for test
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 10};
        int aim = 99;
        System.out.println(minNums(arr, aim));
        System.out.println(dp(arr, aim));
    }

}
