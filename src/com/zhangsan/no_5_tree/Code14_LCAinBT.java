package com.zhangsan.no_5_tree;

import com.zhangsan.util.BinaryTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树上两个节点的最低公共祖先
 * @author zhangsan
 * @date 2021/2/14 13:22
 */
public class Code14_LCAinBT {


    /** 在x树上，a节点和b节点最低公共祖先 */
    public static BinaryTree lowestCommonAncestor(BinaryTree x, BinaryTree a, BinaryTree b) {

        Queue<BinaryTree> queue = new LinkedList<>();
        HashMap<BinaryTree, BinaryTree> parentMap = new HashMap<>();

        // 填充parentMap
        {
            queue.add(x);
            parentMap.put(x, null);

            BinaryTree parent = null;
            while (!queue.isEmpty()) {
                parent = queue.poll();
                if (parent.left != null) {
                    queue.add(parent.left);
                    parentMap.put(parent.left, parent);
                }
                if (parent.right != null) {
                    queue.add(parent.right);
                    parentMap.put(parent.right, parent);
                }
            }
        }

        HashSet<BinaryTree> parentSet = new HashSet<>();
        while (parentMap.containsKey(a)) {
            parentSet.add(a);
            a = parentMap.get(a);
        }
        while (parentMap.containsKey(b)) {
            if(parentSet.contains(b)) {
                return b;
            }
            b = parentMap.get(b);
        }
        return null;
    }

    public static class Info {
        public boolean findA;
        public boolean findB;
        public BinaryTree ans;

        public Info(boolean findA, boolean findB, BinaryTree ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    /** 在x树上，a节点和b节点最低公共祖先 */
    public static BinaryTree lowestCommonAncestor2(BinaryTree x, BinaryTree a, BinaryTree b) {
        return findProcess(x, a, b).ans;
    }

    public static Info findProcess(BinaryTree x, BinaryTree a, BinaryTree b) {
        if(x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = findProcess(x.left, a, b);
        Info rightInfo = findProcess(x.right, a, b);

        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        BinaryTree ans = null;


        if(leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if(rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            // 左右都无答案
            if(findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }

}

class TestCode14 {
    public static void main(String[] args) {
        BinaryTree a = new BinaryTree(1);
        BinaryTree b = new BinaryTree(2);
        BinaryTree c = new BinaryTree(3);
        BinaryTree d = new BinaryTree(4);
        BinaryTree e = new BinaryTree(5);
        BinaryTree f = new BinaryTree(6);
        BinaryTree g = new BinaryTree(7);
        BinaryTree h = new BinaryTree(8);

        a.left=b; a.right=c;
        b.left=d; b.right=e;
        c.left=f; c.right=g;
        d.left=h;


        System.out.println(Code14_LCAinBT.lowestCommonAncestor(a, c, g));
        System.out.println(Code14_LCAinBT.lowestCommonAncestor2(a, c, g));
    }
}
