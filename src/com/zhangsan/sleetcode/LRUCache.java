package com.zhangsan.sleetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 偷懒版, 利用jdk提供的LinkedHashMap 链表map,它是有添加顺序的,用它来完成了
 * get和put方法
 * @author zhangsan
 * @date 2021/2/26 11:35
 */
public class LRUCache {

    LinkedHashMap<Integer, Integer> cache;
    int capacity;

    /** 正整数作为容量 capacity 初始化 LRU 缓存 */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer, Integer>( capacity, 0.75F, true ) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    /** 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 */
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    /** 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，
     * 它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     */
    public void put(int key, int value) {
        cache.put(key, value);
    }

}

// for test
class TestLRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        // ["LRUCache","put","put","get","put","get","put","get","get","get"]
        // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));    // 1        2 1
        lruCache.put(3, 3);                     //          1 3
        System.out.println(lruCache.cache);
        System.out.println(lruCache.get(2));    // -1        null
        lruCache.put(4, 4);                     //          3 4
        System.out.println(lruCache.get(1));    // -1
        System.out.println(lruCache.get(3));    // 3
        System.out.println(lruCache.get(4));    // 4

    }
}
