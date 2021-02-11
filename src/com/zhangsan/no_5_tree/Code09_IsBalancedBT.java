package com.zhangsan.no_5_tree;

/**
 * 是否平衡二叉树
 *
 * @author zhangsan
 * @date 2021/2/11 19:21
 */
public class Code09_IsBalancedBT {

    /** 二叉树 */
    public static class Node {
        public Node left;
        public Node right;
        public String value;
        public Node(String value) { this.value = value; }
        public Node() {}
    }

    public static class Info {
        public boolean isBalanced;
        public int height;
        public Info(boolean isBalanced, int height) { this.isBalanced = isBalanced; this.height = height; }
    }

    public static boolean isBalancedBT(Node head) {
        return process(head).isBalanced;
    }

    public static Info process(Node head) {
        if(head == null) { return new Info(true, 0); }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        boolean isBalanced = true;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        if( (!leftInfo.isBalanced) || (!rightInfo.isBalanced) || (Math.abs(leftInfo.height-rightInfo.height) > 1) ) {
            isBalanced = false;
        }
        return new Info(isBalanced, height);
    }

}


class TestCode09 {
    public static void main(String[] args) {
        Code09_IsBalancedBT.Node a = new Code09_IsBalancedBT.Node("a");
        Code09_IsBalancedBT.Node b = new Code09_IsBalancedBT.Node("b");
        Code09_IsBalancedBT.Node c = new Code09_IsBalancedBT.Node("c");
        Code09_IsBalancedBT.Node d = new Code09_IsBalancedBT.Node("d");
        Code09_IsBalancedBT.Node e = new Code09_IsBalancedBT.Node("e");
        Code09_IsBalancedBT.Node f = new Code09_IsBalancedBT.Node("f");
        Code09_IsBalancedBT.Node g = new Code09_IsBalancedBT.Node("g");
        Code09_IsBalancedBT.Node h = new Code09_IsBalancedBT.Node("h");
        Code09_IsBalancedBT.Node i = new Code09_IsBalancedBT.Node("i");

        a.left=b; a.right=c;
        b.left=d; b.right=e;
        c.left=f; c.right=g;

        b.right = null;
        c.left = null;
        d.left = h;

        System.out.println(Code09_IsBalancedBT.isBalancedBT(a));
    }
}
