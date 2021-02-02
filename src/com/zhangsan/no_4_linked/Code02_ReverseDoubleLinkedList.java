package com.zhangsan.no_4_linked;

/**
 * @author zhangsan
 * @date 2021/2/2 17:35
 */
public class Code02_ReverseDoubleLinkedList {
    public Node head;
    public Node last;

    public void add(String value) {
        Node node = new Node(value, last, null);
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
    public void reversePrint() {
        System.out.print("[");
        Node cur = last;
        while (cur != null){
            System.out.print(cur + ", ");
            cur = cur.pre;
        }
        System.out.print("]\n");
    }

    /**
     * 单链表
     */
    public class Node {
        public Node next;
        public Node pre;
        private String data;
        public Node(String data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + ((next==null)?"null": next.data) +
                    ", pre=" + ((pre==null)?"null": pre.data) +
                    ", data='" + data + '\'' +
                    '}';
        }
    }

    /**
     * 反转单链表
     */
    public void reverseLinkedList() {
        // 	    a -> b -> c -> null
        //  null  <-  <-
        // 反转：
        //	    c -> b -> a -> null
        //  null  <-  <-
        Node next = null;
        Node pre = null;
        this.last = this.head;

        while ( head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
            // next=b     a->null   b<-a  pre=a   head=b
            // next=c     b->a      c<-b  pre=b   head=c
            // next=null  c->b      pre=c   head=null
        }
        this.head = pre;
    }


}

class TestCode02_ReverseDoubleLinkedList {
    public static void main(String[] args) {
        Code02_ReverseDoubleLinkedList reverseList = new Code02_ReverseDoubleLinkedList();
        reverseList.add("a");
        reverseList.add("b");
        reverseList.add("c");

        reverseList.print();
        reverseList.reversePrint();

        reverseList.reverseLinkedList();
        System.out.println("反转后===========");

        reverseList.print();
        reverseList.reversePrint();

    }
}
