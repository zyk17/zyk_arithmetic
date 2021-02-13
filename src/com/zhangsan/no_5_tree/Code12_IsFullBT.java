package com.zhangsan.no_5_tree;

import com.zhangsan.util.BinaryTree;
import com.zhangsan.util.TreeUtil;

import java.util.LinkedList;
import java.util.Queue;

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

    public static boolean isFullBT2(BinaryTree bt) {
        if(bt == null) {
            return true;
        }
        Info info = compute(bt);
        return (1<<info.height) - 1 == info.nodes;
    }

    public static Info compute(BinaryTree bt) {
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(bt);

        BinaryTree left = null, right = null, cur = null;

        BinaryTree curEnd = bt, nextEnd = null;
        int height = 0, nodes = 0;
        while (! queue.isEmpty()) {
            cur = queue.poll();
            left = cur.left;
            right = cur.right;
            if(left != null) {
                queue.add(left);
                nextEnd = left;
            }
            if(right != null) {
                queue.add(right);
                nextEnd = right;
            }
            if(cur == curEnd ) {
                curEnd = nextEnd;
                height++;
            }
            nodes++;
        }
        return new Info(height, nodes);
    }

}

class TestCode12 {
    public static void main(String[] args) {
        int times = 1000000;
        int maxLevel = 6;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < times; i++) {
            BinaryTree binaryTree = TreeUtil.generateRandomTree(maxLevel, maxValue);
            BinaryTree bt2 = TreeUtil.copyBT(binaryTree);
            boolean r1 = Code12_IsFullBT.isFullBT(binaryTree);
            boolean r2 = Code12_IsFullBT.isFullBT2(bt2);
            if (r1 != r2) {
                succeed = false;
                TreeUtil.level(binaryTree);
                break;
            }
        }
        System.out.println("测试结束： " + (succeed? "成功！": "失败！"));
    }
}
