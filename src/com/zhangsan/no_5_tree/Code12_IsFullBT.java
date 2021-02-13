package com.zhangsan.no_5_tree;

import com.zhangsan.util.BinaryTree;
import com.zhangsan.util.TreeUtil;

/**
 * 是否满二叉树
 * @author zhangsan
 * @date 2021/2/13 18:18
 */
public class Code12_IsFullBT {

    public static class Info {
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isFullBT(BinaryTree bt) {
        Info info = process(bt);
        return (1<<info.height) - 1 == info.nodes;
    }

    public static Info process(BinaryTree head) {
        if(head == null) { return new Info(0, 0); }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
    }

}

class TestCode12 {
    public static void main(String[] args) {
        int times = 1000;
        int maxLevel = 6;
        int maxValue = 10;
        for (int i = 0; i < times; i++) {
            BinaryTree binaryTree = TreeUtil.generateRandomTree(maxLevel, maxValue);
            boolean r1 = Code12_IsFullBT.isFullBT(binaryTree);
            System.out.println(r1);
            if (r1) {
                TreeUtil.level(binaryTree);
            }
        }
    }
}
