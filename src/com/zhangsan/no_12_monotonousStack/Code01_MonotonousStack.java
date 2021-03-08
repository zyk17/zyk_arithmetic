package com.zhangsan.no_12_monotonousStack;

import com.zhangsan.util.ArrayUtil;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 单调栈结构
 * @author zhangsan
 * @date 2021/3/8 12:41
 */
public class Code01_MonotonousStack {


    /** 找到数组中每个数 左边离他最近的下标,和右离他最近的下标, 没有重复元素的情况 */
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int N = arr.length;
        int[][] ans = new int[N][2];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                Integer cur = stack.pop();
                ans[cur][1] = i;
                ans[cur][0] = stack.isEmpty()? -1 : stack.peek();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer last = stack.pop();
            ans[last][1] = -1;
            ans[last][0] = stack.isEmpty()? -1 : stack.peek();
        }
        return ans;
    }

    /** 找到数组中每个数 左边离他最近的下标,和右离他最近的下标,有重复元素情况 */
    public static int[][] getNearLessHasRepeat(int[] arr) {
        int N = arr.length;
        int[][] ans = new int[N][2];

        Stack<Deque<Integer>> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().peekLast()]) {
                Deque<Integer> cur = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().peekLast();
                for (Integer curIndex : cur) {
                    ans[curIndex][1] = i;
                    ans[curIndex][0] = leftLessIndex;
                }
            }

            if(!stack.isEmpty() && arr[i] == arr[stack.peek().peekLast()]) {
                Deque<Integer> deque = stack.peek();
                deque.add(i);
            }else {
                Deque<Integer> deque = new LinkedList<>();
                deque.add(i);
                stack.push(deque);
            }
        }

        while (!stack.isEmpty()) {
            Deque<Integer> last = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().peekLast();
            for (Integer lastIndex : last) {
                ans[lastIndex][1] = -1;
                ans[lastIndex][0] = leftLessIndex;
            }
        }
        return ans;
    }



    // for test
    public static void main(String[] args) {
        /*int[] arr = {5,3,6,9,7,2,8};
        int[][] r1 = getNearLessNoRepeat(arr);
        for (int[] a : r1) {
            ArrayUtil.printArr(a);
        }*/
        int[] arr = {5,3,6,3,7,2,8,3,2,9};
        int[][] r1 = getNearLessHasRepeat(arr);
        for (int[] a : r1) {
            ArrayUtil.printArr(a);
        }
    }

}
