package com.zhangsan.util;

/**
 * 字符串工具类
 * @author zhangsan
 * @date 2021/2/23 16:22
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
