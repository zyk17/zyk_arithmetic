package com.zhangsan.util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的工具类
 *
 * @author zhangsan
 * @date 2021/2/13 15:38
 */
public class TreeUtil {

    /**
     * 生成一个随机的二叉树
     */
    public static BinaryTree generateRandomTree(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static BinaryTree generate(int level, int maxLevel, int maxValue) {
        if(level > maxLevel || Math.random() < 0.1){
            return null;
        }
        BinaryTree head = new BinaryTree( (int) (Math.random() * maxValue + 1) );
        head.left = generate( level+1, maxLevel, maxValue );
        head.right = generate( level+1, maxLevel, maxValue );
        return head;
    }

    public static BinaryTree generateRandomTree2(int maxLevel, int maxValue) {
        maxLevel = (int) (Math.random() * maxLevel + 1);
        if( Math.random() < 0.02 ) {
            // 百分之2的概率返回空
            return null;
        }
        BinaryTree head = new BinaryTree( (int) (Math.random() * maxValue + 1) );

        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(head);

        BinaryTree cur = null;
        BinaryTree left = null;
        BinaryTree right = null;

        BinaryTree nextEnd = null;
        BinaryTree curEnd = head;
        int curLevel = 1;
        while (! queue.isEmpty()) {
            if(curLevel == maxLevel) {
                break;
            }
            cur = queue.poll();
            left = null;
            right = null;
            if(cur == null) { continue; }
            if( Math.random() > 0.1 ) {
                left = new BinaryTree( (int) (Math.random() * maxValue + 1) );
            }
            if( Math.random() > 0.1 ) {
                right = new BinaryTree( (int) (Math.random() * maxValue + 1) );
            }
            cur.left = left;
            cur.right = right;
            queue.add(left);
            queue.add(right);

            if(left != null || right != null) {
                nextEnd = right != null? right: left;
            }

            if(curEnd == cur) {
                curEnd = nextEnd;
                curLevel++;
            }
        }
        return head;
    }

    public static BinaryTree copyBT(BinaryTree head) {
        return copyProcess(head);
    }

    private static BinaryTree copyProcess(BinaryTree head) {
        if(head == null) { return null; }
        BinaryTree target = new BinaryTree(head.value);
        target.left = copyProcess(head.left);
        target.right = copyProcess(head.right);
        return target;
    }

    public static BinaryTree copyBT2(BinaryTree head) {
        if(head == null) { return null; }

        Queue<BinaryTree> queue = new LinkedList<>();
        Queue<BinaryTree> queue2 = new LinkedList<>();
        queue.add(head);

        BinaryTree cp = new BinaryTree(head.value);
        queue2.add(cp);
        BinaryTree target;
        while (! queue.isEmpty()) {
            head = queue.poll();
            target = queue2.poll();
            if(head.left != null) {
                queue.add(head.left);
                BinaryTree targetLeft = new BinaryTree(head.left.value);
                queue2.add(targetLeft);
                target.left = targetLeft;
            }
            if(head.right != null) {
                queue.add(head.right);
                BinaryTree targetRight = new BinaryTree(head.right.value);
                queue2.add(targetRight);
                target.right = targetRight;
            }
        }
        return cp;
    }

    public static void pre(BinaryTree head) {
        if(head == null) {
            return;
        }
        System.out.print(head.value + " ");
        pre(head.left);
        pre(head.right);
    }

    public static void in(BinaryTree head) {
        if(head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.value + " ");
        in(head.right);
    }

    public static void pos(BinaryTree head) {
        if(head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.value + " ");
    }

    public static void level(BinaryTree head) {
        if(head == null) {
            return;
        }
        System.out.println("按层遍历:");
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(head);

        BinaryTree nextEnd = null;
        BinaryTree curEnd = head;
        while (! queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if(head.left != null) {
                queue.add(head.left);
                nextEnd = head.left;
            }
            if(head.right != null) {
                queue.add(head.right);
                nextEnd = head.right;
            }


            if(curEnd == head) {
                System.out.println();
                curEnd = nextEnd;
            }
        }
        System.out.println();
    }

    public static void level1(BinaryTree head) {
        if(head == null) {
            return;
        }
        System.out.println("按层遍历:");
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(head);
        while (! queue.isEmpty()) {
            head = queue.poll();
            System.out.print(head.value + " ");
            if(head.left != null) {
                queue.add(head.left);
            }
            if(head.right != null) {
                queue.add(head.right);
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            BinaryTree binaryTree = generateRandomTree2(6, 90);
            BinaryTree binaryTree2 = copyBT(binaryTree);
            level(binaryTree);
            level(binaryTree2);
            System.out.println();
        }

    }
}
