package com.zhangsan.no_17_nonResouce;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * 限制资源类
 *
 * @author zhangsan
 * @date 2021/3/25 12:37
 */
public class Test {

    public static String[] diff(int N) {
        String[] ans = new String[N];
        for (int i = 0; i < N; i++) {
            ans[i] = "zhangsan" + i + "zhangsi" + i;
        }
        return ans;
    }

    public static int hash(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(s.getBytes());

            for (byte b : bytes) {
                System.out.print(b);
            }
            System.out.println();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {

        int aaa = hash("aaa");

        int N = 1000;
        String[] strs = diff(N);
        int[] count = new int[10];
        for (int i = 0; i < strs.length; i++) {
            int hash = strs[i].hashCode();
            count[ Math.abs(hash%10) ]++;
        }
        for (int i = 0; i < count.length; i++) {
            System.out.println(i+ " : " + count[i]);
        }
    }

}
