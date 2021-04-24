package com.zhangsan.no_14_kmp;

/**
 * 第二遍学习KMP
 * @author zhangsan
 * @date 2021/4/24 15:39
 */
public class Code02_KMP2 {

    public static int kmp(String str, String match) {
        if(match.length() > str.length()) return -1;    // 非空判断就不做了, 由外边调用的人判断吧
        int[] next = getNextArr(match);     // 获得匹配串的跳跃数组
        int x = 0, y = 0;       // x: str串的下标   y: 匹配串的下标
        while (x < str.length() && y < match.length()) {
            if(str.charAt(x) == match.charAt(y)) {    // 目前下标的内容相等
                x++;
                y++;
            }else if(next[y] != -1) {   // 不想等了
                // 如果我匹配串还有最长相等的内容, 我就跳到我前边最长相等内容跟你比
                y = next[y];
            }else {         // 不想等了
                // y来到0了, 那就只能让和str的下一个位置重新开始匹配了
                x++;
            }
        }
        return y == match.length() ? x - y : -1;    // 如果最后匹配串匹配到了最后就说明有匹配成功的, 然后在str串的下标就是 x - y
    }

    // 获取str的next数组, 即他前边的字符串(不能包含整体, 因为整体必然从前缀到后缀都相等啦)的最长前缀和后缀相等的长度
    private static int[] getNextArr(String str) {
        int N = str.length();
        int[] next = new int[N];
        next[0] = -1;   // 人为规定, 0的最长 前缀后缀相等的长度为-1
        next[1] = 0;    // 1的前边只有0, 不能取整体, 所以最长前缀后缀相等的长度为为0
        int i  = 2;     // 从2开始填
        // cn 代表长度现在的匹配的长度, 从0-cn, 也代表回退的时候,回退到哪儿个位置
        int cn = 0;     // 因为1的时候是认为他没有的也就是失败的. 所以2也是从0开始往后比的
        while (i < N) {     // i < N, 控制i往后边填
            if(str.charAt(i-1) == str.charAt(cn)) {     // 如果我的前缀 == 当前的cn也就是前面努力过的最后最长相等, 如果命中, 直接可以赋值
                next[i++] = ++cn;
            }else if(cn != 0) {       // 否则就是没有匹配上
                // 如果我还不是0的话, 那我就往前跳到一个其次的最长位置, 继续努力匹配
                cn = next[cn];
            }else {                     // 否则就是没有匹配上
                // 如果到0了, 只能宣告这一位没有可以匹配的了
                next[i++] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {
        /*String str = "aaaaaaaaaaaaaaaaab";
        String match = "aaab";
        long s1 = System.currentTimeMillis();
        int ans1 = kmp(str, match);
        long s2 = System.currentTimeMillis();
        int ans2 = Code01_KMP.kmp(str, match);
        long s3 = System.currentTimeMillis();
        int ans3 = Code01_KMP.compareF(str, match);
        long s4 = System.currentTimeMillis();
        int ans4 = str.indexOf(match);
        long s5 = System.currentTimeMillis();
        if(ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
            System.out.println("OOPS");
        }
        System.out.println("=====================================");
        System.out.println("刚写的kmp: " + (s2 - s1));
        System.out.println("之前的kmp: " + (s3 - s2));
        System.out.println("暴力解: " + (s4 - s3));
        System.out.println("系统api: " + (s5 - s4));
        System.out.println("=====================================");*/

        int times = 100;
        int maxLen = 2000;
        int matchStrMaxLen = 100;
        for (int i = 0; i < times; i++) {
            String str = randomStr(maxLen);
            String match = randomStr(matchStrMaxLen);
            long s1 = System.nanoTime();
            int ans1 = kmp(str, match);
            long s2 = System.nanoTime();
            int ans2 = Code01_KMP.kmp(str, match);
            long s3 = System.nanoTime();
            int ans3 = Code01_KMP.compareF(str, match);
            long s4 = System.nanoTime();
            int ans4 = str.indexOf(match);
            long s5 = System.nanoTime();
            if(ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("OOPS");
            }
            System.out.println("=====================================");
            System.out.println("str: " + str);
            System.out.println("match: " + match);
            System.out.println("刚写的kmp: " + (s2 - s1));
            System.out.println("之前的kmp: " + (s3 - s2));
            System.out.println("暴力解: " + (s4 - s3));
            System.out.println("系统api: " + (s5 - s4));
            System.out.println("=====================================");
        }
    }

    public static String randomStr(int maxLen) {
        maxLen = (int) (Math.random() * maxLen) + 2;
        char[] str = new char[maxLen];
        for (int i = 0; i < str.length; i++) {
            str[i] = (char) (Math.random() * 26+ 96);
        }
        return new String(str);
    }

}
