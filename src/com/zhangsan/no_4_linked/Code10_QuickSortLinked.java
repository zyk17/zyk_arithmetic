package com.zhangsan.no_4_linked;

/**
 * 快速排序 单链表
 *
 * @author zhangsan
 * @date 2021/2/9 15:04
 */
public class Code10_QuickSortLinked {
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

    public void print() {
        System.out.print("[");
        Node cur = head;
        while (cur != null) {
            System.out.print(cur + ", ");
            cur = cur.next;
        }
        System.out.print("]\n");
    }

    public void partition(int value) {
        Node smallHead = null, smallTail = null, midHead = null, midTail = null, bigHead = null, bigTail = null;

        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            if (cur.data == value) {
                if (midHead == null) {
                    midHead = cur;  // 第一次头尾赋值
                    midTail = cur;
                } else {
                    midTail.next = cur; // 第二次，尾巴和头下一个都变了，然后又变了尾巴，再进来就只变尾巴了
                    midTail = cur;
                }
            } else if (cur.data < value) {
                if (smallHead == null) {
                    smallHead = cur;
                    smallTail = cur;
                } else {
                    smallTail.next = cur;
                    smallTail = cur;
                }
            } else {
                if (bigHead == null) {
                    bigHead = cur;
                    bigTail = cur;
                } else {
                    bigTail.next = cur;
                    bigTail = cur;
                }
            }
            cur = next;
        }
//        if(smallTail!= null) { smallTail.next = null; }
//        if(midTail!= null) { midTail.next = null; }
//        if(bigTail!= null) { bigTail.next = null; }

        if (midHead != null) {
            midTail.next = bigHead;
        }
        if (smallHead != null) {
            smallTail.next = midHead == null ? bigHead : midHead;
        }
        if (smallHead == null) {
            if (midHead == null) {
                head = bigHead;
            } else {
                head = midHead;
            }
        } else {
            head = smallHead;
        }

    }

    /**
     * 单链表
     */
    public class Node {
        public Node next;
        public int data;

        public Node(int data) {
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

class TestQuickSortLinked {
    public static void main(String[] args) {
        Code10_QuickSortLinked linked = new Code10_QuickSortLinked();
        linked.add(3);
        linked.add(5);
        linked.add(2);
        linked.add(6);
        linked.add(1);
        linked.add(3);
        linked.add(3);
        linked.add(7);
        linked.add(1);
        linked.print();

        linked.partition(3);
        linked.print();

    }
}
