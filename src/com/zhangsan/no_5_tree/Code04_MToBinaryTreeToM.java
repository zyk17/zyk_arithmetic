package com.zhangsan.no_5_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 多叉树转成，二叉树
 * 二叉树再转回 多叉树
 *
 * @author zhangsan
 * @date 2021/2/10 17:59
 */
public class Code04_MToBinaryTreeToM {

    /**
     * 二叉树
     */
    public static class BinaryTree {
        String data;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(String value) {
            this.data = value;
        }
    }

    /**
     * 多叉树
     */
    public static class Node {
        String data;
        List<Node> children;

        public Node(String value) {
            this.data = value;
            children = new ArrayList<>();
        }

        public Node(String value, List<Node> children) {
            this.data = value;
            this.children = children;
        }
    }

    /**
     * 多叉树转成二叉树
     */
    public static BinaryTree encode(Node head) {
        if (head == null) {
            return null;
        }
        BinaryTree bt = new BinaryTree(head.data);
        en(bt, head.children);
        return bt;
    }

    public static void en(BinaryTree bt, List<Node> children) {
        if (children == null || children.size() == 0) {
            return;
        }
        bt.left = new BinaryTree(children.get(0).data);
        en(bt.left, children.get(0).children);
        for (int i = 1; i < children.size(); i++) {
            BinaryTree cur = bt.left;
            while (cur.right != null) {
                cur = cur.right;
            }
            cur.right = new BinaryTree(children.get(i).data);
            en(cur.right, children.get(i).children);
        }
    }

    /**
     * 二叉树转会多叉树
     */
    public static Node decode(BinaryTree bt) {
        if (bt == null) {
            return null;
        }
        return new Node(bt.data, de(bt));
    }

    public static List<Node> de(BinaryTree head) {
        if (head.left == null) {
            return null;
        }
        BinaryTree bt = head.left;
        ArrayList<Node> children = new ArrayList<>();
        while (bt != null) {
            Node target = new Node(bt.data, de(bt));
            children.add(target);
            bt = bt.right;
        }
        return children;
    }


    /**
     * 按层多叉树
     */
    public static void printNode(Node head) {
        if (head == null) {
            return;
        }
        System.out.println("按层打印多叉树:");
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.data + " ");
            if (head.children != null) {
                queue.addAll(head.children);
            }
        }
        System.out.println();
    }

    /**
     * 按层二叉树
     */
    public static void level(BinaryTree head) {
        if (head == null) {
            return;
        }
        System.out.println("按层打印二叉树:");
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.data + " ");
            if (head.left != null) {
                queue.add(head.left);
            }
            if (head.right != null) {
                queue.add(head.right);
            }
        }
        System.out.println();
    }

}

class TestCode04 {
    public static void main(String[] args) {
        Code04_MToBinaryTreeToM.Node a = new Code04_MToBinaryTreeToM.Node("a");
        Code04_MToBinaryTreeToM.Node b = new Code04_MToBinaryTreeToM.Node("b");
        Code04_MToBinaryTreeToM.Node c = new Code04_MToBinaryTreeToM.Node("c");
        Code04_MToBinaryTreeToM.Node d = new Code04_MToBinaryTreeToM.Node("d");
        Code04_MToBinaryTreeToM.Node e = new Code04_MToBinaryTreeToM.Node("e");
        Code04_MToBinaryTreeToM.Node f = new Code04_MToBinaryTreeToM.Node("f");
        Code04_MToBinaryTreeToM.Node g = new Code04_MToBinaryTreeToM.Node("g");
        Code04_MToBinaryTreeToM.Node h = new Code04_MToBinaryTreeToM.Node("h");
        Code04_MToBinaryTreeToM.Node i = new Code04_MToBinaryTreeToM.Node("i");
        Code04_MToBinaryTreeToM.Node j = new Code04_MToBinaryTreeToM.Node("j");
        Code04_MToBinaryTreeToM.Node k = new Code04_MToBinaryTreeToM.Node("k");
        Code04_MToBinaryTreeToM.Node l = new Code04_MToBinaryTreeToM.Node("l");
        Code04_MToBinaryTreeToM.Node m = new Code04_MToBinaryTreeToM.Node("m");
        Code04_MToBinaryTreeToM.Node n = new Code04_MToBinaryTreeToM.Node("n");

        a.children.add(b);
        a.children.add(c);
        a.children.add(d);
        b.children.add(e);
        b.children.add(f);
        b.children.add(g);
        c.children.add(h);
        d.children.add(i);
        d.children.add(j);
        d.children.add(k);
        h.children.add(l);
        h.children.add(m);
        h.children.add(n);

        Code04_MToBinaryTreeToM.printNode(a);

        Code04_MToBinaryTreeToM.BinaryTree binaryTree = Code04_MToBinaryTreeToM.encode(a);
        Code04_MToBinaryTreeToM.level(binaryTree);

        Code04_MToBinaryTreeToM.Node decode = Code04_MToBinaryTreeToM.decode(binaryTree);
        Code04_MToBinaryTreeToM.printNode(decode);


    }
}
