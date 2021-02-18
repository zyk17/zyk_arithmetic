package com.zhangsan.no_9_recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印一个字符串的全部子序列
 * @author zhangsan
 * @date 2021/2/18 19:26
 */
public class Code02_PrintAllSubsequences {

    public static List<String> subs(String str) {
        List<String> ans = new ArrayList<>();
        process1(str.toCharArray(), 0, ans, "");
        return ans;
    }

    public static void process1(char[] str, int index, List<String> ans, String path) {
        if( index == str.length ) {
            ans.add(path);
            return;
        }
        // 没要index位置的字符
        // String no = path;
        process1(str, index+1, ans, path);
        // 要了index的字符
        // String yes = path + str[index];
        process1(str, index+1, ans, path + str[index]);
    }

    public static List<String> subs2(String str) {
        Set<String> set = new HashSet<>();
        process2(str.toCharArray(), 0, set, "");
        return new ArrayList<>(set);
    }

    public static void process2(char[] str, int index, Set<String> ans, String path) {
        if( index == str.length ) {
            ans.add(path);
            return;
        }
        // 没要index位置的字符
        // String no = path;
        process2(str, index+1, ans, path);
        // 要了index的字符
        // String yes = path + str[index];
        process2(str, index+1, ans, path + str[index]);
    }

    public static void main(String[] args) {
        String str = "1231";
        List<String> subs = subs(str);
        List<String> subs2 = subs2(str);

        System.out.println(subs);
        System.out.println("=====================");
        System.out.println(subs2);
        System.out.println("=====================");
    }

}
