package com.zhangsan.no_7_unionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 岛屿问题3
 * 基数特别大，并行解决，差分然后算出在合并
 * 比如一张世界地图，陆地为0，有水地方为1，求所有水的地方。基数特别大，单个cpu解决特别慢，并集解决
 * 难点:差分,计算完后在合并
 *
 * @author zhangsan
 * @date 2021/2/17 14:48
 */
public class Code05_NumberOfIslandslll {

    /** 做法1：递归实现，找到一个1就把附近的都感染为2. */
    public static int numbersOfIslands1(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '1') {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void infectProcess(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length || board[i][j] != '1') {
            return;
        }
        board[i][j] = 0;
        infectProcess(board, i - 1, j);
        infectProcess(board, i + 1, j);
        infectProcess(board, i, j - 1);
        infectProcess(board, i, j + 1);
    }

    public static class MyComputeTask implements Callable<Integer> {
        char[][] board;
        public MyComputeTask(char[][] board) {
            this.board = board;
        }
        @Override
        public Integer call() throws Exception {
            return numbersOfIslands1(board);
        }
    }

    /** 拆分多线程解 4个线程，按行拆.固定行为16行切成4个线程操作。因为这台机器为物理4核这样最快，或机器无限制可以动态算行  */
    public static int numbersOfIslands2(char[][] board) throws ExecutionException, InterruptedException {
        // 固定线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        List<Future<Integer>> rList = new ArrayList<>(4);
        char[][] board1 = copyBoard(board, 0, 3);
        char[][] board2 = copyBoard(board, 4, 7);
        char[][] board3 = copyBoard(board, 8, 11);
        char[][] board4 = copyBoard(board, 12, 15);

        rList.add(threadPool.submit(new MyComputeTask(board1)));
        rList.add(threadPool.submit(new MyComputeTask(board2)));
        rList.add(threadPool.submit(new MyComputeTask(board3)));
        rList.add(threadPool.submit(new MyComputeTask(board4)));

        int sum = 0;
        for (Future<Integer> future : rList) {
            sum += future.get();
        }
        System.out.println(sum);
        for (char[] chars : board) {
            for (char c : chars) {
                System.out.print(c + "\t");
            }
            System.out.println();
        }

        for (int i = 0; i < board[0].length; i++) {
            if(board[3][i] == 0 && board[4][i] == 0) {
                sum--;
            }
            if(board[7][i] == 0 && board[8][i] == 0) {
                sum--;
            }
            if(board[11][i] == 0 && board[12][i] == 0) {
                sum--;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        char[][] board = {
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '0', '0', '0', '0', '0', '0'},
                {'1', '0', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '0', '1', '1'},
                {'1', '0', '1', '1', '1', '0', '1', '1'},
                {'1', '0', '1', '1', '1', '0', '1', '1'},
                {'1', '0', '1', '1', '1', '0', '1', '1'},
                {'1', '0', '1', '1', '1', '0', '1', '1'},
                {'1', '0', '1', '1', '1', '0', '1', '1'},
                {'1', '0', '0', '0', '0', '0', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '0', '1', '1'},
                {'1', '0', '1', '1', '1', '0', '1', '1'},
                {'1', '0', '0', '0', '0', '0', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1'}
        };
        char[][] board1 = copyBoard(board);
        long s1 = System.nanoTime();
        int r1 = numbersOfIslands1(board);
        long s2 = System.nanoTime();
        System.out.println("递归感染单线程，结果：" + r1 + "时间:" + (s2-s1) + "纳秒");
        long s3 = System.nanoTime();
        int r2 = numbersOfIslands2(board1);
        long s4 = System.nanoTime();
        System.out.println("递归感染多线程，结果：" + r2 + "时间:" + (s4-s3) + "纳秒");
    }

    private static char[][] copyBoard(char[][] board) {
        char[][] board1 = new char[board.length][];
        for (int i = 0; i < board1.length; i++) {
            board1[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return board1;
    }

    private static char[][] generateBoard(int row, int col) {
        char[][] board = new char[row][col];
        for (char[] chars : board) {
            for (int i = 0; i < chars.length; i++) {
                chars[i] = (char) (Math.random()>0.5? 49: 48);
            }
        }
        return board;
    }

    /** 复制的是引用，返回结果原数组第 0-3行 4-7行 n-m的引用。不是新的数组 */
    private static char[][] copyBoard(char[][] board, int rowStart, int rowEnd) {
        char[][] board1 = new char[rowEnd-rowStart+1][];
        int i = 0;
        for (; rowStart <= rowEnd; rowStart++) {
            board1[i++] = board[rowStart];
        }
        return board1;
    }
}
