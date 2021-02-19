package com.zhangsan.no_9_recursion;

import java.util.Stack;

/**
 * 使用递归反转栈,不使用任何额外数据结构
 * 栈：先进后出
 * @author zhangsan
 * @date 2021/2/18 21:23
 */
public class Code04_ReverseStackByRecursive {

    public static void reverseStack(Stack<Integer> stack) {
        if(stack == null || stack.isEmpty()) {
            return;
        }
        int i = getBottomStack(stack);
        System.out.println(i);
        reverseStack(stack);
        stack.add(i);
    }

    private static int getBottomStack(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if(stack.isEmpty()) {
            return pop;
        }
        int last = getBottomStack(stack);
        stack.add(pop);
        return last;
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        /*while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "\t");
        }
        System.out.println();*/
        System.out.println(stack);

        reverseStack(stack);

        System.out.println(stack);
        /*while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "\t");
        }
        System.out.println();*/
    }

}
