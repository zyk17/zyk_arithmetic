package com.zhangsan.no_1_binary;

import com.zhangsan.util.ArrayUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author zhangsan
 * @date 2021/2/2 15:01
 */
public class Code02_KM {

    /**
     * 一个数组，一个数出现了k次，其他数出现了m次。
     * m>1, k<m
     */
    public static int km(int[] arr, int k, int m) {
        int[] a = new int[32];
        // 遍历这些数
        for (int i = 0; i < arr.length; i++) {
            // 遍历这些数的每一位如果不为1，就往a数组的某一位累加1
            for (int j = 31; j >= 0; j--) {
                if (((arr[i] & (1 << j)) != 0)) {
                    a[j] += 1;
                }
            }
        }

        int ans = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            if(a[i] % m == 0) {
                continue;
            }
            if ((a[i] % m == k)) {
//                ans += (1 << i);
//                ans ^= (1 << i);
                ans |= (1 << i);
            } else {
                return -1;
            }
        }
        return ans;
    }

    /**
     * 对比方法
     */
    public static int compareM(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == k) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] arr2 = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2};
        System.out.println(km(arr2, 3, 4));
        System.out.println(compareM(arr2, 3, 4));

        int maxValue = 100;
        int kinds = 10;
        int testTimes = 1000;
        int max = 10;

        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int a = (int) (Math.random() * max) + 1;
            int b = (int) (Math.random() * max) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m)
                m++;
            int[] arr = generateTheTestArray(kinds, maxValue, k, m);

            /*System.out.println("k: " + k + ", m: " + m);
            ArrayUtil.printArr(arr);*/

            int r1 = km(arr, k, m);
            int r2 = compareM(arr, k, m);
            if (r1 != r2) {
                System.out.println("出错了。。");
                System.out.println("k: " + k + ", m: " + m);
                ArrayUtil.printArr(arr);
                System.out.println(r1 + "\t" + r2);
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 生成一个只有一个出现了k次，其他出现了m次的数组
     */
    public static int[] generateTheTestArray(int someKindsNum, int maxValue, int k, int m) {
        // 除了k的数，还有多少种数
        int mN = someKindsNum - 1;
        k = Math.random() > 0.5 ? k : k + 1;   // 模拟50%几率出现错误情况

        int[] arr = new int[k + mN * m];

        int kNum = (int) ((maxValue + 1) * Math.random() - maxValue * Math.random());
        int index = 0;
        for (; index < k; index++) {
            arr[index] = kNum;
        }

        HashSet<Integer> set = new HashSet<>();
        set.add(kNum);
        for (int i = 0; i < mN; i++) {
            int mNum = -1;
            do {
                mNum = (int) ((maxValue + 1) * Math.random() - maxValue * Math.random());
            } while (set.contains(mNum));
            int i1 = index + m;
            for (; index < i1; index++) {
                arr[index] = mNum;
            }
        }
        // 至此数组已经填完数了，接下来把数组打乱
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }


}
