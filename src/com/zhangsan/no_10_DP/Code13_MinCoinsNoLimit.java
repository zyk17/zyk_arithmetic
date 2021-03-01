package com.zhangsan.no_10_DP;

/**
 * 怎么样组成用的张数最少
 * arr: 货币的面值,每张张数不限
 * aim: 组成多少的货币
 * @author zhangsan
 * @date 2021/3/1 16:13
 */
public class Code13_MinCoinsNoLimit {

    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if(index == arr.length) {
            return rest == 0? 0: Integer.MAX_VALUE;
        }else {
            int ans = Integer.MAX_VALUE;
            for (int zhang = 0; zhang*arr[index] <= rest; zhang++) {
                int next = process(arr, index+1, rest-zhang*arr[index]);
                if( next != Integer.MAX_VALUE ) {
                    ans = Math.min( ans, next+zhang );
                }
            }
            return ans;
        }
    }

    // for test
    public static void main(String[] args) {
        int[] arr = {1, 10, 100};
        int aim = 1000;
        System.out.println(minCoins(arr, aim));
    }

}
