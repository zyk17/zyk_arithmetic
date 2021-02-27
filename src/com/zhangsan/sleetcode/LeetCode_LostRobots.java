package com.zhangsan.sleetcode;

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
        if (r == row || c == col) { return false; }
        if( obstacleGrid[r][c] == 1 ) { return false; }
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

    public static void main(String[] args) {
        int[][] obstacleGrid =
                {{0,0,0,0,0},{0,0,0,0,1},{0,0,0,1,0},{0,0,1,0,0}};

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
    }


}
