package com.zhangsan.no_6_greedy;

import com.zhangsan.util.ArrayUtil;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 一个字符串数组，怎么拼接字典序最小
 * @author zhangsan
 * @date 2021/2/15 14:39
 */
public class Code01_LowestLexicography {

    public static String lowestString(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, (str1, str2) -> (str1+str2).compareTo( (str2+str1) ));
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static String lowestString2(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        return process(strs).first();
    }

    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if(strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndex(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    public static String[] removeIndex(String[] strs, int index) {
        String[] ans = new String[strs.length - 1];
        int ansIndex = 0;
        for (int i = 0; i < strs.length; i++) {
            if(i != index) {
                ans[ansIndex++] = strs[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        String[] strs = ArrayUtil.generateRandomStringArray(10, 3);
        String[] strs2 = ArrayUtil.copyArr(strs);
        System.out.println(Arrays.toString(strs));
        System.out.println(Arrays.toString(strs2));
        System.out.println(lowestString(strs));
        System.out.println(lowestString2(strs2));

//        int times = 10000;
//        int maxSize = 10;
//        int maxStringLength = 3;
//
//
//        for (int i = 0; i < times; i++) {
//            String[] strs = ArrayUtil.generateRandomStringArray(maxSize, maxStringLength);
//            String[] strs2 = ArrayUtil.copyArr(strs);
//            System.out.println(Arrays.toString(strs));
//            System.out.println(Arrays.toString(strs2));
//            String r1 = lowestString(strs);
//            String r2 = lowestString2(strs2);
//            if(!r1.equals(r2)) {
//                System.out.println("OOPS");
//                System.out.println(Arrays.toString(strs));
//                System.out.println(Arrays.toString(strs2));
//                System.out.println(r1 + "\t" + r2);
//            }
//        }
//        System.out.println("finish");
    }

}
