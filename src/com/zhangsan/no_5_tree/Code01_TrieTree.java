package com.zhangsan.no_5_tree;

/**
 * 前缀树
 *
 * @author zhangsan
 * @date 2021/2/8 13:00
 */
public class Code01_TrieTree {

    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nodes;

        public Node1() {
            nodes = new Node1[26];
        }
    }

    public static class TrieTree {
        public Node1 root;
        public TrieTree() {
            root = new Node1();
        }

        public void insert(String word) {
            if(word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
            int path = -1;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                node.pass++;
                if(node.nodes[path] == null) {
                    node.nodes[path] = new Node1();
                }
                node = node.nodes[path];
            }
            node.pass++;
            node.end++;
        }

        public int search(String word) {
            if(word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            int path = -1;
            Node1 node = root;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                node = node.nodes[path];
            }
            return node.end;
        }

        public int prefixNumber(String word) {
            if(word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            int path = -1;
            Node1 node = root;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                node = node.nodes[path];
            }
            return node.pass;
        }

        public void delete(String word) {
            if(search(word) == 0) {
                return;
            }
            char[] chars = word.toCharArray();
            int path = -1;
            Node1 node = root;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if(--node.pass == 0) {
                    node = null;
                }else {
                    node = node.nodes[path];
                }
            }
            if(node != null)
                node.end--;
        }
    }


    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.insert("abc");
        tree.insert("abd");
        // 测试添加
        /*System.out.println(tree.root.pass);
        System.out.println(tree.root.end);
        System.out.println(tree.root.nodes[0].pass);
        System.out.println(tree.root.nodes[0].end);
        System.out.println(tree.root.nodes[0].nodes[1].pass);
        System.out.println(tree.root.nodes[0].nodes[1].end);
        System.out.println(tree.root.nodes[0].nodes[1].nodes[2].pass);
        System.out.println(tree.root.nodes[0].nodes[1].nodes[2].end);*/

        // 查找测试
        tree.insert("abc");
        tree.insert("cdp");

        tree.delete("abc");
        /*System.out.println(tree.search("abc"));
        System.out.println(tree.search("cdp"));
        System.out.println(tree.search("abd"));*/

        // 前缀查找测试
        System.out.println(tree.search("abc"));
    }

}

