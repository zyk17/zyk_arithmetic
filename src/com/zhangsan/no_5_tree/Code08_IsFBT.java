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

    public static class Info {
        public int height;
        public boolean isCBT;     // 是否完全二叉树
        public boolean isFBT;     // 是否满二叉树

        public Info(int height, boolean isCBT, boolean isFBT) {
            this.height = height;
            this.isCBT = isCBT;
            this.isFBT = isFBT;
        }
    }

    public static boolean isFBT2(Node head) {
        return process(head).isCBT;
    }

    public static Info process(Node x) {
        if(x == null) {
            return new Info(0, true, true);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height)+1;
        boolean isFBT = false;
        boolean isCBT = false;
        if(leftInfo.isFBT && rightInfo.isFBT && leftInfo.height == rightInfo.height) {
            isCBT = true;
            isFBT = true;
        } else if(leftInfo.isCBT && rightInfo.isFBT && leftInfo.height == rightInfo.height+1){
            isCBT = true;
        } else if(leftInfo.isFBT && rightInfo.isFBT && leftInfo.height == rightInfo.height+1){
            isCBT = true;
        } else if(leftInfo.isFBT && rightInfo.isCBT && leftInfo.height == rightInfo.height){
            isCBT = true;
        }

        return new Info(height, isCBT, isFBT);
    }

    public static void level(Node head) {
        if(head == null) {
            return;
        }
        System.out.println("按层遍历:");
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        Node curEnd = head;
        Node nextEnd = null;
        Node cur = null;
        while (! queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(head.value + " ");
            if(cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if(cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            if(cur == curEnd) {
                curEnd = nextEnd;
                System.out.println();
            }
        }
        System.out.println();
    }

}

class TestCode08 {
    public static void main(String[] args) {
        /*Code08_IsFBT.Node a = new Code08_IsFBT.Node("a");
        Code08_IsFBT.Node b = new Code08_IsFBT.Node("b");
        Code08_IsFBT.Node c = new Code08_IsFBT.Node("c");
        Code08_IsFBT.Node d = new Code08_IsFBT.Node("d");
        Code08_IsFBT.Node e = new Code08_IsFBT.Node("e");
        Code08_IsFBT.Node f = new Code08_IsFBT.Node("f");

        Code08_IsFBT.Node g = new Code08_IsFBT.Node("g");
        Code08_IsFBT.Node h = new Code08_IsFBT.Node("h");
        Code08_IsFBT.Node i = new Code08_IsFBT.Node("i");

        a.left=b; a.right=c;
        b.left=d; b.right=e;
        c.left=f; //c.right=g;

//        b.left = null;
//        b.right = null;
//        c.right = null;

        System.out.println(Code08_IsFBT.isFBT(a));
        System.out.println(Code08_IsFBT.isFBT2(a));*/


        int times = 100000;
        int maxLevel = 6;
        for (int i = 0; i < times; i++) {
            Code08_IsFBT.Node node = generate(maxLevel);
            boolean r1 = Code08_IsFBT.isFBT(node);
            boolean r2 = Code08_IsFBT.isFBT2(node);
            if(r1 != r2) {
                Code08_IsFBT.level(node);
                System.out.println(r1 + "\t" + r2 +"fail");
                break;
            }
        }
        System.out.println("finish!");
    }


    public static Code08_IsFBT.Node generate(int maxLevel) {
        return generateProcess(1, maxLevel );
    }

    public static Code08_IsFBT.Node generateProcess(int level, int maxLevel) {
        if(level > maxLevel || Math.random() < 0.1) {
            return null;
        }
        Code08_IsFBT.Node head = new Code08_IsFBT.Node();
        head.left = generateProcess(level+1, maxLevel);
        head.right = generateProcess(level+1, maxLevel);
        return head;
    }
}
