package com.zhangsan.util;

public class IndexTree {

    int[] src;
    int[] tree;
    int N;

    public IndexTree(int size) {
        this.N = size;
        src = new int[N + 1];
        tree = new int[N + 1];
    }

    // i & -i : 提取出i最右侧的1出来
    public void add(int i, int num) {
        while (i <= N) {
            tree[i] += num;
            i += i & -i;
        }
    }

    /** 1~i的累加和 */
    public int sum(int i) {
        int ret = 0;
        while (i > 0) {
            ret += tree[i];
            i -= i & -i;
        }
        return ret;
    }

}
