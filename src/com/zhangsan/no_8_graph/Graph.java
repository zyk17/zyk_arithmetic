package com.zhangsan.no_8_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 图结构
 * @author zhangsan
 * @date 2021/2/17 18:51
 */
public class Graph {

    public Node head;
    public HashMap<Integer, Node> nodes = new HashMap<>();
    public HashSet<Edge> edges = new HashSet<>();

    public boolean add(int value) {
        Node node = new Node(value);
        if(head == null) {
            head = node;
        }
        nodes.put(value, node);
        return true;
    }

    public boolean connect(int weight, int fromValue, int toValue) {
        if (!nodes.containsKey(fromValue)) {
            add(fromValue);
        }
        if (!nodes.containsKey(toValue)) {
            add(toValue);
        }
        Node from = nodes.get(fromValue);
        Node to = nodes.get(toValue);
        connectProcess(from, to, weight);
        return true;
    }

    public boolean connect(Node from, Node to, int weight) {
        connectProcess(from, to, weight);
        return true;
    }

    private void connectProcess(Node from, Node to, int weight) {
        from.nexts.add(to);
        from.out++;
        to.in++;

        Edge edge = new Edge(weight, from, to);
        from.edges.add(edge);
        edges.add(edge);
        this.edges.add(edge);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "\n\thead=" + head +
                ", \n\tnodes=" + nodes +
                ", \n\tedges=" + edges +
                "\n}";
    }

    public static class Node {
        public int value;
        public int in;
        public int out;
        public List<Node> nexts = new ArrayList<>();
        public List<Edge> edges = new ArrayList<>();

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", in=" + in +
                    ", out=" + out +
                    '}';
        }
    }

    public static class Edge {

        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "weight=" + weight +
                    ", from=" + from.value +
                    ", to=" + to.value +
                    '}';
        }
    }

}
