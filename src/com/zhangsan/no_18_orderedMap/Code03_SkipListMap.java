package com.zhangsan.no_18_orderedMap;

import java.util.ArrayList;

/**
 *
 * 跳表.
 * 搜索. (有平衡)
 *
 * @author zhangsan
 * @date 2021/3/26 16:06
 */
public class Code03_SkipListMap {

    public static class SkipListMap<K extends Comparable<K>, V> implements SortedMap<K, V> {
        private static final double PROBABILITY = 0.5; // < 0.5 继续做，>=0.5 停
        private SkipListNode<K, V> head;
        private int size;
        private int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<>(null, null);
            // 初始化出来第一层.
            maxLevel = 0;
            head.nextNodes.add(null);
        }

        /**
         * 当前层比key小的最右节点
         * @param cur 当前的节点(可能已经跨过很多节点了)
         * @param level 在第几层从0开始
         * @param key 要比较的key
         * @return 答案
         */
        private SkipListNode<K, V> mostRightLessInLevel(SkipListNode<K, V> cur, int level, K key) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        /**
         * 整棵树比key小的最右节点
         * @param key 要比较的key
         * @return 答案
         */
        private SkipListNode<K, V> mostRightLessInTree(K key) {
            if(key == null) {
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                cur = mostRightLessInLevel(cur, level--, key);
            }
            return cur;
        }

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public boolean containsKey(K key) {
            if(key == null) {
                return false;
            }
            SkipListNode<K, V> target = mostRightLessInTree(key).nextNodes.get(0);
            return target != null && target.isKeyEqual(key);
        }

        /**
         * 新增, 或修改
         * @param key 添加的key
         * @param value value
         */
        @Override
        public void put(K key, V value) {
            if(key == null) {
                return;
            }
            // 找到整棵树最右比他小的节点, 和最右比它小的下一个节点
            SkipListNode<K, V> less = mostRightLessInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            // 更新
            if(find != null && find.isKeyEqual(key) ) {
                find.v = value;
            }else {
                // 新增
                size++;     // 整棵树大小+1
                int newNodeLevel = 0;   // 他有多少层
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                // 如果他是前所未有的高度
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                for (int i = 0; i <= newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }

                // 准备完毕, 开始添加了
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessInLevel(pre, level, key);
                    if(newNodeLevel >= level) {
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }

            }
        }

        /**
         * 删除key
         * @param key 要删除的key
         */
        @Override
        public void remove(K key) {
            if(containsKey(key)) {
                size--;
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                SkipListNode<K, V> next;
                while (level >= 0) {
                    pre = mostRightLessInLevel(pre, level, key);
                    next = pre.nextNodes.get(level);
                    // 每层都把它删掉
                    if(next != null && next.isKeyEqual(key)) {
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }

                    // 如果删掉后只剩一个head节点, 就把这层给清空, 0层除外
                    if(level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        /** 查找 */
        @Override
        public V get(K key) {
            if(key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            return find != null && find.isKeyEqual(key) ? find.v: null;
        }

        @Override
        public K firstKey() {
            SkipListNode<K, V> t = head.nextNodes.get(0);
            return t == null? null: t.k;
        }

        @Override
        public K lastKey() {
            SkipListNode<K, V> t = head;
            int level = maxLevel;
            while (level >= 0) {
                SkipListNode<K, V> next = t.nextNodes.get(level);
                while (next != null) {
                    t = next;
                    next = t.nextNodes.get(level);
                }
                level--;
            }
            return t.k;
        }

        @Override
        public K floorKey(K key) {
            if(key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.k : less.k;
        }

        @Override
        public K ceilingKey(K key) {
            if(key == null) {
                return null;
            }
            SkipListNode<K, V> next = mostRightLessInTree(key).nextNodes.get(0);
            return next == null? null: next.k;
        }


        private static class SkipListNode<K extends Comparable<K>, V> {
            public K k; // 可能为空, 头节点为空,其他不允许空值
            public V v; // 可能为空
            public ArrayList<SkipListNode<K, V>> nextNodes;

            public SkipListNode(K k, V v) {
                this.k = k;
                this.v = v;
                nextNodes = new ArrayList<>();
            }

            public boolean isKeyLess(K key) {
                return key != null && (this.k == null || k.compareTo(key) < 0);
            }

            public boolean isKeyEqual(K key) {
                return key == k || (key != null && k != null && key.compareTo(k) == 0);
            }
        }
    }

    // for test
    public static void printAll(SkipListMap<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipListMap.SkipListNode<String, String> cur = obj.head;
            while (cur.nextNodes.get(i) != null) {
                SkipListMap.SkipListNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.k + " , " + next.v + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        SkipListMap<String, String> test = new SkipListMap<>();
        printAll(test);
        System.out.println("======================");
        test.put("A", "10");
        printAll(test);
        System.out.println("======================");
        test.remove("A");
        printAll(test);
        System.out.println("======================");
        test.put("E", "E");
        test.put("B", "B");
        test.put("A", "A");
        test.put("F", "F");
        test.put("C", "C");
        test.put("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.containsKey("B"));
        System.out.println(test.containsKey("Z"));
        System.out.println(test.firstKey());
        System.out.println(test.lastKey());
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.remove("D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.floorKey("D"));
        System.out.println(test.ceilingKey("D"));


    }

}
