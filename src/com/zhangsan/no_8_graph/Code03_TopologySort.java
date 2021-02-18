package com.zhangsan.no_8_graph;

import com.zhangsan.no_8_graph.Graph.Node;

import java.util.*;

/**
 * 拓扑排序
 * @author zhangsan
 * @date 2021/2/17 22:34
 */
public class Code03_TopologySort {

    public static List<Node> topologySort(Graph graph) {
        // 入度表：key：后序节点， value：忽略掉当前节点它的入度为0就加入
        Map<Node, Integer> inMap = new HashMap<>();
        // 入度为0的队列：剩余入度为0加入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        List<Node> result = new ArrayList<>();

        for (Node node : graph.nodes.values()) {
            if(node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.containsKey(next)? inMap.get(next) - 1 : next.in-1);
                if(inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[][] matrix = {
                { 0, 0, 1 },
                { 0, 0, 2 },
                { 0, 0, 3 },
                { 0, 1, 4 },
                { 0, 2, 4 },
                { 0, 3, 4 },
                { 0, 2, 5 },
                { 0, 3, 5 },
        };
        Graph graph = GraphGenerator.create(matrix);
        List<Node> nodes = topologySort(graph);
        for (Node node : nodes) {
            System.out.print(node.value + "\t");
        }
        System.out.println();
    }

}
