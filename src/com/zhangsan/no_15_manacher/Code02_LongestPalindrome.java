package com.zhangsan.no_15_manacher;

/**
 * @author zhangsan
 * @date 2021/4/24 21:24
 */
public class Code02_LongestPalindrome {

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // https://leetcode-cn.com/problems/longest-palindromic-substring/
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        if(s.length() == 1) return s;
        char[] str = manacherString(s);     // "12132" -> "#1#2#1#3#2#"
        int[] pArr = new int[str.length];   // 处理串的半径数组 比如回文直径7, 回文半径为3
        int max = 1, bi = 0;           // 最长半径
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
                    pArr[i] = R - i;     // 因为相对C L~R 是回文, 且_i的在L~R的回文 = i在L~R的回文, 所以i一定不会更长了,如果更长那么相对C的L~R范围应该更长
                } else {     // 刚好压中L, 加速扩, 直接从R+1开始扩
                    int v = expand(str, i, R - i + 1);
                    pArr[i] = v - i;
                    if (v > R) {
                        R = v;
                        C = i;
                    }
                }
            }
            if (pArr[i] > max) {
                max = Math.max(max, pArr[i]);
                bi = (i >> 1) - (max >> 1);
            }
        }
        return s.substring(bi, bi + max);
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

    public static void main(String[] args) {
        String str = "bdabad";
        System.out.println(longestPalindrome(str));
    }

}
