package com.zhangsan.no_8_graph;

import com.zhangsan.no_8_graph.Graph.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * dijkstra 算法
 * 迪杰斯特拉算法，求图上 某个节点到后续节点所有的最短路径
 * 要求一个环上不能有负数。
 * @author zhangsan
 * @date 2021/2/18 13:52
 */
public class Code06_Dijkstra {

    public static Map<Node, Integer> dijkstra1(Node from) {
        Map<Node, Integer> dijkstraMap = new HashMap<>();
        dijkstraMap.put(from, 0);
        HashSet<Node> lockNodes = new HashSet<>();
        Node minNode = findOneUnLockNodeAndMinDistance(dijkstraMap, lockNodes);

        while (minNode != null) {
            int distance = dijkstraMap.get(minNode);
            for (Edge edge : minNode.edges) {
                if( dijkstraMap.containsKey( edge.to ) ) {
                    dijkstraMap.put(edge.to, Math.min( dijkstraMap.get(edge.to), distance + edge.weight ));
                } else {
                    dijkstraMap.put( edge.to, distance + edge.weight );
                }
            }
            lockNodes.add(minNode);
            minNode = findOneUnLockNodeAndMinDistance( dijkstraMap, lockNodes );
        }

        return dijkstraMap;
    }

    /** 找到一个未锁的最小距离的node */
    private static Node findOneUnLockNodeAndMinDistance(Map<Node, Integer> dijkstraMap, HashSet<Node> selectedNodes) {
        Node target = null;
        int distance = Integer.MAX_VALUE;

        for (Map.Entry<Node, Integer> entry : dijkstraMap.entrySet()) {
            Node cur = entry.getKey();
            int curDistance = entry.getValue();
            if( selectedNodes.contains(cur) || curDistance >= distance ) {
                continue;
            }
            distance = curDistance;
            target = cur;
        }
        return target;
    }


    public static void main(String[] args) {
        Integer[][] martix = {
                { 1, 1, 2 },
                { 6, 1, 3 },
                { 2, 1, 4 },
                { 2, 2, 3 },
                { 6, 2, 5 },
                { 5, 3, 4 },
                { 1, 3, 5 },
                { 10, 4, 5 },
        };
        Graph graph = GraphGenerator.create(martix);
        System.out.println(dijkstra1(graph.head));
    }

}
