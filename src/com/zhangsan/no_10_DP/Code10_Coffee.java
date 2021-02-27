package com.zhangsan.no_10_DP;

import com.zhangsan.util.ArrayUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目
 * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 * @author zhangsan
 * @date 2021/2/24 16:10
 */
public class Code10_Coffee {

    /** 咖啡机 */
    public static class Machine implements Comparable<Machine> {
        /** 什么时间点可用 */
        public int timePoint;
        /** 泡一杯咖啡的时间 */
        public int workTime;

        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }

        @Override
        public int compareTo(Machine o) {
            return (this.timePoint + this.workTime) - (o.timePoint + o.workTime);
        }

        @Override
        public String toString() {
            return "Machine{" +
                    "timePoint=" + timePoint +
                    ", workTime=" + workTime +
                    '}';
        }
    }

    /**
     * 利用贪心解出每个人在不洗杯子的情况下最快喝到时间
     * @param arr 每个机器以及它的工作效率
     * @param n n个人
     * @param a a代表那一台洗咖啡杯的机器洗一个杯子的时间
     * @param b b代表自然挥发的时间
     * @return 返回最有时间点喝完
     */
    public static int minTimes1(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> smallHeap = new PriorityQueue<>();
        for (int i : arr) {
            smallHeap.add(new Machine(0, i));
        }
        int[] ans = new int[n];
        Machine curFast = null;
        for (int i = 0; i < n; i++) {
            curFast = smallHeap.poll();
            ans[i] = curFast.timePoint;
            curFast.timePoint += curFast.workTime;
            smallHeap.add(curFast);
        }
        /*System.out.println("没有洗杯子的情况下，每个人最快喝到咖啡的时间点：");
        for (int i = 0; i < ans.length; i++) {
            System.out.println("第" + i + "个人喝到咖啡的时间点为：" + ans[i]);
        }*/
        return bestTime1(ans, a, b, 0, 0);
    }

    /**
     * 使用暴力递归,计算当前杯子洗的情况和挥发的情况下
     * @param drinks 每个人不洗杯子最快喝到咖啡的事件点
     * @param wash 洗机器洗干一个杯子的时间, 洗杯子为串行操作杯子
     * @param air 自然挥发干净的事件, 杯子挥发干净属于并行操作
     * @param index 当前到哪儿个杯子了
     * @param free 洗机器可以工作的时间
     * @return 返回最快需要多久所有人喝完咖啡且洗完杯子
     */
    public static int bestTime1(int[] drinks, int wash, int air, int index, int free) {
        // base case 下标越界
        if( index == drinks.length ) { return 0; }
        // 机器洗杯子的情况, 喝完和机器空闲取个最大值
        int selfClean1 = Math.max(drinks[index], free) + wash;
        int restClean1 = bestTime1(drinks, wash, air, index+1, selfClean1);

        // 自我挥发的情况.
        int selfClean2 = drinks[index] + air;
        int restClean2 = bestTime1(drinks, wash, air, index+1, free);

        int p1 = Math.max(selfClean1, restClean1);
        int p2 = Math.max(selfClean2, restClean2);
        return Math.min(p1, p2);
    }

    /** 暴力计算最优解那一步加入记忆化搜索 */
    public static int minTimes2(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> smallHeap = new PriorityQueue<>();
        for (int i : arr) {
            smallHeap.add(new Machine(0, i));
        }
        int[] ans = new int[n];
        Machine curFast = null;
        for (int i = 0; i < n; i++) {
            curFast = smallHeap.poll();
            ans[i] = curFast.timePoint;
            curFast.timePoint += curFast.workTime;
            smallHeap.add(curFast);
        }

//        System.out.println("没有洗杯子的情况下，每个人最快喝到咖啡的时间点：");
//        ArrayUtil.printArr(ans);
        int[][] dp = new int[n][ a*n ];
        int result = bestTime2(ans, a, b, 0, 0, dp);

        /*System.out.println("二位缓存表内容: ");
        for (int[] cache : dp) {
            ArrayUtil.printArr(cache);
        }*/

        return result;
    }
    // 可变参数 index, free,准备一个二维表做缓存
    public static int bestTime2(int[] drinks, int wash, int air, int index, int free, int[][] dp) {
        if( index == drinks.length ) { return 0; }

        if( dp[index][free] != 0 ) {
            return dp[index][free];
        }

        int selfClean1 = Math.max(drinks[index], free) + wash;
        int restClean1 = bestTime2(drinks, wash, air, index+1, selfClean1, dp);

        int selfClean2 = drinks[index] + air;
        int restClean2 = bestTime2(drinks, wash, air, index+1, free, dp);

        int p1 = Math.max(selfClean1, restClean1);
        int p2 = Math.max(selfClean2, restClean2);

        int ans = Math.min(p1, p2);
        dp[index][free] = ans;
        return ans;
    }

    /** 测试 */
    public static void test() {
        int[] ans = {1, 3, 5, 6, 8};
        int[][] dp = new int[5][ 10 ];

        System.out.println("\n\n不洗杯子每个人最快喝完时间点 ");
        ArrayUtil.printArr(ans);
        int result = bestTime2(ans, 2, 5, 0, 0, dp);
        System.out.println("二位缓存表内容: ");
        for (int[] cache : dp) {
            ArrayUtil.printArr(cache);
        }
        System.out.println("测试结果" + result);
    }

    public static class MachineComparator implements Comparator<Machine> {

        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }

    }

    public static int minTimes3(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        return bestTimeDp(drinks, a, b);
    }

    public static int bestTimeDp(int[] drinks, int wash, int air) {
        int N = drinks.length;
        int maxFree = 0;
        for (int i = 0; i < drinks.length; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + wash;
        }
        int[][] dp = new int[N + 1][maxFree + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free) + wash;
                if (selfClean1 > maxFree) {
                    break; // 因为后面的也都不用填了
                }
                // index号杯子 决定洗
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);
                // index号杯子 决定挥发
                int selfClean2 = drinks[index] + air;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }


    public static int pick(int[][] dp, int row, int col) {
        if( row < 0 || col < 0 || row >= dp.length || col >= dp[0].length ) {
            return 0;
        }
        return dp[row][col];
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 7};
        int n = 20;
        int a = 2;
        int b = 5;

        long s1 = System.nanoTime();
        int r1 = minTimes1(arr, n, a, b);
        long s2 = System.nanoTime();
        int r2 = minTimes2(arr, n, a, b);
        long s3 = System.nanoTime();
        int r3 = minTimes3(arr, n, a, b);
        long s4 = System.nanoTime();

        System.out.println("贪心政策+暴力计算, 结果： " + r1 + ", 共耗时: " + (s2 - s1));
        System.out.println("加入傻缓存, 结果： " + r2 + ", 共耗时: " + (s3 - s2));
        System.out.println("动态规划 结果： " + r3 + ", 共耗时: " + (s4 - s3));

//        test();
    }

}
