package com.zhangsan.no_4_linked;

import java.util.HashMap;

/**
 * node有两个指针，next和random随机指针
 * copy这个结构。
 *
 * @author zhangsan
 * @date 2021/2/9 16:01
 */
public class Code11_CopyLinkedWithRandom {

    public Node head;
    public Node last;

    public void add(int value) {
        Node node = new Node(value);
        if (last == null) {
            head = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public void add(Node node) {
        if (last == null) {
            head = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public void print() {
        System.out.print("[");
        Node cur = head;
        while (cur != null) {
            System.out.print(cur + ", ");
            cur = cur.next;
        }
        System.out.print("]\n");
    }

    public void print(Node node) {
        System.out.print("[");
        while (node != null) {
            System.out.print(node + ", ");
            node = node.next;
        }
        System.out.print("]\n");
    }

    /**
     * 使用容器copy
     */
    public Node copyLinkedWithRandom1() {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.data));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            Node target = map.get(cur);
            target.next = map.get(cur.next);
            target.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 不用容器，空间复杂度O(1)
     */
    public Node copyLinkedWithRandom2() {
        // 复制的和原有的串成一个大链
        Node cur  = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.data);
            cur.next.next = next;
            cur = next;
        }
        // 设置复制的元素的random
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 分割
        cur = head;
        Node r = cur.next;
        print();
        while (cur != null) {
            Node target = cur.next;

            Node next = cur.next.next;
            Node targetNext = target.next==null? null : target.next.next;

            cur.next = next;
            target.next = targetNext;

            cur = next;
        }
        return r;
    }


    /**
     * 单链表
     */
    public static class Node {
        public Node next;
        public Node random;
        public int data;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data='" + data + '\'' +
                    ",next=" + ((next == null) ? "null" : next.data) +
                    ",random=" + ((random == null) ? "null" : random.data) +
                    '}';
        }
    }

}

class TestCopyLinkedWithRandom {
    public static void main(String[] args) {
        Code11_CopyLinkedWithRandom linked = new Code11_CopyLinkedWithRandom();
        Code11_CopyLinkedWithRandom.Node a1 = new Code11_CopyLinkedWithRandom.Node(1);
        Code11_CopyLinkedWithRandom.Node b1 = new Code11_CopyLinkedWithRandom.Node(2);
        Code11_CopyLinkedWithRandom.Node c1 = new Code11_CopyLinkedWithRandom.Node(3);
        a1.random = c1;
        b1.random = a1;
        c1.random = c1;
        linked.add(a1);
        linked.add(b1);
        linked.add(c1);

        linked.print();
//        Code11_CopyLinkedWithRandom.Node node = linked.copyLinkedWithRandom1();
//        linked.print(node);

        Code11_CopyLinkedWithRandom.Node node = linked.copyLinkedWithRandom2();
        linked.print(node);

        // 证明这两个结果已经完全不管了
        /*
        node.next = node.next.next;
        linked.print();
        linked.print(node);
        */
    }
}
