package com.zhangsan.no_9_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 * abc
 * abc acb bac bca cab cba
 * @author zhangsan
 * @date 2021/2/18 20:21
 */
public class Code03_PrintAllPermutations {

    /** 初版，便于理解，可优化 */
    public static List<String> permutation1(String str) {
        List<String> ans = new ArrayList<>();
        if(str == null || str.length() == 0) {
            return ans;
        }
        List<Character> rest = new ArrayList<>();
        for (char c : str.toCharArray()) {
            rest.add(c);
        }
        process1(rest, "", ans);
        return ans;
    }

    public static void process1(List<Character> rest, String path, List<String> ans) {
        if( rest.isEmpty() ) {
            ans.add(path);
        }
        for (int i = 0; i < rest.size(); i++) {
            char cur = rest.get(i);
            rest.remove(i);
            process1(rest, path+cur, ans);
            rest.add(i, cur);
        }
    }

    /** 参数优化，直接在源字符串上操作，并有了动态性 */
    public static List<String> permutation2(String str) {
        List<String> ans = new ArrayList<>();
        if(str == null || str.length() == 0) {
            return ans;
        }
        process2(str.toCharArray(), 0, ans);
        return ans;
    }

    public static void process2(char[] str, int i, List<String> ans) {
        if( i == str.length ) {
            ans.add(new String(str));
        }
        for (int j = i; j < str.length; j++) {
            swap(str, i, j);
            process2(str, i+1, ans);
            swap(str, i, j);
        }
    }

    /** 去重版 */
    public static List<String> permutation3(String str) {
        List<String> ans = new ArrayList<>();
        if(str == null || str.length() == 0) {
            return ans;
        }
        process3(str.toCharArray(), 0, ans);
        return ans;
    }

    public static void process3(char[] str, int i, List<String> ans) {
        if( i == str.length ) {
            ans.add(new String(str));
        }
        boolean[] visited = new boolean[256];
        for (int j = i; j < str.length; j++) {
            if(!visited[str[j]]) {
                visited[str[j]] = true;
                swap(str, i, j);
                process3(str, i + 1, ans);
                swap(str, i, j);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        /*char temp = str[i];
        str[i] = str[j];
        str[j] = temp;*/
        if(i != j) {
            str[i] = (char) (str[i] ^ str[j]);
            str[j] = (char) (str[i] ^ str[j]);
            str[i] = (char) (str[i] ^ str[j]);
        }
    }

    public static void main(String[] args) {
        String str = "acc";
        System.out.println(permutation1(str));

        System.out.println("===============");

        System.out.println(permutation2(str));

        System.out.println("===============");

        System.out.println(permutation3(str));
    }

}
