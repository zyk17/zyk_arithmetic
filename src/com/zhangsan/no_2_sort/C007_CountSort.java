package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * 统计排序，不基于比较的排序，前置条件在一个范围上，可控的，不然一直调整也很麻烦。使用场景特殊
 * 时间复杂度O(N)
 * <p>
 * 一个公司，年龄都是18 - 70岁中间，将他们排序
 *
 * @author zhangsan
 * @date 2021/2/8 14:49
 */
public class C007_CountSort {

    public static void main(String[] args) {
        /*int[] arr = {18, 55, 25, 38, 24, 46, 64, 52, 19, 34, 64, 18, 69, 46, 52,
                41, 66, 34, 28, 49, 67, 28, 19, 65, 59, 65, 25, 35, 26, 19, 65, 25};
        int[] arr1 = ArrayUtil.copyArr(arr);
        ArrayUtil.printArr(arr);
        long start = System.nanoTime();
        countSort(arr);
        long end1 = System.nanoTime();
        ArrayUtil.sort(arr1);
//        C001_BubbleSort.bubbleSort(arr1);
        long end2 = System.nanoTime();
        ArrayUtil.printArr(arr);
        ArrayUtil.printArr(arr1);
        // 计数: 9000纳秒， 系统： 221600纳秒
        System.out.println("排序耗时：计数排序：" + (end1-start) + ", 系统排序：" + (end2-end1));*/

        int times = 100000;
        int maxSize = 1000;
        int minValue = 18;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {

            int[] arr = generateArr(maxSize, maxValue, minValue);
            int[] arr1 = ArrayUtil.copyArr(arr);

            ArrayUtil.sort(arr);
            countSort(arr1, maxValue, minValue);
            if (!ArrayUtil.isEquals(arr, arr1)) {
                succeed = false;
                break;
            }
        }
        System.out.println("测试结束：" + (succeed ? "成功" : "失败"));
    }

    public static void countSort(int[] arr, int max, int min) {
        // 遍历了arr次
        int[] help = new int[(max - min) + 1];
        for (int i : arr) {
            help[i - min]++;
        }
        // 虽然是双重循环，但实际上还是遍历了arr次
        // 所以时间复杂度: O(N) = 2N
        int index = 0;
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[i]; j++) {
                arr[index++] = i + min;
            }
        }
    }

    public static int[] generateArr(int maxSize, int maxValue, int minValue) {
        maxSize = (int) (Math.random() * maxSize + 1);
        int[] rs = new int[maxSize];
        for (int i = 0; i < rs.length; i++) {
            int v = (int) (Math.random() * maxValue + 1);
            while (v < minValue) {
                v = (int) (Math.random() * maxValue + 1);
            }
            rs[i] = v;
        }
        return rs;
    }

}
