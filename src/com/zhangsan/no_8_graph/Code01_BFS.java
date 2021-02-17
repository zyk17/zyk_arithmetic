package com.zhangsan.no_8_graph;

import com.zhangsan.no_8_graph.Graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的宽度优先遍历
 *
 * @author zhangsan
 * @date 2021/2/17 19:50
 */
public class Code01_BFS {

    public static void bfs(Graph graph) {
        System.out.println("宽度优先遍历： ");
        if(graph.head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();

        queue.add(graph.head);
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            set.add(cur);
            System.out.print(cur.value + "\t");
            for (Node next : cur.nexts) {
                if(!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*Integer[][] matrix = {
                {6, 1, 2 },
                {4, 2, 3 },
                {4, 2, 4 },
                {5, 3, 4 },
                {3, 4, 5 },
                {2, 5, 6 },
                {10, 6, 1}
        };*/
        Integer[][] matrix = {
                {2, 1, 2},
                {7, 1, 3},
                {4, 1, 7},
                {6, 2, 3},
                {8, 3, 4},
                {10, 2, 5},
                {1, 3, 6}
        };
        Graph graph = GraphGenerator.create(matrix);
        bfs(graph);
//        bfs(matrix);
    }

    public static Graph generateGraph( int maxNumber, int maxValue, int maxWeight ) {
        maxNumber = (int) (Math.random()*maxNumber+1);

        Graph graph = new Graph();
        for (int i = 0; i < maxNumber; i++) {
            int fromValue = (int) (Math.random()*maxValue+1);
            int toValue = (int) (Math.random()*maxValue+1);
            int weight = (int) (Math.random()*maxWeight+1);
            graph.connect(weight, fromValue, toValue);
        }
        return graph;
    }

}
