package com.zhangsan.no_1;

/**
 * 打印二进制
 * @author zhangsan
 * @date 2021/1/24 18:42
 */
public class Code01_PrintBinary {


    public static void main(String[] args) {
//        int i = Integer.MAX_VALUE;
//        int i = -2;
//        System.out.println(Integer.toBinaryString(i));
//        printBinary(i);

        int a = 1234653;
        int b = 4865321;
        printBinary(a);
        printBinary(b);
        System.out.println("与运算");
        printBinary(a & b);
        System.out.println(a & b);
        System.out.println("或运算");
        printBinary(a | b);
        System.out.println(a | b);
        System.out.println("有一个1为1否则为0");
        printBinary(a ^ b);
        System.out.println(a ^ b);


        System.out.println("取反-1  ||  +1取反");
        System.out.println(~a);
    }

    public static void printBinary(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print( ((num & (1 << i)) == 0) ? 0 : 1 );
        }
        System.out.println();
    }

}
