package com.zhangsan.no_4_linked;

/**
 * 单向链表删除元素
 * @author zhangsan
 * @date 2021/2/2 16:58
 */
public class Code03_DelElementLinkedList {
    public Node head;
    public Node last;

    public void add(String value) {
        Node node = new Node(value);
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

    public void del(String value) {
        // 考虑全是要删除
        while (head != null) {
            if (!head.data.equals(value)) {
                break;
            }
            head = head.next;
        }
        // 此时 head可能指向一个node也可能为空
        Node cur = head;
        Node pre = head;
        while (cur != null) {
            if( cur.data.equals(value) ) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
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


    /**
     * 反转单链表
     */
    public void reverseLinkedList() {
        // a -> b -> c -> null
        // c -> b -> a -> null
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            // next=b     a->null   pre=a   head=b
            // next=c     b->a      pre=b   head=c
            // next=null  c->b      pre=c   head=null
        }
        this.head = pre;
    }


}

class TestCode03_DelElementLinkedList {
    public static void main(String[] args) {
        Code03_DelElementLinkedList reverseList = new Code03_DelElementLinkedList();
        reverseList.add("a");
        reverseList.add("a");
        reverseList.add("c");
        reverseList.add("d");
        reverseList.add("c");

        reverseList.print();
        reverseList.del("c");
        reverseList.print();


    }
}
