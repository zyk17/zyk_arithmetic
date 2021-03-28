package com.zhangsan.no_18_orderedMap;

import java.util.HashSet;

/**
 * 练习题:
 * 一个数组,有多少个子数组的和在 lower-upper 区间内
 * 这个类改写有序表示先功能.具体思路如下
 *
 * 根据求区间,想到用前缀和数组和以i结尾的子数组有多少个满足的. 当前项的前缀和-区间.得到一个数的范围
 *
 * {@link com.zhangsan.no_2_sort.C004_03_InArraySonSumInLowerToUpper} 写了暴力实现和,归并排序过程中统计
 * @author zhangsan
 * @date 2021/3/28 11:45
 */
public class Code04_CountOfRangeSum {

    public static int countRangeSum1(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }
    /** 归并做法 */
    private static int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1)
            return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper)
                + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower)
                k++;
            while (j < end && sums[j] - sums[i] <= upper)
                j++;
            while (t < end && sums[t] < sums[i])
                cache[r++] = sums[t++];
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }


    public static int countRangeSum2(int[] nums, int lower, int upper) {
        SizeBalancedTreeSet treeSet = new SizeBalancedTreeSet();
        long sum = 0;
        int ans = 0;
        treeSet.add(0);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            long low = treeSet.lessKeySize( sum-upper );
            long height = treeSet.lessKeySize( sum-lower+1 );
            ans += (height-low);
            treeSet.add(sum);
        }
        return ans;
    }

    public static class SizeBalancedTreeSet {
        private SBTNode root;
        private HashSet<Long> set = new HashSet<>();

        private SBTNode rightRotate(SBTNode cur) {
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTNode leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            // all modify
            leftNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
            return leftNode;
        }

        private SBTNode leftRotate(SBTNode cur) {
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            // all modify
            rightNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
            return rightNode;
        }

        private SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            long leftSize = cur.l != null ? cur.l.size : 0;
            long leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            long leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            long rightSize = cur.r != null ? cur.r.size : 0;
            long rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            long rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        /** 添加主函数 */
        public void add(long sum) {
            boolean contains = set.contains(sum);
            root = add(root, sum, contains);
            set.add(sum);
        }

        private SBTNode add(SBTNode cur, long key, boolean contains) {
            if(cur == null) {
                return new SBTNode(key);
            }else {
                cur.all++;
                if(!contains) {
                    cur.size++;
                }
                if(key < cur.k) {
                    cur.l = add(cur.l, key, contains);
                }else if( key > cur.k ) {
                    cur.r = add(cur.r, key, contains);
                }else {
                    return cur;
                }
            }
            return maintain(cur);
        }

        /** 小于key有多少个 */
        public long lessKeySize(long key) {
            SBTNode cur = root;
            long ans = 0;
            while (cur != null) {
                if(key == cur.k) {
                    // ?   当前的 = 当前-右树?
                    return ans + (cur.l != null ? cur.l.all : 0);
//                    return ans + cur.all - (cur.r==null? 0 : cur.r.all);
                }else if(key < cur.k) {
                    cur = cur.l;
                }else {
                    ans += cur.all - (cur.r == null ? 0 : cur.r.all);
                    cur = cur.r;
                }
            }
            return ans;
        }

        public long moreKeySize(long key) {
            return root != null ? (root.all - lessKeySize(key + 1)) : 0;
        }


        private static class SBTNode {
            public long k;
            // v代表元素数量, 代表这棵树一共添加过多少元素. 添加重复元素沿途节点v++
            public long all;
            public SBTNode l;
            public SBTNode r;
            // 不同的节点的数量, 平衡因子. 添加不同元素沿途size++
            public long size;

            public SBTNode(long key) {
                this.k = key;
                this.all = 1;
                this.size = 1;
            }
        }
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] generateArray(int len, int varible) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * varible);
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 200;
        int varible = 50;
        for (int i = 0; i < 10000; i++) {
            int[] test = generateArray(len, varible);
            int lower = (int) (Math.random() * varible) - (int) (Math.random() * varible);
            int upper = lower + (int) (Math.random() * varible);
            int ans1 = countRangeSum1(test, lower, upper);
            int ans2 = countRangeSum2(test, lower, upper);
            if (ans1 != ans2) {
                printArray(test);
                System.out.println(lower);
                System.out.println(upper);
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }

    }

}
