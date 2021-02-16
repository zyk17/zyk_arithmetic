package com.zhangsan.no_6_greedy;

/**
 * 给定一个字符串 只有 'x' 和 '.' 组成
 * ’x‘代表墙，’.‘代表街道
 * 墙不用放灯无需照亮，街道需要照亮。一盏灯能照亮左右两边的街道
 * 怎样放灯，需要灯最少，并且全部照亮
 * @author zhangsan
 * @date 2021/2/16 16:00
 */
public class Code05_Light {

    public static int light(String str) {
        char[] chars = str.toCharArray();
        int light = 0;
        int N = chars.length;

        for (int i = 0; i < N; ) {
            if(chars[i] == 'x') {
                i++;
            } else {
                // .
                light++;
                if( i+1 < N && chars[i+1] == 'x') {
                    // .x
                    i+=2;
                } else {
                    // ..
                    i+=3;
                    /*if( i+2 < N && chars[i+2] == 'x') {
                        // ..x
                        i+=3;
                    } else {
                        // ...
                        i+=3;
                    }*/
                }
            }
        }

        return light;
    }


    public static void main(String[] args) {
        String str = "...x...x...x..";
        System.out.println(light(str));

    }

}
