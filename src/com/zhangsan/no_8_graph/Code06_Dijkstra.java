package com.zhangsan.no_8_graph;

import com.zhangsan.no_8_graph.Graph.*;
import com.zhangsan.util.HeapGreater;

import java.util.Comparator;
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

    public static class ToNodeRecord {
        Node node;
        int distance;
        public ToNodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /** 利用加强堆实现 */
    static class ToNodeHeap extends HeapGreater<ToNodeRecord> {
        public ToNodeHeap(Comparator<ToNodeRecord> comparator) {
            super(comparator);
        }

        @Override
        public void push(ToNodeRecord obj) {
            for (ToNodeRecord record : heap) {
                if(record.node == obj.node) {
                    if( obj.distance < record.distance ) {
                        record.distance = obj.distance;
                        resign(record);
                    }
                    return;
                }
            }
            heap.add(obj);
            indexMap.put(obj, heapSize);
            heapInsert(heapSize++);
        }
    }

    /** 利用加强堆实现  */
    public static Map<Node, Integer> dijkstra2(Node from) {
        Map<Node, Integer> dijkstraMap = new HashMap<>();
        ToNodeHeap heap = new ToNodeHeap( (o1, o2) -> o2.distance - o1.distance );
        heap.push(new ToNodeRecord(from, 0));

        HashSet<Node> popedNodes = new HashSet<>();

        while (heap.size() > 0) {
            ToNodeRecord record = heap.pop();
            Node node = record.node;
            int distance = record.distance;

            popedNodes.add(node);
            for (Edge edge : node.edges) {
                Node to = edge.to;
                if(!popedNodes.contains( to )) {
                    heap.push( new ToNodeRecord( to, distance+edge.weight ) );
                }
            }
            dijkstraMap.put(node, distance);
        }
        return dijkstraMap;
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
        long s1 = System.nanoTime();
        Map<Node, Integer> r1 = dijkstra1(graph.head);
        long s2 = System.nanoTime();
        System.out.println( "普通的dijkstra算法 结果:" + r1 + "\n耗时:" + (s2 - s1) + "ns" );
        long s3 = System.nanoTime();
        Map<Node, Integer> r2 = dijkstra2(graph.head);
        long s4 = System.nanoTime();
        System.out.println( "使用加强堆的dijkstra算法 结果:" + r2 + "\n耗时:" + (s4 - s3) + "ns" );
    }

}
