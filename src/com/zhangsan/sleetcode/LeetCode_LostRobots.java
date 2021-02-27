package com.zhangsan.sleetcode;

import com.zhangsan.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 迷路的机器人
 * https://leetcode-cn.com/problems/robot-in-a-grid-lcci/
 *
 * @author zhangsan
 * @date 2021/2/27 18:02
 */
public class LeetCode_LostRobots {
    public static List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        Stack<List<Integer>> result = new Stack<>();
        List<List<Integer>> ans = new ArrayList<>();
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        process(obstacleGrid, 0, 0, row, col, result);
        while (!result.isEmpty()) {
            ans.add(result.pop());
        }
        return ans;
    }

    public static boolean process(int[][] obstacleGrid, int r, int c, int row, int col, List<List<Integer>> result) {
        // base case
        if (r == row || c == col || obstacleGrid[r][c] == 1) { return false; }
        ArrayList<Integer> way = new ArrayList<>();
        way.add(r);
        way.add(c);
        if (r == row - 1 && c == col - 1) {
            result.add(way);
            return true;
        }
        boolean isOk = process(obstacleGrid, r + 1, c, row, col, result) ||
                process(obstacleGrid, r, c + 1, row, col, result);
        if (isOk) {
            result.add(way);
        }
        return isOk;
    }


    public static List<List<Integer>> pathWithObstacles2(int[][] obstacleGrid) {
        Stack<List<Integer>> result = new Stack<>();
        List<List<Integer>> ans = new ArrayList<>();
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[][] dp = new int[row + 1][ col + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        boolean isOk = process2(obstacleGrid, 0, 0, row, col, result, dp);
        if(isOk) {
            while (!result.isEmpty()) {
                ans.add(result.pop());
            }
        }
        return ans;
    }

    public static boolean process2(int[][] obstacleGrid, int r, int c, int row, int col, List<List<Integer>> result, int[][] dp) {
        // base case
        if (r == row || c == col || obstacleGrid[r][c] == 1 ) { dp[r][c] = 0; return false; }
        if( dp[r][c] != -1 ) {
            return dp[r][c] == 1;
        }

        ArrayList<Integer> way = new ArrayList<>();
        way.add(r);
        way.add(c);
        if (r == row - 1 && c == col - 1) {
            result.add(way);
            dp[r][c] = 1;
            return true;
        }
        boolean isOk = process2(obstacleGrid, r + 1, c, row, col, result, dp) ||
                process2(obstacleGrid, r, c + 1, row, col, result, dp);
        if (isOk) {
            result.add(way);
        }
        dp[r][c] = isOk? 1: 0;
        return isOk;
    }

    /** dp */
    public static List<List<Integer>> pathWithObstacles3(int[][] obstacleGrid) {
        List<List<Integer>> ans = new ArrayList<>();
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        if( obstacleGrid[row-1][col-1] == 1 ) {
            return ans;
        }
        // 从最后一排往左遍历有 下边或右边有1 则它变为1
        // 先做最后一行,只看右边
        for (int c = col-3; c >=0 ; c--) {
            if( obstacleGrid[row-1][c+1] == 1 ) {
                obstacleGrid[row-1][c] = 1;
            }
        }
        // 最后1列,只看下边
        for (int r = row-3; r >=0 ; r--) {
            if( obstacleGrid[r+1][col-1] == 1 ) {
                obstacleGrid[r][col-1] = 1;
            }
        }
        for (int i = row-2; i >= 0; i--) {
            for (int j = col-2; j >= 0; j--) {
                if( obstacleGrid[i][j+1] == 1 && obstacleGrid[i+1][j] == 1 ) {
                    obstacleGrid[i][j] = 1;
                }
            }
        }

        for (int[] arr : obstacleGrid) {
            ArrayUtil.printArr(arr);
        }
        if( obstacleGrid[0][0] == 0 ) {
            // 可以走
            int r = 0;
            int c = 0;
            List<Integer> way = new ArrayList<>();
            way.add(r);
            way.add(c);
            ans.add(way);
            while ( r< row && c<col ) {
                while ( r<row-1 && obstacleGrid[r+1][c] == 0 ) {
                    way = new ArrayList<>();
                    r += 1;
                    way.add(r);
                    way.add(c);
                    ans.add(way);
                }
                while ( c<col-1 && obstacleGrid[r][c+1] == 0 ) {
                    way = new ArrayList<>();
                    c += 1;
                    way.add(r);
                    way.add(c);
                    ans.add(way);
                }
                if( r==row-1 && c==col-1 ) {
                    break;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] obstacleGrid =
//                {{0,0,0,0,0},{0,0,0,0,1},{0,0,0,1,0},{0,0,1,0,0}};
//                {{0,0,0,0,0},{0,0,0,0,1},{0,0,0,1,0},{0,0,0,0,0}};
                {{0, 1}};

        System.out.println("暴力递归: ");
        List<List<Integer>> r = pathWithObstacles(obstacleGrid);
        for (List<Integer> way : r) {
            System.out.println(way);
        }
        System.out.println("记忆化搜索: ");
        List<List<Integer>> r2 = pathWithObstacles2(obstacleGrid);
        for (List<Integer> way : r2) {
            System.out.println(way);
        }

        System.out.println("dp: ");
        List<List<Integer>> r3 = pathWithObstacles3(obstacleGrid);
        for (List<Integer> way : r3) {
            System.out.println(way);
        }
    }


}
