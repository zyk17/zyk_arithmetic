package com.zhangsan.no_4_linked;

import java.util.Stack;

/**
 * 单链表获取中点位置, 是否是回文链表
 *
 * @author zhangsan
 * @date 2021/2/9 13:22
 */
public class Code09_LinkedGetMid {
    public Node head;
    public Node last;
    public int length;

    public void add(String value) {
        Node node = new Node(value);
        if (last == null) {
            head = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        length++;
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

    /**
     * 奇数长度返回中点， 偶数长度返回上中点
     */
    public int getMid1() {
        // 快慢指针, 快慢节点
        int qp = 0, sp = 0;
        Node qn = head, sn = head;
        while (qn != null && qn.next != null) {
            qn = qn.next.next;
            qp += 2;
            sp++;
        }
        return qn==null? sp-1: sp;
    }

    public int getMid1A() {  return length % 2 == 0 ? length / 2 - 1 : length / 2;  }

    /**
     * 奇数长度返回中点， 偶数长度返回下中点
     */
    public int getMid2() {
        // 快慢指针, 快慢节点
        int qp = 0, sp = 0;
        Node qn = head, sn = head;
        while (qn != null && qn.next != null) {
            qn = qn.next.next;
            qp += 2;
            sp++;
        }
        return sp;
    }
    public int getMid2A() { return length / 2; }

    /**
     * 奇数长度返回中点前一个， 偶数长度返回上中点前一个
     */
    public int getMid3() {
        // 快慢指针, 快慢节点
        int qp = 0, sp = 0;
        Node qn = head, sn = head;
        while (qn != null && qn.next != null) {
            qn = qn.next.next;
            qp += 2;
            sp++;
        }
        return qn == null? sp-2: sp-1;
    }
    public int getMid3A() { return length% 2 == 0? length/2-2: length / 2 - 1; }

    /**
     * 奇数长度返回中点前一个， 偶数长度返回下中点前一个，也就是上中点
     */
    public int getMid4() {
        // 快慢指针, 快慢节点
        int qp = 0, sp = 0;
        Node qn = head, sn = head;
        while (qn != null && qn.next != null) {
            qn = qn.next.next;
            qp += 2;
            sp++;
        }
        return sp-1;
    }
    public int getMid4A() { return length / 2 - 1; }

    /**
     * 是否回文链表
     */
    public boolean isPlalindrome() {
        int mid = getMid1();
        Node midNode = head;
        for (int i = 0; i < mid; i++) {
            midNode = midNode.next;
        }
        Node node = reverseLinkedList(midNode);
        boolean success = true;
        Node pros = head, cons = node;
        while (cons!= null && cons.next != null) {
            if(! cons.data.equals(pros.data) ) {
                success = false;
                break;
            }
            pros = pros.next; cons = cons.next;
        }
        node = reverseLinkedList(node);
        Node midPre = head;
        for (int i = 0; i < mid-1; i++) {
            midPre = head.next;
        }
        midPre.next = node;
        return success;
    }
    public boolean isPlalindromeA() {
        Stack<Node> nodeStack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            nodeStack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if( ! cur.data.equals(nodeStack.pop().data)) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 反转单链表
     */
    public Node reverseLinkedList(Node head) {
        // a -> b -> c -> null
        // c -> b -> a -> null
        Node next = null;
        Node pre = null;
        while ( head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            // next=b     a->null   pre=a   head=b
            // next=c     b->a      pre=b   head=c
            // next=null  c->b      pre=c   head=null
        }
        return pre;
    }

    /**
     * 单链表
     */
    public class Node {
        public Node next;
        private String data;

        public Node(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + ((next == null) ? "null" : next.data) +
                    ", data='" + data + '\'' +
                    '}';
        }
    }

}

class TestLinkedGetMid {
    public static void main(String[] args) {
        Code09_LinkedGetMid linked = new Code09_LinkedGetMid();
        // 测试找寻mid
        /*
        {
            linked.add("a");
            linked.add("b");
            linked.add("c");
            linked.add("d");
            System.out.println("奇数长度返回中点， 偶数长度返回上中点");

            System.out.println(linked.length + "个:" + linked.getMid1());
            System.out.println(linked.length + "个:" + linked.getMid1A());
            linked.add("e");
            System.out.println(linked.length + "个:" + linked.getMid1());
            System.out.println(linked.length + "个:" + linked.getMid1A());

            System.out.println("奇数长度返回中点， 偶数长度返回下中点");

            System.out.println(linked.length + "个:" + linked.getMid2());
            System.out.println(linked.length + "个:" + linked.getMid2A());
            linked.add("e");
            System.out.println(linked.length + "个:" + linked.getMid2());
            System.out.println(linked.length + "个:" + linked.getMid2A());

            System.out.println("奇数长度返回中点前一个， 偶数长度返回上中点前一个");

            System.out.println(linked.length + "个:" + linked.getMid3());
            System.out.println(linked.length + "个:" + linked.getMid3A());
            linked.add("e");
            System.out.println(linked.length + "个:" + linked.getMid3());
            System.out.println(linked.length + "个:" + linked.getMid3A());

            System.out.println("奇数长度返回中点前一个， 偶数长度返回下中点前一个，也就是上中点");

            System.out.println(linked.length + "个:" + linked.getMid4());
            System.out.println(linked.length + "个:" + linked.getMid4A());
            linked.add("e");
            System.out.println(linked.length + "个:" + linked.getMid4());
            System.out.println(linked.length + "个:" + linked.getMid4A());
        }
        */
        // 测试是否回文
        linked.add("a");
        linked.add("b");
//        linked.add("c");
        linked.add("b");
        linked.add("a");
        linked.print();
        System.out.println(linked.isPlalindromeA());
        linked.print();
        System.out.println(linked.isPlalindrome());
        linked.print();
    }
}
