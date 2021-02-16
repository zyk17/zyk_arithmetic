package com.zhangsan.no_1_binary;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * // 异或， 相同为0，不同为1
 * // 1. 归零律：a ⊕ a = 0
 * // 2. 恒等律：a ⊕ 0 = a
 * // 3. 交换律：a ⊕ b = b ⊕ a
 * // 4. 结合律：a ⊕b ⊕ c = a ⊕ (b ⊕ c) = (a ⊕ b) ⊕ c;
 * // 5. 自反：a ⊕ b ⊕ a = b.
 * // 6. d = a ⊕ b ⊕ c 可以推出 a = d ⊕ b ⊕ c.
 * // 7.若x是二进制数0101，y是二进制数1011；
 * // 则x⊕y=1110
 * // 只有在两个比较的位不同时其结果是1，否则结果为0
 * // 即“两个输入相同时为0，不同则为1”！
 *
 * @author zhangsan
 * @date 2021/1/31 16:55
 */
public class xor {

    public static void main(String[] args) {
        // 一个数组，所有都出现了偶数次，只有一个数出现了奇数次，找到那个数
       /*
        int[] arr = { 1, 2 ,3 ,4 ,5, 4, 1 ,3, 1 ,5, 4, 4, 7 ,2, 1};
        long start = System.nanoTime();
        System.out.println(find(arr));
        long end = System.nanoTime();
        System.out.println(find2(arr));
        long end2 = System.nanoTime();
        System.out.println("find()用时: " + (end-start) + "纳秒, find2()用时: " + (end2 - end) + "纳秒。");
        */

        // 找到一个数的最右侧的1
        // 10111000
        // 01000111 取反
        // 01001000 取反+1: (~num+1),即-num
        // 与运算： 10111000 & 01001000     即：00001000
        /*
        int num = 1020;
        int target = -1;
        Code01_PrintBinary.printBinary(num);
        target = num & (-num);
        Code01_PrintBinary.printBinary(target);
        System.out.println(target);
        */

        // 数组两个数出现了奇数次，找到那连个数
        /*
        int[] arr = { 1, 2 ,3 ,4 ,5, 4, 1 ,3, 1 ,5, 4, 4, 7, 5 ,2, 1};
        find3(arr);
        */

    }

    /**
     * 一个数组，所有都出现了偶数次，只有一个数出现了奇数次，找到那个数
     */
    public static int find(int[] arr) {
        int target = 0;
        for (int i = 0; i < arr.length; i++) {
            target ^= arr[i];
        }
        return target;
    }

    private static int find2(int[] arr) {
        AtomicInteger target = new AtomicInteger();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            /*Integer r = map.get(arr[i]);
            if(r == null) {
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], r+1);
            }*/
            map.merge(arr[i], 1, Integer::sum);
        }
        map.forEach((k, v) -> {
            if (v % 2 == 1)
                target.set(k);
        });
        return target.get();
    }

    /**
     * 一个数组，只有2个数出现了奇数次，找到那2个数
     */
    public static void find3(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // 异或之后结果变成那两个奇数异或后的结果，eor = a^b
        // 因为eor是a^b后的结果，所以eor二进制位有1的位置，a和b是一定不相同的
        // 然后拿到eor中最后一个为1的数 0010 | 0100 | 1000
        // 根据这个数把数组分成2份
        int eor1 = eor & (-eor);
        int eor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            // 因为此时eor只有1位有1， 所以通过&运算
            if ((arr[i] & eor1) != eor1 /*0*/) {
                eor2 ^= arr[i];
            }
        }
        System.out.println(eor);
        System.out.println(eor2);
        System.out.println(eor ^ eor2);
    }

}
