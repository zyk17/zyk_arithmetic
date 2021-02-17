package com.zhangsan.no_8_graph;

/**
 * @author zhangsan
 * @date 2021/2/17 18:51
 */
public class Edge {

    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
