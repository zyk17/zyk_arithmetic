package com.zhangsan.no_5_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 一个纸条折n次，打印它的 凹 凸 样子
 * @author zhangsan
 * @date 2021/2/11 16:42
 */
public class Code07_PrintAotuByBt {

    /** 二叉树 */
    public static class Node {
        public Node left;
        public Node right;
        public String value;

        public Node(String value) { this.value = value; }
        public Node() {}
    }

    /** 对折n次，打印凹凸。实现了这个二叉树做 */
    public static Node printAoto(int n) {
        if(n < 1) { return null; }
        Node head = new Node("1凹");
        if( n == 1 ) { return head; }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int level = Integer.parseInt(cur.value.substring(0,1))+1;

            if(level == n+1) {
                break;
            }

            cur.left = new Node(level+"凹");
            cur.right = new Node(level+"凸");

            queue.add(cur.left);
            queue.add(cur.right);
        }

        return head;
    }

    public static void printAoto2(int n) {
        printAoto2(1, n, 1+"凹");
        System.out.println();
    }

    public static void printAoto2(int i, int n, String print){
        if(i > n) {
            return;
        }
        printAoto2(i+1, n, (i+1)+"凹");
        System.out.print(print+" ");
        printAoto2(i+1, n, (i+1)+"凸");
    }


    public static void in(Node head) {
        if(head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.value + " ");
        in(head.right);
    }
    public static void level(Node head) {
        if(head == null) {
            return;
        }
        System.out.println("按层遍历:");
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (! queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if(head.left != null) {
                queue.add(head.left);
            }
            if(head.right != null) {
                queue.add(head.right);
            }
        }
        System.out.println();
    }

}

class TestCode07 {
    public static void main(String[] args) {
        Code07_PrintAotuByBt.printAoto2(8);

        Code07_PrintAotuByBt.Node node = Code07_PrintAotuByBt.printAoto(8);
        Code07_PrintAotuByBt.in(node);
    }
}
