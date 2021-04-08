package com.zhangsan.no_other;

import com.zhangsan.util.ArrayUtil;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/**
 * 给定一个无序数组, 求出前k大的元素
 * 并要求有序
 *
 * @author zhangsan
 * @date 2021/4/6 22:41
 */
public class TopKNums {

    // 方法1: 排序取k个大的元素 O(n*logn)
    public static int[] topk1(int[] nums, int k) {
        int[] ans = new int[k];
        Arrays.sort(nums);
        for (int i = nums.length - 1; k > 0; i--) {
            ans[--k] = nums[i];
        }
        return ans;
    }

    // 方法2: 构建堆, 堆顶取元素 O(N+ k*logN)
    public static int[] topk2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        // 自底向上构建堆
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
        }
        // 每次从堆中取元素
        int[] ans = new int[k];
        while (k > 0) {
            ans[--k] = heap.poll();
        }
        return ans;
    }

    // 方法3: 找到第k个元素O(N), 再从后边遍历一边,比他大的都赋值,最后还剩多少个赋值成k, 然后排序 O(N+k*logK)
    public static int[] topk3(int[] nums, int k) {
        int kNum = Code01_FindMinKth.minKth3(nums, nums.length-k);
        int[] ans = new int[k];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > kNum) {
                ans[count++] = nums[i];
            }
        }
        while (count < k) {
            ans[count++] = kNum;
        }
        Arrays.sort(ans);
        return ans;
    }

    // O(N*logK)
    // 构建门槛, 小根堆
    // 遍历 n-k次, 遇到比堆顶大的就加入堆, 直到最后, 堆里边全是小的内容
    public static int[] topk4(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int[] kHeap = new int[k];
        // 自底向上构建一颗k长度的堆
        for (int i = 0; i != k; i++) {
            heapInsert(kHeap, arr[i], i);
        }
        for (int i = k; i != arr.length; i++) {
            // 从k+1开始如果比堆顶小,替换堆顶,然后向下调整堆
            if (arr[i] > kHeap[0]) {
                kHeap[0] = arr[i];
                heapify(kHeap, 0, k);
            }
        }
        return kHeap;
    }

    public static void heapInsert(int[] arr, int value, int index) {
        arr[index] = value;
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (arr[parent] > arr[index]) {
                swap(arr, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        while (left < heapSize) {
            if (arr[left] < arr[index]) {
                largest = left;
            }
            if (right < heapSize && arr[right] < arr[largest]) {
                largest = right;
            }
            if (largest != index) {
                swap(arr, largest, index);
            } else {
                break;
            }
            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    // for test
    public static void main(String[] args) {
        /*int[] nums = {1, 2, 31, 3, 32, 132, 15, 465, 313, 213, 13, 15, 1321, 321, 3};
        int[] nums2 = ArrayUtil.copyArr(nums);
        int[] nums3 = ArrayUtil.copyArr(nums);
        int k = 5;
        int[] ans1 = topk1(nums, k);
        int[] ans2 = topk2(nums2, k);
        int[] ans3 = topk3(nums3, k);


        ArrayUtil.printArr(ans1);
        ArrayUtil.printArr(ans2);
        ArrayUtil.printArr(ans3);*/

        // 以10倍递增测速
        for (int i = 10; i < Integer.MAX_VALUE; i*=10) {
            int[] nums = ArrayUtil.generateRandomArray(i, i, true, true);
            int[] nums2 = ArrayUtil.copyArr(nums);
            int[] nums3 = ArrayUtil.copyArr(nums);
            int[] nums4 = ArrayUtil.copyArr(nums);
//            int k = (int) (Math.random()*i);
            int k = 5;

            long s1 = System.currentTimeMillis();
            int[] ans1 = topk1(nums, k);
            long s2 = System.currentTimeMillis();
            int[] ans2 = topk2(nums2, k);
            long s3 = System.currentTimeMillis();
            int[] ans3 = topk3(nums3, k);
            long s4 = System.currentTimeMillis();
            int[] ans4 = topk3(nums4, k);
            long s5 = System.currentTimeMillis();

            System.out.println("================size: "+ i +"=================");
            System.out.println("排序寻找:     O(N*logN)    : " + (s2-s1));
            System.out.println("通过大根堆:   O(N+ k*logN)  : " + (s3-s2));
            System.out.println("通过Kth:     O(N+ k*logK)  :" + (s4 -s3));
            System.out.println("通过门槛小根堆 O(N*logK)     :" + (s5 -s4));
            System.out.println("=================================");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!ArrayUtil.isEquals(ans1, ans2) || !ArrayUtil.isEquals(ans2, ans3) || !ArrayUtil.isEquals(ans3, ans4)) {
                System.out.println("OOPS!");
                ArrayUtil.printArr(ans1);
                ArrayUtil.printArr(ans2);
                ArrayUtil.printArr(ans3);
                ArrayUtil.printArr(ans4);
            }
        }

    }

}
