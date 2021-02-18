package com.zhangsan.no_8_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度优先遍历实现拓扑排序
 * @author zhangsan
 * @date 2021/2/18 10:40
 */
public class Code03_TopologicalOrderBFS {

    /** 不提交，给定的结构， 当前节点，和他后序的邻居 */
    static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
    };


    /** 宽度优先做法 */
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 思想：计算每个节点的入度是多少。然后转成普通的拓扑排序
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        HashMap<DirectedGraphNode, Integer> inMap = new HashMap<>();
        // 入度为0的队列：剩余入度为0加入这个队列
        Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();

        // 赋值inmap 入度表
        for (DirectedGraphNode node : graph) {
            inMap.put(node, 0);
        }
        for (DirectedGraphNode cur : graph) {
            for (DirectedGraphNode neighbor : cur.neighbors) {
                inMap.put(neighbor, inMap.get(neighbor) + 1);
            }
        }
        // 普通的拓扑排序操作===
        for (DirectedGraphNode node : inMap.keySet()) {
            if( inMap.get(node) == 0 ) {
                zeroInQueue.add(node);
            }
        }

        while (!zeroInQueue.isEmpty()) {
            DirectedGraphNode cur = zeroInQueue.poll();
            result.add(cur);
            for (DirectedGraphNode neighbor : cur.neighbors) {
                inMap.put(neighbor, inMap.get(neighbor) - 1);
                if(inMap.get(neighbor) == 0) {
                    zeroInQueue.add(neighbor);
                }
            }
        }

        return result;
    }

}
