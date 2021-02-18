package com.zhangsan.no_8_graph;

import java.util.ArrayList;

/**
 * 图的拓扑排序，深度优先遍历
 */
public class Code03_TopologicalOrderDFS1 {

    /** 不提交，给定的结构， 当前节点，和他后序的邻居 */
    static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
    };

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<>();

        return result;
    }
}
