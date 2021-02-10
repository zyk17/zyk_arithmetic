package com.zhangsan.no_5_tree;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 树的， 前序序列化与反序列化， 后序 序列化与反序列化
 * 按层序列化与反序列化
 *
 * @author zhangsan
 * @date 2021/2/10 15:45
 */
public class Code03_TreeSerialize {

    /** 前序序列化 */
    public static Queue<String> preSerialize(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(head.data);
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    /** 前序反序列化 */
    public static Node preDeserialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        return preDes(queue);
    }

    private static Node preDes(Queue<String> queue) {
        String s = queue.poll();
        if (s == null) {
            return null;
        }
        Node node = new Node(s);
        node.left = preDes(queue);
        node.right = preDes(queue);
        return node;
    }

    /** 后序序列化 */
    public static Queue<String> posSerialize(Node head) {
        Queue<String> ans = new LinkedList<>();
        poss(head, ans);
        return ans;
    }

    public static void poss(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            poss(head.left, ans);
            poss(head.right, ans);
            ans.add(head.data);
        }
    }

    /** 后序反序列化 */
    public static Node posDeserialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posDes(stack);
    }

    private static Node posDes(Stack<String> stack) {
        String s = stack.pop();
        if (s == null) {
            return null;
        }
        Node node = new Node(s);
        node.right = posDes(stack);
        node.left = posDes(stack);
        return node;
    }


    /** 按层序列化 */
    public static Queue<String> levelSerialize(Node head) {
        if (head == null) {
            return null;
        }
        Queue<Node> q1 = new LinkedList<>();
        Queue<String> ans = new LinkedList<>();
        q1.add(head);
        while (!q1.isEmpty()) {
            Node cur = q1.poll();
            if(cur == null) {
                ans.add(null);
            }else {
                ans.add(cur.data);
                q1.add(cur.left);
                q1.add(cur.right);
            }
        }
        return ans;
    }

    /** 按层反序列化 */
    public static Node levelDeserialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        Queue<Node> q2 = new LinkedList<>();
        Node ans = new Node(queue.poll());
        q2.add(ans);

        while (!q2.isEmpty() && (!queue.isEmpty()) ){
            Node cur = q2.poll();
            if(cur == null) {
                continue;
            }
            String left = queue.poll();
            String right = queue.poll();
            cur.left = left == null? null: new Node(left);
            cur.right = right == null? null: new Node(right);

            q2.add(cur.left);
            q2.add(cur.right);
        }
        return ans;
    }


    /** 按层遍历 */
    public static void level(Node head) {
        if (head == null) {
            return;
        }
        System.out.println("按层遍历:");
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.data + " ");
            if (head.left != null) {
                queue.add(head.left);
            }
            if (head.right != null) {
                queue.add(head.right);
            }
        }
        System.out.println();
    }

    public static class Node {
        String data;
        Node left;
        Node right;

        public Node(String value) {
            this.data = value;
        }
    }

}

class TestTreeSerialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Code03_TreeSerialize.Node a = new Code03_TreeSerialize.Node("a");
        Code03_TreeSerialize.Node b = new Code03_TreeSerialize.Node("b");
        Code03_TreeSerialize.Node c = new Code03_TreeSerialize.Node("c");
        Code03_TreeSerialize.Node d = new Code03_TreeSerialize.Node("d");
        Code03_TreeSerialize.Node e = new Code03_TreeSerialize.Node("e");
        Code03_TreeSerialize.Node f = new Code03_TreeSerialize.Node("f");
        Code03_TreeSerialize.Node g = new Code03_TreeSerialize.Node("g");
        a.left = b;
        a.right = c;

        b.left = d;
//        b.right = e;

        c.left = f;
        c.right = g;

//        Code03_TreeSerialize.level(a);
//        // 前序序列化
//        Queue<String> queue = Code03_TreeSerialize.preSerialize(a);
////        queue.forEach(System.out::println);
//        /*
//        // 写入文件
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("链表前序反序列化.pse"));
//        oos.writeObject(queue);
//
//        // 从文件读
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("链表前序反序列化.pse"));
//        Object o = ois.readObject();
//        Queue<String> queue = (Queue<String>) o;
//        */
//
//        // 前序反序列化
//        Code03_TreeSerialize.Node node = Code03_TreeSerialize.preDeserialize(queue);
//        Code03_TreeSerialize.level(node);

//        Code03_TreeSerialize.level(a);
//        // 后序序列化
//        Queue<String> queue = Code03_TreeSerialize.posSerialize(a);
////        queue.forEach(System.out::println);
//        // 后序反序列化
//        Code03_TreeSerialize.Node node = Code03_TreeSerialize.posDeserialize(queue);
//        Code03_TreeSerialize.level(node);

        Code03_TreeSerialize.level(a);
        // 按层序列化
        Queue<String> queue = Code03_TreeSerialize.levelSerialize(a);
        queue.forEach(System.out::println);
        // 按层反序列化
        Code03_TreeSerialize.Node node = Code03_TreeSerialize.levelDeserialize(queue);
        Code03_TreeSerialize.level(node);
    }
}
