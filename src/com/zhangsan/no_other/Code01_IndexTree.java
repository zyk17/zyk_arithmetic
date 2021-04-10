package com.zhangsan.no_other;

/**
 * @author zhangsan
 * @date 2021/4/10 18:43
 */
public class Code01_IndexTree {

    public static class IndexTree {
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
        com.zhangsan.no_other.IndexTree indexTree = new com.zhangsan.no_other.IndexTree(size);
        TestIndexTree.Right right = new TestIndexTree.Right(size);


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
