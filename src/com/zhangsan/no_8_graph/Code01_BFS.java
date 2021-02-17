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
        System.out.println("深度优先遍历： ");
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

    public static void bfs(Integer[][] matrix) {
        /* ×
        HashSet<Integer> set = new HashSet<>();
        for (Integer[] arr : matrix) {
            if( !set.contains(arr[1]) ) {
                set.add(arr[1]);
                System.out.print(arr[1] + "\t");
            }
            if( !set.contains(arr[2]) ) {
                set.add(arr[2]);
                System.out.print(arr[2] + "\t");
            }
        }
        System.out.println();*/
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
                {1, 6, 3}
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
