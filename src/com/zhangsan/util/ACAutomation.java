package com.zhangsan.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ACAutomation {

    class Node {
        // 敏感词单词
        public String endWorld;
        public Node fail;
        public Node[] nexts;     // 先只写出小写字母
        Node() {
            nexts = new Node[26];
        }
    }

    private final Node root = new Node();         // 根结点

    // 正常的构建前缀树
    public void insert(String s) {
        char[] str = s.toCharArray();
        Node cur = root;
        for (char c : str) {
            int path = c - 'a';
            if (cur.nexts[path] == null)
                cur.nexts[path] = new Node();
            cur = cur.nexts[path];
        }
        cur.endWorld = s;
    }

    // 构建 fail 指针，BFS构建
    //      1) 头指向 null
    //      2) 头子 指向 root
    //      3)  1' 当前节点，顺着fail 指针找， 如果路上某个节点有当前字符， 指向指向他。
    //          2' 没有的情况指向根结点
    public void build() {
        Queue<Node> q = new LinkedList<>();

        // 2) 代码可以简化到下边BFS的流程中，因为最后会指向root。为了明确，注释版的不简化了
        for (Node next : root.nexts) {
            if(next != null) {
                next.fail = root;
                q.add(next);
            }
        }

        // BFS
        while (!q.isEmpty()) {
            Node parent = q.poll();     // BFS
            for (int path = 0; path < 26; path++) {
                // 3) 当前孩子，连fail
                if (parent.nexts[path] != null) {
                    Node cur = parent.nexts[path];
                    Node cFail = parent.fail;
                    while (cFail != null) {     // 不是根结点
                        if( cFail.nexts[path] != null ) {   // 如果找到，直接连
                            cur.fail = cFail.nexts[path];
                            break;
                        }
                        cFail = cFail.fail;
                    }
                    cur.fail = cur.fail == null ? root : cur.fail;  // 3) 如果没找到，指向根结点

                    q.add(cur); // BFS
                }
            }
        }
    }


    // 返回包含了多少个敏感单词
    public int containNum(String content) {
        char[] str = content.toCharArray();
        Node cur = root;
        Node follow = null;
        int ans = 0;
        for (char c : str) {
            int path = c - 'a';

            // 匹配不到了，赶紧跳到下个好的开始点
            while (cur.nexts[path] == null && cur != root) {
                cur = cur.fail;
            }
            // 当前往下匹配
            cur = cur.nexts[path] != null ? cur.nexts[path] : root;

            follow = cur;   // dhe  he    dh  h     e e     分别收集答案
            while (follow != root) {
                { // 不同的需求，在这一段{ }之间修改
                    ans += follow.endWorld == null ? 0 : 1;
                } // 不同的需求，在这一段{ }之间修改
                follow = follow.fail;
            }
        }
        return ans;
    }

    // 返回包含的敏感词
    public List<String> containWord(String content) {
        char[] str = content.toCharArray();
        Node cur = root;
        Node follow = null;
        List<String> ans = new ArrayList<>();
        for (char c : str) {
            int path = c - 'a';

            // 匹配不到了，赶紧跳到下个好的开始点
            while (cur.nexts[path] == null && cur != root) {
                cur = cur.fail;
            }
            // 当前往下匹配
            cur = cur.nexts[path] != null ? cur.nexts[path] : root;

            follow = cur;   // dhe  he    dh  h     e e     分别收集答案
            while (follow != root) {
                { // 不同的需求，在这一段{ }之间修改
                    if (follow.endWorld != null) {
                        ans.add(follow.endWorld);
                    }
                } // 不同的需求，在这一段{ }之间修改
                follow = follow.fail;
            }
        }
        return ans;
    }

    // 测试
    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("c");
        ac.build();
        System.out.println(ac.containNum("cdhe"));
        System.out.println(ac.containWord("cdhe"));
    }

}
