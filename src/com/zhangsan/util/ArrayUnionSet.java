package com.zhangsan.util;

public class ArrayUnionSet {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;

        public ArrayUnionSet(int n) {
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
