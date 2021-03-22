package com.zhangsan.no_other;

/**
 *
 * 线段树
 *
 * @author zhangsan
 * @date 2021/3/19 14:04
 */
public class Code02_SegmentTree {

    public static class SegmentTree {




    }


    public static void main(String[] args) {
        int a = 2;
        int b = a<<1 | 1;   // *2+1, 因为一个数*2后 1的位置永远是0,所以和1|一下, 1位置就变成1也就是加了1. 但如果随机一个数1位置可能不是0所以|一下不一定就+1了
        System.out.println( b  );
    }
}
