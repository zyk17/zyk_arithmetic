package com.zhangsan.no_7_unionFind;

import java.util.*;

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

    /** 自定义并查集，每次连接O(1)的时间复杂度, 初始化为O(row* col)
     * 但是初始化时，要准备三个row*col的数组。如果row和col过大会导致初始化时间过久 */
    public static List<Integer> numbersOfIslands1(int row, int col, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind1 uf = new UnionFind1(row, col);
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }
    public static class UnionFind1 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int row;
        private int col;
        private int sets;

        public UnionFind1(int row, int col) {
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

    /** 解决上述row和col过大，导致初始化时间复杂度高的问题 */
    public static List<Integer> numbersOfIslands2(int row, int col, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind2 uf = new UnionFind2();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }
    public static class UnionFind2 {

        private Map<String, String> parents = new HashMap<>();
        private Map<String, Integer> sizeMap = new HashMap<>();
        private int sets;

        private String findHead(String str) {
            // 优化：把寻找过程中的节点的头节点指向head
            Stack<String> stack = new Stack<>();
            while (!str.equals(parents.get(str))) {
                stack.push(str);
                str = parents.get(str);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), str);
            }
            return str;
        }

        private void union(String s1, String s2) {
            // 如果目标不存在，直接结束
            if( !parents.containsKey(s1) ) {
                return;
            }
            // 拿到大小
            int size1 = sizeMap.get(s1), size2 = sizeMap.get(s2);
            String headS1 = findHead(s1), headS2 = findHead(s2);
            // 合并两个并集
            if(size1 >= size2) {
                parents.put(headS2, headS1);
                sizeMap.put(headS1, size1+size2);
                sizeMap.remove(headS2);
            } else {
                parents.put(headS1, headS2);
                sizeMap.put(headS2, size1+size2);
                sizeMap.remove(headS1);
            }
            sets--;
        }

        public int connect(int row, int col) {
            String oneself = row + "_" + col;
            if(!parents.containsKey(oneself)) {
                parents.put(oneself, oneself);
                sizeMap.put(oneself, 1);
                sets++;

                union((row - 1) + "_" + col, oneself);
                union((row + 1) + "_" +  col, oneself);
                union(row + "_" + (col -1) , oneself);
                union(row + "_" +  (col + 1), oneself);
            }
            return sets;
        }

        public int sets() {
            return sizeMap.size();
        }

    }


    public static void main(String[] args) {
        // 输入：   m:3, n:3, positions: [0,0],[0,1],[1,2],[2,1]
        // 输出：   [1,1,2,3]
        int row = 100000000;
        int col = 3;
//        int row = 3;
//        int col = 3;
        int[][] positions = {
                {0, 0},
                {0, 1},
                {1, 2},
                {2, 1}
        };
        long s1 = System.nanoTime();
        List<Integer> r1 = numbersOfIslands1(row, col, positions);
        long s2 = System.nanoTime();
        System.out.println("先初始化结果: " + r1 + "\t耗时: " + (s2 - s1) + "纳秒");
        long s3 = System.nanoTime();
        List<Integer> r2 = numbersOfIslands2(row, col, positions);
        long s4 = System.nanoTime();
        System.out.println("省略初始化结果: " + r2 + "\t耗时: " + (s4 - s3) + "纳秒");
    }

}
