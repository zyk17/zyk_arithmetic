package com.zhangsan.no_10_DP;

import java.util.HashMap;
import java.util.Map;

/**
 * 拼字贴纸
 * https://leetcode-cn.com/problems/stickers-to-spell-word/
 * @author zhangsan
 * @date 2021/2/22 20:44
 */
public class Code06_StickersToSpellWord {


    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE? -1: ans;
    }

    private static int process1(String[] stickers, String target) {
        if( target.length() == 0 ) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus( first, target );
            if( rest.length() != target.length() ) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + ( min==Integer.MAX_VALUE ? 0: 1 );
    }

    private static String minus(String s1, String s2) {
        char[] cur = s1.toCharArray();
        char[] target = s2.toCharArray();

        int[] count = new int[26];
        for (char c : target) {
            count[c-'a']++;
        }
        for (char c : cur) {
            count[c-'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i]; j++) {
                sb.append( (char) ('a' + i) );
            }
        }
        return sb.toString();
    }


    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        int[][] wordMap = new int[N][26];
        char[] chars;
        for (int i = 0; i < N; i++) {
            chars = stickers[i].toCharArray();
            for (char c : chars) {
                wordMap[i][c-'a']++;
            }
        }
        int ans = process2(wordMap, target);
        return ans == Integer.MAX_VALUE? -1: ans;
    }

    // stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
    // 每一种贴纸都有无穷张
    // 返回搞定target的最少张数
    // 最少张数
    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        // target做出词频统计
        // target  aabbc  2 2 1..
        //                0 1 2..
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 尝试第一张贴纸是谁
            int[] sticker = stickers[i];
            // 最关键的优化(重要的剪枝!这一步也是贪心!)
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    /** dp加入缓存 */
    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] wordMap = new int[N][26];
        char[] chars;
        for (int i = 0; i < N; i++) {
            chars = stickers[i].toCharArray();
            for (char c : chars) {
                wordMap[i][c-'a']++;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(wordMap, target, dp);
        return ans == Integer.MAX_VALUE? -1: ans;
    }

    // stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
    // 每一种贴纸都有无穷张
    // 返回搞定target的最少张数
    // 最少张数
    public static int process3(int[][] stickers, String t, Map<String, Integer> dp) {
        if(dp.containsKey( t )) {
            return dp.get(t);
        }
        // target做出词频统计
        // target  aabbc  2 2 1..
        //                0 1 2..
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 尝试第一张贴纸是谁
            int[] sticker = stickers[i];
            // 最关键的优化(重要的剪枝!这一步也是贪心!)
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }


    public static void main(String[] args) {
        String[] stickers = {"with", "example", "science"};
        String target = "thehat";


        System.out.println(minStickers1(stickers, target));
        System.out.println(minStickers2(stickers, target));
        System.out.println(minStickers3(stickers, target));
    }

}
