package com.zhangsan.no_3_dichotomy;

/**
 * @author zhangsan
 * @date 2021/1/30 18:23
 */
public class Test {

    public static void main(String[] args) {
        int l = 1_900_000_000;
        int r = 2_000_000_000;

        int mid1 = l + ((r - l)>>1);
        System.out.println("中间位置 = " + mid1);

        // 可能出错：int最大值溢出
        int mid2 = (l + r)/2;
        System.out.println("中间位置 = " + mid2);

    }

}
