package com.zhangsan.util;

import java.util.Arrays;

/**
 * 数组工具类
 * @author zhangyuekun
 * @date 2020/12/29 13:57
 */
public class ArrayUtil {

    /**
     * 生成一个随机的数组
     * @param maxSize 随机数组的最大长度为多少
     * @param maxValue 随机数组的最大值为多少
     * @return 随机数组
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random() - maxValue * Math.random());
        }
        return arr;
    }

    public static String[] generateRandomStringArray(int maxSize, int maxStringLength) {
        String[] arr = new String[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateRandomString(maxStringLength);
        }
        return arr;
    }

    private static String generateRandomString(int maxStringLength) {
        char[] ans = new char[(int) ((maxStringLength + 1) * Math.random())];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() < 0.5) ? (char) ((65) + value): (char) (97 + value);
        }
        return new String(ans);
    }

    public static int[] copyArr(int[] arr){
        return Arrays.copyOf(arr, arr.length);
    }

    public static String[] copyArr(String[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static void printArr(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static boolean isEquals(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    public static void sort(int[] arr) {
        Arrays.sort(arr);
    }

}
