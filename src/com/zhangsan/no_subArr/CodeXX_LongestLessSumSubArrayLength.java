package com.zhangsan.no_subArr;

/**
 * 给定数组(有正数负数和0), 子数组累加和不超过K的最大长度是多少
 * O(N)解: 转移为 minSum, minSumEnd两个数组,代表从i开始往右扩或不扩的最小值, 以及他们能扩到哪里的下标
 * 然后根据这个再转移成窗口, 不会退并且有舍弃的解法.
 *
 * @author zhangsan
 * @date 2021/4/15 22:08
 */
public class CodeXX_LongestLessSumSubArrayLength {

    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        int[] minSum = new int[arr.length],
                minSumEnd = new int[arr.length];
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] <= 0) {
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }

        int end = 0, sum = 0, res = 0;
        // i是窗口的最左位置, end是扩初来的窗口的最右位置+1, 也就是下一块儿的开始地方
        // 窗口[i ~ end]
        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + minSum[end] <= k) {    // 直接往右扩, 知道累加和不比K小了, 然后设置当前窗口累加和. 以及end = 下一个范围开始的位置
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            res = Math.max(res, end - i);   // 收集一下答案, end = 下一个开始的位置, 所以窗口的范围[i ~ end-1] 个数也就是 end - i
            // 看看窗口是否还有值, 如果窗口为空了, 直接从下一个位置开始
            if (end > i)
                sum -= arr[i];  // 不为空
            else
                end = i + 1;
        }
        return res;
    }


    // for test
    public static void main(String[] args) {
        int[] arr = {12,456,-41,-12,15,1,-46,-6,4,4,94,-6,-4,94,-64,1,-1};
        int k = 100;
        System.out.println(maxLengthAwesome(arr, k));
    }

}
