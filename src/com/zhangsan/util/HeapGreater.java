package com.zhangsan.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆
 *
 * @author zhangsan
 * @date 2021/2/6 17:33
 */
public class HeapGreater<T> {

    protected ArrayList<T> heap;
    protected HashMap<T, Integer> indexMap;
    protected int heapSize;
    protected Comparator<T> comp;

    public HeapGreater(Comparator<T> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = comparator;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    public void remove(T obj) {
        T replace = heap.get(heapSize-1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if(obj != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public int size() {
        return heapSize;
    }

    public List<T> getAllElement() {
        return new ArrayList<>(heap);
    }

    protected void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get(((index - 1) / 2))) > 0) {
            swap(index, ((index - 1) >> 1));
            index = ((index - 1) >> 1);
        }
    }

    protected void heapify(int index) {
        int left = (index << 1) + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) > 0 ? left + 1 : left;
            largest = comp.compare(heap.get(largest), heap.get(index)) > 0 ? largest : index;
            if (largest == index) {
                break;
            }
            swap(largest, index);
            index = largest;
            left = (largest << 1) + 1;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(j, o1);
        heap.set(i, o2);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }

}
