package com.zhangsan.util;

/**
 * 堆
 * @author zhangsan
 * @date 2021/2/5 21:11
 */
public class Heap {

    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[((index - 1) / 2)]) {
            swap(arr, index, ((index - 1) >> 1));
            index = ((index - 1) >> 1);
        }
    }

    public void heapify(int[] arr, int index, int heapSize) {
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

    private void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

}
