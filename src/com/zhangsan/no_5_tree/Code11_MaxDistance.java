package com.zhangsan.no_5_tree;

import com.zhangsan.util.BinaryTree;
import com.zhangsan.util.TreeUtil;

/**
 * 求x树上，距离最远的两个节点的距离
 *
 * @author zhangsan
 * @date 2021/2/13 17:40
 */
public class Code11_MaxDistance {

    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static int maxDistance(BinaryTree bt) {
        return process(bt).maxDistance;
    }

    private static Info process(BinaryTree bt) {
        if (bt == null) { return new Info(0, 0); }
        Info leftInfo = process(bt.left);
        Info rightInfo = process(bt.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), (leftInfo.height + rightInfo.height + 1));
        return new Info(maxDistance, height);
    }

}

class TestCode11 {
    public static void main(String[] args) {
        int times = 100;
        int maxLevel = 6;
        int maxValue = 10;
        for (int i = 0; i < times; i++) {
            BinaryTree binaryTree = TreeUtil.generateRandomTree(maxLevel, maxValue);

            System.out.println(Code11_MaxDistance.maxDistance(binaryTree));
        }
    }
}
