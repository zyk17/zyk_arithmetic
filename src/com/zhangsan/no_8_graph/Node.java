package com.zhangsan.no_8_graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图结构
 *
 * @author zhangsan
 * @date 2021/2/17 18:47
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public List<Node> nexts = new ArrayList<>();
    public List<Edge> edges = new ArrayList<>();

    public Node(int value) {
        this.value = value;
    }
}
