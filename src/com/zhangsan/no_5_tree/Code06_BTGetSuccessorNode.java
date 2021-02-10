package com.zhangsan.no_5_tree;

/**
 * 获取后记节点
 * 中序遍历它的下一个节点
 * 下边二叉树的结构，节点可以找到父节点
 * @author zhangsan
 * @date 2021/2/10 21:29
 */
public class Code06_BTGetSuccessorNode {

    /** 二叉树 */
    public static class BinaryTree {
        String data;
        BinaryTree left;
        BinaryTree right;
        BinaryTree parent;

        public BinaryTree(String value) {
            this.data = value;
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "data='" + data + '\'' +
                    ", left=" + (left == null ? "null": left.data) +
                    ", right=" + (right == null ? "null": right.data) +
                    ", parent=" + (parent == null ? "null": parent.data) +
                    '}';
        }
    }

    /** 寻找它的后继节点 */
    public static BinaryTree getSuccessorNode(BinaryTree bt) {
        // 先找到根节点通过parent，在中序遍历中找到后继节点
        if(bt == null) { return null; }
        BinaryTree head = bt;
        while (head.parent != null) {
            head = head.parent;
        }

        return inGet(head, bt);
    }

    public static BinaryTree inGet(BinaryTree cur, BinaryTree target) {
        /*Stack<BinaryTree> stack = new Stack<>();
        while ( !stack.isEmpty() || cur != null ) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                if(cur == target) {
                    return getLeftMost(cur.right);
                }
                cur =cur.right;
            }
        }*/
        return null;
    }

    /** 寻找它的后继节点 */
    public static BinaryTree getSuccessorNode1(BinaryTree bt) {
        if(bt == null) { return null; }
        if( bt.right != null ) { return getLeftMost(bt.right); }

        BinaryTree t = bt;
        while (t.parent != null && t.parent.right == t) {
            t = t.parent;
        }
        return t.parent;
    }

    private static BinaryTree getLeftMost(BinaryTree head) {
        if(head == null) {
            return null;
        }
        while (head.left != null) {
            head = head.left;
        }
        return head;
    }
}

class TestCode06 {
    public static void main(String[] args) {
        Code06_BTGetSuccessorNode.BinaryTree m = new Code06_BTGetSuccessorNode.BinaryTree("m");
        Code06_BTGetSuccessorNode.BinaryTree a = new Code06_BTGetSuccessorNode.BinaryTree("a");
        Code06_BTGetSuccessorNode.BinaryTree b = new Code06_BTGetSuccessorNode.BinaryTree("b");
        Code06_BTGetSuccessorNode.BinaryTree c = new Code06_BTGetSuccessorNode.BinaryTree("c");
        Code06_BTGetSuccessorNode.BinaryTree d = new Code06_BTGetSuccessorNode.BinaryTree("d");
        Code06_BTGetSuccessorNode.BinaryTree e = new Code06_BTGetSuccessorNode.BinaryTree("e");
        Code06_BTGetSuccessorNode.BinaryTree f = new Code06_BTGetSuccessorNode.BinaryTree("f");
        Code06_BTGetSuccessorNode.BinaryTree g = new Code06_BTGetSuccessorNode.BinaryTree("g");
        Code06_BTGetSuccessorNode.BinaryTree h = new Code06_BTGetSuccessorNode.BinaryTree("h");
        Code06_BTGetSuccessorNode.BinaryTree i = new Code06_BTGetSuccessorNode.BinaryTree("i");
        Code06_BTGetSuccessorNode.BinaryTree q = new Code06_BTGetSuccessorNode.BinaryTree("q");

        m.left = a; m.right = q;
        a.left = b; a.right = c;
        b.left = d; b.right = e;
        c.left = f; c.right = g;
        q.left = h;
        h.right = i;

        a.parent = m; q.parent = m; b.parent = a; c.parent = a; h.parent = q;
        d.parent = b; e.parent = b; f.parent = c; g.parent = c; i.parent = h;

        System.out.println(Code06_BTGetSuccessorNode.getSuccessorNode(i));
        System.out.println(Code06_BTGetSuccessorNode.getSuccessorNode1(i));

    }
}
