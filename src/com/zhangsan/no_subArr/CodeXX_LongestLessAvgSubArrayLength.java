package com.zhangsan.no_subArr;

import java.util.HashMap;

/**
 * 给定一个数组, 求数组平均值小于k的最长子数组的长度
 *
 * @author zhangsan
 * @date 2021/4/15 23:05
 */
public class CodeXX_LongestLessAvgSubArrayLength {

    // 转移: 数组所有值都减去k, 求子数组累加和<=0的最大子数组长度
    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= k;
        }

        // 最大累加和 <= 0(有负数有正数)
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, ans = -1;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int key = sum;
            if (map.containsKey(key))
                ans = Math.max(ans, i - map.get(key));
            if (!map.containsKey(sum))
                map.put(sum, i);
        }
        return ans;

    }

    // for test
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 5, 15, 13, 1, 11, 31, 31, 3, 13, 15};
        int k = 10;
        System.out.println(maxLength(arr, k));
    }

}
