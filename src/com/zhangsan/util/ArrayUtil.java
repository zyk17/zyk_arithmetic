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

    public static int[] copyArr(int[] arr){
        return Arrays.copyOf(arr, arr.length);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Arrays.toString(generateRandomArray(10, 100)));
        }
    }

}
