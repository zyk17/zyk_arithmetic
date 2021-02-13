package com.zhangsan.util;

import java.util.List;

/**
 * 多叉树
 * @author zhangsan
 * @date 2021/2/13 14:15
 */
public class MultiWayTree {
    public String value;
    public List<MultiWayTree> children;

    public MultiWayTree ( String value ) {
        this.value = value;
    }

    public MultiWayTree(String value, List<MultiWayTree> children) {
        this.value = value;
        this.children = children;
    }

    public MultiWayTree(){}
}
