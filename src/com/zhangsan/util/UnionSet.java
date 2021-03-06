package com.zhangsan.util;

import java.util.*;

public class UnionSet<V> {

    private static class Node<V> {
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    private Map<V, Node<V>> nodes = new HashMap<>();
    private Map<Node<V>, Node<V>> parents = new HashMap<>();
    private Map<Node<V>, Integer> sizeMap = new HashMap<>();

    public UnionSet(Collection<V> list) {
        init(list);
    }

    private void init(Collection<V> set) {
        Node<V> cur = null;
        for (V v : set) {
            cur = new Node<>(v);
            nodes.put(v, cur);
            parents.put(cur, cur);
            sizeMap.put(cur, 1);
        }
    }

    /**
     * 找到头节点
     */
    private Node<V> findHead(Node<V> v) {
        // 优化：把寻找过程中的节点的头节点指向head
        Stack<Node<V>> stack = new Stack<>();
        while (v != parents.get(v)) {
            stack.push(v);
            v = parents.get(v);
        }
        while (!stack.isEmpty()) {
            parents.put(stack.pop(), v);
        }
        return v;
    }

    /**
     * 是否同一组
     */
    public boolean isSameSet(V a, V b) {
        return findHead(nodes.get(a)) == findHead(nodes.get(b));
    }

    /**
     * 连接这两个所在的集合为一组
     */
    public void union(V a, V b) {
        Node<V> aHead = findHead(nodes.get(a));
        Node<V> bHead = findHead(nodes.get(b));
        if (aHead != bHead) {
            int nodeASize = sizeMap.get(aHead);
            int nodeBSize = sizeMap.get(bHead);
            Node<V> big, small;
            if (nodeASize >= nodeBSize) {
                big = aHead;
                small = bHead;
            } else {
                big = bHead;
                small = aHead;
            }
            parents.put(small, big);
            sizeMap.put(big, nodeASize + nodeBSize);
            sizeMap.remove(small);
        }
    }

    public int sets() {
        return sizeMap.size();
    }

}
