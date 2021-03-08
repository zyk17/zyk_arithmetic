package com.zhangsan.no_12_monotonousStack;

import java.util.Stack;

/**
 *
 * 给定一个数组, 代表线型图的每个位置的高度, 求这个线型图中最大的长方形面积.
 *
 * [5, 3, 2, 5, 2, 4]
 * 口       口
 * 口       口    口
 * 口 口    口    口
 * 口 口 口 口 口  口
 * 口 口 口 口 口  口
 *
 * @author zhangsan
 * @date 2021/3/8 16:35
 */
public class Code03_LargestRectangleInHistogram {

    public static int max(int[] arr) {
        // 计算以每个列都为高度, 往左右扩(如果比他高)的面积.中最大的
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int height = arr[stack.pop()];
                /*int l = stack.isEmpty()? -1: stack.peek();
                int r = i;
                int width = i - l - 1;*/
                int width = i - 1 - (stack.isEmpty()? -1: stack.peek());
                maxArea = Math.max( maxArea, width*height );
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int height = arr[stack.pop()];

            /*int l = stack.isEmpty()? 0 : stack.peek()+1;
            int r = arr.length;
            int width = r - l;*/
            int width = arr.length - (stack.isEmpty()? 0 : stack.peek()+1);

            maxArea = Math.max( maxArea, width*height );
        }

        return maxArea;
    }

    // for test
    public static void main(String[] args) {
        int[] arr = {5, 3, 3, 5, 6, 4, 2};
        int r1 = max(arr);
        System.out.println(r1);
    }

}
