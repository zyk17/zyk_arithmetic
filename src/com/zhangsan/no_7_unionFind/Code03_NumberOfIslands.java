package com.zhangsan.no_7_unionFind;

import com.zhangsan.util.ArrayUnionSet;
import com.zhangsan.util.UnionSet;

import java.util.*;

/**
 * 岛屿的数量问题
 * <p>
 * 传入一个二维数组，上下左右 中连在一起的1称之为一个岛屿，共有多少个岛屿
 * <p>
 * '1' ,'1' ,'1' ,'1' ,'1' ,'0' ,'0' ,'0' ,'1' ,'1' ,1
 * '0' ,'0' ,'1' ,'1' ,'0' ,'1' ,'1' ,'0' ,'0' ,'1' ,1
 * '1' ,'0' ,'1' ,'0' ,'1' ,'1' ,'0' ,'0' ,'1' ,'0' ,1
 * '1' ,'0' ,'1' ,'0' ,'1' ,'1' ,'1' ,'0' ,'1' ,'1' ,0
 *
 * @author zhangsan
 * @date 2021/2/16 20:10
 */
public class Code03_NumberOfIslands {

    /** 做法1：递归实现，找到一个1就把附近的都感染为2. */
    public static int numbersOfIslands1(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '1') {
                    infectProcess(board, i, j);
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

    /** 做法二：使用map的并查集。 把所有的1认为是并集，遇到左边或上边为1就合并。 */
    public static int numbersOfIslands2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        Object[][] objects = new Object[row][col];
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1') {
                    Object o = new Object();
                    objects[i][j] = o;
                    list.add(o);
                }
            }
        }
        UnionSet<Object> unionSet = new UnionSet<>(list);
        for (int i = 1; i < row; i++) {
            // 行遍历。只做第一列。向上
            if(board[i][0]  == '1' && '1' ==  board[i - 1][0]) {
                unionSet.union(objects[i][0], objects[i-1][0]);
            }
        }
        for (int i = 1; i < col; i++) {
            // 列遍历。只做第一行。向左
            if(board[0][i]  == '1' && '1' ==  board[0][i - 1]) {
                unionSet.union(objects[0][i], objects[0][i - 1]);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j]  == '1' && '1' ==  board[i][j - 1]) {
                    unionSet.union(objects[i][j], objects[i][j - 1]);
                }
                if (board[i][j]  == '1' && '1' ==  board[i - 1][j]) {
                    unionSet.union(objects[i][j], objects[i-1][j]);
                }
            }
        }

        return unionSet.sets();
    }

    /** 做法二：使用数组的并查集。 把所有的1认为是并集，遇到左边或上边为1就合并。 */
    public static int numbersOfIslands3(char[][] board) {
        int row = board.length, col = board[0].length;
        Object[][] objects = new Object[row][col];
        Map<Object, Integer> indexMap = new HashMap<>();
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1') {
                    Object o = new Object();
                    objects[i][j] = o;
                    indexMap.put(o, index++);
                }
            }
        }
        ArrayUnionSet unionSet = new ArrayUnionSet(indexMap.size());
        for (int i = 1; i < row; i++) {
            // 行遍历。只做第一列。向上
            if(board[i][0] == '1' && '1' == board[i - 1][0]) {
                unionSet.union( indexMap.get(objects[i][0]), indexMap.get(objects[i-1][0]) );
            }
        }
        for (int i = 1; i < col; i++) {
            // 列遍历。只做第一行。向左
            if(board[0][i]  == '1' && '1' ==  board[0][i - 1]) {
                unionSet.union(indexMap.get(objects[0][i]), indexMap.get(objects[0][i - 1]));
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j]  == '1' && '1' ==  board[i][j - 1]) {
                    unionSet.union(indexMap.get(objects[i][j]), indexMap.get(objects[i][j - 1]));
                }
                if (board[i][j]  == '1' && '1' ==  board[i - 1][j]) {
                    unionSet.union(indexMap.get(objects[i][j]), indexMap.get(objects[i-1][j]));
                }
            }
        }

        return unionSet.sets();
    }


    public static void main(String[] args) {
        /*char[][] board = {
                {'1', '1', '1', '1', '1', '0', '0', '0', '1', '1', '1'},
                {'0', '0', '1', '1', '0', '1', '1', '0', '0', '1', '1'},
                {'1', '0', '1', '0', '1', '1', '0', '0', '1', '0', '1'},
                {'1', '0', '1', '0', '1', '1', '1', '0', '1', '1', '0'}
        };
        char[][] board1 = copyBoard(board);

        long s1 = System.currentTimeMillis();
        System.out.print("结果：" + numbersOfIslands1(board));
        long e1 = System.currentTimeMillis();
        System.out.print("\t递归污染方法耗时:" + (e1-s1) + "毫秒\n");
        long s2 = System.currentTimeMillis();
        System.out.print("结果：" + numbersOfIslands2(board1));
        long e2 = System.currentTimeMillis();
        System.out.print("\t并查集map结构方法耗时:" + (e2-s2) + "毫秒\n");
        long s3 = System.currentTimeMillis();
        System.out.print("结果：" + numbersOfIslands3(board1));
        long e3 = System.currentTimeMillis();
        System.out.print("\t并查集数组结构方法耗时:" + (e3-s3) + "毫秒\n");*/

        int times = 10000;
        int row = 1000;
        int col = 1000;
        for (int i = 0; i < times; i++) {
            char[][] t1 = generateBoard(row, col);
            char[][] t2 = copyBoard(t1);
            char[][] t3 = copyBoard(t1);


            long s1 = System.currentTimeMillis();
            System.out.print("结果：" + numbersOfIslands1(t1));
            long e1 = System.currentTimeMillis();
            System.out.print("\t递归污染方法耗时:" + (e1-s1) + "毫秒\n");
            long s2 = System.currentTimeMillis();
            System.out.print("结果：" + numbersOfIslands2(t2));
            long e2 = System.currentTimeMillis();
            System.out.print("\t并查集map结构方法耗时:" + (e2-s2) + "毫秒\n");
            long s3 = System.currentTimeMillis();
            System.out.print("结果：" + numbersOfIslands3(t3));
            long e3 = System.currentTimeMillis();
            System.out.print("\t并查集数组结构方法耗时:" + (e3-s3) + "毫秒\n");
            System.out.println("=======================");
        }

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

}
