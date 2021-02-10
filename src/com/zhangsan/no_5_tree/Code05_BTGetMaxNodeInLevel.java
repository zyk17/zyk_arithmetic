package com.zhangsan.no_5_tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 获取二叉树，每层上最多节点的层
 * @author zhangsan
 * @date 2021/2/10 19:36
 */
public class Code05_BTGetMaxNodeInLevel {

    /** 二叉树 */
    public static class BinaryTree {
        String data;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(String value) {
            this.data = value;
        }
    }

    /** 找到每层上节点最大的层数 */
    public static int getMaxNodeInLevel(BinaryTree head) {
        if (head == null) {
            return 0;
        }
        BinaryTree curEnd = head, nextEnd = null;
        int max = 1, levelCount = 0;
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            levelCount++;
            if (head.left != null) {
                queue.add(head.left);
                nextEnd = head.left;
            }
            if (head.right != null) {
                queue.add(head.right);
                nextEnd = head.right;
            }
            if(head == curEnd) {
                max = Math.max(max, levelCount);
                curEnd = nextEnd;
                levelCount = 0;
            }
        }
        max = Math.max(max, levelCount);
        return max;
    }
    /** 通过容器 */
    public static int getMaxNodeInLevel2(BinaryTree head) {
        if (head == null) {
            return 0;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(head);
        // key 在哪一层， v
        HashMap<BinaryTree, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1, curLevelNode = 0, max = 0;
        while (!queue.isEmpty()) {
            head = queue.poll();
            int curNodeLevel = levelMap.get(head);
            if (head.left != null) {
                queue.add(head.left);
                levelMap.put(head.left, curNodeLevel + 1);
            }
            if (head.right != null) {
                queue.add(head.right);
                levelMap.put(head.right, curNodeLevel + 1);
            }
            if(curNodeLevel == curLevel){
                curLevelNode++;
            } else {
                max = Math.max(max, curLevelNode);
                curLevel++;
                curLevelNode = 1;   // 重置为1，因为少进了一次上边的if
            }
        }
        max = Math.max(max, curLevelNode);  // 最后一次进了if，没进else也就少算了一次。把它计算
        return max;
    }

    /** 按层遍历二叉树 */
    public static void levelPrint(BinaryTree head) {
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

class TestCode05 {
    public static void main(String[] args) {
        Code05_BTGetMaxNodeInLevel.BinaryTree a = new Code05_BTGetMaxNodeInLevel.BinaryTree("a");
        Code05_BTGetMaxNodeInLevel.BinaryTree b = new Code05_BTGetMaxNodeInLevel.BinaryTree("b");
        Code05_BTGetMaxNodeInLevel.BinaryTree c = new Code05_BTGetMaxNodeInLevel.BinaryTree("c");
        Code05_BTGetMaxNodeInLevel.BinaryTree d = new Code05_BTGetMaxNodeInLevel.BinaryTree("d");
        Code05_BTGetMaxNodeInLevel.BinaryTree e = new Code05_BTGetMaxNodeInLevel.BinaryTree("e");
        Code05_BTGetMaxNodeInLevel.BinaryTree f = new Code05_BTGetMaxNodeInLevel.BinaryTree("f");
        Code05_BTGetMaxNodeInLevel.BinaryTree g = new Code05_BTGetMaxNodeInLevel.BinaryTree("g");

        a.left=b;
        a.right=c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;

        Code05_BTGetMaxNodeInLevel.levelPrint(a);
        System.out.println(Code05_BTGetMaxNodeInLevel.getMaxNodeInLevel(a));
        System.out.println(Code05_BTGetMaxNodeInLevel.getMaxNodeInLevel2(a));

    }
}
