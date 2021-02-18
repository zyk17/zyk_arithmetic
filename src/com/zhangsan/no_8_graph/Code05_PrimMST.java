package com.zhangsan.no_8_graph;

import com.zhangsan.no_8_graph.Graph.*;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * prim 算法
 * @author zhangsan
 * @date 2021/2/18 12:49
 */
public class Code05_PrimMST {

    public static Set<Edge> primMST(Graph graph ) {
        Set<Edge> result = new HashSet<>();

        // 解锁的边
        PriorityQueue<Edge> unlockEdge = new PriorityQueue<>( (o1, o2) -> o1.weight-o2.weight );
        // 解锁的节点
        HashSet<Node> unlockNode = new HashSet<>();
        unlockNode.add(graph.head);

        unlockEdge.addAll(graph.head.edges);
        while (!unlockEdge.isEmpty()) {
            Edge edge = unlockEdge.poll();
            Node to = edge.to;
            if( !unlockNode.contains(to) ) {
                unlockNode.add(to);
                result.add(edge);
                unlockEdge.addAll(to.edges);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Integer[][] matrix = {
                { 1, 1, 2 },
                { 3, 1, 6 },
                { 4, 2, 3 },
                { 9, 2, 4 },
                { 6, 2, 5 },
                { 1, 2, 6 },
                { 7, 3, 1 },
                { 10, 4, 3 },
                { 1, 5, 4 },
                { 4, 6, 5 },
        };
        Graph graph = GraphGenerator.create(matrix);
        Set<Edge> edges = primMST(graph);
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }

}
