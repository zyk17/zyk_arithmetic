package com.zhangsan.no_5_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 是否完全二叉树
 *
 * @author zhangsan
 * @date 2021/2/11 18:38
 */
public class Code08_IsFBT {

    /** 二叉树 */
    public static class Node {
        public Node left;
        public Node right;
        public String value;
        public Node(String value) { this.value = value; }
        public Node() {}
    }


    public static boolean isFBT(Node head) {
        if(head == null) {
            return true;
        }
        Node cur = head;
        while (cur.left != null && cur.right != null) {

        }
        System.out.println("按层遍历:");
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        Node left = null;
        Node right = null;
        boolean leaf = false;
        while (! queue.isEmpty()) {
            head = queue.poll();
            left = head.left;
            right = head.right;
            if( (left==null && right!=null) || (leaf && (left!= null || right!= null)) ){
                return false;
            }
            if(left != null) { queue.add(left); }
            if(right != null) { queue.add(right); }
            if(left == null || right == null) { leaf = true; }
        }
        return true;
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

class TestCode08 {
    public static void main(String[] args) {

    }
}
