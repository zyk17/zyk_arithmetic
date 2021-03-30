package com.zhangsan.no_moore;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * 找到出现了 1/3 以上的元素
 *
 * @author zhangsan
 * @date 2021/3/29 23:23
 */
public class Code02_MajorityElement {

    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new LinkedList<>();
        if(nums == null || nums.length == 0) {
            return ans;
        }
        int candidate1 = -1, count1 = 0,
            candidate2 = -1, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if(candidate1 == nums[i]) {
                count1++;
                continue;
            }
            if(candidate2 == nums[i]) {
                count2++;
                continue;
            }
            if(count1 == 0) {
                candidate1 = nums[i];
                count1++;
                continue;
            }
            if(count2 == 0) {
                candidate2 = nums[i];
                count2++;
                continue;
            }
            count1--;
            count2--;
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if(candidate1 == num) {
                count1++;
            }else if(candidate2 == num) {
                count2++;
            }
        }
        if(count1 > nums.length/3) {
            ans.add(candidate1);
        }
        if(count2 > nums.length/3) {
            ans.add(candidate2);
        }
        return ans;
    }


    /** 对数器方法 */
    public static List<Integer> compareM(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> ans = new LinkedList<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > arr.length/3) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }

    // for test
    public static boolean isListEqual(List<Integer> l1, List<Integer> l2) {
        if(l1 == l2) {
            return true;
        }
        if(l1 == null || l2 == null) {
            return false;
        }
        if(l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            if(!l1.get(i).equals(l2.get(i))) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void main(String[] args) {

        int[] nums = {
                3, 3, 0, 1, 0, 1, 0, 3
        };
        List<Integer> r1 = majorityElement(nums);
        List<Integer> r2 = compareM(nums);
        System.out.println(r1);
        System.out.println(r2);

        /*int maxValue = 5;
        int maxSize = 200;
        int times = 200000;
        for (int i = 0; i < times; i++) {
            int[] arr = ArrayUtil.generateRandomArray(maxSize, maxValue, false, false);
            List<Integer> r1 = majorityElement(arr);
            List<Integer> r2 = compareM(arr);
            if(!isListEqual(r1, r2)) {
                System.out.println("OOPS");
                ArrayUtil.printArr(arr);
                System.out.println(r1);
                System.out.println(r2);
                break;
            }
        }*/
    }


}
