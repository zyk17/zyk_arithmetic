package com.zhangsan.sleetcode;

import java.util.*;

/**
 * 越改越麻烦了.
 * 不应该考虑单纯用图来解决
 * 因为图要考虑
 * 1.最后一个节点是否可以连向空也就是有不继续往后走的路
 * 2.当最后一个匹配字符为.的时候, 需要注意要记录所有的可能为最后的结果然后看看他们是否又包含最后指向空的边
 *
 * 因此写起来麻烦,而且效率也不高
 */
public class WordDictionary2 {

     public static class  Graph {

        public Graph.Node head;
        public HashMap<Integer, Node> nodes = new HashMap<>();
        public HashSet<Graph.Edge> edges = new HashSet<>();

        public boolean add(int value) {
            if( nodes.containsKey(value) ) {
                return false;
            }
            Graph.Node node = new Graph.Node(value);
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
            Graph.Node from = nodes.get(fromValue);
            Graph.Node to = nodes.get(toValue);
            connectProcess(from, to, weight);
            return true;
        }

        public boolean connect(Graph.Node from, Graph.Node to, int weight) {
            connectProcess(from, to, weight);
            return true;
        }

        private void connectProcess(Graph.Node from, Graph.Node to, int weight) {
            Graph.Edge edge = new Graph.Edge(weight, from, to);
            if (this.edges.contains( edge )) {
                return;
            }
            if(to != null) {
                from.nexts.add(to);
                from.out++;
                to.in++;
            }

            from.edges.add(edge);
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
            public List<Graph.Node> nexts = new ArrayList<>();
            public List<Graph.Edge> edges = new ArrayList<>();

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
            public Graph.Node from;
            public Graph.Node to;

            public Edge(int weight, Graph.Node from, Graph.Node to) {
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

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Graph.Edge edge = (Graph.Edge) o;
                return weight == edge.weight &&
                        Objects.equals(from, edge.from) &&
                        Objects.equals(to, edge.to);
            }

            @Override
            public int hashCode() {
                return Objects.hash(weight, from, to);
            }
        }

    }

    Graph graph;
    /** Initialize your data structure here. */
    public WordDictionary2() {
        graph = new Graph();
    }

    /** 构建这个图 */
    public void addWord(String word) {
        char[] str = word.toCharArray();
        graph.add(str[0]);
        for (int i = 1; i < str.length; i++) {
            graph.connect(1, str[i-1], str[i]);
        }
        Graph.Node node = graph.nodes.get((int) str[str.length - 1]);
        graph.connect(node, null, 1);   // 再给每个单词最后一个字母添加一条连向null的边
    }

    public boolean search(String word) {
        if(word == null || word.equals("")) {return false;}
        char[] str = word.toCharArray();
        Graph.Node head = graph.nodes.get((int)str[0]);
        if( str[0] == '.' ) {
            head = graph.head;
        }
        if(head == null) {
            return false;
        }

        Queue<Graph.Node> queue = new LinkedList<>();
        queue.add(head);
        Graph.Node cur = null;
        int i = 0;
        while (!queue.isEmpty() && i < str.length) {
            cur = queue.poll();
            if( cur.value == str[i] || str[i] == '.' ) {
                queue.clear();
                i++;
            } else {
                continue;
            }
            queue.addAll(cur.nexts);
        }
        return cur != null && i == word.length() && cur.edges.contains(new Graph.Edge(1, cur, null));
    }


    // for test
    public static void main(String[] args) {
        WordDictionary2 tree = new WordDictionary2();
        tree.addWord("add");
        tree.addWord("at");
        tree.addWord("and");
        tree.addWord("an");
        tree.addWord("add");
        System.out.println(tree.search("a"));
        System.out.println(tree.search(".at"));
        tree.addWord("bat");

        System.out.println(tree.search(".at"));
        System.out.println(tree.search("an."));
        System.out.println(tree.search("a.d."));
        System.out.println(tree.search("b."));
        System.out.println(tree.search("a.b"));
        System.out.println(tree.search("."));

    }
}
