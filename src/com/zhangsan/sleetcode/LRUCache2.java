package com.zhangsan.sleetcode;

import java.util.*;

/**
 * @author zhangsan
 * @date 2021/2/26 12:58
 */
public class LRUCache2 {

    Queue<Integer> keys = new LinkedList<>();
    Map<Integer, Integer> cache = new HashMap<>();
    int capacity;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(keys.contains(key)) {
            // 把当前元素移动到队尾
            keys.remove(key);
            keys.add(key);
            return cache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if( keys.contains(key) ) {
            // 有该元素
            keys.remove(key);
        } else if( keys.size() == capacity ) {
            // 无该元素,且元素已满,删一个元素
            Integer delKey = keys.poll();
            cache.remove(delKey);
        }
        keys.add(key);
        cache.put(key, value);
    }

}

// for test
class TestLRU {
    public static void main(String[] args) {
        System.out.println("LRU2:");
        LRUCache2 lruCache = new LRUCache2(2);

        // ["LRUCache","put","put","get","put","put","get"]
        // [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]

        lruCache.put(2, 1);         //              k:2,v:1
        lruCache.put(2, 2);         //              k:2 v:2
        System.out.println(lruCache.get(2));     // 2
        lruCache.put(1, 1);                      // k:2 v:2 k:1 v:1
        lruCache.put(4, 1);                      // k:1 v:1 k:4 v:1
        System.out.println(lruCache.get(2));     // -1
    }
}
