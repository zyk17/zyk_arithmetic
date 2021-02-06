package com.zhangsan.no_2_sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定一堆线段，算出最大重合的线段数量
 *
 * @author zhangsan
 * @date 2021/2/6 15:31
 */
public class C006_04_CoverMax {

    public static void main(String[] args) {
//        int[][] lines = new int[10][2];
        /*int[][] lines = {
                {1, 6},
                {4, 9},
                {8, 10},
                {2, 5},
                {6, 7},
                {4, 8},
                {8, 9},
        };
        System.out.println(compareM(lines));
        System.out.println(getCoverMax(lines));*/

        int times = 10000;
        int maxSize = 20;
        int maxValue = 100;
        boolean succeed = true;

        System.out.println("测试开始！");
        for (int i = 0; i < times; i++) {
            int[][] lines = generateLines(maxSize, maxValue);
            int[][] lines2 = copyLines(lines);

            int r1 = getCoverMax(lines);
            int r2 = compareM(lines2);
            if (r1 != r2) {
                succeed = false;
                break;
            }
        }
        System.out.println("测试结束！");
        System.out.println(succeed ? "succeed!" : "wrong fuck!");

        int[][] a = generateLines(10000, 1000);
        int[][] b = copyLines(a);
        long start = System.nanoTime();
        int r1 = getCoverMax(a);
        long end = System.nanoTime();
        int r2 = compareM(b);
        long end1 = System.nanoTime();
        System.out.println(r1 + "\t" + r2+"耗时：" + (end-start) + "耗时：" + (end1-end));
    }

    public static int getCoverMax(int[][] lines) {
        int coverMax = 0;
        // 先按照最小线段把它排序
        Arrays.sort(lines, (o1, o2) -> o1[0] - o2[0]);
        // 准备小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int[] line : lines) {
            while ((!queue.isEmpty()) && queue.peek() <= line[0]) {
                queue.poll();
            }
            queue.add(line[1]);
            coverMax = Math.max(coverMax, queue.size());
        }
        return coverMax;
    }

    public static int compareM(int[][] lines) {
        int min = lines[0][0], max = lines[0][0], coverCount, coverMax = 0;
        for (int[] line : lines) {
            for (int i : line) {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
        }
        for (double i = min + 0.5; i < max; i += 1) {
            coverCount = 0;
            // 遍历的是所有线段，算的是 n.5在多少个线段上有重合
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] < i && lines[j][1] > i) {
                    coverCount++;
                }
            }
            coverMax = Math.max(coverMax, coverCount);
        }
        return coverMax;
    }

    public static int[][] generateLines(int maxSize, int maxValue) {
        maxSize = (int) (Math.random() * maxSize + 1);
        int[][] lines = new int[maxSize][2];
        for (int[] line : lines) {
            line[0] =  ((int) (Math.random() * maxValue + 1)) >> 1;
            int v2 = (int) (Math.random() * maxValue + 1);
            while (v2 <= line[0]) {
                v2 = (int) (Math.random() * maxValue + 1);
            }
            line[1] =  v2;
        }
        return lines;
    }

    public static int[][] copyLines(int[][] lines) {
        return Arrays.copyOf(lines, lines.length);
    }


}
