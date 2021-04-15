package com.zhangsan.no_subArr;

import java.util.HashMap;

/**
 * 给定数组中, 找到累加和位K的最长子数组的长度
 * 包含负数
 *
 * @author zhangsan
 * @date 2021/4/15 20:59
 */
public class CodeXX_LongestSumSubArrayLengthInPositiveArray2 {

    // 包含负数, 改成,必须以谁为结尾的答案, 然后找到最大的答案, 继而可以改成动态规划版
    // 解法: 以谁为底出现的答案.
    public static int maxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0) return 0;
        // 存放最早出现的累加和, 的下标
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, ans = -1;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int k = sum - K;
            if (map.containsKey(k))
                ans = Math.max(ans, i - map.get(k));
            if (!map.containsKey(sum))
                map.put(sum, i);
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int[] nums = {5, 1, 2, 1, 9, -3, 3, 6, 3, -6, -3, 4, 2};
        int k = 6;
        System.out.println(maxLength(nums, k));
    }

}
