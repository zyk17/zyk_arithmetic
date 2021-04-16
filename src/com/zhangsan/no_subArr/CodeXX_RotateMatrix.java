package com.zhangsan.no_subArr;

import com.zhangsan.util.ArrayUtil;

/**
 * 旋转正方形
 *
 * @author zhangsan
 * @date 2021/4/16 22:24
 */
public class CodeXX_RotateMatrix {

    public static void rotate(int[][] matrix) {
        // ab左上角, cd右下角     a, c行坐标, bd列坐标
        int a = 0, b = 0, c = matrix.length - 1, d = matrix[0].length - 1;
        if (c != d) return;  // 不是正方形不能旋转
        while (a < c) {
//            leftRotate(matrix, a++, b++, c--, d--);     // 四个角代表一个方块边缘调整
            rightRotate(matrix, a++, b++, c--, d--);     // 四个角代表一个方块边缘调整
        }
    }

    // 左旋
    private static void leftRotate(int[][] matrix, int a, int b, int c, int d) {
        int temp;
        for (int i = 0; i < c - a; i++) {
            temp = matrix[a][b + i];
            matrix[a][b + i] = matrix[a + i][d];    // 左上角右边第几个 = 右上角下边第几个
            matrix[a + i][d] = matrix[c][d - i];    // 右上角下边第几个 = 右下角左边第几个
            matrix[c][d - i] = matrix[c - i][b];    // 右下角左边第几个 = 左下角上边第几个
            matrix[c - i][b] = temp;                // 左下角上边第几个 = 左上角右边第几个
        }
    }

    private static void rightRotate(int[][] matrix, int a, int b, int c, int d) {
        int temp;
        for (int i = 0; i < c - a; i++) {
            temp = matrix[a][b + i];                // 左上角右边第几个
            matrix[a][b + i] = matrix[c - i][b];    // 左上角右边第几个 = 右上角下边第几个
            matrix[c - i][b] = matrix[c][d - i];    // 右下角左边第几个 = 左下角上边第几个
            matrix[c][d - i] = matrix[a + i][d];    // 左下角上边第几个 = 右下角左边第几个
            matrix[a + i][d] = temp;    // 右上角下边第几个 = 左上角右边第几个
        }
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int[] nums : matrix) {
            ArrayUtil.printArr(nums);
        }
    }

    // for test
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        int num = 10;
        for (int[] nums : matrix) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = num++;
            }
        }
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("旋转后==================");
        printMatrix(matrix);
    }

}
