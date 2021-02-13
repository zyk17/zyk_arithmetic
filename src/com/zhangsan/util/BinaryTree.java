package com.zhangsan.util;

/**
 * 二叉树
 *
 * @author zhangsan
 * @date 2021/2/13 14:13
 */
public class BinaryTree {

    public int value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
        this.value = value;
    }

    public BinaryTree(int value, BinaryTree left, BinaryTree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryTree() {
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "value='" + value + '\'' +
                ", left=" + (left == null ? "null" : left.value) +
                ", right=" + (right == null ? "null" : right.value) +
                '}';
    }
}
