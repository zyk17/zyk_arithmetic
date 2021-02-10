package com.zhangsan.no_5_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhangsan
 * @date 2021/2/9 19:57
 */
public class Code02_RecursiveTraversalBT {

    public static void pre(Node head) {
        if(head == null) {
            return;
        }
        System.out.print(head.data + " ");
        pre(head.left);
        pre(head.right);
    }

    public static void pre1(Node head) {
        if(head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.data + " ");
            if( head.right != null ) {
                stack.push(head.right);
            }
            if(head.left != null) {
                stack.push(head.left);
            }
        }
        System.out.println();
    }

    public static void in(Node head) {
        if(head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.data + " ");
        in(head.right);
    }

    public static void in1(Node cur) {
        if(cur == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while ( !stack.isEmpty() || cur != null ) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                System.out.print(cur.data + " ");
                cur =cur.right;
            }
        }
        System.out.println();
    }

    public static void pos(Node head) {
        if(head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.data + " ");
    }

    public static void pos1(Node head) {
        if(head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack1 = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            stack1.push(head);
            if(head.left != null) {
                stack.push(head.left);
            }
            if( head.right != null ) {
                stack.push(head.right);
            }
        }
        while (!stack1.isEmpty()) {
            System.out.print(stack1.pop().data + " ");
        }
        System.out.println();
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
            System.out.print(head.data + " ");
            if(head.left != null) {
                queue.add(head.left);
            }
            if(head.right != null) {
                queue.add(head.right);
            }
        }
        System.out.println();
    }

    public static class Node {
        String data;
        Node left;
        Node right;
        public Node(String value) {
            this.data = value;
        }
    }

}

class TestRecursiveTraversalBT {
    public static void main(String[] args) {
        Code02_RecursiveTraversalBT.Node a = new Code02_RecursiveTraversalBT.Node("a");
        Code02_RecursiveTraversalBT.Node b = new Code02_RecursiveTraversalBT.Node("b");
        Code02_RecursiveTraversalBT.Node c = new Code02_RecursiveTraversalBT.Node("c");
        Code02_RecursiveTraversalBT.Node d = new Code02_RecursiveTraversalBT.Node("d");
        Code02_RecursiveTraversalBT.Node e = new Code02_RecursiveTraversalBT.Node("e");
        Code02_RecursiveTraversalBT.Node f = new Code02_RecursiveTraversalBT.Node("f");
        Code02_RecursiveTraversalBT.Node g = new Code02_RecursiveTraversalBT.Node("g");
        a.left = b;
        a.right = c;

        b.left = d;
        b.right = e;

        c.left = f;
        c.right = g;


        Code02_RecursiveTraversalBT.pre(a);
        System.out.println();
        Code02_RecursiveTraversalBT.pre1(a);

        Code02_RecursiveTraversalBT.in1(a);
        Code02_RecursiveTraversalBT.in(a);
        System.out.println();

        Code02_RecursiveTraversalBT.pos1(a);
        Code02_RecursiveTraversalBT.pos(a);
        System.out.println();


        Code02_RecursiveTraversalBT.level(a);

    }
}
