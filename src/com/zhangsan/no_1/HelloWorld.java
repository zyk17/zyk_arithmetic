package com.zhangsan.no_1;

public class HelloWorld {

    public static void main(String[] args) {
        int num = 1071824;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for (int i = 31; i >= 0 ; i--) {
            sb.append( ((1 << i) & num) == 0 ? 0 +" ": 1 +" " );
            sb1.append( ((1 << i)) +" ");
            sb2.append( ((1 << i) & num) + " ");
        }
        System.out.println("===" + (16 &num));
        System.out.println(num + "的二进制：" + sb);
        System.out.println("2的31-0次方：" + sb1);
        System.out.println(num + "与2的31-0次方" + sb2);
        System.out.println("Hello World");

        Code01_PrintBinary.printBinary(Integer.MIN_VALUE);
    }

}
