package com.zhangsan.util;

/**
 * 二叉树
 * @author zhangsan
 * @date 2021/2/13 14:13
 */
public class BinaryTree {

    public String value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree ( String value ) {
        this.value = value;
    }

    public BinaryTree(String value, BinaryTree left, BinaryTree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryTree(){}
}
