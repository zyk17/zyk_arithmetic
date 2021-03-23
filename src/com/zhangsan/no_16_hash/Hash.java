package com.zhangsan.no_16_hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author zhangsan
 * @date 2021/3/23 14:55
 */
public class Hash {

    private MessageDigest hash;

    public Hash(String algorithm) {
        try {
            hash = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("不支持该算法.");
        }
    }

    public String hash(String input) {
        byte[] bytes = hash.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(b);
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        /*for (Provider provider : Security.getProviders()) {
            System.out.println("=================");
            //    提供者。身份证的名字
            System.out.println("身份证的名字" + String.valueOf(provider.getName()));
            //提供者。id版本
            System.out.println("id版本" + String.valueOf(provider.getVersionStr()));
            //提供者。id信息
            System.out.println("id信息" + String.valueOf(provider.getInfo()));
            //提供者。id名称
            System.out.println("id名称" + provider.getClass().getName());
            System.out.println("===================");
        }*/

        System.out.println("支持的算法:");
        for (String str : Security.getAlgorithms("MessageDigest")) {
            System.out.println(str);
        }
        System.out.println("=============");

        String algorithm = "SHA";
        Hash hash = new Hash(algorithm);

        String str1 = "zykzs1";
        String str2 = "zykzs2";
        String str3 = "zykzs3";
        String str4 = "zykzs4";
        String str5 = "zykzs5";

        System.out.println(hash.hash(str1));
        System.out.println(hash.hash(str2));
        System.out.println(hash.hash(str3));
        System.out.println(hash.hash(str4));
        System.out.println(hash.hash(str5));
    }

}
