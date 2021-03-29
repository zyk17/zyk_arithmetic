package com.zhangsan.no_moore;

import com.zhangsan.util.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 摩尔投票 算法
 * <p>
 * 给定一个数组,寻找出现过一半以上的数.
 * 时间复杂度O(N) , 空间复杂度O(1)
 *
 * 做法:
 * 选择一个候选人有一张票,和其他人pk, 如果不相等,则互相抵消票数, 如果相等则累加可抵消票数
 *
 * @author zhangsan
 * @date 2021/3/29 23:04
 */
public class Code01_T {

    public static int moore(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int candidate = arr[0], count = 1;
        for (int i = 1; i < arr.length; i++) {
            if(count == 0) {
                candidate = arr[i];
                count++;
                continue;
            }
            if(candidate == arr[i]) {
                count++;
            }else {
                count--;
            }
        }
        // 找到了可能出现半数以上的数, 在遍历计数一下看是否正确
        count = 0;
        for (int i : arr) {
            if(i == candidate) {
                count++;
            }
        }
        return count > arr.length/2 ? candidate : -1;
    }


    public static int compareM(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > arr.length/2) {
                return entry.getKey();
            }
        }
        return -1;
    }

    // for test
    public static void main(String[] args) {
        /*int[] arr = {1, 2, 3, 1, 31, 2, 1, 1, 11, 1, 1};
        int r1 = moore(arr);
        System.out.println(r1);*/

        int maxValue = 5;
        int maxSize = 200;
        int times = 200000;
        for (int i = 0; i < times; i++) {
            int[] arr = ArrayUtil.generateRandomArray(maxSize, maxValue, false, false);
            int r1 = moore(arr);
            int r2 = compareM(arr);
            if(r1 != r2) {
                System.out.println("OOPS");
                ArrayUtil.printArr(arr);
                System.out.println(r1 + "\t" + r2);
                break;
            }
        }
    }

}
