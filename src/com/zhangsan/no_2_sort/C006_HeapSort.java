package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * @author zhangsan
 * @date 2021/2/5 23:37
 */
public class C006_HeapSort {

    public static void main(String[] args) {
        /*int[] arr = {6, 3, 5, 4, 8, 2, 4, 1};
        ArrayUtil.printArr(arr);
        quickSort1(arr);
        ArrayUtil.printArr(arr);*/

        boolean succeed = true;
        for (int i = 0; i < 100000; i++) {
            int[] arr1 = ArrayUtil.generateRandomArray(100, 1000000);
            int[] arr2 = ArrayUtil.copyArr(arr1);

            ArrayUtil.sort(arr1);
            try {
                heapSort(arr2);
            }catch (Exception e) {
                System.out.println("=====出现异常：=====");
                ArrayUtil.printArr(arr2);
            }

            // 排序不成功。
            if (!ArrayUtil.isEquals(arr1, arr2)) {
                ArrayUtil.printArr(arr1);
                System.out.println("========================================");
                ArrayUtil.printArr(arr2);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "排序成功!" : "排序有误!");
        int[] arr1 = ArrayUtil.generateRandomArray(20, 10000);
        ArrayUtil.printArr(arr1);
        heapSort(arr1);
        ArrayUtil.printArr(arr1);
    }

    public static void heapSort(int[] arr) {
        int heapSize = 0;
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
            heapSize++;
        }
        // 排序
        for (int i = 1; i < arr.length; i++) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[((index - 1) / 2)]) {
            swap(arr, index, ((index - 1) >> 1));
            index = ((index - 1) >> 1);
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = (index << 1) + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = (largest<<1) + 1;
        }
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

}
