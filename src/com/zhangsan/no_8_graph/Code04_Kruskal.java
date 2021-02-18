package com.zhangsan.no_8_graph;

import com.zhangsan.no_8_graph.Graph.Edge;
import com.zhangsan.no_8_graph.Graph.Node;
import com.zhangsan.util.UnionSet;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 图的最小生成树
 * @author zhangsan
 * @date 2021/2/18 11:14
 */
public class Code04_Kruskal {

    public static Set<Edge> kruskalMST( Graph graph ) {
        Set<Edge> result = new HashSet<>();
        // 并查集， 作用：检验节点是否为环。
        UnionSet<Node> unionSet = new UnionSet<>(graph.nodes.values());
        // 小根堆， 作用：排序
        PriorityQueue<Edge> smallHeap = new PriorityQueue<>( (o1, o2) -> o1.weight-o2.weight);
        smallHeap.addAll(graph.edges);
        while (!smallHeap.isEmpty()) {
            Edge edge = smallHeap.poll();
            if( !unionSet.isSameSet(edge.from, edge.to) ) {
                result.add(edge);
                unionSet.union(edge.from, edge.to);
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
        Set<Edge> edges = kruskalMST(graph);
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }

}
