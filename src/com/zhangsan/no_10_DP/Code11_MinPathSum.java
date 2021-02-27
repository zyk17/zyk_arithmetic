package com.zhangsan.no_10_DP;

import com.zhangsan.util.ArrayUtil;

/**
 * m*n的矩阵
 * 向右向下,都有路径
 * 从左上角到右下角, 只能向右向下走
 * 怎么走,路径最少
 *
 * @author zhangsan
 * @date 2021/2/27 20:32
 */
public class Code11_MinPathSum {

    /** 暴力递归 */
    public static int minPath(int[][] m) {
        int row = m.length;
        int col = m[0].length;
        return process(m, 0, 0, row, col);
    }

    public static int process(int[][] m, int r, int c, int row, int col) {
        if (r == row || c == col) { return Integer.MAX_VALUE; }
        if (r == row - 1 && c == col - 1) {
            return m[r][c];
        }
        int path = m[r][c];
        int rightPath = process(m, r, c + 1, row, col);
        int bottomPath = process(m, r+1, c, row, col);
        return path + Math.min( rightPath, bottomPath );
    }

    /** dp */
    public static int minPath2(int[][] m) {
        int row = m.length;
        int col = m[0].length;
        // 最后一行, 倒数第二个开始
        for (int c = col-2; c >= 0; c--) {
            m[row-1][c] += m[row-1][c+1];
        }
        // 最后一列, 倒数第二个开始
        for (int r = row-2; r >= 0; r--) {
            m[r][col-1] += m[r+1][col-1];
        }
        // 填充剩余的, 依赖下边和右边去最小的+自己
        for (int r = row-2; r >= 0; r--) {
            for (int c = col-2; c >= 0; c--) {
                m[r][c] += Math.min( m[r+1][c], m[r][c+1] );
            }
        }

        return m[0][0];
    }

    /** dp2, 省空间, 上边dp其实需要一个等规模的辅助数组, 我在原数组上边更改值了所以没有使用额外空间,但其实那个算法需要等规模额外辅助数组
     * 下边这种只要n*m 中1个m|n的数组就够了
     */
    public static int minPath3(int[][] m) {
        int row = m.length;
        int col = m[0].length;
        // 可以判断row和col那个小,创建哪个dp数组,两种赋值方法,其实都差不多,一种从下往上推,一种从右往左推
        // 思路差不多,就只写一种
        int[] dp = new int[col];
        // 先推出来最后一行
        dp[col-1] = m[row-1][col-1];
        for (int c = col-2; c >= 0; c--) {
            dp[c] = dp[c+1] + m[row-1][c] ;
        }

        for (int r = row-2; r >= 0; r--) {
            dp[col-1] += m[r][col-1];
            for (int c = col-2; c >= 0; c--) {
                dp[c] = m[r][c] + Math.min( dp[c], dp[c+1] );
            }
        }

        return dp[0];
    }


    public static void main(String[] args) {
        int[][] m = {
                { 3, 7, 8, 5},
                { 1, 2, 6, 4},
                { 10, 3, 8, 9},
                { 8, 1, 2, 0},
        };
        int[][] mCopy  = new int[m.length][];
        for (int i = 0; i < mCopy.length; i++) {
            mCopy[i] = ArrayUtil.copyArr(m[i]);
        }

        System.out.println(minPath(m));
        // 这个方法,我在原数组上操作了
        System.out.println(minPath2(mCopy));
        System.out.println(minPath3(m));
    }

}
