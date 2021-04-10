package com.zhangsan.no_ac;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhangsan
 * @date 2021/4/10 23:08
 */
public class Code01_AC1 {

    private static class Node {
        public String end;      // 如果这个节点是end, 就是整条路的值
        public boolean endUse;  // 只有end有值, 如果为true表示这个字符串匹配过
        public Node fail;
        public Node[] nexts;
        public Node() {
            nexts = new Node[26];
        }
    }

    public static class ACAutomation {
        private Node root;
        public ACAutomation() {
            root = new Node();
        }

        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            int path;
            for (char c : str) {
                path = c - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
            cur.end = s;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            Node cur, cfail;
            while (!queue.isEmpty()) {
                // cur -> 父节点
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    // 某个儿子节点不为空
                    if(cur.nexts[i] != null) {
                        // 先设置儿子节点fail指针指向root, 如果找到父节点沿着fail指针找到有fail的就将儿子的fail指针指向那个节点.
                        cur.nexts[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            if(cfail.nexts[i] != null) {
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public List<String> containWords(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';   // 路
                // 如果当前字符在这条路上没配出来，就随着fail方向走向下条路径
                while (cur.nexts[index] == null && cur != root)
                    cur = cur.fail;
                // 1) 现在来到的路径，是可以继续匹配的
                // 2) 现在来到的节点，就是前缀树的根节点
                cur = cur.nexts[index] != null? cur.nexts[index]: root;
                follow = cur;
                // 没来到一个字符都匹配一圈
                while (follow != root) {
                    if (follow.endUse) {
                        break;
                    }
                    // 不同的需求，在这一段之间修改
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.endUse = true;
                    }
                    // 不同的需求，在这一段之间修改
                    follow = follow.fail;
                }
            }
            return ans;
        }

    }

    // for test
    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }

}
