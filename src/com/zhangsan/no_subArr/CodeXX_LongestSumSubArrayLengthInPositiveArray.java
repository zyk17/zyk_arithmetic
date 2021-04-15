package com.zhangsan.no_subArr;

/**
 * 给定数组中, 找到累加和位K的最长子数组的长度
 * 非负数组, 所以大小是递加的, 满足单调性所以窗口解
 *
 * @author zhangsan
 * @date 2021/4/15 20:00
 */
public class CodeXX_LongestSumSubArrayLengthInPositiveArray {

    // 非负数组
    public static int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0)
            return 0;
        int l = 0, r = 0, ans = -1, sum = arr[0];
        while (r < arr.length) {
            if (sum == K) {
                ans = Math.max(ans, r - l + 1);
                if (++r == arr.length) {
                    break;
                }
                sum += arr[r];
            } else if (sum < K) {
                if (++r == arr.length) {
                    break;
                }
                sum += arr[r];
            } else {
                sum -= arr[l++];
            }
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int[] nums = {3, 3, 2, 1, 1, 1, 2, 2, 2};
        int k = 6;
        System.out.println(getMaxLength(nums, k));
    }


}
