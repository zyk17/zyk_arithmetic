package com.zhangsan.sleetcode;

/**
 * 添加单词的方法
 * 以及查找单词的方法
 * 添加的词是26个小写字母
 * 比如词典有 'abcd' 查找'abcd' return true;  查找'ab..', return true; 一个.代表一个字母
 * https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
 */
public class WordDictionary {

    public static class Node {
        public int pass;
        public int end;
        public Node[] nodes;

        public Node() {
            nodes = new Node[26];
        }
    }
    public Node root;

    /** 构建这颗前缀树 */
    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        if(word == null) {
            return;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        int path = -1;
        for (int i = 0; i < chars.length; i++) {
            path = chars[i] - 'a';
            node.pass++;
            if(node.nodes[path] == null) {
                node.nodes[path] = new Node();
            }
            node = node.nodes[path];
        }
        node.pass++;
        node.end++;
    }

    public boolean search(String word) {
        if( word == null || word.equals("") ) { return false; }
        char[] str = word.toCharArray();
        Node node = root;
        return process(str, 0, node) > 0;
    }

    /** 递归没有重复调用的 */
    public int process(char[] str, int i, Node cur) {
        if( i == str.length ) {
            return cur.end;
        }
        if( str[i] == '.' ) {
            // 遇到.无论如何, 都往下边找.
            Node[] childNodes = cur.nodes;
            int ans = 0;
            for (Node node : childNodes) {
                if( node!=null) {
                    ans += process(str, i + 1, node);
                }
            }
            return ans;
        } else {
            // 单个找
            int path = str[i] - 'a';
            Node next = cur.nodes[path];
            if( next == null || next.pass == 0 ) {
                return 0;
            }
            return process(str, i+1, next);
        }
    }


    // for test
    public static void main(String[] args) {
        WordDictionary tree = new WordDictionary();
        tree.addWord("abc");
        tree.addWord("abd");
        tree.addWord("ae");
        tree.addWord("aec");
        // 测试添加
        /*System.out.println(tree.root.pass);
        System.out.println(tree.root.end);
        System.out.println(tree.root.nodes[0].pass);
        System.out.println(tree.root.nodes[0].end);
        System.out.println(tree.root.nodes[0].nodes[1].pass);
        System.out.println(tree.root.nodes[0].nodes[1].end);
        System.out.println(tree.root.nodes[0].nodes[1].nodes[2].pass);
        System.out.println(tree.root.nodes[0].nodes[1].nodes[2].end);*/

        // 测试search

        System.out.println(tree.search("abc"));
        System.out.println(tree.search("abd"));
        System.out.println(tree.search("a."));
        System.out.println(tree.search("a.c"));
        System.out.println(tree.search("a.d"));
        System.out.println(tree.search("a.."));

    }
}
