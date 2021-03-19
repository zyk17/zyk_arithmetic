package com.zhangsan.no_15_manacher;

/**
 *
 * 最长回文子串
 * @author zhangsan
 * @date 2021/3/12 13:38
 */
public class Code01_LongestSubString {

    /** 暴力解, 左右都拼接一个字符向左向右扩看.结果除以二就是回文长度 */
    public static int longestSubString(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        String[] old = str.split("");
        String joinStr = String.join("#", old);
        joinStr = "#" + joinStr + "#";
        char[] charArray = joinStr.toCharArray();

        int len = 1;
        int range = 1;
        int max = 0;
        for (int i = 0; i < charArray.length; i++) {
            len = 1;
            while (i-range >= 0 && i+range < charArray.length) {
                int z = range-1;
                for (; z <= range && charArray[i-z] == charArray[i+z]; z++) {
                }
                if(z != range+1) {
                    break;
                }
                len = range*2+1;
                range++;
            }
            max = Math.max(max, len/2);
        }
        return max;
    }

    // for test
    public static void main(String[] args) {
        String str = "123454321";
        int r1 = longestSubString(str);
        System.out.println(r1);
    }
}
