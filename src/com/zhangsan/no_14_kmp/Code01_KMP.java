package com.zhangsan.no_14_kmp;

/**
 * kmp算法
 * @author zhangsan
 * @date 2021/3/11 11:34
 */
public class Code01_KMP {

    public static int kmp(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = getNextArray(str2);
        int x = 0;
        int y = 0;
        while (x < str1.length && y < str2.length) {
            // 匹配上了, 都往后推
            if(str1[x] == str2[y]) {
                x++;
                y++;
            // 否则y要往前推, 如果来到第0位了, 那就整个匹配不上, 就要 y从0和x的下一位开始匹配
            }else if(next[y] == -1) {
                x++;
                // y = 0; y已经是0了
            }else {
                y = next[y];
            }
        }
        return y==str2.length ? x-y: -1;
    }

    public static int[] getNextArray(char[] str) {
        int N = str.length;
        int[] next = new int[N];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < N) {
            if(str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            }else if(cn > 0) {
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }

    /** 返回s1中包含s2的子串,第一次出现的位置, 暴力解 */
    public static int compareF(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        for (int i = 0; i <= str1.length-str2.length; i++) {
            int str1Index = i;
            int str2Index = 0;
            for (; str2Index < str2.length; str2Index++, str1Index++) {
                if(str1[str1Index] != str2[str2Index]) {
                    break;
                }
            }
            if(str2Index == str2.length) {
                return i;
            }
        }
        return -1;
    }


    // for test
    public static void main(String[] args) {

        String s1 = "aaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaab";
        String s2 = "aaab";
        // 7
        long n1 = System.nanoTime();
        int r1 = compareF(s1, s2);
        long n2 = System.nanoTime();
        int r2 = s1.indexOf(s2);
        long n3 = System.nanoTime();
        int r3 = kmp(s1, s2);
        long n4 = System.nanoTime();

        System.out.println("暴力解: " + r1 + ", 时间: " + (n2-n1));
        System.out.println("系统提供: " + r2 + ", 时间" + (n3-n2));
        System.out.println("kmp算法: " + r3 + ", 时间" + (n4-n3));
    }
}
