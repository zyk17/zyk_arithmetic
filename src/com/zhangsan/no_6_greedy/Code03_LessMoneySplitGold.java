package com.zhangsan.no_6_greedy;

import java.util.PriorityQueue;

/**
 * 最少的前切割黄金问题
 * 一个黄金，切一下得要两边的钱，切成想要的几个段怎么切花费的前最少
 *
 * 比如想要 3 6 9 长度18怎么切花费最少的前
 * @author zhangsan
 * @date 2021/2/15 17:38
 */
public class Code03_LessMoneySplitGold {


    public static int lessMoneySplitGold(int[] arr) {
        // 贪心策略，把要切成的段放进小根堆里，小根堆取出两个相加放回小根堆，结果累加这个值，就是最终的答案。
        // 证明：无
        int ans = 0;
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        for (int i : arr) {
            smallHeap.add(i);
        }
        while (smallHeap.size() > 1) {
            int c = smallHeap.poll() + smallHeap.poll();
            ans += c;
            smallHeap.add(c);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = {1,1,2,2,3,3,4};
        System.out.println(lessMoneySplitGold(arr));
    }

}
