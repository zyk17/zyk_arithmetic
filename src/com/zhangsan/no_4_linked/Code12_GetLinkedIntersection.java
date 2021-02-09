package com.zhangsan.no_4_linked;

import java.util.HashSet;

/**
 * 两个单链表找第一个相交点
 * 单链表可能为环形
 * @author zhangsan
 * @date 2021/2/9 17:01
 */
public class Code12_GetLinkedIntersection {

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
     * 专门打印环形
     */
    public void loopPrint(Node node) {
        System.out.print("[");
        HashSet<Node> set = new HashSet<>();
        while (node != null) {
            if(set.contains(node)) {
                break;
            }
            set.add(node);
            System.out.print(node + ", ");
            node = node.next;
        }
        System.out.print("]\n");
    }

    /**
     * 如果是环形返回第一个是环形的地方，如果不是环形返回空
     */
    public Node getLoopNode(Node head) {
        // 快走两个，慢走一个
        // 快到null，没有环形，返回null
        // 快和慢 相遇 ，快重新走，再次相遇，就是第一个入环点
        Node fastNode = head, slowNode = head;
        boolean isFirst = true; // 是否第一次相遇
        while (true) {
            try {
                fastNode = isFirst ? fastNode.next.next : fastNode.next;
            } catch (Exception e) {
                return null;
            }
            slowNode = slowNode.next;
            if (fastNode == slowNode && (!isFirst)) {
                return fastNode;
            }
            if (fastNode == slowNode && isFirst) {
                isFirst = false;
                fastNode = head;
            }
        }
    }

    public Node getLoopNode2(Node head) {
        HashSet<Node> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }

    /**
     * 获取两个链表第一个相遇的地方,没有返回空
     */
    public Node getFirstNodeAtTwoLinked(Node first, Node second) {
        Node loop1 = getLoopNode(first);
        Node loop2 = getLoopNode(second);
        // 三种情况
        // 1：两个根本不相交
        // 2：第一个环形点相交，找到第一个相交点
        // 3：两个在一个环形上，但相交点不一样
        // 情况2：环形点 都为空或相等，找相交点
        if(loop1 == loop2) {
            // 如果没有环，直接用没有环的做法算
            if(loop1 == null){
                return getFirstNodeAtTwoLinkedIsNoLoop(first, second);
            }
            // 两个环形地方相交，去掉环找第一个相交的地方
            Node next = loop1.next;
            loop1.next = null;
            loop2.next = null;
            Node r = getFirstNodeAtTwoLinkedIsNoLoop(first, second);
            // 再把人的环给还回去
            loop1.next = next;
            loop2.next = next;
            return r;
        } else {
            // 进这个有三种情况， loop1 = null && loop2 != null  loop1 != null && loop2 = null  （ (loop1 != null && loop2 != null) && (loop1 != loop2)）
            // 前两种情况直接返回null
            // 第三种情况又出现两种情况
            // 1：两个根本不相交
            // 2：两个在一个环形上，但相交点不一样
            try {
                Node t = loop1.next;
                Node c = loop2.next;    // 尝试让他出异常，在异常里统一解决
                while ( t != null ) {
                    if(t == loop1) {
                        break;
                    }
                    if(t == loop2) {
                        // 找到了，相交点，这个环形哪儿都可以
                        return t;
                    }
                    t = t.next;
                }
                // 循环结束了，还没有相遇，那就是没有相交点
            }catch (Exception e) {
                // loop1 == null || loop2 == null直接返回空，这种情况不存在
                return null;
            }
        }
        return null;
    }

    /**
     * 两个链表都没有环的情况。
     */
    public Node getFirstNodeAtTwoLinkedIsNoLoop(Node first, Node second) {
        Node cur1 = first, cur2 = second;
        int n = 0;
        while (cur1.next != null) {
            cur1 = cur1.next;
            n++;
        }
        while (cur2.next != null) {
            cur2 = cur2.next;
            n--;
        }
        if (cur1 != cur2) {
            return null;
        }

        cur1 = n > 0 ? first : second;
        cur2 = cur1 == first? second: first;
        // cur1 为长链表，cur2为锻炼表,或者它两个相等
        n = Math.abs(n);
        while ( n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /** 利用set容器找 */
    public Node getFirstNodeAtTwoLinked2(Node first, Node second) {
        HashSet<Node> set = new HashSet<>();
        HashSet<Node> set2 = new HashSet<>();
        while (first != null) {
            if (set.contains(first)) {
                break;
            }
            set.add(first);
            first = first.next;
        }
        while (second != null) {
            if (set.contains(second)) {
                return second;
            }
            if (set2.contains(second)) {
                return null;
            }
            set2.add(second);
            second = second.next;
        }
        return null;
    }

    /**
     * 单链表
     */
    public static class Node {
        public Node next;
        public int data;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data='" + data + '\'' +
                    ",next=" + ((next == null) ? "null" : next.data) +
                    '}';
        }
    }

}


class TestGetLinkedIntersection {
    public static void main(String[] args) {
        Code12_GetLinkedIntersection linked = new Code12_GetLinkedIntersection();
        Code12_GetLinkedIntersection.Node a = new Code12_GetLinkedIntersection.Node(1);
        Code12_GetLinkedIntersection.Node b = new Code12_GetLinkedIntersection.Node(2);
        Code12_GetLinkedIntersection.Node c = new Code12_GetLinkedIntersection.Node(3);
        Code12_GetLinkedIntersection.Node d = new Code12_GetLinkedIntersection.Node(4);
        Code12_GetLinkedIntersection.Node e = new Code12_GetLinkedIntersection.Node(5);
        Code12_GetLinkedIntersection.Node f = new Code12_GetLinkedIntersection.Node(6);

        Code12_GetLinkedIntersection.Node g = new Code12_GetLinkedIntersection.Node(7);
        Code12_GetLinkedIntersection.Node h = new Code12_GetLinkedIntersection.Node(8);
        Code12_GetLinkedIntersection.Node i = new Code12_GetLinkedIntersection.Node(9);
        Code12_GetLinkedIntersection.Node j = new Code12_GetLinkedIntersection.Node(10);
        a.next = b; b.next = c; c.next = d; d.next = e; e.next = f;
        f.next = c;
        g.next = h; h.next = i; i.next = j; // j.next = h;
//        j.next = b;
//        System.out.println(linked.getLoopNode(a));
//        System.out.println(linked.getLoopNode2(a));
        System.out.println(linked.getFirstNodeAtTwoLinked2(a, g));
//        System.out.println(linked.getFirstNodeAtTwoLinkedIsNoLoop(a, g));
        System.out.println(linked.getFirstNodeAtTwoLinked(a, g));

        linked.loopPrint(a);
        linked.loopPrint(g);
    }
}
