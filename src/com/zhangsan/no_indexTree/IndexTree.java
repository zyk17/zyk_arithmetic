package com.zhangsan.no_indexTree;

/**
 * index tree 结构
 * 例子: 计算累加和, 原数组的可以更改然后还能快速计算
 *
 * @author zhangsan
 * @date 2021/3/22 11:24
 */
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


// for test
class TestIndexTree {

    // 对数器, 比对方法
    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static void main(String[] args) {
        int size = 8;
        IndexTree indexTree = new IndexTree(size);
        Right right = new Right(size);


        indexTree.add(1, 8);
        right.add(1, 8);
        System.out.println(indexTree.sum(8));
        System.out.println(right.sum(8));

        indexTree.add(2, 6);
        right.add(2, 6);
        System.out.println(indexTree.sum(8));
        System.out.println(right.sum(8));

        indexTree.add(3, 9);
        right.add(3, 9);
        System.out.println(indexTree.sum(8));
        System.out.println(right.sum(8));

        indexTree.add(1, 8);
        right.add(1, 8);
        System.out.println(indexTree.sum(8));
        System.out.println(right.sum(8));

    }
}
