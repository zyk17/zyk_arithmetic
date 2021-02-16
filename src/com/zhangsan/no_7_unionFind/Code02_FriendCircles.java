package com.zhangsan.no_7_unionFind;

/**
 * 给定一个二维数组
 * (数组)和(数组中的数组)长度一样。表示1-n个人互相认不认识
 * 如果 i认识j，那么j也必定认识i
 * 请问他们中有多少个朋友圈
 * 1认识2，认识3。 2认识1，不认识3. 但1,2 3 算是一个朋友圈。有共同认识的人
 * <p>
 * 参数例子:
 * 0代表认识，1代表不认识。 1-5个人之间的关系
 * 0,   1,  2   3   4   5
 * 1,   1,  1,  0,  1,  0
 * 2    1,  1,  0,  0,  1
 * 3    0,  0,  1,  0,  0
 * 4    1,  0,  0,  1,  0
 * 5    0,  1,  0,  0,  1
 *
 * @author zhangsan
 * @date 2021/2/16 18:38
 */
public class Code02_FriendCircles {

    public static int friendCircles(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        int n = m.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (m[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets();
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
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

        /** 是否同一组 */
        public boolean isSameSet(int a, int b) {
            return findHead(a) == findHead(b) ;
        }

        public void union(int i, int j) {
            int headI = findHead(i);
            int headJ = findHead(j);
            if( headI != headJ ) {
                if(size[ headI ] >= size[ headJ ]) {
                    parent[headJ] = headI;
                    size[headI] += size[headJ];
                    size[headJ] = 0;
                }else {
                    parent[headI] = headJ;
                    size[headJ] += size[headI];
                    size[headI] = 0;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }


    public static void main(String[] args) {
        int[][] m = {
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1},
        };
        System.out.println(friendCircles(m));
    }

}
