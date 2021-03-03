package com.zhangsan.no_11_window;

/**
 * 加油站问题
 *
 * 给定两个长度相等的数组 gas, cost
 * gas代表这个加油站能加多少油, cost表示到下一个加油站要消耗的油
 * 一开始油箱是空的,返回从每个加油站出发 是否能够转完一圈
 *
 * @author zhangsan
 * @date 2021/3/3 11:44
 */
public class Code03_ {

    // 暴力解
    public static boolean[] right(int[] gas, int[] cost) {
        if( gas == null || cost == null || gas.length==1 || gas.length != cost.length ) { return null; };
        int N = gas.length;
        boolean[] result = new boolean[N];

        for (int i = 0; i < N; i++) {
            int step = 0;
            int index = i;
            int gasSum = gas[i];
            int nextCost = cost[i];
            do {
                if( gasSum < nextCost ) {
                    break;  // 答案默认false, 因此无需重复赋值
                }
                step++;
                index = index==N-1? 0: index+1;
                gasSum = gasSum - nextCost + gas[index];
                nextCost = cost[index];
            } while (index != i);
            result[i] = step==N;
        }

        return result;
    }

    // for test
    public static void main(String[] args) {

        int[] gas = { 1,1,3,1 };
        int[] cost = { 2,2,1,1 };
        boolean[] r = right(gas, cost);

        for (int i = 0; i < r.length; i++) {
            System.out.println(r[i]);
        }
    }




}
