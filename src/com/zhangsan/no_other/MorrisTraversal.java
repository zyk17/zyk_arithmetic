package com.zhangsan.no_other;

/**
 *
 * morris遍历
 *
 * @author zhangsan
 * @date 2021/3/18 12:08
 */
public class MorrisTraversal {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void morris(Node head) {
        if(head == null) { return; }
        Node cur  = head;
        Node mostRight;
        while (cur != null) {
            if(cur.left == null) {
                // 无左树
                cur = cur.right;
            }else {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null) {
                    // 左树最右节点指向空第一次到
                    mostRight.right = cur;
                    cur = cur.left;
                }else /*if(mostRight.right == cur)*/ {
                    // 左树最右节点指向当前节点第二次到
                    cur = cur.right;
                    mostRight.right = null;
                }
            }
        }
    }

    // morris 序改先序
    public static void morrisPre(Node head) {
        if(head == null) { return; }
        Node cur  = head;
        Node mostRight;
        while (cur != null) {
            if(cur.left == null) {
                // 无左树
                System.out.print(cur.value + " ");
                cur = cur.right;
            }else {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null) {
                    // 左树最右节点指向空第一次到
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                }else /*if(mostRight.right == cur)*/ {
                    // 左树最右节点指向当前节点第二次到
                    cur = cur.right;
                    mostRight.right = null;
                }
            }
        }
        System.out.println();
    }
    // morris 序改中序
    public static void morrisIn(Node head) {
        if(head == null) { return; }
        Node cur  = head;
        Node mostRight;
        while (cur != null) {
            if(cur.left != null)  {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null) {
                    // 左树最右节点指向空第一次到
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else /*if(mostRight.right == cur)*/ {
                    // 左树最右节点指向当前节点第二次到
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }
    // morris 序改后序
    public static void morrisPos(Node head) {
        if(head == null) { return; }
        Node cur  = head;
        Node mostRight;
        while (cur != null) {
            if(cur.left == null) {
                // 无左树
                cur = cur.right;
            }else {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null) {
                    // 左树最右节点指向空第一次到
                    mostRight.right = cur;
                    cur = cur.left;
                }else /*if(mostRight.right == cur)*/ {
                    // 左树最右节点指向当前节点第二次到
                    mostRight.right = null;
                    reversedPrintRightBorder(cur.left);
                    cur = cur.right;
                }
            }
        }
        reversedPrintRightBorder(head);
        System.out.println();
    }

    /** 逆序打印右边界 */
    public static void reversedPrintRightBorder(Node head) {
        if(head == null) { return; }
        Node cur = head;
        Node pre = null;
        Node next;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }

        cur = pre;
        while (cur != null) {
            // 做事情
            System.out.print(cur.value + " ");
            cur = cur.right;
        }

        cur = pre;
        pre = null;
//        next;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }

    }

    // for test
    public static void main(String[] args) {
        Node d = new Node(3);
        Node e = new Node(4);
        Node f = new Node(6);
        Node g = new Node(7);
        Node b = new Node(2, d, e);
        Node c = new Node(5, f, g);
        Node a = new Node(1, b, c);
        //                a1
        //         b2           c5
        //     d3     e4     f6    g7

//        morris(a);
        morrisPre(a);
        morrisIn(a);
        morrisPos(a);
        morrisPre(a);

//        reverse(a);
//        System.out.println();
//        morrisPre(a);
    }

}
