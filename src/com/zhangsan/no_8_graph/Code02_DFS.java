package com.zhangsan.no_8_graph;

import com.zhangsan.no_8_graph.Graph.Node;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图的宽度优先遍历
 * @author zhangsan
 * @date 2021/2/17 21:17
 */
public class Code02_DFS {

    public static void dfs1(Graph graph) {
        System.out.println("深度优先遍历： ");
        if(graph.head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(graph.head);
        set.add(graph.head);
        System.out.print(graph.head.value + "\t");
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if(!set.contains(next)) {
                    stack.push(cur);    // 经典之笔
                    stack.push(next);
                    set.add(next);
                    System.out.print(next.value + "\t");
                    break;
                }
            }
        }
        System.out.println();
    }

    public static void dfs2(Graph graph) {
        System.out.println("深度优先遍历： ");
        if(graph.head == null) {
            return;
        }
        HashSet<Node> set = new HashSet<>();
        dfsProcess(graph.head, set);
        System.out.println();
    }

    private static void dfsProcess(Node cur, HashSet<Node> set) {
        if(set.contains(cur)) {
            return;
        }
        System.out.print(cur.value + "\t");
        set.add(cur);
        for (Node next : cur.nexts) {
            dfsProcess(next, set);
        }
    }


    public static void main(String[] args) {
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
        dfs1(graph);
        dfs2(graph);
    }

}
