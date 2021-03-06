package com.zhangsan.no_15_manacher;

/**
 * 最长回文子串
 *
 * @author zhangsan
 * @date 2021/3/12 13:38
 */
public class Code01_LongestSubString {

    // 暴力解, 左右都拼接一个字符向左向右扩看.结果除以二就是回文长度
    public static int longestSubString(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = manacherString(s);

        int max = 1;
        for (int i = 1; i < str.length; i++) {
            int r = expand(str, i, 1);
            max = Math.max(max, r - i);
        }
        return max;
    }

    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        // 回文半径的大小
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置
        // coding：最右的扩成功位置的，再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) { // 0 1 2
            // R第一个违规的位置，i>= R
            // i位置扩出来的答案，i位置扩的区域，至少是多大。
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int manacher2(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = manacherString(s);     // "12132" -> "#1#2#1#3#2#"
        int[] pArr = new int[str.length];   // 处理串的半径数组 比如回文直径7, 回文半径为3
        int max = 1;           // 最长半径
        int R = 0, C = 0, L = 0;     // C: 中心点   R: 中心点对应的右边界. R求最右的,中心点跟着他变化   L = C - (R-C) = C-R+C = 2*C-R
        int _i;     // i相对C的对应点     _i = C - (i - C)  = 2*C-i
        for (int i = 1; i < pArr.length; i++) {
            if (i > R) {     // i在R外, 暴力扩
                int v = expand(str, i, 1);
                pArr[i] = v - i;
                if (v > R) {
                    R = v;
                    C = i;
                }
            } else {
                _i = (C << 1) - i;    // 2*C-i
                L = (C << 1) - R;     // 2*C-R
                if (_i - pArr[_i] > L) {     // i相对C的对应点的 回文范围在 L~R中
                    pArr[i] = pArr[_i];     // 因为相对C L~R 是回文, 且 _i的回文在L~R中, 所以i的回文和_i是一样的
                } else if (_i - pArr[_i] < L) {  // i相对C的对应点的 回文范围在 L~R外
                    pArr[i] = R-i;     // 因为相对C L~R 是回文, 且_i的在L~R的回文 = i在L~R的回文, 所以i一定不会更长了,如果更长那么相对C的L~R范围应该更长
                } else {     // 刚好压中L, 加速扩, 直接从R+1开始扩
                    int v = expand(str, i, R - i + 1);
                    pArr[i] = v - i;
                    if (v > R) {
                        R = v;
                        C = i;
                    }
                }
            }
            max = Math.max(max, pArr[i]);
        }
        return max;
    }

    // 相对i位置开始往外扩, 从range为区间开始尝试, 返回最后扩到的右边界
    private static int expand(char[] str, int i, int range) {
        int l, r = -1;
        for (; (r = i + range) < str.length && (l = i - range) > -1; range++) {     // 不管怎样先让右边界都+range, 管他哪儿边现超r都已经+上了
            if (str[l] != str[r])
                return r - 1;
        }
        return r - 1;
    }

    // for test
    public static void main(String[] args) {
        String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                +"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        long s1 = System.nanoTime();
        int r1 = longestSubString(str);
        long s2 = System.nanoTime();
        int r2 = manacher(str);
        long s3 = System.nanoTime();
        int r3 = manacher2(str);
        long s4 = System.nanoTime();
        System.out.println("暴力解: " + r1 + ", 时间: " + (s2 - s1));
        System.out.println("manacher : " + r2 + ", 时间: " + (s3 - s2));
        System.out.println("manacher(搓代码) : " + r3 + ", 时间: " + (s4 - s3));

        /*int times = 100;
        int maxLen = 100000;
        for (int i = 0; i < times; i++) {
            String str = randomStr(maxLen);

            long s1 = System.nanoTime();
            int r1 = longestSubString(str);
            long s2 = System.nanoTime();
            int r2 = manacher(str);
            long s3 = System.nanoTime();
            int r3 = manacher2(str);
            long s4 = System.nanoTime();

            if(r1 != r2 || r2 != r3) {
                System.out.println("OOPS");
                System.out.println(str);
                System.out.println(r1);
                System.out.println(r2);
                System.out.println(r3);
                break;
            }

            System.out.println("暴力解: " + r1 + ", 时间: " + (s2 - s1));
            System.out.println("manacher : " + r2 + ", 时间: " + (s3 - s2));
            System.out.println("manacher(搓代码) : " + r3 + ", 时间: " + (s4 - s3));
        }*/
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
