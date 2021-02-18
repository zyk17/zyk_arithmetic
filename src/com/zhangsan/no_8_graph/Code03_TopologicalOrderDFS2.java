package com.zhangsan.no_8_graph;

import java.util.*;

/**
 * https://www.lintcode.com/problem/topological-sorting
 *
 * label是自己，neighbors是他相邻的点
 *
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class Code03_TopologicalOrderDFS2 {

    /** 做法，先求出每个节点的后序节点数量。然后后序节点数量大的就先遍历就是拓扑排序后的结果 */
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        HashMap<DirectedGraphNode, Record> orderMap = new HashMap<>();
        List<Record> r = new ArrayList<>();
        for (DirectedGraphNode node : graph) {
            r.add( f(node, orderMap) );
        }
        r.sort( (o1, o2) -> Long.compare(o2.nextNodeNumber, o1.nextNodeNumber));
        for (Record record : r) {
            result.add(record.node);
        }
        return result;
    }

    /** 计算当前节点的后序节点数量，并把它缓存到orderMap表中。 */
    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> orderMap) {
        if(orderMap.containsKey(cur)) {
            return orderMap.get(cur);
        }
        long nodes = 0;
        for (DirectedGraphNode node : cur.neighbors) {
            nodes += f(node, orderMap).nextNodeNumber;
        }
        Record ans = new Record(cur, nodes + 1);
        orderMap.put(cur, ans);
        return ans;
    }

    /** 用于存放，节点与后序节点数量的类 */
    public static class Record {
        DirectedGraphNode node; // 当前节点
        long nextNodeNumber;     // 后序节点数量
        public Record(DirectedGraphNode node, long nextNodeNumber) {
            this.node = node;
            this.nextNodeNumber = nextNodeNumber;
        }
    }

    static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
    };
}
