package com.zhangsan.no_18_orderedMap;

/**
 * @author zhangsan
 * @date 2021/3/25 19:02
 */
public interface SortedMap<K extends Comparable<K>, V> {

     int size();

     boolean containsKey(K key);

     void put(K key, V value);

     void remove(K key);

     V get(K key);

     K firstKey();

     K lastKey();

     K floorKey(K key);

     K ceilingKey(K key);

}
