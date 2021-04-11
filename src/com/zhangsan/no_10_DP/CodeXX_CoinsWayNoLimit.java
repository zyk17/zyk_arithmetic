package com.zhangsan.no_10_DP;

/**
 * 给定一个货币面值数组, 和aim
 * 每一张面值的货币都是无限的, 返回组成aim的方法数
 *
 * @author zhangsan
 * @date 2021/4/11 13:30
 */
public class CodeXX_CoinsWayNoLimit {

    // 暴力解: 在num*面值 不超过剩余金额的情况下, 尝试每个货币都选择num张
    public static int ways(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int i, int rest) {
        if (rest < 0) return 0;
        if (i == arr.length) return rest == 0 ? 1 : 0;

        int way = 0;
        for (int num = 0; num * arr[i] <= rest; num++) {
            way += process(arr, i + 1, rest - num * arr[i]);
        }
        return way;
    }

    // dp
    public static int dp(int[] arr, int aim) {
        int R = arr.length + 1, C = aim + 1;
        int[][] dp = new int[R][C];
        dp[R - 1][0] = 1;

        // 从下往上, 从左往右. 下往上内层循环, 左往右外层
        for (int c = 0; c < C; c++) {
            for (int r = R - 2; r >= 0; r--) {
                int way = 0;
                for (int num = 0; num * arr[r] <= c; num++) {
                    way += dp[r + 1][c - num * arr[r]];
                }
                dp[r][c] = way;
            }
        }
        return dp[0][aim];
    }

    // dp2, 根据dp的空间推导出来的, 其实dp的那个推导的for的查询也是o(1)
    public static int dp2(int[] arr, int aim) {
        int R = arr.length + 1, C = aim + 1;
        int[][] dp = new int[R][C];
        dp[R - 1][0] = 1;

        // 从下往上, 从左往右. 下往上内层循环, 左往右外层
        for (int c = 0; c < C; c++) {
            for (int r = R - 2; r >= 0; r--) {
                dp[r][c] = dp[r+1][c];
                if(c - arr[r] >= 0) {
                    dp[r][c] += dp[r][c - arr[r]];
                }
            }
        }
        return dp[0][aim];
    }


    // for test
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 10, 20};
        int aim = 100;
        System.out.println(ways(arr, aim));
        System.out.println(dp(arr, aim));
        System.out.println(dp2(arr, aim));
    }

}
