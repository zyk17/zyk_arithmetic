package com.zhangsan.no_7_unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集
 * @author zhangsan
 * @date 2021/2/16 17:24
 */
public class Code01_UnionFind {

    public static class Node<V> {
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {

        public Map<V, Node<V>> nodes = new HashMap<>();
        public Map<Node<V>, Node<V>> parents = new HashMap<>();
        public Map<Node<V>, Integer> sizeMap = new HashMap<>();

        public UnionSet(List<V> list) {
            init(list);
        }

        private void init(List<V> list) {
            Node<V> cur = null;
            for (V v : list) {
                cur = new Node<>(v);
                nodes.put(v, cur);
                parents.put(cur, cur);
                sizeMap.put(cur, 1);
            }
        }

        /** 找到头节点 */
        public Node<V> findHead(Node<V> v) {
            // 优化：把寻找过程中的节点的头节点指向head
            Stack<Node<V>> stack = new Stack<>();
            while ( v != parents.get(v) ) {
                stack.push(v);
                v = parents.get(v);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), v);
            }
            return v;
        }

        /** 是否同一组 */
        public boolean isSameSet(V a, V b) {
            return findHead(nodes.get(a)) == findHead(nodes.get(b)) ;
        }

        /** 连接这两个所在的集合为一组 */
        public void union(V a, V b) {
            Node<V> aHead = findHead(nodes.get(a));
            Node<V> bHead = findHead(nodes.get(b));
            if(aHead != bHead) {
                int nodeASize = sizeMap.get(aHead);
                int nodeBSize = sizeMap.get(bHead);
                Node<V> big, small;
                if(nodeASize >= nodeBSize) {
                    big = aHead; small = bHead;
                }else {
                    big = bHead; small = aHead;
                }
                parents.put(small, big);
                sizeMap.put(big, nodeASize + nodeBSize);
                sizeMap.remove(small);
            }
        }
    }

}
