package com.zhangsan.no_dp_skill;

/**
 *
 * 给定一个数N是否能被一堆连续的数相加刚好为N, 就返回true,否则返回fase
 *
 * @author zhangsan
 * @date 2021/3/28 21:59
 */
public class Code03_MSumToN {

    public static boolean isMSum1(int num) {
        for (int i = 1; i < num; i++) {
            int sum = i;
            for (int j = i+1; j < num; j++) {
                if(sum + j > num) {
                    break;
                }
                if(sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    public static boolean isMSum(int num) {
//        return (num & (num-1)) != 0;
//        return (num & (~num+1)) != num;
        return (num & (-num)) != num;
    }

    // for test
    public static void main(String[] args) {
        for (int i = 1; i <= 256; i++) {
            System.out.println( i + "  :  " +isMSum1(i));
        }
    }

}
