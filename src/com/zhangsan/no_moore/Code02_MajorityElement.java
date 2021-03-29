package com.zhangsan.no_moore;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 找到出现了 1/3 以上的元素
 *
 * @author zhangsan
 * @date 2021/3/29 23:23
 */
public class Code02_MajorityElement {

    public static int majorityElement(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int


        return -1;
    }


    /** 对数器方法 */
    public static int compareM(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > arr.length/3) {
                return entry.getKey();
            }
        }
        return -1;
    }

    // for test
    public static void main(String[] args) {

    }


}
