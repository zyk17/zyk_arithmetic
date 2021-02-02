package com.zhangsan.no_4_linked;

/**
 * @author zhangsan
 * @date 2021/2/2 16:58
 */
public class Code01_ReverseLinkedList {
    public Node head;
    public Node last;

    public void add(String value) {
        Node node = new Node(value);
        if(last == null) {
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
        while (cur != null){
            System.out.print(cur + ", ");
            cur = cur.next;
        }
        System.out.print("]\n");
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
                    "next=" + ((next==null)?"null":next.data) +
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
        while ( head != null) {
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

class TestCode01_ReverseLinkedList {
    public static void main(String[] args) {
        Code01_ReverseLinkedList reverseList = new Code01_ReverseLinkedList();
        reverseList.add("a");
        reverseList.add("b");
        reverseList.add("c");

        reverseList.print();
        reverseList.reverseLinkedList();
        reverseList.print();

    }
}
