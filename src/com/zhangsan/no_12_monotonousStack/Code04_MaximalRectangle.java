package com.zhangsan.no_12_monotonousStack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/maximal-rectangle/
 *
 * @author zhangsan
 * @date 2021/3/8 17:48
 */
public class Code04_MaximalRectangle {


    public static int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] help = new int[col];

        int max = -1;
        for (int R = 0; R < row; R++) {
            for (int C = 0; C < help.length; C++) {
                /*if(matrix[R][C] == '1') {
                    help[C] = help[C] + 1;
                }else {
                    help[C] = 0;
                }*/
                help[C] = matrix[R][C] == '1'? help[C] + 1: 0;
            }
            max = Math.max(max, computeLargestRectangleInHistogram(help));
        }

        return max;
    }

    public static int computeLargestRectangleInHistogram(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int height = arr[stack.pop()];
                int width = i - 1 - (stack.isEmpty()? -1: stack.peek());
                maxArea = Math.max( maxArea, width*height );
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int height = arr[stack.pop()];
            int width = arr.length - (stack.isEmpty()? 0 : stack.peek()+1);

            maxArea = Math.max( maxArea, width*height );
        }

        return maxArea;
    }


    // for test
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };


        System.out.println(maximalRectangle(matrix));
    }

}
