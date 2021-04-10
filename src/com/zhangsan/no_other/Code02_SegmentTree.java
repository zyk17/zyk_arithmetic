package com.zhangsan.no_other;

import java.util.Arrays;

/**
 * 线段树
 *
 * @author zhangsan
 * @date 2021/3/19 14:04
 */
public class Code02_SegmentTree {

    /** 线段树结构 */
    public static class SegmentTree {

        private int N;          // 数组的长度
        private int[] arr;      // 原始传入的数组, 只不过下标从1开始
        private int[] sum;      // 模拟线段树维护区间和

        private int[] lazy;     // 为累加的标志以及累加的值如果累加0那就代表没有累加, 代表i的区间都要 + lazy[i]
        private int[] change;   // 为更新的值, 代表i的区间都要更新为change[i]
        private boolean[] update; // 为更新懒标记, 用于表示是否为懒更新, 因为如果change[i] == 0, 无法表示是没有懒信息还是代表change[i]的区间都要更新为0

        public SegmentTree(int[] origin) {
            this.N = origin.length + 1;
            this.arr = new int[N];
            System.arraycopy(origin, 0, arr, 1, arr.length - 1);
            this.sum = new int[N << 2];
            this.lazy = new int[N << 2];
            this.change = new int[N << 2];
            this.update = new boolean[N << 2];

            build(1, N-1, 1);
        }

        /**
         * 计算 a~b 的累加和的结果
         */
        private void pushUp(int ri) {
            // 左孩子 + 右孩子    左:i*2 右:i*2+1
            sum[ri] = sum[ri << 1] + sum[ri << 1 | 1];
        }

        /**
         * ri有缓存同时计算过sum了, 把ri的缓存清空同时计算左右子树的sum并给他们加上缓存, 然后左右子树到时候在清空缓存在计算他们的左右子树的sum...
         */
        private void pushDown(int ri, int ln, int rn) {
            int lci = ri << 1, rci = ri << 1 | 1;  // 左右孩子下标
            // 修改任务的缓存
            if(update[ri]) {
                lazy[lci] = 0;
                lazy[rci] = 0;
                update[lci] = true;
                update[rci] = true;
                change[lci] = change[ri];
                change[rci] = change[ri];
                sum[lci] = change[ri] * ln;
                sum[rci] = change[ri] * rn;
                update[ri] = false;
                /*change[ri] = 0;*/  // 因为我是用update[i] 表示的懒更新, 所以我change[i] 更不更新无所谓
            }
            // add任务的缓存 lazy有值代表上一层, 揽住了, 同时计算过sum, 但是他下边还没有计算sum, 所以分发下去同时计算他下边的sum
            if (lazy[ri] != 0) {
                lazy[lci] += lazy[ri];
                lazy[rci] += lazy[ri];
                sum[lci] += lazy[ri] * ln;
                sum[rci] += lazy[ri] * rn;
                lazy[ri] = 0;   // 自己给清空
            }
        }

        /**
         * 构建sum累加和数组, sum[ri]构建, 它代表l~r范围的值
         */
        private void build(int l, int r, int ri) {
            // 先计算孩子的值, l==r意味着没有叶子节点, 也就是直接对应原数组的l或r位置, 孩子都计算好了,然后计算自己的值
            if (l == r) {
                sum[ri] = arr[l];
                return;
            }
            int m = l + ((r - l) >> 1);
            build(l, m, (ri << 1));
            build(m + 1, r, (ri << 1 | 1));
            pushUp(ri);
        }

        /**
         * L,R,V 代表任务 (L~R)累加V  l,r,ri代表线段树要操作的ri以及它代表的范围l~r
         */
        private void add(int L, int R, int V, int l, int r, int ri) {
            // 我在这个任务的范围内, 那么就把这个任务揽住
            if (l >= L && r <= R) {
                sum[ri] += V * (r - l + 1);
                lazy[ri] += V;
            } else {
                // 没有揽玩
                int m = l + ((r - l) >> 1);
                pushDown(ri, m - l + 1, r - m);     // 把自己的懒信息给分发下去
                // 看看哪里揽不住, 再分发给哪边
                // l ~ m ,  m+1~r
                if(L <= m) {
                    add(L, R, V, l, m, ri << 1);
                }
                if(R > m) {
                    add(L, R, V, m+1, r, ri << 1 | 1);
                }
                pushUp(ri);     // 左右都添加完毕, 再算一下自己的值
            }
        }

        /**
         * L,R,V 代表任务 (L~R)修改成V  l,r,ri代表线段树要操作的ri以及它代表的范围l~r
         */
        private void update(int L, int R, int V, int l, int r, int ri) {
            // 我在这个任务的范围内, 那么就把这个任务揽住
            if (l >= L && r <= R) {
                // 计算自己的sum同时标志懒更新和懒更新的值, 同时把懒增加的值更清空,因为现在已经是懒更新了
                update[ri] = true;
                change[ri] = V;
                sum[ri] = V * (r - l + 1);
                lazy[ri] = 0;
            } else {
                // 没有揽玩
                int m = l + ((r - l) >> 1);
                pushDown(ri, m - l + 1, r - m);     // 把自己的懒信息给分发下去
                // 看看哪里揽不住, 再分发给哪边
                // l ~ m ,  m+1~r
                if(L <= m) {
                    update(L, R, V, l, m, ri << 1);
                }
                if(R > m) {
                    update(L, R, V, m+1, r, ri << 1 | 1);
                }
                pushUp(ri);     // 左右都添加完毕, 再算一下自己的值
            }
        }

        private long query(int L, int R, int l, int r, int ri) {
            // 是否揽住了
            if (l >= L && r <= R) {
                return sum[ri];
            }else {
                // 没有揽玩
                int m = l + ((r - l) >> 1);
                pushDown(ri, m - l + 1, r - m);     // 把自己的懒信息给分发下去
                long ans = 0;
                if(L <= m) {
                    ans += query(L, R, l, m, ri << 1);
                }
                if(R > m) {
                    ans += query(L, R, m+1, r, ri << 1 | 1);
                }
                return ans;
            }
        }


        /** ============对外暴漏的接口=============== */
        public void add(int L, int R, int V) {
            add(L, R, V, 1, N-1, 1);
        }
        public void update(int L, int R, int V) {
            update(L, R, V, 1, N-1, 1);
        }
        public long query(int L, int R) {
            return query(L, R, 1, N-1, 1);
        }

    }

    /** 对数器 */
    public static class Right {
        private int[] arr;
        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            System.arraycopy(origin, 0, arr, 1, origin.length);
        }

        public void update(int L, int R, int V) {
            while (L <= R) arr[L++] = V;
        }

        public void add(int L, int R, int V) {
            while (L <= R) arr[L++] += V;
        }

        public long query(int L, int R) {
            long ans = 0;
            while (L <= R) ans += arr[L++];
            return ans;
        }
    }

    public static void main(String[] args) {
        /*int[] origin = ArrayUtil.generateRandomArray(1000, 10000, false, true);
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 3; // 操作区间的开始位置 -> 可变
        int R = 621; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);*/

        System.out.println("对数器功能测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

        // 性能测试
        System.out.println("对数器性能测试开始...");
        for (int i = 10; i <= 100000000; i *= 10) {
            System.out.println("===============size:"+ i +"=================");
            int[] array = genarateRandomArray(i, 1000, true);
            int[] array1 = Arrays.copyOf(array, array.length);
            int addOrUpdateTimes = i*2;
            int queryTimes = i;

            long s1 = System.currentTimeMillis();
            SegmentTree tree = new SegmentTree(array);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                if (Math.random() < 0.5) {
                    tree.add(1, i, 5);
                } else {
                    tree.update(1, i, 5);
                }
            }
            for (int j = 0; j < queryTimes; j++) {
                tree.query(1, i);
            }
            long s2 = System.currentTimeMillis();
            System.out.println("线段树O(logN): " + (s2-s1));
            long s3 = System.currentTimeMillis();
            Right right = new Right(array1);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                if (Math.random() < 0.5) {
                    right.add(1, i, 5);
                } else {
                    right.update(1, i, 5);
                }
            }
            for (int j = 0; j < queryTimes; j++) {
                right.query(1, i);
            }
            long s4 = System.currentTimeMillis();
            System.out.println("暴力O(N): " + (s4-s3));
            System.out.println("================================");
        }

    }

    public static int[] genarateRandomArray(int len, int max, boolean fixedSize) {
        int size = fixedSize? len: (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 1000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max, false);
            SegmentTree seg = new SegmentTree(origin);
            int N = origin.length;
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }
}
