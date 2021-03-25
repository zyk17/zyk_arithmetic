package com.zhangsan.no_17_nonResouce;

import com.zhangsan.no_1_binary.Code01_PrintBinary;

/**
 * @author zhangsan
 * @date 2021/3/25 13:46
 */
public class Test2 {

    public static void main(String[] args) {

        int N = (int) Math.pow(2, 10)+10;
//        int N = 32;
        // 找到0-N-1 的数组中为出现的数字们
        int[] nums = new int[N];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.random() <0.5? i: -1;
        }
        nums[31] = 31;
        for (int num : nums) {
            System.out.print(num + ", ");
        }
        System.out.println();

        // 使用bit map来做
        // java中int是32位, 320个数组所以准备10个int就足够了
        int[] bitmap = new int[(N%32 == 0)? N/32 : N/32+1];
        for (int num : nums) {
            if(num >= 0) {
                // 找到对应位图的位置
                // index对应bitmap的下标
                int index = num / 32;
                // 在bitmap中int的第i位, 由小到大,从右往左填的
                int i = num % 32;
                int v = (int) Math.pow(2, i) == Integer.MAX_VALUE? Integer.MIN_VALUE: (int) Math.pow(2, i);
                bitmap[index] |= v;
            }
        }
        for (int i : bitmap) {
            Code01_PrintBinary.printBinary(i);
        }

        int num = 0;
        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < 32; j++) {
                if(num <= N) {
                    System.out.print(num + " : " + (((bitmap[i] & (1 << j)) == 0) ? "未出现" : "出现了") + "\t");
                    num++;
                    if (num % 5 == 0) {
                        System.out.println();
                    }
                }else {
                    break;
                }
            }
        }

    }

}
