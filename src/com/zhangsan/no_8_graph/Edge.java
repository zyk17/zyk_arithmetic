package com.zhangsan.no_8_graph;

/**
 * @author zhangsan
 * @date 2021/2/17 18:51
 */
public class Edge {

    public int weight;
    public Node in;
    public Node out;

    public Edge(int weight, Node in, Node out) {
        this.weight = weight;
        this.in = in;
        this.out = out;
    }
}
