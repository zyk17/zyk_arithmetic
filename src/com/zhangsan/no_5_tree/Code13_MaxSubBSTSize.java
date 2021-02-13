package com.zhangsan.no_5_tree;

import com.zhangsan.util.BinaryTree;

/**
 * x树上，最大的子树（包括自己）的树
 * @author zhangsan
 * @date 2021/2/13 19:52
 */
public class Code13_MaxSubBSTSize {

    public static class Info {
        public int maxSubBSTSize;
        public int max;
        public int min;
        public int size;

        public Info(int maxSubBSTSize, int max, int min, int size) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.max = max;
            this.min = min;
            this.size = size;
        }
    }

    public static int maxSubBSTSize(BinaryTree bt) {
        if(bt == null) {
            return 0;
        }
        return process(bt).maxSubBSTSize;
    }

    private static Info process(BinaryTree bt) {
        if(bt == null) {
            return null;
        }
        Info leftInfo = process(bt.left);
        Info rightInfo = process(bt.right);

        int maxSubBSTSize = 1;
        int max = bt.value;
        int min = bt.value;
        int size = 1;
        if(leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.max(min, leftInfo.min);
            size += leftInfo.size;
        }
        if(rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            size += rightInfo.size;
        }

        if(leftInfo != null && rightInfo != null) {
            // 加上bt左边 右边 还是不是完全搜索的二叉树了
            boolean lIsBSt = (leftInfo.maxSubBSTSize == leftInfo.size && leftInfo.max < bt.value),
                    rIsBST = (rightInfo.maxSubBSTSize == rightInfo.size) && rightInfo.min > bt.value;
            // 1.加上bt 它左右两个还是搜索树。
            if( lIsBSt && rIsBST ) {
                maxSubBSTSize = size;
            }
            // 2. 最大子搜索树大小 = 左子树或右子树的最大搜索树大小
            /*else if(lIsBSt || rIsBST) {
                maxSubBSTSize += ( lIsBSt? leftInfo.maxSubBSTSize : rightInfo.maxSubBSTSize );
            }*/ else {
                maxSubBSTSize = Math.max(leftInfo.maxSubBSTSize, rightInfo.maxSubBSTSize);
            }
        } else {
            // 左或右有一个为空或者都为空
            if(leftInfo != null) {
                maxSubBSTSize = (leftInfo.maxSubBSTSize == leftInfo.size && leftInfo.max < bt.value)? leftInfo.maxSubBSTSize+1:leftInfo.maxSubBSTSize;
            }
            if(rightInfo != null) {
                maxSubBSTSize = (rightInfo.maxSubBSTSize == rightInfo.size) && rightInfo.min > bt.value? rightInfo.maxSubBSTSize+1 : rightInfo.maxSubBSTSize;
            }
        }


        return new Info(maxSubBSTSize, max, min, size);
    }


}

class TestCode13 {
    public static void main(String[] args) {
        BinaryTree a = new BinaryTree(9);
        // a左边
        BinaryTree b = new BinaryTree(6);
        BinaryTree d = new BinaryTree(2);
        BinaryTree e = new BinaryTree(16);

        // a右边
        BinaryTree c = new BinaryTree(8);
        a.left = b; b.right=c; c.left = d; a.right=e;

        BinaryTree f = new BinaryTree(6);
        BinaryTree g = new BinaryTree(9);

        BinaryTree h = new BinaryTree(1);
        BinaryTree i = new BinaryTree(1);
        BinaryTree j = new BinaryTree(1);

//        a.left=b; a.right=c;
//        b.left=d; b.right=e;
//        c.left=f; c.right=g;


        System.out.println(Code13_MaxSubBSTSize.maxSubBSTSize(a));
    }
}
