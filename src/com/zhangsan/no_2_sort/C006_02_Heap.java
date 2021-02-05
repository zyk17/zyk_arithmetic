package com.zhangsan.no_2_sort;

import com.zhangsan.util.ArrayUtil;

/**
 * 堆
 *
 * @author zhangsan
 * @date 2021/2/5 21:11
 */
public class C006_02_Heap {

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

class TestHeap {

    public static void main(String[] args) {
        int[] arr = {6, 3, 5, 4, 8, 2, 4, 1};
        ArrayUtil.printArr(arr);
        C006_02_Heap heap = new C006_02_Heap();
        int heapSize = 0;
        for (int i = 0; i < arr.length; i++) {
            heap.heapInsert(arr, i);
            heapSize++;
        }
        ArrayUtil.printArr(arr);
        // 排序
        for (int i = 1; i < arr.length; i++) {
            swap(arr, 0, --heapSize);
            heap.heapify(arr, 0, heapSize);
            System.out.println(i);
            ArrayUtil.printArr(arr);
        }
        ArrayUtil.printArr(arr);

    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }


}
