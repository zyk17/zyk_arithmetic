package com.zhangsan.no_7_unionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * 空降岛屿
 * row行col列的矩阵。
 * positions传入空降点
 * 每空降一下输出一下有多少做岛屿
 *
 * 例子：
 * 输入：   m:3, n:3, positions: [0,0],[0,1],[1,2],[2,1]
 * 输出：   [1,1,2,3]
 * @author zhangsan
 * @date 2021/2/16 20:10
 */
public class Code04_NumberOfIslandsll {

    public static List<Integer> numbersOfIslands1(int row, int col, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind uf = new UnionFind(row, col);
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int row;
        private int col;
        private int sets;

        public UnionFind(int row, int col) {
            this.row = row;
            this.col = col;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        /** 空降位置的索引 */
        private int index (int row, int col) {
            return row * this.col + col;
        }

        private int findHead(int i) {
            // 减少寻址的时间，即把i所在的并集中的元素的父亲都设置成为i的头，然后在寻找它们的时候就能很快地找到那个头
            int helpIndex= 0;
            while (i != parent[i]) {
                help[helpIndex++] = i;
                i = parent[i];
            }
            while (helpIndex > 0) {
                parent[ help[--helpIndex] ] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            // 如果目标不合法，直接结束
            if( r1 < 0 || r1 >= this.row || c1 < 0 || c1 >= this.col ) {
                return;
            }
            // 拿到两个位置的并查集中的下标
            int targetIndex = index(r1, c1), myIndex = index(r2, c2);
            // 如果目标尚未初始化，直接结束
            if(size[targetIndex] == 0) {
                return;
            }

            // 找到目标和自己的头节点
            int head1 = findHead(targetIndex), head2 = findHead(myIndex);
            // 找到两个并集的大小
            int size1 = size[head1], size2 = size[head2];

            // 合并两个并集
            if(size1 >= size2) {
                parent[head2] = head1;
                size[head1] += size2;
            } else {
                parent[head1] = head2;
                size[head2] += size1;
            }
            sets--;
        }

        public int connect(int row, int col) {
            int index = index(row, col);
            // 如果尚未初始化，进行初始化，并直接合并上下左右
            // 如果初始化过，直接跳过
            if(size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(row - 1, col, row, col);
                union(row + 1, col, row, col);
                union(row, col -1 , row, col);
                union(row, col + 1, row, col);
            }
            return sets;
        }

        public int sets() {
            return sets;
        }

    }


    public static void main(String[] args) {
        // 输入：   m:3, n:3, positions: [0,0],[0,1],[1,2],[2,1]
        // 输出：   [1,1,2,3]
        int row = 3;
        int col = 3;
        int[][] positions = {
                {0, 0},
                {0, 1},
                {1, 2},
                {2, 1}
        };
        List<Integer> result = numbersOfIslands1(row, col, positions);
        for (Integer r : result) {
            System.out.println(r);
        }
    }

}
