package com.zhangsan.no_5_tree;


import com.zhangsan.util.BinaryTree;
import com.zhangsan.util.TreeUtil;

import java.util.ArrayList;

/**
 * 是否为搜索二叉树
 * @author zhangsan
 * @date 2021/2/13 14:12
 */
public class Code10_IsSearchBT {

    public static class Node {

        public int value;
        public Node left;
        public Node right;

        public Node ( int value ) {
            this.value = value;
        }

        public Node(){}

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    ", left=" + (left == null ? "null" : left.value) +
                    ", right=" + (right == null ? "null" : right.value) +
                    '}';
        }
    }

    public static class Info {
        public boolean isSearch;
        public int max;
        public int min;

        public Info(boolean isSearch, int max, int min) {
            this.isSearch = isSearch;
            this.max = max;
            this.min = min;
        }
    }


    public static boolean isSearchBT(BinaryTree head) {
        return head == null || process(head).isSearch;
    }

    public static Info process(BinaryTree head) {
        if(head == null){
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);


        boolean isSearch = true;
        // max, min 是计算当前树的最大值和最小值
        int max = head.value;
        int min = head.value;
        if(leftInfo!=null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            isSearch = leftInfo.isSearch;
        }
        if(rightInfo!=null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            isSearch = rightInfo.isSearch;
        }
        if( (leftInfo != null && leftInfo.max >= head.value) || (rightInfo != null && rightInfo.min <= head.value) ){
            isSearch = false;
        }

        return new Info(isSearch, max, min);
    }


    public static boolean isSearchBT2(BinaryTree head) {
        if(head == null) { return true; }
        ArrayList<BinaryTree> list = new ArrayList<>();
        in(head, list);

        for (int i = 0; i < list.size() - 1; i++) {
            if(list.get(i + 1).value <= list.get(i).value ) {
                return false;
            }
        }
        return true;
    }

    private static void in(BinaryTree head, ArrayList<BinaryTree> list) {
        if(head == null) { return; }
        in(head.left, list);
        list.add(head);
        in(head.right, list);
    }

}

class TestCode10 {
    public static void main(String[] args) {
//        BinaryTree a = new BinaryTree(5);
//        BinaryTree b = new BinaryTree(3);
//        BinaryTree c = new BinaryTree(7);
//        BinaryTree d = new BinaryTree(2);
//        BinaryTree e = new BinaryTree(4);
//        BinaryTree f = new BinaryTree(6);
//        BinaryTree g = new BinaryTree(8);
//
//        a.left=b; a.right=c;
////        b.left=e; b.right=d;
//        b.left=d; b.right=e;
//        c.left=f; c.right=g;
//
//        System.out.println(Code10_IsSearchBT.isSearchBT(a));
//        System.out.println(Code10_IsSearchBT.isSearchBT2(a));

        int times = 1000;
        int maxLevel = 4;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < times; i++) {
            BinaryTree bt = TreeUtil.generateRandomTree(maxLevel, maxValue);
            BinaryTree bt1 = TreeUtil.copyBT(bt);

            boolean r1 = Code10_IsSearchBT.isSearchBT(bt);
            boolean r2 = Code10_IsSearchBT.isSearchBT2(bt1);

            if(r1 != r2) {
                succeed = false;
                System.out.println(r1 + "\t" + r2);
                TreeUtil.level(bt);
                break;
            }
        }
        System.out.println("测试结束，" + (succeed? "成功！" : "失败！"));
    }
}
