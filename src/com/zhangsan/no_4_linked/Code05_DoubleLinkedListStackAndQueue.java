package com.zhangsan.no_4_linked;

/**
 * @author zhangsan
 * @date 2021/2/2 17:35
 */
public class Code05_DoubleLinkedListStackAndQueue {
    public Node head;
    public Node last;

    public void add(String value) {
        Node node = new Node(value, last, null);
        if (last == null) {
            head = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
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
        if (cur != null) {
            cur.pre = null;
            while (cur != null) {
                if (cur.data.equals(value)) {
                    pre.next = cur.next;
                    if (cur.next == null) {
                        break;
                    }
                    cur.next.pre = pre;
                } else {
                    pre = cur;
                }
                cur = cur.next;
            }
        }
    }

    public void delLast() {
        if(last != null) {
            // 只有一个，或多个
            if(last.pre != null) {
                last.pre.next = null;
            }
            last = last.pre;
        }
    }

    public void delFirst() {
        if(head != null) {
            head = head.next;
            head.pre = null;
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

    public void reversePrint() {
        System.out.print("[");
        Node cur = last;
        while (cur != null) {
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
                    "next=" + ((next == null) ? "null" : next.data) +
                    ", pre=" + ((pre == null) ? "null" : pre.data) +
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

        while (head != null) {
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

class TestCode05_DoubleLinkedListStackAndQueue {
    public static void main(String[] args) {
        Code05_DoubleLinkedListStackAndQueue reverseList = new Code05_DoubleLinkedListStackAndQueue();
        // 先进后出，栈
        /*reverseList.add("a");
        reverseList.add("b");
        reverseList.delLast();
        reverseList.add("c");
        reverseList.add("d");
        reverseList.add("e");

        reverseList.print();
        reverseList.delLast();
        reverseList.delLast();
        reverseList.print();*/
        // 先进先出，队列
        reverseList.add("a");
        reverseList.add("b");
        reverseList.delFirst();
        reverseList.add("c");
        reverseList.add("d");
        reverseList.add("e");

        reverseList.print();
        reverseList.delFirst();
        reverseList.delFirst();
        reverseList.print();

    }
}
